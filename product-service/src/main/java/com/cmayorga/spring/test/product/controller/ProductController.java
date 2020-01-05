package com.cmayorga.spring.test.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cmayorga.spring.test.common.model.entity.Product;
import com.cmayorga.spring.test.product.model.service.IProductService;

@RestController
public class ProductController {
     
    @Autowired
    private IProductService iProductService;
    
    @GetMapping("/list")
    public List<Product> getProductList() {
         return iProductService.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getProductDetail(@PathVariable long id) {
        Product product = iProductService.findById(id);

//        boolean ok = false;
//        if(ok == false) {
//            throw new Exception("Error simulado para probar hystrix desde item-service");
//        }
        
        return product;
    }
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {        
        return iProductService.save(product);
    }
    
    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {        
        
        Product productToUpdate = iProductService.findById(id);
        
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        
        return iProductService.save(productToUpdate);
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        iProductService.deleteById(id);
    }
    

}
