<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>winter</title>
<link rel="icon"
	href="${pageContext.request.contextPath}/img/favicon.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
<!-- font awesome CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nice-select.css">
<!-- flaticon CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/flaticon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css">
<!-- swiper CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/price_rangs.css">
<!-- style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="bg-white">
	<!--::header part start::-->
	<header class="main_menu home_menu">
		<div class="container-fluid">
			<div class="row align-items-center justify-content-center">
				<div class="col-lg-11">
					<nav class="navbar navbar-expand-lg navbar-light">
						<a class="navbar-brand" href="/admin"> <img
							src="${pageContext.request.contextPath}/img/logo.png" alt="logo">
						</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="menu_icon"><i class="fas fa-bars"></i></span>
						</button>

						<div class="collapse navbar-collapse main-menu-item"
							id="navbarSupportedContent">
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link" href="/admin">Home</a>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="blog.html"
									id="navbarDropdown_1" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">Catalog
										Management </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown_1">
										<a class="dropdown-item" href="/admin/categorymanagement">Category
											Management </a> <a class="dropdown-item"
											href="/admin/subcategorymanagement">Subcategory
											Management </a> <a class="dropdown-item"
											href="/admin/productmanagement">Product Management </a>
									</div></li>

							</ul>
						</div>

						<div class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
								class="ti-user"></i></a>
							<div class="dropdown-menu">
								<a href="#" class="dropdown-item"><security:authorize
										access="isAuthenticated()">
										<security:authentication property="principal.username" />
									</security:authorize></a> <a href="/logout" class="dropdown-item">Logout</a>
							</div>
						</div>

					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- Header part end-->

	<!--================Home Banner Area =================-->
	<!-- breadcrumb start-->
	<section class="breadcrumb breadcrumb_bg">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="breadcrumb_iner">
						<div class="breadcrumb_iner_item">
							<p>Admin / Product Management</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->
	<section class="new_arrival section_padding">
		<div class="container" style="margin-top: -5%">
			<div class="arrival_tittle">
				<h3>Product Management</h3>
			</div>
			<div class="container">
				<div class="col-lg-12">
					<ul class="list">

						<c:forEach var="category" items="${categories}">
							<c:forEach var="subcategory" items="${category.subCategories}">
								<li class="sub-menu"><a href="#"
									class=" d-flex justify-content-between">
										${subcategory.subCategoryName}
										<div class="right ti-plus"></div>
								</a></li>

								<ul>
									<table class="table" id="subCategoryTable">
										<thead>
											<tr>
												<th scope="col">Subcategory Name</th>
												<th scope="col"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="subcategory"
												items="${category.subCategories}">
												<tr>
													<td>
														<form class="form-inline" action="updatesubcategory"
															method="post">

															<input type="hidden" name="subCategoryId"
																value="${subcategory.subCategoryId}"> <input
																type="text" class="form-control col-sm-4"
																name="subCategoryName"
																value="${subcategory.subCategoryName}">
															<button class="btn btn-primary"
																onclick="$(this).closest('form').submit()">Update
																SubCategory</button>
														</form>
													</td>
													<td><form action="deletesubcategory" method="post">
															<input type="hidden" name="subCategoryId"
																value="${subcategory.subCategoryId}">
															<button class="btn btn-primary"
																onclick="$(this).closest('form').submit()">Delete
																Category</button>
														</form></td>

												</tr>

											</c:forEach>
											<tr>
												<button type="button" class="btn btn-primary col-sm-2"
													onclick="ShowHideDiv(${subcategory.subcategoryId})">Add
													Product</button>
											</tr>
										</tbody>
									</table>
								</ul>
							</c:forEach>
						</c:forEach>
					</ul>
				</div>
	</section>

	<!-- jquery plugins here-->
	<!-- jquery -->
	<script
		src="${pageContext.request.contextPath}/js/jquery-1.12.1.min.js"></script>
	<!-- popper js -->
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<!-- bootstrap js -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- easing js -->
	<script
		src="${pageContext.request.contextPath}/js/jquery.magnific-popup.js"></script>
	<!-- swiper js -->
	<script src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
	<!-- swiper js -->

	<!-- particles js -->
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
	<!-- slick js -->
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.counterup.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/contact.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.ajaxchimp.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/mail-script.js"></script>
	<script src="${pageContext.request.contextPath}/js/stellar.js"></script>
	<script src="${pageContext.request.contextPath}/js/price_rangs.js"></script>
	<!-- custom js -->
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
</body>

</html>
</html>