package com.epam.model;

import java.util.ArrayList;

public class SubCategory {

	private int subCategoryId;
	private String subCategoryName;
	private ArrayList<Product> products=new ArrayList<Product>();
	public SubCategory(int subCategoryId, String subCategoryName, ArrayList<Product> products) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.products = products;
	}
	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "SubCategory [subCategoryId=" + subCategoryId + ", subCategoryName=" + subCategoryName + ", products="
				+ products + "]";
	}
	
}
