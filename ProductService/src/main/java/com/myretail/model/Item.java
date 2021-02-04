package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	
	private Long tcin;
	private ProductDescription product_description;

	public Item(Long tcin, ProductDescription product_description) {
		this.tcin = tcin;
		this.product_description = product_description;
	}

	public Item() {
	}

	public Long getTcin() {
		return tcin;
	}

	public void setTcin(Long tcin) {
		this.tcin = tcin;
	}

	public ProductDescription getProduct_description() {
		return product_description;
	}

	public void setProduct_description(ProductDescription product_description) {
		this.product_description = product_description;
	}

}
