package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exception.DataNotFoundException;
import com.epam.model.Product;
import com.epam.model.SubCategory;
import com.epam.repository.ProductRepository;
import com.epam.repository.SubCategoryRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new DataNotFoundException("Products are not present");
		}
		return products;
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Product Not Found"));
	}

	@Override
	public List<Product> getProductsBasedOnSubCategory(Long subCategoryId) {
		SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
				.orElseThrow(() -> new DataNotFoundException("SubCategory Not Found"));
		List<Product> products = subCategory.getProducts();

		return products;
	}

	@Override
	public byte[] getPhotoById(Long productId) {
		Product product = productRepository.findByProductId(productId);
		return product.getProductImage();
	}

}
