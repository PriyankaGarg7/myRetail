package com.myretail.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Bonus {

	@GeneratedValue
	@Id
	@JsonIgnore
	Long Id;

	@NotNull
	Long productId;

	@NotNull
	BigDecimal bonus;

	public Bonus() {
		
	}
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
