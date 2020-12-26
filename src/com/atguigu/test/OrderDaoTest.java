package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class OrderDaoTest {

    @Test
    public void saveOrder(){
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));

    }


    @Test
    public void queryMyOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        Integer userId = 1;
        List<Order> orders = orderDao.queryMyOrders(userId);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}