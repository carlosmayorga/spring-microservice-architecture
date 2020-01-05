package com.cmayorga.spring.test.item.model.service;

import java.util.List;

import com.cmayorga.spring.test.item.model.Item;
import com.cmayorga.spring.test.common.model.entity.Product;

public interface IItemService {
    
    public List<Item> findAll();
    public Item findById(Long id, Integer amount);
    
    public Product createProduct(Product product);
    public Product updateProduct(Product product, Long id);
    public void deleteProduct(Long id);

}
