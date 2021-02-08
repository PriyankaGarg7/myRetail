package com.myretail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.model.Bonus;
import com.myretail.model.MyRetailProduct;

public class MyRetailBonusService {

	@Autowired
	RestTemplate restTemplate;

	public Bonus createBonus(MyRetailProduct productPrice) {
		Bonus createBonus = new Bonus(productPrice.getId(), productPrice.getCurrentPrice().getValue());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String uri = "http://localhost:8082/bonus/";
		HttpEntity<Bonus> request = new HttpEntity<>(createBonus, headers);
		RequestEntity<Bonus> bonus = restTemplate.postForObject(uri, request, RequestEntity.class);
		return bonus.getBody();
	}

	public void updatePrice(Long productId, MyRetailProduct newProductPrice) {
		String uri = "http://localhost:8082/bonus/" + productId;
		restTemplate.put(uri, newProductPrice);
	}

	public Bonus getBonus(Long productId) {
		String uri = "http://localhost:8082/bonus/" + productId;
		Bonus retrievedBonus = null;
		try {
			retrievedBonus = restTemplate.getForEntity(uri, Bonus.class).getBody();
		} catch (RestClientException exc) {
			retrievedBonus = new Bonus(productId, null);
		}
		return retrievedBonus;
	}
}
