package com.epam.model;


public class Cart {

	private int productId;
	private String productName;
	private double productPrice;
	private int quantityPurchased;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	public Cart(int productId, String productName, double productPrice, int quantityPurchased) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantityPurchased = quantityPurchased;
	}
	@Override
	public String toString() {
		return "" + productId + "\t" + productName + "\t\t" + productPrice
				+ "\t\t" + quantityPurchased + "";
	}
	
	
}
