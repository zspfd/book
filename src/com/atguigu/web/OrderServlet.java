package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 先获取Cart购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        // 获取UserId
        User loginUser = (User)req.getSession().getAttribute("user");

        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();
        System.out.println(userId);
        //调用orderService.createOrder(Cart, UserId); 生成订单
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }

    /**
     * 订单列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取UserId
        User loginUser = (User)req.getSession().getAttribute("user");
        Integer userId = loginUser.getId();
        // 获取订单列表
        List<Order> orders = orderService.myOrders(userId);
        // orders保存到 session域中
        req.setAttribute("orders", orders);
        // 请求转发到/pages/order/order.jsp 页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 订单详细
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // 获取OrderId
        String orderId = req.getParameter("orderId");
        Double orderPrice = WebUtils.parseDouble(req.getParameter("orderPrice"), 0);
        // 获取orderItems
        List<OrderItem> orderItems = orderService.OrderDetails(orderId);
        // 保存到 Request 域中
        req.setAttribute("orderPrice", orderPrice);
        req.setAttribute("orderId", orderId);
        req.setAttribute("orderItems", orderItems);
        // 请求转发到/pages/order/orderDetails.jsp 页面
        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req,resp);
    }

    /**
     * 查询所有的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有的订单
        List<Order> orderList = orderService.AllOrders();
        // orderList保存到request域中
        req.setAttribute("orderList", orderList);
        // 请求转发到/pages/manager/order_manager.jsp
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);

    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要发货的订单号
        String orderId = req.getParameter("orderId");
        // 执行发货操作
        orderService.sendOrder(orderId);
        // 请求转发
        req.getRequestDispatcher("orderServlet?action=allOrders").forward(req,resp);


    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取OrderId
        String orderId = req.getParameter("orderId");
        // 签收
        orderService.receiveOrder(orderId); 
        // 请求转发
        req.getRequestDispatcher("orderServlet?action=myOrders").forward(req,resp);
    }
}
