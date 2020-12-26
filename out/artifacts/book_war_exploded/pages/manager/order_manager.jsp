<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--    静态包含，base标签，css样式，jQuery--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<a href="index.jsp">
				<img class=	"logo_img" alt="" src="static/img/DDlogoNEW.gif">
			</a>
			<span class="wel_word">订单管理系统</span>
			<%--静态包含 mananger管理模块的菜单--%>
			<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>物流状态</td>
				
			</tr>
			<c:forEach items="${requestScope.orderList}" var="entry">
				<tr>
					<td>${entry.orderId}</td>
					<td>${entry.createTime}</td>
					<td>${entry.price}</td>
					<td><a href="orderServlet?action=orderDetails&orderId=${entry.orderId}&orderPrice=${entry.price}">查看详情</a></td>

						<%--未发货--%>
						<c:if test="${entry.status == 0}">
							<td><a href="orderServlet?action=sendOrder&orderId=${entry.orderId}">发货</a></td>
						</c:if>
						<%--已发货--%>
						<c:if test="${entry.status == 1}">
							<td>已发货</td>
						</c:if>
						<%--已签收--%>
						<c:if test="${entry.status == 2}">
							<td>已签收</td>
						</c:if>

				</tr>
			</c:forEach>
		</table>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>