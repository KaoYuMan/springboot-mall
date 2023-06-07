package com.milly.springbootmall.service;

import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
