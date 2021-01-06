package com.yesid.roulette.infrastructure.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yesid.roulette.application.handler.CreateRouletteHandler;
import com.yesid.roulette.application.handler.OpenRouletteHandler;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

	private CreateRouletteHandler createRouletteHandler;
	private OpenRouletteHandler openRouletteHandler;

	public RouletteController(CreateRouletteHandler createRouletteHandler, 
			OpenRouletteHandler openRouletteHandler) {
		this.createRouletteHandler = createRouletteHandler;
		this.openRouletteHandler = openRouletteHandler;
	}
	
	@PostMapping
	public String createRoulette() {
		return createRouletteHandler.createRoulette();
	}
	
	@PostMapping("/{rouletteId}/open")
	public String openRoulette(@PathVariable String rouletteId) {
		return openRouletteHandler.openRoulette(rouletteId);
	}
}
