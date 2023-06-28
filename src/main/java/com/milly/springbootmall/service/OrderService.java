package com.milly.springbootmall.service;

import com.milly.springbootmall.dto.CreateOrderRequest;
import com.milly.springbootmall.dto.EcpayParamms;
import com.milly.springbootmall.dto.OrderQueryParams;
import com.milly.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    List<Order>getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder (Integer userId, CreateOrderRequest createOrderRequest);
    String ecpay(EcpayParamms ecpayParamms);

}
