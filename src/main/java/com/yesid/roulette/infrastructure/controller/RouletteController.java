package com.yesid.roulette.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yesid.roulette.application.handler.CreateRouletteHandler;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

	private CreateRouletteHandler createRouletteHandler;

	public RouletteController(CreateRouletteHandler createRouletteHandler) {
		this.createRouletteHandler = createRouletteHandler;
	}
	
	@PostMapping
	public String createRoulette() {
		return createRouletteHandler.createRoulette();
	}
	
}
