package com.epam.service;

import java.util.List;

import com.epam.model.Product;

public interface ProductService {

	List<Product> getProductsBasedOnSubCategory(int subcategoryOption) ;
}
