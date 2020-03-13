package com.epam.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.repository.ProductRepository;
import com.epam.repository.SubCategoryRepository;

@Repository
public class ProductDaoImpl implements ProductDao {
	private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);
	@Autowired
	ProductRepository productRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Override
	public List<Product> getAllProducts() {
		LOGGER.info("------Called Product dao getAllProducts-------");
		return productRepository.findAll();
	}

	@Override
	public Product getPhotoById(Long productId) {
		LOGGER.info("------Called Product dao get Photo-------");
		return productRepository.findByProductId(productId);
	}

	@Override
	public List<Product> getProductsBasedOnSubCategoryId(Long subCategoryId) {
		LOGGER.info("------Called Product dao get products based on subcategory-------");
		SubCategory subCategory = subCategoryRepository.findBySubCategoryId(subCategoryId);
		return productRepository.findBySubCategory(subCategory);
	}

	@Override
	public Product getProductDetails(Long productId) {
		LOGGER.info("------Called Product dao get product details-------");
		return productRepository.findByProductId(productId);
	}

}
