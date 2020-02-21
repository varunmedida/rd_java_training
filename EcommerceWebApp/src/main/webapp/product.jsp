<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Products</title>
</head>
<body class="container">
	<div class="container jumbotron">
		<h1>Products</h1>
	</div>
	<form action="cart" method="get">
	<table class="table table-dark table-hover">
	<tr>
	<th>Choose Product</th>
	<th>Product Name</th>
	<th>Product Price</th>
	<th>Quantity Available</th>
	</tr>
	<c:forEach var="product" items="${products}">
	<tr>
	<td><input type="radio" name="product" value="${product.productId}"></td>
	<td>${product.productName}</td>
	<td>${product.productPrice}</td>
	<td>${product.quantityOfStock}</td>
	</tr>
	</c:forEach>
	</table>
	<label for="quantity">Quantity to purchase</label>
	<input type="number" name="quantity"> <br>
	<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>