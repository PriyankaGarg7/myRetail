package com.myretail.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.model.Price;
import com.myretail.repository.PricingRepository;

@Service
public class PricingService {

	@Autowired
	PricingRepository priceRepository;

	public Price getPrice(Long productId) {

		Optional<Price> resultPrice = priceRepository.findPriceByProductId(productId);
		Price originalPrice = null;

		if (!resultPrice.isPresent())
			originalPrice = new Price(productId, BigDecimal.valueOf(10.00), "USD");
		else
			originalPrice = resultPrice.get();

		return originalPrice;

	}
}
