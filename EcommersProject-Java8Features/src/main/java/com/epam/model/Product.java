package com.epam.model;

public class Product {

	private int subCategoryId;
	private int productId;
	private String productName;
	private double productPrice;
	private int quantityInStock;

	public Product(int subCategoryId, int productId, String productName, double productPrice, int quantityInStock) {
		super();
		this.subCategoryId = subCategoryId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantityInStock = quantityInStock;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
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

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	@Override
	public String toString() {
		return "|\t"+productId + "\t" + productName
				+ "\t" + productPrice + "\t   " + quantityInStock + "    |";
	}

}
