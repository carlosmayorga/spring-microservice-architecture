package com.cmayorga.spring.test.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cmayorga.spring.test.item.model.Item;
import com.cmayorga.spring.test.item.model.Product;
import com.cmayorga.spring.test.item.model.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
    
    @Autowired
    //@Qualifier("serviceRestTemplate")
    @Qualifier("serviceFeign")
    private IItemService iItemService;
    
    
    @GetMapping("/list")
    public List<Item> getItemList() {
        return iItemService.findAll();
    }
    
    @HystrixCommand(fallbackMethod = "AlternativeMethod")
    @GetMapping("/{id}/amount/{amount}")
    public Item getItemDetail(@PathVariable Long id, @PathVariable Integer amount) {
        return iItemService.findById(id, amount);
    }
    public Item AlternativeMethod(Long id, Integer amount){
        Item item = new Item();
        Product product = new Product();
        
        item.setAmount(amount);
        product.setId(id);
        product.setName("Default Product");
        product.setPrice(000.00);
        item.setProduct(product);
        
        return item;
    }
    
    @PostMapping("/create/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return iItemService.createProduct(product);
    }
    
    @PutMapping("/update/product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        
        return iItemService.updateProduct(product, id);
    }
    
    @DeleteMapping("/delete/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        iItemService.deleteProduct(id);
    }

}
