package com.epam;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.epam.exception.InsufficientQuantity;
import com.epam.model.Cart;
import com.epam.model.Category;
import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.service.OnlineShoppingService;
import com.epam.service.OnlineShoppingServiceImpl;

public class OnlineShoppingApplication {

	private static Scanner sc;
	static OnlineShoppingService service = new OnlineShoppingServiceImpl();

	public static void main(String[] args) {

		System.out.println("-----------------------------------------");
		System.out.println("\tWelcome to EPAMER's Store");
		System.out.println("-----------------------------------------");
		boolean continueUsingSystem = true;
		do {
			System.out.println("Are you a user or admin?");
			System.out.println("1. Admin\n2. Customer\n3. Exit");
			System.out.println("Choose one option from the above:");
			sc = new Scanner(System.in);

			try {
				int role = sc.nextInt();
				switch (role) {
				case 1:
					inventoryManagementAdmin();
					break;
				case 2:
					onlineShoppingUser();
					break;
				case 3:
					continueUsingSystem = false;
					System.out.println("Thank you for using our service.");
					break;
				default:
					System.out.println("Invalid option");

				}
			} catch (InputMismatchException inputMismatch) {
				System.out.println("Input is number and you have not entered a number.");
				sc.next();
			}
		} while (continueUsingSystem);

	}

	private static void onlineShoppingUser() {
		// TODO Auto-generated method stub

		boolean continueShopping = true;
		do {
			System.out.println("---------------");
			System.out.println("User Operations");
			System.out.println("---------------");
			System.out.println("1. Shopping\n2. View Cart\n3. Remove Products from Cart\n4. Checkout\n5. Exit");
			System.out.println("Choose the option you want to perform:");
			sc = new Scanner(System.in);

			try {
				int option = sc.nextInt();
				switch (option) {
				case 1:
					shopCategoryWise();
					break;
				case 2:
					viewProductsInCart();
					break;
				case 3:
					removeProductsFromCart();
					break;
				case 4:
					boolean checkedOut=checkout();
					if(checkedOut==true)
						continueShopping = false;
					break;
				case 5:
					continueShopping = false;
					System.out.println("Thank you for shopping with us.");
					break;
				default:
					System.out.println("Invalid option.");
				}
			} catch (InputMismatchException inputMismatch) {
				System.out.println("Input is number and you have not entered a number.");
				sc.next();
			}
		} while (continueShopping);

	}

	private static boolean checkout() {
		// TODO Auto-generated method stub
		boolean checkedOut=false;
		try {
			ArrayList<Cart> products = service.viewProductsInCart();
			System.out.println("******************CART*****************");
			System.out.println("ProductId\tProductName\tProductPrice\tQuantity Added to Cart");
			for (Cart product : products)
				System.out.println(product);
			System.out.println("***************************************");
			double totalAmount = service.totalAmount(products);
			System.out.println("TOTAL AMOUNT: "+totalAmount);
			System.out.println("Are you sure you want to checkout?Y/N");
			char checkout=sc.next().charAt(0);
			if (checkout=='Y') {
				service.updateStockInInventory(products);
				System.out.println("Thank you for shopping with us.");
				checkedOut=true;
			}
		} catch (NullPointerException nullPointer) {
			// TODO: handle exception
			System.out.println("Empty Cart");
		}
		return checkedOut;
		
		
	}

	private static void removeProductsFromCart() {
		// TODO Auto-generated method stub
		System.out.println("Enter the product Id to be removed.");
		int productId=sc.nextInt();
		service.removeProductsFromCart(productId);
		viewProductsInCart();
	}

	private static void viewProductsInCart() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Cart> products = service.viewProductsInCart();
			System.out.println("******************CART*****************");
			System.out.println("ProductId\tProductName\tProductPrice\tQuantity Added to Cart");
			for (Cart product : products)
				System.out.println(product);
			System.out.println("***************************************");
		} catch (NullPointerException nullPointer) {
			// TODO: handle exception
			System.out.println("Empty Cart");
		}
	}

	private static void shopCategoryWise() {
		// TODO Auto-generated method stub
		System.out.println("------------------");
		System.out.println("Shop Category Wise");
		System.out.println("------------------");
		ArrayList<Category> categories = service.getAllCategories();
		for (Category category : categories) {
			System.out.println(category.getCategoryId() + ". " + category.getCategoryName());
		}
		System.out.println("Choose a category to shop:");
		try {
			int category = sc.nextInt();
			displaySubCategoriesOfCategory(categories.get(category - 1).getSubCategories());
		} catch (NullPointerException nullPointer) {
			System.out.println("No sub-categories in this category");
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Input is number and you have not entered a number.");
			sc.next();
		}
	}

	private static void displaySubCategoriesOfCategory(ArrayList<SubCategory> subCategories) {
		// TODO Auto-generated method stub
		System.out.println("Sub-Categories");
		for (SubCategory subCategory : subCategories)
			System.out.println(subCategory.getSubCategoryId() + ". " + subCategory.getSubCategoryName());
		System.out.println("Choose a sub-category to shop:");
		try {
			int subCategory = sc.nextInt();
			displayProductsInSubCategory(subCategories.get(subCategory - 1).getProducts());
		} catch (NullPointerException nullPointer) {
			System.out.println("No products available in this sub-category");
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Input is number and you have not entered a number.");
			sc.next();
		}
	}

	private static void displayProductsInSubCategory(ArrayList<Product> products) {
		// TODO Auto-generated method stub
		System.out.println("Products Details and Available Quantity");
		System.out.println("ProductId\tProductName\tProductPrice\tQuantity Available");
		for (Product product : products)
			System.out.println(product);
		System.out.println("Select the product you want to add to cart.");
		try {
			int productId = sc.nextInt();
			System.out.println("Enter the quantity you want to purchase.");
			int quantity = sc.nextInt();
			addProductToCart(products.get(productId - 1), quantity);
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Input is number and you have not entered a number.");
			sc.next();
		}
	}

	private static void addProductToCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		if (product.getQuantity() > quantity) {
			service.addProductToCart(product, quantity);
			System.out.println("Product successfully added into the cart.");
		} else {
			try {
				throw new InsufficientQuantity();
			} catch (InsufficientQuantity e) {
				// TODO Auto-generated catch block
				System.out.println("Insufficient Quantity to purchase");
			}
		}
	}

	private static void inventoryManagementAdmin() {
		// TODO Auto-generated method stub
		
	}

}
