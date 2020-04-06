package com.epam.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@NonNull
	private String productName;
	@NonNull
	private Double productPrice;
	@NonNull
	private Long quantity;
	@Lob
	@JsonIgnore
    private byte[] productImage;
	@ManyToOne
	@JsonIgnore
	private SubCategory subCategory;
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<ProductAttribute> productAttribures = new ArrayList<>();
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", quantity=" + quantity + ", productImage=" + Arrays.toString(productImage) + ", subCategory="
				+ subCategory + ", productAttribures=" + productAttribures + "]";
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public List<ProductAttribute> getProductAttribures() {
		return productAttribures;
	}
	public void setProductAttribures(List<ProductAttribute> productAttribures) {
		this.productAttribures = productAttribures;
	}
	
	
	
}
