package com.milly.springbootmall.dao;

import com.milly.springbootmall.constant.ProductCategory;
import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductCategory category,String serch);
    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void  updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
