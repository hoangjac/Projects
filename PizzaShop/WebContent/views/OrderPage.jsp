<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Pizza Order</title>
<link rel='stylesheet' type='text/css' href='css/order.css'>
</head>
<body>

	<%--get attribute name of the customer--%>
	<div class='welcomeMessage'>
		<h1>Welcome ${sessionScope.name}!</h1>
	</div>

	<%--create a form so that a user must add a pizza--%>
	<div class='order'>
		<form action='orderpizza' method='POST'>
			<h2>Tell us your order!</h2>

			<!-- size of the pizza -->
			<h2>Size:</h2>

			<!-- select the size --> 
			
			<label class="sizes"><input type='radio' name='size' value='S'>Small</label>
			<label class="sizes"><input type='radio' name='size' value='M'>Medium</label>
			<label class="sizes"><input type='radio' name='size' value='L' checked='checked'>Large</label>
			<label class="sizes"><input type='radio' name='size' value='XL'>Extra Large</label>
			
			<!-- choose toppings where user can enter the amount -->
			<h2>Toppings</h2>
			<input name='numSauce' type='number' min='0' max='2' value='0'> Extra Tomato Sauce<br> 
			<input name='numCheese' type='number' min='0' max='2' value='0'> Extra Cheese<br> 
			<input name='numPepperoni' type='number' min='0' max='2' value='0'> Pepperoni<br> 
			<input name='numBeef' type='number' min='0' max='2' value='0'> Beef<br> 
			<input name='numChicken' type='number' min='0' max='2' value='0'> Chicken<br>
			<input name='numSausage' type='number' min='0' max='2' value='0'> Sausage<br> 
			<input name='numOnion' type='number' min='0' max='2' value='0'> Onion<br> 
			<input name='numMushroom' type='number' min='0' max='2' value='0'> Mushroom<br> 
			<input name='numPineapple' type='number' min='0' max='2' value='0'> Pineapple<br> 
			<input name='numGreenPepper' type='number' min='0' max='2' value='0'> Green Pepper<br> 
			<input name='numHotPepper' type='number' min='0' max='2' value='0'> Hot Pepper<br> 
			<input name='numOlive' type='number' min='0' max='2' value='0'> Olive<br> <br> 
			<input type='hidden' name='page' value="2"> 
			<input id='submitBtn' type='submit' value='Add Pizza'>
		</form>

	</div>
</body>
</html>