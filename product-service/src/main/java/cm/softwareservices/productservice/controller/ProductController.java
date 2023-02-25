package cm.softwareservices.productservice.controller;

import cm.softwareservices.productservice.dto.ProductRequest;
import cm.softwareservices.productservice.dto.ProductResponse;
import cm.softwareservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ss/products")
public class ProductController {

    private final ProductService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        service.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductList() {
        return service.getAllProducts();
    }
}
