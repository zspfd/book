package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }

    @Test
    public void OrderDetails() {
        OrderService orderService = new OrderServiceImpl();
        List<OrderItem> orderItems = orderService.OrderDetails("160770567773914");
        System.out.println("----------");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void AllOrders() {
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.AllOrders();
        for (Order order : orders) {
            System.out.println(order);

        }
    }

    @Test
    public void sendOrderId() {
        OrderService orderService = new OrderServiceImpl();
        orderService.sendOrder("160770693575714");
    }

    @Test
    public void receiveOrder() {
        OrderService orderService = new OrderServiceImpl();
        orderService.receiveOrder("160770567773914");
    }
}