/*
 * package com.epam.controller;
 * 
 * import static org.mockito.Mockito.when;
 * 
 * import org.junit.jupiter.api.Assertions; import
 * org.junit.jupiter.api.BeforeAll; import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.MockitoAnnotations;
 * 
 * import com.epam.exception.EmptyCartException; import com.epam.model.CartItem;
 * import com.epam.model.Product; import com.epam.model.ShoppingCart; import
 * com.epam.service.CartServiceImpl;
 * 
 * class CartControllerTest {
 * 
 * @Mock CartServiceImpl cartService;
 * 
 * @InjectMocks CartController cartController;
 * 
 * @BeforeEach public void setup() { MockitoAnnotations.initMocks(this); }
 * 
 * static Product apple; static ShoppingCart shoppingCart;
 * 
 * @BeforeAll public static void setInitVars() { shoppingCart = new
 * ShoppingCart(); apple = new Product(); apple.setProductId((long) 1);
 * apple.setProductName("Apple"); apple.setQuantity((long) 10);
 * apple.setProductPrice((double) 1000); }
 * 
 * @Test void testViewCart() throws EmptyCartException {
 * when(cartService.viewCart()).thenReturn(shoppingCart);
 * Assertions.assertNotNull(cartController.viewCart()); }
 * 
 * @Test void testAddToCart() { CartItem cartItem = new CartItem();
 * cartItem.setCartId((long) 1); cartItem.setProduct(apple);
 * cartItem.setQuantity((long) 1); cartItem.setShoppingCart(shoppingCart);
 * when(cartService.addToCart(apple.getProductId(), (long)
 * 1)).thenReturn(cartItem);
 * Assertions.assertNotNull(cartController.addToCart(apple.getProductId(),
 * (long) 1)); }
 * 
 * @Test void testUpdateCart() { CartItem cartItem = new CartItem();
 * cartItem.setCartId((long) 1); cartItem.setProduct(apple);
 * cartItem.setQuantity((long) 1); cartItem.setShoppingCart(shoppingCart);
 * when(cartService.updateCart(apple.getProductId(), (long)
 * 1)).thenReturn(cartItem);
 * Assertions.assertNotNull(cartController.updateCart(apple.getProductId(),
 * (long) 1)); }
 * 
 * @Test void testDeleteCartItem() { CartItem cartItem = new CartItem();
 * cartItem.setCartId((long) 1); cartItem.setProduct(apple);
 * cartItem.setQuantity((long) 1); cartItem.setShoppingCart(shoppingCart);
 * when(cartService.deleteCartItem(apple.getProductId())).thenReturn(cartItem);
 * Assertions.assertNotNull(cartController.deleteCartItem(apple.getProductId()))
 * ; }
 * 
 * @Test void testCheckout() {
 * when(cartService.checkout()).thenReturn(shoppingCart);
 * Assertions.assertNotNull(cartController.checkout()); }
 * 
 * }
 */