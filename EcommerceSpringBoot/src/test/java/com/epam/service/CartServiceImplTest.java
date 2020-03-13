package com.epam.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.dao.CartDao;
import com.epam.dao.ProductDao;
import com.epam.exception.EmptyCartException;
import com.epam.exception.InsufficientQuantityException;
import com.epam.model.Product;
import com.epam.model.ShoppingCart;

class CartServiceImplTest {


	@InjectMocks
	CartServiceImpl cartService;
	
	@Mock
	CartDao cartDao;

	@Mock
	ProductDao productDao;

	static ShoppingCart shoppingCart;
	static Product product;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setInitVars() {
		shoppingCart = new ShoppingCart();
		shoppingCart.setShoppingCartId();
		product=new Product();
		product.setProductId((long) 1);
		product.setProductName("Apple");
		product.setQuantity((long) 10);
		
	}

	@Test
	void testViewCart() throws EmptyCartException {
		Mockito.when(cartDao.viewCart()).thenReturn(shoppingCart);
		Assertions.assertEquals(shoppingCart.getShoppingCartId(), cartService.viewCart().getShoppingCartId());
	}

	@Test
	void testAddToCart() throws InsufficientQuantityException {
		Long purchasedQuantity = (long) 1;
		Long exceededQuantity = (long) 11;
		Mockito.when(productDao.getProductDetails(product.getProductId())).thenReturn(product);
		Assertions.assertThrows(InsufficientQuantityException.class, () -> cartService.addToCart(product.getProductId(), exceededQuantity));
		Mockito.when(cartDao.addToCart(productDao.getProductDetails(product.getProductId()), purchasedQuantity)).thenReturn(true);
		Assertions.assertEquals(cartService.addToCart(product.getProductId(), purchasedQuantity), true);
	}

	@Test
	void testDeleteProduct() {
		Mockito.when(cartDao.deleteProduct((long) 1)).thenReturn(true);
		Assertions.assertEquals(cartService.deleteProduct((long) 1), true);

	}

}
