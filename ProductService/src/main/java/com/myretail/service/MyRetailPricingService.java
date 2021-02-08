package com.myretail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.model.Price;

@Service
public class MyRetailPricingService {

	@Autowired
	RestTemplate restTemplate;

	public Price getPrice(Long productId) {

		String uri = "http://localhost:8081/product/" + productId + "/price";
		ResponseEntity<Price> price = null;
		Price retrievedPrice = null;
		try {
			price = restTemplate.getForEntity(uri, Price.class);
			retrievedPrice = price.getBody();
		} catch (RestClientException exc) {
			retrievedPrice = new Price(productId, null, "");
		}
		return retrievedPrice;
	}
}