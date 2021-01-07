package com.yesid.roulette.domain.model;

import java.util.UUID;

import com.yesid.roulette.domain.repository.RouletteRepository;
import com.yesid.roulette.domain.service.RouletteBettor;
import com.yesid.roulette.domain.service.RouletteCreator;
import com.yesid.roulette.domain.service.RouletteOpener;

public class Croupier implements RouletteCreator, RouletteOpener, RouletteBettor {
	
	private static final String INVALID_NUMBER = "El número apostado no es valido";
	private static final String INVALID_COLOR = "El color apostado no es valido";
	private static final String MAXIMUM_AMOUNT_EXCEEDED = "El monto apostado supera el máximo permitido";
	private static final double MAXIMUM_AMOUNT = 10000d;
	private static final String ROULETTE_NOT_YET_CREATED = "La ruleta aún no ha sido creada";
	private static final String ROULETTE_ALREADY_OPEN = "La ruleta ya ha sido abierta anteriormente";
	private static final String ROULETTE_CLOSED = "La ruleta ya ha sido cerrada";
	private static final String ROULETTE_NOT_OPEN = "La ruleta aún no ha sido abierta";
	private RouletteRepository rouletteRepository;

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
		validateRouletteToOpen(roulette);
		roulette.setRouletteStatus(RouletteStatus.OPEN);
		rouletteRepository.updateRoulette(roulette);

		return roulette.getRouletteStatus().value;
	}
	
	@Override
	public void bet(BettingInformation bettingInformation) {
		validateValue(bettingInformation.getValue());
		validateMaximumAmount(bettingInformation.getAmount());
		validateOpenRoulette(bettingInformation.getRouletteId());		
		rouletteRepository.saveBet(bettingInformation);
	}

	private void validateValue(String value) {
		if(isNumeric(value)) {
			validateNumber(value);
		} else {
			validateColor(value);
		}
	}
	
	private void validateColor(String value) {
		switch (value) {
		case "black":			
			break;
		case "red":			
			break;
		default:
			throw new RuntimeException(INVALID_COLOR);
		}
	}

	private void validateNumber(String value) {
		int number;
		try {
	        number = Integer.parseInt(value);	        
	    } catch (NumberFormatException e) {
	        throw new RuntimeException(INVALID_NUMBER);
	    }
		if(number < 0 || number > 36) {
			throw new RuntimeException(INVALID_NUMBER);
		}
	}

	public boolean isNumeric(String value) {
	    if (value == null) return false;
	    try {
	        Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}

	private void validateOpenRoulette(String rouletteId) {
		Roulette roulette = rouletteRepository
				.findRoulette(rouletteId)
				.orElseThrow(() -> new RuntimeException(ROULETTE_NOT_YET_CREATED));
		switch (roulette.getRouletteStatus()) {
		case CREATED:
			throw new RuntimeException(ROULETTE_NOT_OPEN);
		case CLOSED:
			System.out.println("Erro cerrada");
			throw new RuntimeException(ROULETTE_CLOSED);
		default:
			break;
		}
	}

	private void validateMaximumAmount(double amount) {
		if(amount > MAXIMUM_AMOUNT) {
			throw new RuntimeException(MAXIMUM_AMOUNT_EXCEEDED);
		}
	}

	private void validateRouletteToOpen(Roulette roulette) {
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
