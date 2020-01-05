package com.cmayorga.spring.test.item.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cmayorga.spring.test.item.model.Item;
import com.cmayorga.spring.test.common.model.entity.Product;
import com.cmayorga.spring.test.item.model.rest_client.IProductRestClient;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

    @Autowired
    private IProductRestClient iProductRestClient;
    
    @Override
    public List<Item> findAll() {
        return iProductRestClient.getProductList().stream()
                .map(product -> new Item(product, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        return new Item(iProductRestClient.getProductDetail(id), amount);
    }

    @Override
    public Product createProduct(Product product) {
        return iProductRestClient.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        return iProductRestClient.updateProduct(product, id);
    }

    @Override
    public void deleteProduct(Long id) {
        iProductRestClient.deleteProduct(id);
        
    }

}
