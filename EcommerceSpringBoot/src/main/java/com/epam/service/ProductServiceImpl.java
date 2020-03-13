package com.epam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.ProductDao;
import com.epam.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Product> getAllProducts() {
		LOGGER.info("------Called Product Service getAllProducts-------");
		return productDao.getAllProducts();
	}

	@Override
	public byte[] getPhotoById(Long productId) {
		LOGGER.info("------Called Product Service get Photo-------");
		Product product = productDao.getPhotoById(productId);
		return product.getProductImage();
	}

	@Override
	public List<Product> getProductsBasedOnSubCategoryId(Long subCategoryId) {
		LOGGER.info("------Called Product Service Products Based on SubCategory-------");
		return productDao.getProductsBasedOnSubCategoryId(subCategoryId);
	}

	@Override
	public Product getProductDetails(Long productId) {
		LOGGER.info("------Called Product Service to get a product details-------");
		return productDao.getProductDetails(productId);
	}

}
