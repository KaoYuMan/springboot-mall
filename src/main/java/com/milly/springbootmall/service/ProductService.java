package com.milly.springbootmall.service;

import com.milly.springbootmall.constant.ProductCategory;
import com.milly.springbootmall.dao.ProductQueryParams;
import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
