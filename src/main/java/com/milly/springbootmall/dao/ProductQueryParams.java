package com.milly.springbootmall.dao;

import com.milly.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    private ProductCategory category;
    private String serch;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSerch() {
        return serch;
    }

    public void setSerch(String serch) {
        this.serch = serch;
    }
}
