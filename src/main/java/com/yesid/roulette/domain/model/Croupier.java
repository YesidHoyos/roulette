package com.yesid.roulette.domain.model;

import java.util.UUID;

import com.yesid.roulette.domain.repository.RouletteRepository;
import com.yesid.roulette.domain.service.RouletteCreator;

public class Croupier implements RouletteCreator {
	
	private RouletteRepository repository;

	public Croupier(RouletteRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public String createRoulette() {
		String id = generateRouletteId();
		Roulette roulette = new Roulette(id, RouletteStatus.CREATED);
		repository.saveRoulette(roulette);
		return id;
	}

	private String generateRouletteId() {
		return UUID.randomUUID().toString();
	}

}
