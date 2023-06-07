package com.milly.springbootmall.service.impl;

import com.milly.springbootmall.constant.ProductCategory;
import com.milly.springbootmall.dao.ProductDao;
import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;
import com.milly.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductCategory category,String serch) {
        return productDao.getProducts(category,serch);
    }

    @Override
    public Product getProductById(Integer productId) {
       return productDao.getProductById(productId);
    }

    @Override
    public Integer creatProduct(ProductRequest productRequest) {
        return productDao.creatProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

}
