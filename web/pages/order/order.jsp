<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%--    静态包含，base标签，css样式，jQuery--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
    <%--给签收的 a 标签绑定单击事件，用于签收的确认以及无效操作的提示--%>
    <script type="text/javascript">
        $(function () {
            // 在事件的 function 函数中，有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象。
            /**
             * confirm 是确认提示框函数
             * 参数是它的提示内容
             * 它有两个按钮，一个确认，一个是取消。
             * 返回 true 表示点击了，确认，返回 false 表示点击取消
             * */
            $("a.receiveOrder").click(function () {

                if($(this).parent().prev().prev().text() == "已签收") {
                    alert("你已经签收");
                    return false
                } else if($(this).parent().prev().prev().text() == "未发货"){
                    alert("商家未发货，请耐心等待");
                    return false
                }else{
                    return confirm("你确定要签收吗?");
                }
            })
        })

    </script>
</head>
<body>
<div id="header">
    <a href="index.jsp">
        <img class="logo_img" alt="" src="static/img/DDlogoNEW.gif">
    </a>
    <span class="wel_word" style="font-family: 华文琥珀">我的订单</span>
    <div>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="index.jsp">返回</a>
    </div>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty requestScope.orders}">
            <%--如果订单为空的情况--%>
            <tr>
                <td colspan="5"><a href="pages/cart/cart.jsp">亲，当前订单为空！去购物车下订单吧</a>
                </td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.orders}">
            <%--如果购物车非空的情况--%>
            <c:forEach items="${requestScope.orders}" var="entry">
                <tr>
                    <td>${entry.createTime}</td>
                    <td>${entry.price}</td>
                    <c:if test="${entry.status == 0}">
                        <td id="0">未发货</td>
                    </c:if>
                    <c:if test="${entry.status == 1}">
                        <td id="1">已发货</td>
                    </c:if>
                    <c:if test="${entry.status == 2}">
                        <td id="2">已签收</td>
                    </c:if>
                    <td><a href="orderServlet?action=orderDetails&orderId=${entry.orderId}&orderPrice=${entry.price}">查看详情</a></td>
                    <td><a class="receiveOrder" href="orderServlet?action=receiveOrder&orderId=${entry.orderId}">签收</a> </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>


</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>