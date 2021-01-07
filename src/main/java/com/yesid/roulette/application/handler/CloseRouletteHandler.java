package com.yesid.roulette.application.handler;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.yesid.roulette.domain.model.BettingInformation;
import com.yesid.roulette.domain.service.RouletteCloser;

@Component
public class CloseRouletteHandler {

	private RouletteCloser rouletteCloser;

	public CloseRouletteHandler(RouletteCloser rouletteCloser) {
		this.rouletteCloser = rouletteCloser;
	}
	
	public Set<BettingInformation> closeRoulette(String rouletteId){
		return rouletteCloser.closeRoulette(rouletteId);
	}

}
