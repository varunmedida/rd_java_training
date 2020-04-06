<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Winter</title>
<link rel="icon" href="img/favicon.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/lightslider.min.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- style CSS -->
<link rel="stylesheet" href="css/style.css">
<!-- <script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'> -->

</script>
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

	<!-- breadcrumb start-->
	<section class="breadcrumb breadcrumb_bg">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="breadcrumb_iner">
						<div class="breadcrumb_iner_item">
							<p>Home/Shop/Product Details</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================Single Product Area =================-->
	<div class="product_image_area section_padding" style="margin-top: -3%">
		<div class="container">
			<div class="row s_product_inner">
				<div class="col-lg-5">
					<div class="product_slider_img">
						<div id="vertical">
							<div data-thumb="img/product_details/prodect_details_1.png">
								<img src="getProductPhoto/${product.productId}"
								 />
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5 offset-lg-1">
					<div class="s_product_text">
						<h3>${product.productName}</h3>
						<h2>Rs. ${product.productPrice}/-</h2>
						<ul class="list">
							<li><a href="#"> <span>Availibility</span> :
									${product.quantity}
							</a></li>
						</ul>
						${addedToCart}
						<div class="card_area">
						<c:if test="${product.quantity <=0}">
						<div class='alert alert-danger'>Out of Stock</div>
						</c:if>
						<c:if test="${product.quantity >0}">

							<form action="addtocart" method="post">
								<div class="product_count d-inline-block">
									<span class="inumber-decrement"> <i class="ti-minus"></i></span>
									<input class="input-number" type="text" value="1" min="1"
										name="quantity" max="${product.quantity}" readonly="readonly"> <span
										class="number-increment"> <i class="ti-plus"></i></span>
								</div>
								<div class="add_to_cart">
									<input type="hidden" name="productId"
										value="${product.productId}"><a href="#" class="btn_3"
										onclick="$(this).closest('form').submit()">add to cart</a>
								</div>
								</form>
								</c:if>
								<div class="checkout_btn_inner float-right">
									<a class="btn_1" href="/shopbycategory">Continue Shopping</a>

								</div>
							

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--================End Single Product Area =================-->

	<!-- jquery plugins here-->
	<script src="js/jquery-1.12.1.min.js"></script>
	<!-- popper js -->
	<script src="js/popper.min.js"></script>
	<!-- bootstrap js -->
	<script src="js/bootstrap.min.js"></script>
	<!-- easing js -->
	<script src="js/jquery.magnific-popup.js"></script>
	<!-- swiper js -->
	<script src="js/lightslider.min.js"></script>
	<!-- swiper js -->
	<script src="js/mixitup.min.js"></script>
	<script src="js/lightslider.min.js"></script>
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