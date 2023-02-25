package cm.softwareservices.productservice.service;

import cm.softwareservices.productservice.dto.ProductRequest;
import cm.softwareservices.productservice.dto.ProductResponse;
import cm.softwareservices.productservice.model.Product;
import cm.softwareservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;
    public void createProduct(ProductRequest request) {
        log.info("creating new product with name " + request.getName());
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        repository.save(product);
        log.info("Product saved successfully with id:{}", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
      log.info("Getting all Products");
        List<Product> productList = repository.findAll();
        log.info("Product list size: {}", productList.size());
       return productList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
