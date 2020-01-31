package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

	private List<Product> products;
	double orderPrice = 0.0;

	public Double getPriceOfAvailableProducts() {

		Iterator<Product> iterator = products.iterator();
		removeProductIfNotAvailable(iterator);
		orderPrice = priceOfTotalProducts(products);
		return orderPrice;

	}

	public void removeProductIfNotAvailable(Iterator<Product> iterator) {
		while (iterator.hasNext()) {
			Product p = iterator.next();
			if (!p.isAvailable())
				iterator.remove();
		}
	}

	public double priceOfTotalProducts(List<Product> products) {

		for (Product p : products)
			orderPrice += p.getProductPrice();
		return orderPrice;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
