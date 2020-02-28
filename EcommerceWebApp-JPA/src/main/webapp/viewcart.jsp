<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'>
	
</script>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<div class="container">
	<div class="container jumbotron">
			<h1>Cart</h1>
		</div>
			<c:if test="${cart.isEmpty()!=false}">
			<div class="alert alert-danger">Cart is Empty</div>
			</c:if>
		<c:if test="${cart.isEmpty()==false}">

			<table class="table table-dark table-hover">
				<tr>
					<th>Product Name</th>
					<th>Product Price</th>
					<th>Quantity added to Cart</th>
				</tr>
				<c:forEach var="cartItem" items="${cart}">
					<tr>
						<td>${cartItem.product.productName}</td>
						<td>${cartItem.product.productPrice}</td>
						<td>${cartItem.quantityToCart}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="alert alert-info">Total price: ${totalAmount}</div>
		</c:if>
	</div>
</body>
</html>