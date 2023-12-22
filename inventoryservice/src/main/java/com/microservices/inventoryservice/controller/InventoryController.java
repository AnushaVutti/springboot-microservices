package com.microservices.inventoryservice.controller;

import com.microservices.inventoryservice.dto.InventoryResponse;
import com.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    /*
    * We are changing to retrieve multiple skucodes as a list rather than each sku code at a time
    * since if an order has 100 order line items, then we need to make 100 http calls for 100 skucodes
    * which is time consuming to process the request. So, we maintain a list and pass list of skucodes as
    * a single http request. We also use Request param instead of path variable as it is a more readable form
    * as below:   http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    * */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
