package com.milly.springbootmall.service.impl;

import com.milly.springbootmall.dao.OrderDao;
import com.milly.springbootmall.dao.ProductDao;
import com.milly.springbootmall.dao.UserDao;
import com.milly.springbootmall.dto.BuyItem;
import com.milly.springbootmall.dto.CreateOrderRequest;
import com.milly.springbootmall.dto.EcpayParamms;
import com.milly.springbootmall.dto.OrderQueryParams;
import com.milly.springbootmall.model.Order;
import com.milly.springbootmall.model.OrderItem;
import com.milly.springbootmall.model.Product;
import com.milly.springbootmall.model.User;
import com.milly.springbootmall.service.OrderService;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for(Order order : orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList) ;
        }
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查 User 是否存在
        User user = userDao.getUserById(userId);

        if(user == null){
            log.warn("該 userId： {} 不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查 product 是否存在、庫存是否足夠
            if(product == null){
                log.warn("商品ID： {} 不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("商品ID： {} 庫存不足，剩餘庫存： {}，欲購買數量： {} ",buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            productDao.updateStock(product.getProductId(),product.getStock() - buyItem.getQuantity());

            //計算總金額
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = amount + totalAmount;

            //轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;
    }
    @Override

    public String ecpay(EcpayParamms ecpayParamms) {
        AllInOne allInOne = new AllInOne("");
        AioCheckOutALL obj = new AioCheckOutALL();
        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

        //合作特店交易編號(由合作特店提供)，該交易編號不可重複
        obj.setMerchantID("12345");
        // MerchantTradeNo : 必填 特店訂單編號 (不可重複，因此需要動態產生)
        obj.setMerchantTradeNo(uuId);//"order" + System.currentTimeMillis()
        // MerchantTradeDate : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
        obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
        // TotalAmount : 必填 交易金額
        obj.setTotalAmount(ecpayParamms.getTotalAmount());
        // TradeDesc : 必填 交易描述
        obj.setTradeDesc("StoreID:" + ecpayParamms.getTradeDesc());
        // ItemName : 必填 商品名稱
        obj.setItemName("SpringMall Buy Goods");
        //設定NeedExtraPaidInfo 是否需要額外的付款資訊
        obj.setNeedExtraPaidInfo("N");
        // ClientBackURL : 消費者完成付費後。重新導向的位置
        obj.setClientBackURL("http://localhost:8080/users/" + ecpayParamms.getUserId() + "/orders");
        // ReturnURL : 必填 我用不到所以是隨便填一個英文字\
        //obj.setReturnURL("http://211.23.128.214:5000");
        obj.setReturnURL("a");
        // 回傳form訂單 並自動將使用者導到 綠界
        String form = allInOne.aioCheckOut(obj,null);
        return form;


    }
}
