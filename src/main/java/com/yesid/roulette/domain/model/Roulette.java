package com.yesid.roulette.domain.model;

import java.io.Serializable;

public class Roulette implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private RouletteStatus rouletteStatus;

	public Roulette(String id, RouletteStatus rouletteStatus) {
		this.id = id;
		this.rouletteStatus = rouletteStatus;
	}
	
	public Roulette() {};

	public String getId() {
		return id;
	}	

	public void setId(String id) {
		this.id = id;
	}

	public RouletteStatus getRouletteStatus() {
		return rouletteStatus;
	}
	
	public void setRouletteStatus(RouletteStatus rouletteStatus) {
		this.rouletteStatus = rouletteStatus;
	}

}
