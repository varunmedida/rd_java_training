<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="zxx">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>winter</title>
<link rel="icon" href="img/favicon.png">
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
<!-- style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<!--::header part start::-->
	<header class="main_menu home_menu">
		<div class="container-fluid">
			<div class="row align-items-center justify-content-center">
				<div class="col-lg-11">
					<nav class="navbar navbar-expand-lg navbar-light">
						<a class="navbar-brand" href="/"> <img src="img/logo.png"
							alt="logo">
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
								<li class="nav-item"><a class="nav-link" href="/">Home</a>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="blog.html"
									id="navbarDropdown_1" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> Shop </a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown_1">
										<a class="dropdown-item" href="/shopbycategory"> Shop by
											Category</a>
									</div></li>
							</ul>
						</div>
						<div class="hearer_icon d-flex">
							<a href="/viewcart"> <i class="ti-bag"></i>
							</a>
							<div class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
									class="ti-user"></i></a>
								<div class="dropdown-menu">
									<a href="#"
										class="dropdown-item"><security:authorize
											access="isAuthenticated()">
    <security:authentication
												property="principal.username" />
										</security:authorize></a>
										<a href="/logout" class="dropdown-item">Logout</a> 
								</div>
							</div>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- Header part end-->

	<!-- banner part start-->
	<section class="banner_part">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-7">
					<div class="banner_slider">
						<div class="single_banner_slider">
							<div class="banner_text">
								<div class="banner_text_iner">
									<h5>Winter Cart</h5>
									<h1>A Stop for All your products</h1>
									<a href="/shopbycategory" class="btn_1">shop now</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- banner part start-->

	<!-- feature_part start-->
	<section class="feature_part pt-4"></section>
	<!-- upcoming_event part start-->

	<!-- new arrival part here -->
	<section class="new_arrival section_padding">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-8">
					<div class="arrival_tittle">
						<h2>Products</h2>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="col-lg-12">
				<div class="row">
					<c:forEach var="product" items="${products}">

						<div class="col-lg-3 col-sm-4">
							<form action="productdetail" method="post">
								<div class="single_category_product"
									onclick="$(this).closest('form').submit()"
									style="cursor: pointer">

									<input type="hidden" name="productId"
										value="${product.productId}">
									<div class="single_category_img">

										<img
											src="${pageContext.request.contextPath}/getProductPhoto/${product.productId}"
											height="250px" width="100px" alt="">

										<div class="category_product_text">

											<h5>
												<a>${product.productName}</a>
											</h5>

											<p>Rs. ${product.productPrice}/-</p>
										</div>

									</div>

								</div>
							</form>
						</div>

					</c:forEach>
				</div>
			</div>
		</div>
	</section>

	<!-- jquery plugins here-->
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
	<script src="${pageContext.request.contextPath}/js/mixitup.min.js"></script>
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
	<!-- custom js -->
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
</body>

</html>