package com.cmayorga.spring.test.item.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cmayorga.spring.test.item.model.Item;
import com.cmayorga.spring.test.common.model.entity.Product;


@Service("serviceRestTemplate")
public class ItemServiceImpl implements IItemService {

    @Autowired
    private RestTemplate restTemplateClient;
    
    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(restTemplateClient.getForObject("http://product-service/list", Product[].class));
        
        return products.stream()
                .map(product -> new Item(product, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        Map<String, String> pathVariables = new HashMap<String, String>();

        pathVariables.put("id", id.toString());
        Product product = restTemplateClient.getForObject("http://product-service/{id}", Product.class, pathVariables);
        return new Item(product, amount);
    }

    @Override
    public Product createProduct(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        
        ResponseEntity<Product> response = restTemplateClient.exchange("http://product-service/create", 
                                           HttpMethod.POST, body, Product.class);
        return response.getBody();
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = restTemplateClient.exchange("http://product-service/update/{id}", 
                                           HttpMethod.PUT, body, Product.class, pathVariables);
        
        return response.getBody();
    }

    @Override
    public void deleteProduct(Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        restTemplateClient.delete("http://product-service/delete/{id}", pathVariables);
    }

}
