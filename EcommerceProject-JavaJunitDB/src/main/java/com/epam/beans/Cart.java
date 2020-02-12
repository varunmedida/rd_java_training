package com.epam.beans;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(productPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantityAdded;
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
		Cart other = (Cart) obj;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
			return false;
		if (quantityAdded != other.quantityAdded)
			return false;
		return true;
	}
	
	
}
