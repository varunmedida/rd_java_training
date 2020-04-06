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
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<link rel="stylesheet" href="css/nice-select.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/slick.css">
<!-- style CSS -->
<link rel="stylesheet" href="css/style.css">
</head>

<body class="bg-white">
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
										<a class="dropdown-item" href="/shopbycategory"> shop by
											category</a>
									</div></li>
							</ul>
						</div>
						<div class="hearer_icon d-flex">
								<a href="/viewcart"> <i class="ti-bag"></i>
								</a>
							<div class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="ti-user"></i></a>
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

	<!--================Home Banner Area =================-->
	<!-- breadcrumb start-->
	<section class="breadcrumb breadcrumb_bg">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="breadcrumb_iner">
						<div class="breadcrumb_iner_item">
							<p>Home / checkout</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================Checkout Area =================-->
	<section class="checkout_area section_padding">
		<div class="container">
			<div class="billing_details">
				<div class="row">
					<div class="col-lg-8">
					
						<c:if test="${empty shoppingCart.cartItems}">
						<div class="order_box">
						<h2><security:authorize
											access="isAuthenticated()">
    <security:authentication
												property="principal.username" />
										</security:authorize>, some of your selected products are out of stock please shop again.</h2>
										<a class="btn_3" href="/viewcart">View Cart</a>
										</div>
						</c:if>
						
					<c:if test="${!empty shoppingCart.cartItems}">
						<div class="order_box">
						
							<h2><security:authorize
											access="isAuthenticated()">
    <security:authentication
												property="principal.username" />
										</security:authorize>, your Order is placed successfully.</h2>
							<ul class="list">
								<li><a href="#">Product <span>Total</span>
								</a></li>
								<c:forEach var="cartItem" items="${shoppingCart.cartItems}">
									<li><a href="#">${cartItem.product.productName} <span
											class="middle">x ${cartItem.quantity}</span> <span
											class="last">Rs. ${cartItem.product.productPrice}/-</span>
									</a></li>
								</c:forEach>
							</ul>
							<ul class="list list_2">
								<li><a href="#">Total <span>${shoppingCart.totalAmount}</span>
								</a></li>
							</ul>
							<a class="btn_3" href="/">Shop Again</a>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Checkout Area =================-->





	<!-- jquery plugins here-->
	<script src="js/jquery-1.12.1.min.js"></script>
	<!-- popper js -->
	<script src="js/popper.min.js"></script>
	<!-- bootstrap js -->
	<script src="js/bootstrap.min.js"></script>
	<!-- easing js -->
	<script src="js/jquery.magnific-popup.js"></script>
	<!-- swiper js -->
	<script src="js/swiper.min.js"></script>
	<!-- swiper js -->
	<script src="js/mixitup.min.js"></script>
	<!-- particles js -->
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<!-- slick js -->
	<script src="js/slick.min.js"></script>
	<script src="js/jquery.counterup.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/contact.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/mail-script.js"></script>
	<!-- custom js -->
	<script src="js/custom.js"></script>
</body>

</html>