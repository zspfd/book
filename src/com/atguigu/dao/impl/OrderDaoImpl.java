package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    //Order类成员：String orderId, Date createTime, BigDecimal price, Integer status, Integer userId
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(orderId,createTime,price,status,userId)values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql = "select orderId, createTime, price, status from t_order where userId = ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select * from t_order";
        return queryForList(Order.class, sql, null);
    }

    @Override
    public void sendOrder(String orderId) {
        String sql = "update t_order set status = 1 where orderId = ?";
        update(sql, orderId);
    }

    @Override
    public void receiveOrder(String orderId) {
        String sql = "update t_order set status = 2 where orderId = ?";
        update(sql, orderId);
    }
}
