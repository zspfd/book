package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    //创建订单
    public String createOrder(Cart cart, Integer userId);
    //订单列表
    public List<Order> myOrders(Integer userId);
    //订单详细
    public List<OrderItem>  OrderDetails(String orderId);
    //查询所有订单
    public List<Order> AllOrders();
    //发货
    public void sendOrder(String orderId);
    //签收
    public void receiveOrder(String orderId);
}
