package com.epam.model;

import java.util.ArrayList;

public class Category {

	private int categoryId;
	private String categoryName;
	private ArrayList<SubCategory> subCategories=new ArrayList<SubCategory>();
	public Category(int categoryId, String categoryName, ArrayList<SubCategory> subCategories) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.subCategories = subCategories;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ArrayList<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(ArrayList<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", subCategories="
				+ subCategories + "]";
	}
	
}
