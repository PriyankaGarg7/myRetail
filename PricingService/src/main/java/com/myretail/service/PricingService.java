package com.myretail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.exception.PriceExistsException;
import com.myretail.exception.PriceNotFoundException;
import com.myretail.model.Price;
import com.myretail.repository.PricingRepository;

@Service
public class PricingService {

	@Autowired
	PricingRepository priceRepository;

	public Price getPrice(Long productId) {

		Optional<Price> price = priceRepository.findPriceByProductId(productId);

		if (!price.isPresent())
			throw new PriceNotFoundException("Could not find the price for product " + productId);

		return price.get();
	}

	public void createPrice(Long productId, Price price) {

		Optional<Price> existingPrice = priceRepository.findPriceByProductId(productId);

		if (existingPrice.isPresent())
			throw new PriceExistsException("Price Already exists for " + productId);

		Price createPrice = new Price(productId, price.getValue(), price.getCurrencyCode());

		priceRepository.save(createPrice);
		
	}

	public Price updatePrice(Long productId, Price price) {

		Optional<Price> existingPrice = priceRepository.findPriceByProductId(productId);

		if (!existingPrice.isPresent()) {
			throw new PriceNotFoundException("productId- " +productId);
		}

		existingPrice.get().setBonus(price.getValue());

		return priceRepository.save(existingPrice.get());
	}

}
