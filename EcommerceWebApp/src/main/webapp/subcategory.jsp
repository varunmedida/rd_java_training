<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Sub-Category</title>
</head>
<body>
<div class="container">
	<div class="container jumbotron">
		<h1>Sub-Categories</h1>
	</div>
	<div class="container">
	<form action="product"  method="get">
	<c:forEach var="subcategory" items="${subcategories}">
	<input type="radio" name="subcategory" value="${subcategory.subCategoryId}">
	${subcategory.subCategoryName} <br>
	</c:forEach>
	<br>
	<div class="alert alert-danger">${message}</div>
	<button type="submit"  class="btn btn-primary">Submit</button>
	</form>
	</div>
	</div>
</body>
</html>