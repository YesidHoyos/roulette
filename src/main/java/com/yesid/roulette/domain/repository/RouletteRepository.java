package com.yesid.roulette.domain.repository;

import java.util.Optional;

import com.yesid.roulette.domain.model.Roulette;

public interface RouletteRepository {

	public String saveRoulette(Roulette roulette);
	public Optional<Roulette> findRoulette(String rouletteId);
	public String updateRoulette(Roulette roulette);
}
