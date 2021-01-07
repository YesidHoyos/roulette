package com.yesid.roulette.domain.model;

import java.io.Serializable;

public class BettingInformation implements Serializable {

	private static final long serialVersionUID = 6828764498619937240L;
	private String value;
	private double amount;
	private String userId;
	private String rouletteId;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRouletteId() {
		return rouletteId;
	}
	public void setRouletteId(String rouletteId) {
		this.rouletteId = rouletteId;
	}	
	
}
