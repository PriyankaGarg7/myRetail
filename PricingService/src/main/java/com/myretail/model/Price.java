package com.myretail.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Price {

	@GeneratedValue
	@JsonIgnore
	@Id
	private Long id;

	@JsonIgnore
	private Long productId;

	@JsonIgnore
	private BigDecimal originalPrice;

	@JsonIgnore
	private BigDecimal bonus;

	@Transient
	private BigDecimal value;

	private String currencyCode;

	public Price(Long productId, BigDecimal originalPrice, String currencyCode) {
		this.productId = productId;
		this.originalPrice = originalPrice;
		this.currencyCode = currencyCode;
		this.bonus = new BigDecimal("0.00");
	}

	public Price() {

	}

	public Long getId() {
		return this.id;
	}

	public Long getProductId() {
		return productId;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public BigDecimal getValue() {

		if (originalPrice != null)
			return originalPrice.add(bonus);
		else
			return value;

	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Price))
			return false;
		Price price = (Price) o;
		return Objects.equals(this.id, price.id) && Objects.equals(this.productId, price.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.productId);
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", productId=" + productId + ", originalPrice=" + originalPrice + ", bonus=" + bonus
				+ ", value=" + value + ", currencyCode=" + currencyCode + "]";
	}
	
}
