package com.myretail.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.model.Bonus;
import com.myretail.util.exceptions.InvalidInputException;
import com.myretail.util.exceptions.NotFoundException;
import com.myretail.util.exceptions.RecordExistsException;

@Service
public class BonusService {

	@Autowired
	BonusRepository bonusRepository;

	public Bonus createBonus(Bonus bonus) {
		Long productId = bonus.getProductId();
		Optional<Bonus> existingBonus = bonusRepository.findBonusByProductId(productId);

		if (existingBonus.isPresent())
			throw new RecordExistsException("Bonus exists for " + productId);

		return bonusRepository.save(bonus);
	}

	@Transactional
	public void updateBonus(Long productId, Bonus updateBonus) {
		Optional<Bonus> existingBonus = bonusRepository.findBonusByProductId(productId);

		if(!updateBonus.getProductId().equals(productId))
			throw new InvalidInputException("ProductIds in payload and request path are different");

		if (!existingBonus.isPresent()) {
			throw new NotFoundException("Record exists for productId- " + productId);
		}
		
		
		bonusRepository.update(productId, updateBonus.getBonus());
	}

	public Bonus getBonus(Long productId) {
		Optional<Bonus> existingBonus = bonusRepository.findBonusByProductId(productId);
		if (!existingBonus.isPresent())
			throw new NotFoundException("Bonus does not exist for " + productId);

		return existingBonus.get();
	}

}
