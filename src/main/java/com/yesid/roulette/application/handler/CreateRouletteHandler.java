package com.yesid.roulette.application.handler;

import org.springframework.stereotype.Component;

import com.yesid.roulette.domain.service.RouletteCreator;

@Component
public class CreateRouletteHandler {

	private RouletteCreator rouletteCreator;

	public CreateRouletteHandler(RouletteCreator rouletteCreator) {
		this.rouletteCreator = rouletteCreator;
	}
	
	public String createRoulette() {
		return rouletteCreator.createRoulette();
	}
}
