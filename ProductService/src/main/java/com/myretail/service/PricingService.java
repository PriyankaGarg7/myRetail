package com.myretail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.exception.MyRetailResponseErrorHandler;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Price;

@Service
public class PricingService {

	RestTemplate restTemplate;

	@Autowired
	public PricingService(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new MyRetailResponseErrorHandler());
		this.restTemplate = restTemplate;
	}

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

	public void createPrice(Long productId, Price price) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Price createPrice = new Price(productId, price.getOriginalPrice(), price.getCurrencyCode());
		String uri = "http://localhost:8081/product/" + productId + "/price";
		HttpEntity<Price> request = new HttpEntity<>(createPrice, headers);
		restTemplate.postForLocation(uri, request);
	}

	public void updatePrice(Long productId, Price price) {

		String uri = "http://localhost:8081/product/" + productId + "/price";
		restTemplate.put(uri, price);
	}
}