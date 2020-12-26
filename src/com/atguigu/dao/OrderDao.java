package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {

    public int saveOrder(Order order);
    public List<Order> queryMyOrders(Integer userId);
    public List<Order> queryAllOrders();
    public void sendOrder(String orderId);
    public void receiveOrder(String orderId);
}
