package com.milly.springbootmall.service;

import com.milly.springbootmall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder (Integer userId, CreateOrderRequest createOrderRequest);
}
