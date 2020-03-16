<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Products</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- owl carousel CSS -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/all.css">
<link rel="stylesheet" href="css/nice-select.css">
<!-- flaticon CSS -->
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/themify-icons.css">
<!-- font awesome CSS -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/slick.css">
<!-- swiper CSS -->
<link rel="stylesheet" href="css/price_rangs.css">
<!-- style CSS -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
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
						<a href="/viewcart"> <i class="ti-bag"></i>
						</a>
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
							<p>Home / Category</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- breadcrumb start-->

	<!--================Category Product Area =================-->
	<section class="cat_product_area section_padding border_top" style="margin-top: -2%">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="left_sidebar_area">
						<aside class="left_widgets p_filter_widgets sidebar_box_shadow">
							<div class="l_w_title">
								<h3>Browse Categories</h3>
							</div>
							<div class="widgets_inner">
								<ul class="list">
									<c:forEach var="category" items="${categories}">
										<li class="sub-menu"><a href="#"
											class=" d-flex justify-content-between">
												${category.categoryName}
												<div class="right ti-plus"></div>
										</a>
											<ul>
												<c:forEach var="subcategory"
													items="${category.subCategories}">
													<form action="/" method="post">
														<input type="hidden" name="subCategoryId"
															value="${subcategory.subCategoryId}"> <a href="#"
															onclick="$(this).closest('form').submit()"><li>${subcategory.subCategoryName}</li></a>
													</form>
												</c:forEach>
											</ul></li>
									</c:forEach>
								</ul>
							</div>
						</aside>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="row">
						<c:forEach var="product" items="${products}">
							<div class="col-lg-4 col-sm-6">
							<form action="productdetail" method="post">
								<div class="single_category_product" onclick="$(this).closest('form').submit()" style="cursor: pointer">
								
									<div class="single_category_img">
										<img src="getProductPhoto/${product.productId}" height="250px"
											width="100px" alt="">
										<div class="category_product_text">
											
												<input type="hidden" name="productId"
													value="${product.productId}"><h5> <a 
													>${product.productName}</a></h5>
												<p>Rs. ${product.productPrice}/-</p>
										</div>
									</div>
									
								</div>
								</form>
							</div>
						</c:forEach>
						<div class="col-lg-12 text-center">
							<a href="#" class="btn_2">More Items</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Category Product Area =================-->


	<!-- jquery plugins here-->
	<script src="js/jquery-1.12.1.min.js "></script>
	<!-- popper js -->
	<script src="js/popper.min.js "></script>
	<!-- bootstrap js -->
	<script src="js/bootstrap.min.js "></script>
	<!-- easing js -->
	<script src="js/jquery.magnific-popup.js "></script>
	<!-- swiper js -->
	<script src="js/swiper.min.js "></script>
	<!-- swiper js -->
	<script src="js/mixitup.min.js "></script>
	<script src="js/price_rangs.js "></script>
	<!-- particles js -->
	<script src="js/owl.carousel.min.js "></script>
	<script src="js/jquery.nice-select.min.js "></script>
	<!-- slick js -->
	<script src="js/slick.min.js "></script>
	<script src="js/jquery.counterup.min.js "></script>
	<script src="js/waypoints.min.js "></script>
	<script src="js/contact.js "></script>
	<script src="js/jquery.ajaxchimp.min.js "></script>
	<script src="js/jquery.form.js "></script>
	<script src="js/jquery.validate.min.js "></script>
	<script src="js/mail-script.js "></script>
	<!-- custom js -->
	<script src="js/custom.js "></script>
</body>

</html>