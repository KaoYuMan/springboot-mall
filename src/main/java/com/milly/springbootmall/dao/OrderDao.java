package com.milly.springbootmall.dao;

import com.milly.springbootmall.dto.OrderQueryParams;
import com.milly.springbootmall.model.Order;
import com.milly.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    List<Order>getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    List<OrderItem>getOrderItemsByOrderId(Integer orderId);
    Integer createOrder (Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem>orderItemList);
}
