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

	public String getId() {
		return id;
	}

	public RouletteStatus getRouletteStatus() {
		return rouletteStatus;
	}

}
