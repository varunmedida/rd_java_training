<%@ page language="java" contentType="text/html;"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="icon" href="img/favicon.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<!-- nice select CSS -->
<link rel="stylesheet" href="css/nice-select.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/slick.css">
<link rel="stylesheet" href="css/price_rangs.css">
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

	<!-- breadcrumb start-->
	<section class="breadcrumb breadcrumb_bg">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="breadcrumb_iner">
						<div class="breadcrumb_iner_item">
							<p>Home/Shop/Cart list</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================Cart Area =================-->



	<section class="cart_area section_padding" style="margin-top: -2%">
		<div class="container">
			<div class="cart_inner">
				<div class="table-responsive">
					
					<c:if test="${empty cart.cartItems}">
						<div class="alert alert-danger">
							<strong>Empty Cart!</strong> Add Products to Cart.
						</div>
					</c:if>

					<c:if test="${!empty cart.cartItems}">

						<c:if test="${productRemoved}">
							<div class="alert alert-success">Product Removed from cart.
							</div>
						</c:if>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Product</th>
									<th scope="col">Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Remove Product</th>
									<th scope="col">Price * Quantity</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartItem" items="${cart.cartItems}">
									<tr>
										<td>
											<div class="media">
												<div class="d-flex">
													<img src="getProductPhoto/${cartItem.product.productId}"
														height="150px" width="150px" alt="" />
												</div>
												<div class="media-body">
													<p>${cartItem.product.productName}</p>
												</div>
											</div>
										</td>
										<td>
											<h5>Rs. ${cartItem.product.productPrice}/-</h5>
										</td>
										<td>
										<c:if test="${cartItem.product.quantity <cartItem.quantity}">
										<div class="product_count alert alert-danger">
										Out of Stock.
										</div>
										</c:if>
										<c:if test="${cartItem.product.quantity >=cartItem.quantity}">
											<div class="product_count">
												<form action="updatecart" method="post">
													<input type="hidden" name="productId"
														value="${cartItem.product.productId}"> <input
														type="hidden" name="quantity"
														value="${cartItem.quantity-1}"> <span
														class="input-number-decrement"> <i class="ti-minus"
														onclick="$(this).closest('form').submit()"></i></span>

												</form>
												<form action="updatecart" method="post">
													<input class="input-number" type="text"
														value="${cartItem.quantity}" min="1"
														max="${cartItem.product.quantity}" readonly="readonly">
													<input type="hidden" name="productId"
														value="${cartItem.product.productId}"> <input
														type="hidden" name="quantity"
														value="${cartItem.quantity+1}"> <span
														class="input-number-increment"> <i class="ti-plus"
														onclick="$(this).closest('form').submit()"></i></span>
												</form>
											</div>
											</c:if>
										</td>
										<td>
											<form action="delete" method="post">
												<span><input type="hidden" name="productId"
													value="${cartItem.product.productId}"></span> <img
													src="img/removeproduct.png" alt=""
													style="margin-left: 12%; cursor: pointer;" width="30px"
													height="30px" onclick="$(this).closest('form').submit()">
											</form>
										</td>
										<td>
										 <h5>Rs. ${cartItem.product.productPrice*cartItem.quantity}/-</h5>
										</td>
									</tr>
								</c:forEach>
								<tr class="bottom_button">
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td>
										<h5>Total</h5>
									</td>
									<td>
										<h5>Rs. ${cart.totalAmount}/-</h5>
									</td>
								</tr>
							</tbody>
						</table>
					</c:if>
					<div class="checkout_btn_inner float-right">
						<a class="btn_1" href="/shopbycategory">Continue Shopping</a>

						<c:if test="${!empty cart.cartItems}">
							<button class="btn_1 checkout_btn_1" type="button"
								onclick="myFunction()">Checkout</button>
						</c:if>
						<div class="alert alert-info" id="myDIV" style="display: none;">
						<strong>Would you like to checkout?</strong> <br> <a
							href="/checkout" class="btn_1 checkout_btn_1" class="alert-link">Yes</a>
						<a href="/viewcart" class="btn_1 checkout_btn_1"
							class="alert-link">No</a>
					</div>
						<script type="text/javascript">
							function myFunction() {
								var x = document.getElementById("myDIV");
								if (x.style.display === "none") {
									x.style.display = "block";
								} else {
									x.style.display = "none";
								}
							}
						</script>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!--================End Cart Area =================-->

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
	<script src="js/price_rangs.js"></script>
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