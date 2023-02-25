package cm.softwareservices.inventoryservice.service;

import cm.softwareservices.inventoryservice.dto.InventoryResponse;
import cm.softwareservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository repository;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Wait started");
        Thread.sleep(10000);
        log.info("Wait ended");
     return repository.findBySkuCodeIn(skuCode).stream()
             .map(inventory ->
                 InventoryResponse.builder()
                         .skuCode(inventory.getSkuCode())
                         .isInStock(inventory.getQuantity() > 0)
                         .build()
             ).toList() ;
    }
}
