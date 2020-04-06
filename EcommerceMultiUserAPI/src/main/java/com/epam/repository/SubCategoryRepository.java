package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	public SubCategory findBySubCategoryId(Long subCategoryId);
}
