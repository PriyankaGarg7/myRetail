package com.myretail.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.myretail.model.Bonus;
import com.myretail.service.BonusService;

@RestController
@RequestMapping("/bonus")
public class BonusController {

	@Autowired
	BonusService bonusService;

	@PostMapping
	public ResponseEntity<Object> addBonus(@RequestBody Bonus bonus) {

		Bonus createdBonus = bonusService.createBonus(bonus);

		URI location = UriComponentsBuilder.fromUriString("/bonus/").buildAndExpand().toUri();

		return ResponseEntity.created(location).body(createdBonus);
	}

	@PutMapping("/{productId}")
	public void updateBonus(@PathVariable("productId") Long productId, @Valid @RequestBody Bonus updateBonus) {
		bonusService.updateBonus(productId, updateBonus);

	}

	@GetMapping("/{productId}")
	public Bonus getBonus(@PathVariable("productId") Long productId) {
		return bonusService.getBonus(productId);
	}

}
