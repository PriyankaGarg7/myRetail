package com.myretail.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import nonapi.io.github.classgraph.json.Id;

public class Bonus {

	@JsonIgnore
	@Id
	Long Id;

	Long productId;

	@JsonProperty("bonus")
	BigDecimal bonus;

	public Bonus(Long productId, BigDecimal bonus) {
		this.productId = productId;
		this.bonus = bonus;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

}
