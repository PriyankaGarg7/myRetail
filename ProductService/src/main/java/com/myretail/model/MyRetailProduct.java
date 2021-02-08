package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyRetailProduct {
	
	Long Id;
	
	String name;
	
	@JsonProperty("current_price")
	CurentPrice currentPrice;
	
	

	public MyRetailProduct() {
		
	}

	public MyRetailProduct(Long id, String name) {
		Id = id;
		this.name = name;
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
	
	
	public CurentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "{Id=" + Id + ", name=" + name + ", currentPrice=" + currentPrice + "]";
	}	

}
