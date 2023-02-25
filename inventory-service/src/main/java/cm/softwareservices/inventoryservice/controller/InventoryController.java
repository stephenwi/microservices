package cm.softwareservices.inventoryservice.controller;

import cm.softwareservices.inventoryservice.dto.InventoryResponse;
import cm.softwareservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ss/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;
    @GetMapping //("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return service.isInStock(skuCode);
    }
}
