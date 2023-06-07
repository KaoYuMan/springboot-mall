package com.milly.springbootmall.dao;

import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void  updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
