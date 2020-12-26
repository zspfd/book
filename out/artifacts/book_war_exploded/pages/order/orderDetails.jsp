<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zsp
  Date: 2020/12/11
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详细</title>
    <%--    静态包含，base标签，css样式，jQuery--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
    <a href="index.jsp">
        <img class="logo_img" alt="" src="static/img/DDlogoNEW.gif">
    </a>
    <span class="wel_word" style="font-family: 华文琥珀">订单详细</span>
    <%--    静态包含，登录成功之后的菜单--%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>
<div id="main">
    <p style="font-size: larger">订单号：${requestScope.orderId}</p>
    <table>
        <tr>
            <td>书名</td>
            <td>数量</td>
            <td>价格</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="entry">
            <tr>
                <td>${entry.name}</td>
                <td>${entry.count}</td>
                <td>${entry.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>

    <div class="cart_info">
        <span class="cart_span">总金额<span class="b_price">${requestScope.orderPrice}</span>元</span>
    </div>

</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
