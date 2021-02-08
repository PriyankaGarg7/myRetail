package com.myretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
