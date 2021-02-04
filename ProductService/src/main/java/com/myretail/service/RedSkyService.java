package com.myretail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.exception.MyRetailProductResponseErrorHandler;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Item;
import com.myretail.model.Product;
import com.myretail.model.ProductDescription;
import com.myretail.model.RedSkyProduct;

@Service
public class RedSkyService {

	private Logger logger = LoggerFactory.getLogger(RedSkyService.class);
	
	@Autowired
	RestTemplate restTemplate;

	@Value("${api.base.url:https://redsky.target.com/v3/pdp/tcin/}")
	private String baseUrl;

	@Value("${api.key:?key=ff457966e64d5e877fdbad070f276d18ecec4a01&}")
	private String redSkyKey;

	@Value("${api.excludes:}")
	private String excludes;
	
	// @HystrixCommand(fallbackMethod = "getfalbackProduct")
	public RedSkyProduct getProduct(Long id) {

		logger.info("RedSky Product id: " + id);

		String uri = baseUrl + id + redSkyKey + excludes;

		ResponseEntity<RedSkyProduct> redSkyProduct = null;
		try {
			redSkyProduct = restTemplate.getForEntity(uri, RedSkyProduct.class);
		} catch (RestClientException exc) {
			throw new ProductNotFoundException("Product " + id + " is not available ");
		}	
		
		if(redSkyProduct.getStatusCode().equals(HttpStatus.NOT_FOUND))
			throw new ProductNotFoundException("Product " + id + " is not available ");
		
		return redSkyProduct.getBody();
	}

	public RedSkyProduct getfalbackProduct(Long id) {

		logger.info("RedSky Product id: " + id);

		RedSkyProduct redSkyProduct = new RedSkyProduct();
		redSkyProduct.setProduct(new Product(new Item(id, new ProductDescription("dummy product"))));

		return redSkyProduct;
	}
}
