package com.epam.model;

public class SubCategory {

	private int categoryId;
	private int subCategoryId;
	private String subCategoryName;

	public SubCategory(int categoryId, int subCategoryId, String subCategoryName) {
		super();
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	@Override
	public String toString() {
		return subCategoryId + ". " + subCategoryName + "";
	}

}
