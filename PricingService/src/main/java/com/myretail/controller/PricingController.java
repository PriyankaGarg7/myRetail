package com.myretail.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myretail.model.Price;
import com.myretail.service.PricingService;

@Validated
@RestController
@RequestMapping("/product/")
public class PricingController {

	@Autowired
	private PricingService pricingService;

	@GetMapping("{productId}/price")
	public Price getPrice(@PathVariable("productId") Long productId) {
		return pricingService.getPrice(productId);
	}

	@PostMapping("{productId}/price")
	public ResponseEntity<Object> createPrice(@PathVariable("productId") Long productId, @RequestBody Price newPrice) {

		pricingService.createPrice(productId, newPrice);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("{productId}/price")
	public Price savePrice(@PathVariable("productId") Long productId, @RequestBody Price updatedPrice) {
		return pricingService.updatePrice(productId, updatedPrice);
	}

}
