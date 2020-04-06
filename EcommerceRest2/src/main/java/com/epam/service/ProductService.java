package com.epam.service;

import java.util.List;

import com.epam.model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Long productId);

	List<Product> getProductsBasedOnSubCategory(Long subCategoryId);

	byte[] getPhotoById(Long productId);

}
