package com.milly.springbootmall.dao;

import com.milly.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder (Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem>orderItemList);
}
