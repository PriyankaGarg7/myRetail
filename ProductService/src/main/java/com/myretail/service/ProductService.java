package com.myretail.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.model.Bonus;
import com.myretail.model.CurentPrice;
import com.myretail.model.MyRetailProduct;
import com.myretail.model.Price;
import com.myretail.model.RedSkyProduct;

@Service
public class ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	RedSkyService redskyService;

	@Autowired
	MyRetailPricingService pricingService;

	@Autowired
	MyRetailBonusService bonusService;

	@Autowired
	RestTemplate restTemplate;

	public MyRetailProduct getProduct(Long productId) {

		logger.info("Inside the Product Service");

		RedSkyProduct redskyProduct = redskyService.getProduct(productId);

		Price price = pricingService.getPrice(productId);
		
		Bonus bonus = bonusService.getBonus(productId);

		BigDecimal originalPrice = price.getOriginalPrice();
		BigDecimal value = originalPrice.add(bonus.getBonus());

		CurentPrice currentPrice = new CurentPrice(value, price.getCurrencyCode());
				
		MyRetailProduct myRetailProduct = new MyRetailProduct();
		myRetailProduct.setId(redskyProduct.getProduct().getItem().getTcin());
		myRetailProduct.setName(redskyProduct.getProduct().getItem().getProduct_description().getTitle());
		myRetailProduct.setCurrentPrice(currentPrice);

		
		return myRetailProduct;
	}

	public MyRetailProduct createProductPrice(MyRetailProduct productPrice) {

		Long productId = productPrice.getId();
		RedSkyProduct redskyProduct = redskyService.getProduct(productId);
		Price price = pricingService.getPrice(productId);
		
		Bonus bonus = bonusService.createBonus(productPrice);
		BigDecimal originalPrice = price.getOriginalPrice();
		BigDecimal value = originalPrice.add(bonus.getBonus());

		CurentPrice currentPrice = new CurentPrice(value, price.getCurrencyCode());
				
		MyRetailProduct myRetailProduct = new MyRetailProduct();
		myRetailProduct.setId(redskyProduct.getProduct().getItem().getTcin());
		myRetailProduct.setName(redskyProduct.getProduct().getItem().getProduct_description().getTitle());
		myRetailProduct.setCurrentPrice(currentPrice);
		
		return myRetailProduct;

	}

	public void updateProductPrice(Long productId, MyRetailProduct newProductPrice) {
		bonusService.updatePrice(productId, newProductPrice);
	}
}
