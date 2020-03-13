package com.epam.dao;

import java.util.List;

import com.epam.model.Product;

public interface ProductDao {

	List<Product> getAllProducts();

	Product getPhotoById(Long productId);

	List<Product> getProductsBasedOnSubCategoryId(Long subCategoryId);

	Product getProductDetails(Long productId);

}
