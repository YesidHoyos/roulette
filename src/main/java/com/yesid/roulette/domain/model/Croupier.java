package com.yesid.roulette.domain.model;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.yesid.roulette.domain.repository.RouletteRepository;
import com.yesid.roulette.domain.service.RouletteBettor;
import com.yesid.roulette.domain.service.RouletteCloser;
import com.yesid.roulette.domain.service.RouletteCreator;
import com.yesid.roulette.domain.service.RouletteOpener;

public class Croupier implements RouletteCreator, RouletteOpener, RouletteBettor, RouletteCloser {
	
	private static final String AMOUNT_INVALID = "El monto apostado no es válido";
	private static final String COLOR_RED = "red";
	private static final String COLOR_BLACK = "black";
	private static final String INVALID_NUMBER = "El número apostado no es válido";
	private static final String INVALID_COLOR = "El color apostado no es válido";
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
		Roulette roulette = getRoulette(rouletteId);
		validateRouletteToOpen(roulette);
		roulette.setRouletteStatus(RouletteStatus.OPEN);
		rouletteRepository.updateRoulette(roulette);

		return roulette.getRouletteStatus().value;
	}
	
	@Override
	public void bet(BettingInformation bettingInformation) {
		validateValue(bettingInformation.getValue());
		validateAmount(bettingInformation.getBetAmount());
		validateRouletteOpen(bettingInformation.getRouletteId());		
		rouletteRepository.saveBet(bettingInformation);
	}
	
	@Override
	public Set<BettingInformation> closeRoulette(String rouletteId) {
		Roulette roulette = getRoulette(rouletteId);
		validateRouletteToClose(roulette.getRouletteStatus());
		roulette.setRouletteStatus(RouletteStatus.CLOSED);
		rouletteRepository.updateRoulette(roulette);
		int winningNumber = getWinningNumber();
		return payBets(rouletteId, winningNumber);
	}
	
	private void validateRouletteToClose(RouletteStatus rouletteStatus) {
		switch (rouletteStatus) {
		case CREATED:
			throw new RuntimeException(ROULETTE_NOT_OPEN);
		case CLOSED:
			throw new RuntimeException(ROULETTE_CLOSED);
		default:
			break;
		}
	}

	private Set<BettingInformation> payBets(String rouletteId, int winningNumber) {
		String winningColor = evenNumber(winningNumber) ? COLOR_RED : COLOR_BLACK;
		Set<BettingInformation> bettingsInformation = rouletteRepository.getAllBets(rouletteId);
		bettingsInformation.forEach(bettingInformation -> {
			bettingInformation.setWinningNumber(winningNumber);
			String value = bettingInformation.getValue();			
			if(value.equals(String.valueOf(winningNumber))) {
				bettingInformation.setAmountEarned(bettingInformation.getBetAmount() * 5d);
			} else if (value.equals(winningColor)) {
				bettingInformation.setAmountEarned(bettingInformation.getBetAmount() * 1.8d);
			} else {
				bettingInformation.setAmountEarned(0);
			}
		});
		return bettingsInformation;
	}

	private boolean evenNumber(int winningNumber) {
		return winningNumber % 2 == 0;
	}

	private int getWinningNumber() {
	    return new Random().ints(0, 37).findFirst().getAsInt();
	}

	private Roulette getRoulette(String rouletteId) {
		return rouletteRepository
				.findRoulette(rouletteId)
				.orElseThrow(() -> new RuntimeException(ROULETTE_NOT_YET_CREATED));
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
		case COLOR_BLACK:			
			break;
		case COLOR_RED:			
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

	private void validateRouletteOpen(String rouletteId) {
		Roulette roulette = getRoulette(rouletteId);
		switch (roulette.getRouletteStatus()) {
		case CREATED:
			throw new RuntimeException(ROULETTE_NOT_OPEN);
		case CLOSED:
			throw new RuntimeException(ROULETTE_CLOSED);
		default:
			break;
		}
	}

	private void validateAmount(double amount) {
		if(amount > MAXIMUM_AMOUNT) throw new RuntimeException(MAXIMUM_AMOUNT_EXCEEDED);
		if(amount <= 0) throw new RuntimeException(AMOUNT_INVALID);
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
