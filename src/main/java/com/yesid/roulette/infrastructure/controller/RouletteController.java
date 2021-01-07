package com.yesid.roulette.infrastructure.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yesid.roulette.application.handler.BetRouletteHandler;
import com.yesid.roulette.application.handler.CreateRouletteHandler;
import com.yesid.roulette.application.handler.OpenRouletteHandler;
import com.yesid.roulette.domain.model.BettingInformation;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

	private CreateRouletteHandler createRouletteHandler;
	private OpenRouletteHandler openRouletteHandler;
	private BetRouletteHandler betRouletteHandler;

	public RouletteController(CreateRouletteHandler createRouletteHandler, 
			OpenRouletteHandler openRouletteHandler,
			BetRouletteHandler betRouletteHandler) {
		this.createRouletteHandler = createRouletteHandler;
		this.openRouletteHandler = openRouletteHandler;
		this.betRouletteHandler = betRouletteHandler;
	}
	
	@PostMapping
	public String createRoulette() {
		return createRouletteHandler.createRoulette();
	}
	
	@PostMapping("/{rouletteId}/open")
	public String openRoulette(@PathVariable String rouletteId) {
		return openRouletteHandler.openRoulette(rouletteId);
	}
	
	@PostMapping("/{rouletteId}/bet")
	public void bet(@PathVariable String rouletteId, 
			@RequestBody BettingInformation bettingInformation,
			@RequestHeader("userId") String userId) {
		betRouletteHandler.bet(bettingInformation, rouletteId, userId);
	}
}
