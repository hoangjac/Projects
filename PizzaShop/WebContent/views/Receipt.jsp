<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Pizza Order</title>
<link rel='stylesheet' type='text/css' href='css/order.css'>
</head>
<body>

	<div class='welcomeMessage'>
		<h1>Thank you for your order ${sessionScope.name}!</h1>
	</div>

	<div class='imgBar'>
		<img src='images/pizza.jpg' alt="veggie pizza">
	</div>

	<div class='receipt'>

		<h3>Your Receipt</h3>
		<div class='cusInfo'>
			<b>${sessionScope.name}</b><br> ${sessionScope.email}<br>
			${sessionScope.address}<br> ${sessionScope.phone}<br>
		</div>

		<div class='orderInfo'>

			<table class='currentOrder'>
				<tr>
					<th>Size</th>
					<th>Toppings</th>
					<th>Price</th>
				</tr>

				<c:forEach var="pizza" items="${cart}">
					<tr>
						<td class='size'>${pizza.getPizzaSize()}</td>
						<td class='toppings'>${pizza.getPizzaToppings()}</td>
						<td class='price'><fmt:formatNumber type="number" pattern="00.00" value="${pizza.getPrice()}" /></td>
					</tr>
				</c:forEach>

				<tr>
					<td></td>
					<td class='totalPrice'>Tax (13%):</td>
					<td><c:out value="${taxsubtotal}"></c:out></td>
				</tr>

				<tr>
					<td></td>
					<td class='totalPrice'>Total:</td>
					<td><c:out value="${totalprice}"></c:out></td>
				</tr>
			</table>
		</div>
		
		<div class='deliveryTime'>
		
		<p class="delivery"><b>Delivery Time: ${sessionScope.deliveryDateTime} UTC: ${sessionScope.offset}</b></p>
		
		</div>
		
	</div>
</body>
</html>