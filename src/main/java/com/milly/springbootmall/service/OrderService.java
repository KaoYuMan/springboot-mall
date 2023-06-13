package com.milly.springbootmall.service;

import com.milly.springbootmall.dto.CreateOrderRequest;
import com.milly.springbootmall.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);
    Integer createOrder (Integer userId, CreateOrderRequest createOrderRequest);

}
