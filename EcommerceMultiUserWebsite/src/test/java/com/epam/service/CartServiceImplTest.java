/*
 * package com.epam.service;
 * 
 * import static org.mockito.Mockito.when;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.jupiter.api.Assertions; import
 * org.junit.jupiter.api.BeforeAll; import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.MockitoAnnotations;
 * 
 * import com.epam.exception.DataNotFoundException; import
 * com.epam.exception.EmptyCartException; import
 * com.epam.exception.InsufficientQuantityException; import
 * com.epam.model.CartItem; import com.epam.model.Product; import
 * com.epam.model.ShoppingCart; import com.epam.repository.CartItemRepository;
 * import com.epam.repository.OrderRepository; import
 * com.epam.repository.ProductRepository; import
 * com.epam.repository.ShoppingCartRepository;
 * 
 * class CartServiceImplTest {
 * 
 * @InjectMocks CartServiceImpl cartService;
 * 
 * @Mock ShoppingCartRepository shoppingCartRepository;
 * 
 * @Mock ProductRepository productRepository;
 * 
 * @Mock CartItemRepository cartItemRepository;
 * 
 * @Mock ProductService productService;
 * 
 * @Mock OrderRepository orderRepository;
 * 
 * @Mock ShoppingCart shoppingCart;
 * 
 * static Product apple;
 * 
 * @BeforeEach public void setup() { MockitoAnnotations.initMocks(this); }
 * 
 * @BeforeAll public static void setInitVars() { apple = new Product();
 * apple.setProductId((long) 1); apple.setProductName("Apple");
 * apple.setQuantity((long) 10); apple.setProductPrice((double) 1000); }
 * 
 * @Test void viewCart() throws EmptyCartException {
 * when(shoppingCartRepository.findByShoppingCartId(shoppingCart.
 * getShoppingCartId())).thenReturn(shoppingCart);
 * when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
 * Assertions.assertNotNull(cartService.viewCart()); }
 * 
 * @Test void viewCartWhenNotInitialized() throws EmptyCartException {
 * when(shoppingCartRepository.findByShoppingCartId(shoppingCart.
 * getShoppingCartId())).thenReturn(null);
 * when(shoppingCartRepository.save(shoppingCart)).thenReturn(shoppingCart);
 * Assertions.assertNotNull(cartService.viewCart()); }
 * 
 * @Test void testAddToCartWhenQuantityIsLessThanZero() {
 * Assertions.assertThrows(InsufficientQuantityException.class, () ->
 * cartService.addToCart((long) 1, (long) 0));
 * 
 * }
 * 
 * @Test void testAddToCartWhenQuantityisGreaterAndExceededStockQuantity() {
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * Assertions.assertThrows(InsufficientQuantityException.class, () ->
 * cartService.addToCart((long) 1, (long) 11)); }
 * 
 * @Test void testAddToCartWhenQuantityisGreaterAndStockQuantityNotExceeded() {
 * CartItem newcartItem = new CartItem();
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * newcartItem.setProduct(apple); newcartItem.setQuantity((long) 1);
 * newcartItem.setShoppingCart(shoppingCart);
 * Assertions.assertNull(cartService.addToCart(apple.getProductId(), (long) 1));
 * }
 * 
 * @Test void testAddToCartWhenProductIsAlreadyInCartAndStockQuantityExceeded()
 * { CartItem existingCartItem = new CartItem();
 * existingCartItem.setCartId((long) 1); existingCartItem.setProduct(apple);
 * existingCartItem.setQuantity((long) 1);
 * existingCartItem.setShoppingCart(shoppingCart);
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(existingCartItem);
 * Assertions.assertThrows(InsufficientQuantityException.class, () ->
 * cartService.addToCart((long) 1, (long) 10)); }
 * 
 * @Test void
 * testAddToCartWhenProductIsAlreadyInCartAndStockQuantityNotExceeded() {
 * CartItem existingCartItem = new CartItem(); existingCartItem.setCartId((long)
 * 1); existingCartItem.setProduct(apple); existingCartItem.setQuantity((long)
 * 1); existingCartItem.setShoppingCart(shoppingCart);
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(existingCartItem);
 * Assertions.assertNull(cartService.addToCart(apple.getProductId(), (long) 1));
 * }
 * 
 * @Test void testUpdateCartWhenQuantityIsGreaterThanStock() { CartItem
 * existingCartItem = new CartItem(); existingCartItem.setCartId((long) 1);
 * existingCartItem.setProduct(apple); existingCartItem.setQuantity((long) 1);
 * existingCartItem.setShoppingCart(shoppingCart);
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(existingCartItem);
 * Assertions.assertThrows(InsufficientQuantityException.class, () ->
 * cartService.updateCart((long) 1, (long) 11)); }
 * 
 * @Test void testUpdateCartWhenQuantityIsZeroOrLesser() { CartItem
 * existingCartItem = new CartItem(); existingCartItem.setCartId((long) 1);
 * existingCartItem.setProduct(apple); existingCartItem.setQuantity((long) 1);
 * existingCartItem.setShoppingCart(shoppingCart);
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(existingCartItem);
 * Assertions.assertThrows(InsufficientQuantityException.class, () ->
 * cartService.updateCart((long) 1, (long) 0)); }
 * 
 * @Test void testUpdateCart() { CartItem existingCartItem = new CartItem();
 * existingCartItem.setCartId((long) 1); existingCartItem.setProduct(apple);
 * existingCartItem.setQuantity((long) 1);
 * existingCartItem.setShoppingCart(shoppingCart);
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(existingCartItem);
 * Assertions.assertNull(cartService.updateCart(apple.getProductId(), (long)
 * 1)); }
 * 
 * @Test void deleteCartItemWhenProductNotFound() {
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(null);
 * Assertions.assertThrows(DataNotFoundException.class, () ->
 * cartService.deleteCartItem((long) 1)); }
 * 
 * @Test void deleteCartItem() { CartItem existingCartItem = new CartItem();
 * existingCartItem.setCartId((long) 1); existingCartItem.setProduct(apple);
 * existingCartItem.setQuantity((long) 1);
 * existingCartItem.setShoppingCart(shoppingCart);
 * when(productService.getProductById((long) 1)).thenReturn(apple);
 * when(cartItemRepository.findByProductAndShoppingCart(apple,
 * shoppingCart)).thenReturn(existingCartItem);
 * Assertions.assertNotNull(cartService.deleteCartItem(apple.getProductId())); }
 * 
 * @Test void checkoutWhenCartIsEmpty() { List<CartItem> cartItems = new
 * ArrayList<>();
 * when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(
 * cartItems); Assertions.assertThrows(DataNotFoundException.class,()->
 * cartService.checkout()); }
 * 
 * @Test void checkoutWhenExceeded() { CartItem existingCartItem = new
 * CartItem(); existingCartItem.setCartId((long) 1);
 * existingCartItem.setProduct(apple); existingCartItem.setQuantity((long) 11);
 * existingCartItem.setShoppingCart(shoppingCart); List<CartItem> cartItems =
 * new ArrayList<>(); cartItems.add(existingCartItem);
 * when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(
 * cartItems); Assertions.assertThrows(InsufficientQuantityException.class,()->
 * cartService.checkout()); }
 * 
 * @Test void checkout() { CartItem existingCartItem = new CartItem();
 * existingCartItem.setCartId((long) 1); existingCartItem.setProduct(apple);
 * existingCartItem.setQuantity((long) 1);
 * existingCartItem.setShoppingCart(shoppingCart); List<CartItem> cartItems =
 * new ArrayList<>(); cartItems.add(existingCartItem);
 * when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(
 * cartItems);
 * when(productRepository.findByProductId(apple.getProductId())).thenReturn(
 * apple); Assertions.assertNotNull(cartService.checkout()); } }
 */