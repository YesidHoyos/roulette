package com.yesid.roulette.domain.service;

import java.util.Set;

import com.yesid.roulette.domain.model.BettingInformation;

public interface RouletteCloser {

	public Set<BettingInformation> closeRoulette(String rouletteId);
}
