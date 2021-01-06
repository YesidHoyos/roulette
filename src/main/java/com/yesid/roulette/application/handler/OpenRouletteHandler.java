package com.yesid.roulette.application.handler;

import org.springframework.stereotype.Component;

import com.yesid.roulette.domain.service.RouletteOpener;

@Component
public class OpenRouletteHandler {

	private RouletteOpener rouletteOpener;

	public OpenRouletteHandler(RouletteOpener rouletteOpener) {
		this.rouletteOpener = rouletteOpener;
	}
	
	public String openRoulette(String rouletteId) {
		return rouletteOpener.openRoulette(rouletteId);
	}
	
}
