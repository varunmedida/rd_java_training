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
<title>Category</title>
</head>
<body>
	<div class="container">
		<div class="container jumbotron">
			<h1>Shop By Category</h1>
		</div>
		<div class="container">
			<form action="categories" method="get" name="categoryform">
				<c:forEach var="category" items="${categories}">
					<input type="radio" name="category" value="${category.categoryId}">
	${category.categoryName} <br>
				</c:forEach>
				<br>
			</form>
			<script type='text/javascript'>
				$(document).ready(function() {
					$('input[name=category]').change(function() {
						$('form[name=categoryform]').submit();
					});
				});
			</script>
			<c:if test="${subcategories.size() != 0}">
				<form action="categories" method="get" name="subcategoryform">
					<c:forEach var="subcategory" items="${subcategories}">
						<input type="radio" name="subcategory"
							value="${subcategory.subCategoryId}">
	${subcategory.subCategoryName} <br>
					</c:forEach>
					<br>
				</form>
				<script type='text/javascript'>
					$(document).ready(function() {
						$('input[name=subcategory]').change(function() {
							$('form[name=subcategoryform]').submit();
						});
					});
				</script>
			</c:if>
			<c:if test="${products.size() != 0}">
				<form action="categories" method="get">
					<table class="table table-dark table-hover">
						<tr>
							<th>Choose Product</th>
							<th>Product Name</th>
							<th>Product Price</th>
							<th>Quantity Available</th>
						</tr>
						<c:forEach var="product" items="${products}">
							<tr>
								<td><input type="radio" name="product"
									value="${product.productId}"></td>
								<td>${product.productName}</td>
								<td>${product.productPrice}</td>
								<td>${product.quantityOfStock}</td>
							</tr>
						</c:forEach>
					</table>
					<label for="quantity">Quantity to purchase</label> <input
						type="number" name="quantity"> <br>
					<button type="submit" class="btn btn-primary">Add To Cart</button>
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>