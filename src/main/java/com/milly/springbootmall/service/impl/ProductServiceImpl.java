package com.milly.springbootmall.service.impl;

import com.milly.springbootmall.dao.ProductDao;
import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;
import com.milly.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
       return productDao.getProductById(productId);
    }

    @Override
    public Integer creatProduct(ProductRequest productRequest) {
        return productDao.creatProduct(productRequest);
    }
}
