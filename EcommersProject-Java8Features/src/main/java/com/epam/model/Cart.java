package com.epam.model;

public class Cart {
	private int productId;
	private String productName;
	private double productPrice;
	private int quantityAdded;

	public Cart(int productId, String productName, double productPrice, int quantityAdded) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantityAdded = quantityAdded;
	}

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

	public int getQuantityAdded() {
		return quantityAdded;
	}

	public void setQuantityAdded(int quantityAdded) {
		this.quantityAdded = quantityAdded;
	}

	@Override
	public String toString() {
		return "|\t" + productId + "\t" + productName + "\t" + productPrice + "\t   " + quantityAdded + "    |";
	}

}
