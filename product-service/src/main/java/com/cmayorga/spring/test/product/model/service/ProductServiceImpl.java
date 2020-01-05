package com.cmayorga.spring.test.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmayorga.spring.test.product.model.dao.IProductDao;
import com.cmayorga.spring.test.common.model.entity.Product;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return (List<Product>) iProductDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        // TODO Auto-generated method stub
        return iProductDao.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return iProductDao.save(product);
    }

    @Override
    public void deleteById(Long id) {
        iProductDao.deleteById(id);
    }

}
