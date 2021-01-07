package com.yesid.roulette.domain.model;

import java.io.Serializable;

public class BettingInformation implements Serializable {

	private static final long serialVersionUID = 6828764498619937240L;
	private String value;
	private double betAmount;
	private double amountEarned;
	private String userId;
	private String rouletteId;
	private int winningNumber;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public double getBetAmount() {
		return betAmount;
	}
	public void setBetAmount(double amount) {
		this.betAmount = amount;
	}
	public double getAmountEarned() {
		return amountEarned;
	}
	public void setAmountEarned(double amountEarned) {
		this.amountEarned = amountEarned;
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
	public int getWinningNumber() {
		return winningNumber;
	}
	public void setWinningNumber(int winningNumber) {
		this.winningNumber = winningNumber;
	}	
	
}
