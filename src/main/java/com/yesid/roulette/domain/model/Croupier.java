package com.yesid.roulette.domain.model;

import java.util.UUID;

import com.yesid.roulette.domain.repository.RouletteRepository;
import com.yesid.roulette.domain.service.RouletteCreator;
import com.yesid.roulette.domain.service.RouletteOpener;

public class Croupier implements RouletteCreator, RouletteOpener {
	
	private RouletteRepository rouletteRepository;
	private static final String ROULETTE_NOT_YET_CREATED = "La ruleta aún no ha sido creada";
	private static final String ROULETTE_ALREADY_OPEN = "La ruleta ya ha sido abierta anteriormente";
	private static final String ROULETTE_CLOSED = "La ruleta ha sido cerrada anteriormente";

	public Croupier(RouletteRepository repository) {
		this.rouletteRepository = repository;
	}
	
	@Override
	public String createRoulette() {
		String id = generateRouletteId();
		Roulette roulette = new Roulette(id, RouletteStatus.CREATED);
		rouletteRepository.saveRoulette(roulette);
		return id;
	}
	
	@Override
	public String openRoulette(String rouletteId) {		
		Roulette roulette = rouletteRepository
				.findRoulette(rouletteId)
				.orElseThrow(() -> new RuntimeException(ROULETTE_NOT_YET_CREATED));
		validateRouletteStatus(roulette);
		roulette.setRouletteStatus(RouletteStatus.OPEN);
		rouletteRepository.updateRoulette(roulette);

		return roulette.getRouletteStatus().value;
	}

	private void validateRouletteStatus(Roulette roulette) {
		switch (roulette.getRouletteStatus()) {
		case OPEN:
			throw new RuntimeException(ROULETTE_ALREADY_OPEN);
		case CLOSED:
			throw new RuntimeException(ROULETTE_CLOSED);
		default:
			break;
		}
	}

	private String generateRouletteId() {
		return UUID.randomUUID().toString();
	}

}
