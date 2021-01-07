package com.yesid.roulette.application.handler;

import org.springframework.stereotype.Component;

import com.yesid.roulette.domain.model.BettingInformation;
import com.yesid.roulette.domain.service.RouletteBettor;

@Component
public class BetRouletteHandler {

	private RouletteBettor rouletteBettor;

	public BetRouletteHandler(RouletteBettor rouletteBettor) {
		this.rouletteBettor = rouletteBettor;
	}
	
	public void bet(BettingInformation bettingInformation, String rouletteId, String userId) {
		bettingInformation.setRouletteId(rouletteId);
		bettingInformation.setUserId(userId);
		rouletteBettor.bet(bettingInformation);
	}
	
}
