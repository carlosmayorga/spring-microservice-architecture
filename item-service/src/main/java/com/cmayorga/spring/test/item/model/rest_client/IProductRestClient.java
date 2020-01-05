package com.cmayorga.spring.test.item.model.rest_client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cmayorga.spring.test.item.model.Product;

@FeignClient(name = "product-service")
public interface IProductRestClient {
    
    @GetMapping("/list")
    public List<Product> getProductList();
    
    @GetMapping("/{id}")
    public Product getProductDetail(@PathVariable long id);
    
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product);
    
    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id);
    
    @DeleteMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable Long id);
}
