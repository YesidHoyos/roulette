package com.yesid.roulette.application.handler;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.yesid.roulette.domain.model.Roulette;
import com.yesid.roulette.domain.service.RouletteFinder;

@Component
public class FindRouletteHandler {

	private RouletteFinder rouletteFinder;

	public FindRouletteHandler(RouletteFinder rouletteFinder) {
		this.rouletteFinder = rouletteFinder;
	}
	
	public Set<Roulette> getAllRoulettes() {
		return rouletteFinder.getAllRoulettes();
	}
}
