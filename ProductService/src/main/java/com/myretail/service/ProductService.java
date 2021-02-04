package com.myretail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.MyRetailProduct;
import com.myretail.model.Price;
import com.myretail.model.RedSkyProduct;

@Service
public class ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	RedSkyService redskyService;

	@Autowired
	PricingService pricingService;

	@Autowired
	RestTemplate restTemplate;

	public MyRetailProduct getProduct(Long productId) {

		logger.info("Inside the Product Service");

		RedSkyProduct redskyProduct = redskyService.getProduct(productId);

		Price price = pricingService.getPrice(productId);

		MyRetailProduct myRetailProduct = new MyRetailProduct();
		myRetailProduct.setId(redskyProduct.getProduct().getItem().getTcin());
		myRetailProduct.setName(redskyProduct.getProduct().getItem().getProduct_description().getTitle());
		myRetailProduct.setCurrentPrice(price);

		return myRetailProduct;
	}

	public void createProductPrice(Long productId, MyRetailProduct productPrice) {

		redskyService.getProduct(productId);

		Price createPrice = new Price(productId, productPrice.getCurrentPrice().getValue(),
				productPrice.getCurrentPrice().getCurrencyCode());

		pricingService.createPrice(productId, createPrice);

	}

	public void updateProductPrice(Long productId, MyRetailProduct newProductPrice) {
		Price updatePrice = new Price();
		updatePrice.setValue(newProductPrice.getCurrentPrice().getValue());
		updatePrice.setCurrencyCode(newProductPrice.getCurrentPrice().getCurrencyCode());
		pricingService.updatePrice(productId, updatePrice);
	}

}
