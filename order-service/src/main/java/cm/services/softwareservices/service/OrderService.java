package cm.services.softwareservices.service;

import cm.services.softwareservices.dto.InventoryResponse;
import cm.services.softwareservices.dto.OrderItemsRequest;
import cm.services.softwareservices.dto.OrderRequest;
import cm.services.softwareservices.event.PlaceOrderEvent;
import cm.services.softwareservices.model.Order;
import cm.services.softwareservices.model.OrderItem;
import cm.services.softwareservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, PlaceOrderEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderCode(UUID.randomUUID().toString());

       List<OrderItem> orderItemList = orderRequest.getOrderItemsRequestList()
                .stream()
                .map(this::mapToDo)
                .toList();

        order.setOrderItemList(orderItemList);

        List<String> skuCodes = order.getOrderItemList().stream().map(OrderItem::getSkuCode).toList();

        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try(Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())) {
            // Call Inventory Service and place order if product is in stock
            inventoryServiceLookup.tag("Call", "inventory-service");
            InventoryResponse[] responseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/ss/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(responseArray).allMatch(InventoryResponse::isInStock);
            if (allProductsInStock) {
                repository.save(order);
                kafkaTemplate.send("notificationTopic", new PlaceOrderEvent(order.getOrderCode()));
                return "Ok";
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        } finally {
            inventoryServiceLookup.end();
        }
        // End Call
    }

    private OrderItem mapToDo(OrderItemsRequest orderItemsRequest) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemsRequest.getPrice());
        orderItem.setQuantity(orderItemsRequest.getQuantity());
        orderItem.setSkuCode(orderItemsRequest.getSkuCode());

        return orderItem;
    }
}
