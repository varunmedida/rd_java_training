package com.epam.model;

public class SubCategory {
	private int subCategoryId;
	private int categoryId;
	private String subCategoryName;

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public SubCategory(int subCategoryId, int categoryId, String subCategoryName) {
		super();
		this.subCategoryId = subCategoryId;
		this.categoryId = categoryId;
		this.subCategoryName = subCategoryName;
	}

	@Override
	public String toString() {
		return subCategoryId + ". "	+ subCategoryName ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + subCategoryId;
		result = prime * result + ((subCategoryName == null) ? 0 : subCategoryName.hashCode());
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
		SubCategory other = (SubCategory) obj;
		if (categoryId != other.categoryId)
			return false;
		if (subCategoryId != other.subCategoryId)
			return false;
		if (subCategoryName == null) {
			if (other.subCategoryName != null)
				return false;
		} else if (!subCategoryName.equals(other.subCategoryName))
			return false;
		return true;
	}
	
}
