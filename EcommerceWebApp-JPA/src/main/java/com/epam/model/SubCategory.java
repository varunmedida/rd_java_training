package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subCategoryId;
	private int categoryId;
	private int subCategoryName;
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
	public int getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(int subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + subCategoryId;
		result = prime * result + subCategoryName;
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
		if (subCategoryName != other.subCategoryName)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SubCategory [subCategoryId=" + subCategoryId + ", categoryId=" + categoryId + ", subCategoryName="
				+ subCategoryName + "]";
	}
	
	
	
}
