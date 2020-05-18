<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pizza Order</title>
<link rel='stylesheet' type='text/css' href='css/order.css'>
</head>
<body>
	<div class='welcomeMessage'>
				<%--get name of customer from session--%>
				<h1>Welcome ${sessionScope.name}!</h1>
				</div>

				<div class='order'>
				<form action='orderpizza' method='POST'>
				<h2>Tell us your order!</h2>

				<%--number of pizzas--%>
				<h2>Size:</h2>

				<%--select the size--%>
				<label class="sizes"><input type='radio' name='size' value='S'>Small</label>
				<label class="sizes"><input type='radio' name='size' value='M'>Medium</label>
				<label class="sizes"><input type='radio' name='size' value='L' checked='checked'>Large</label>
				<label class="sizes"><input type='radio' name='size' value='XL'>Extra Large</label>

				<%--choose toppings--%>
				<h2>Toppings</h2>
				<input name='numSauce' type='number' min='0' max='2' value='0'> Extra Tomato Sauce<br>
				<input name='numCheese' type='number' min='0' max='2' value='0'> Extra Cheese<br>
				<input name='numPepperoni' type='number' min='0' max='2' value='0'> Pepperoni<br>
				<input name='numBeef' type='number' min='0' max='2' value='0'> Beef<br>
				<input name='numChicken' type='number' min='0' max='2' value='0'> Chicken <br>
				<input name='numSausage' type='number' min='0' max='2' value='0'> Sausage<br>
				<input name='numOnion' type='number' min='0' max='2' value='0'> Onion<br>
				<input name='numMushroom' type='number' min='0' max='2' value='0'> Mushroom<br>
				<input name='numPineapple' type='number' min='0' max='2' value='0'> Pineapple<br>
				<input name='numGreenPepper' type='number' min='0' max='2' value='0'> Green Pepper<br>
				<input name='numHotPepper' type='number' min='0' max='2' value='0'> Hot Pepper<br>
				<input name='numOlive' type='number' min='0' max='2' value='0'> Olive<br>
				<br>
				<input type="hidden" name="page" value="2">
				<input id='submitBtn' type='submit' value='Add Pizza'>
				</form>
				</div>
							
				<div class='currentview'>
				
					<h2>Current Order</h2>
				
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
									<td class='price'><fmt:formatNumber type="number" pattern="00.00" value="${pizza.getPrice()}"/></td> 
								</tr>
							</c:forEach>
					
						<tr>
							<td></td>
							<td class='totalPrice'>Tax (13%):</td>
							<td> <c:out value="${taxsubtotal}"></c:out></td>
						</tr>
				
						<tr>
							<td></td>
							<td class='totalPrice'>Total:</td>
							<td><c:out value="${totalprice}"></c:out></td>
						</tr>
					</table>
					
					<form action='orderpizza' method='POST'>
					<input type="hidden" name="page" value="3">
					<input type='submit' id='submitBtn' value='Proceed to Checkout'>
					</form>
					
				</div>
			
</body>
</html>