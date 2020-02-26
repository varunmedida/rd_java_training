package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private int productId;
	private int subCategoryId;
	private String productName;
	private double productPrice;
	private int quantityOfStock;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
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

	public int getQuantityOfStock() {
		return quantityOfStock;
	}

	public void setQuantityOfStock(int quantityOfStock) {
		this.quantityOfStock = quantityOfStock;
	}

	public Product(int productId, int subCategoryId, String productName, double productPrice, int quantityOfStock) {
		super();
		this.productId = productId;
		this.subCategoryId = subCategoryId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantityOfStock = quantityOfStock;
	}

	@Override
	public String toString() {
		return productId + " " + productName+ " " + productPrice + " " + quantityOfStock ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(productPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantityOfStock;
		result = prime * result + subCategoryId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
			return false;
		if (quantityOfStock != other.quantityOfStock)
			return false;
		if (subCategoryId != other.subCategoryId)
			return false;
		return true;
	}

	
}
