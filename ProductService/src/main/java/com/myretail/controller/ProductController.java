package com.myretail.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myretail.model.MyRetailProduct;
import com.myretail.model.Price;
import com.myretail.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public MyRetailProduct getProductById(@PathVariable("id") Long productId) {		
		logger.info(String.format("Product id is %s", productId));
		return productService.getProduct(productId);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Object> createPriceForProduct(@PathVariable("id") Long productId, @RequestBody MyRetailProduct productPrice) {
		productService.createProductPrice(productId, productPrice);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public void updatePriceForProduct(@PathVariable("id") Long productId, @RequestBody MyRetailProduct newProductPrice) {
		productService.updateProductPrice(productId, newProductPrice);
	}
}
