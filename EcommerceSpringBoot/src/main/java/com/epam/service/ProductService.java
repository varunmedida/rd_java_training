package com.epam.service;

import java.util.List;

import com.epam.model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	byte[] getPhotoById(Long productId);

	List<Product> getProductsBasedOnSubCategoryId(Long subCategoryId);

	Product getProductDetails(Long productId);

}
