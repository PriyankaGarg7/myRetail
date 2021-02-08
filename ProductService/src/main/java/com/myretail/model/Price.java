package com.myretail.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Price {

	@JsonIgnore
	private Long id;

	@JsonIgnore
	private Long productId;

	@JsonIgnore
	private BigDecimal originalPrice;

	private String currencyCode;


	public Price(Long productId, BigDecimal originalPrice, String currencyCode) {
		this.productId = productId;
		this.originalPrice = originalPrice;
		this.currencyCode = currencyCode;
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

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
