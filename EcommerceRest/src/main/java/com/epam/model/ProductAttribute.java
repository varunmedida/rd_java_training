package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductAttribute {

	@Id
	private String attributeName;
	private String attributeDescription;
	@ManyToOne
	private Product product;
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeDescription() {
		return attributeDescription;
	}
	public void setAttributeDescription(String attributeDescription) {
		this.attributeDescription = attributeDescription;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ProductAttribute [attributeName=" + attributeName + ", attributeDescription=" + attributeDescription
				+ ", product=" + product + "]";
	}
	
	
}
