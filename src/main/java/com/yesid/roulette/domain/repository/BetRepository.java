package com.yesid.roulette.domain.repository;

import java.util.Set;

import com.yesid.roulette.domain.model.BettingInformation;

public interface BetRepository {

	public Long saveBet(BettingInformation bettingInformation);
	public Set<BettingInformation> getAllBets(String rouletteId);
}
