package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyRetailProduct {
	
	Long Id;
	String name;
	@JsonProperty("current_price")
	Price currentPrice;

	public MyRetailProduct() {
		super();
	}

	public MyRetailProduct(Long id, String name, Price price) {
		super();
		Id = id;
		this.name = name;
		this.currentPrice = price;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Price getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Price currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "{Id=" + Id + ", name=" + name + ", currentPrice=" + currentPrice + "]";
	}
	

}
