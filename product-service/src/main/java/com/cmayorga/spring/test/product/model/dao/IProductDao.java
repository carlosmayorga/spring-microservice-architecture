package com.cmayorga.spring.test.product.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.cmayorga.spring.test.common.model.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
    

}
