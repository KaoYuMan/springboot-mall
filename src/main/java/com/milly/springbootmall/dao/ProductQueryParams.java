package com.milly.springbootmall.dao;

import com.milly.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    private ProductCategory category;
    private String serch;

    private String orderBy;

    private String sort;

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
