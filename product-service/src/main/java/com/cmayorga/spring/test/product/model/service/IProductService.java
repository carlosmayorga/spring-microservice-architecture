package com.cmayorga.spring.test.product.model.service;
import java.util.List;

import com.cmayorga.spring.test.common.model.entity.Product;

public interface IProductService {
    
    public List<Product> findAll();
    public Product findById(Long id);
    public Product save(Product product);
    public void deleteById(Long id);
}
