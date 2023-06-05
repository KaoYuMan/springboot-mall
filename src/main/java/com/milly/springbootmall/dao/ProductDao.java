package com.milly.springbootmall.dao;

import com.milly.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
