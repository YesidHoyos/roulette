package com.yesid.roulette.infrastructure.repository.persistence;

import org.springframework.stereotype.Repository;

import com.yesid.roulette.domain.model.Roulette;
import com.yesid.roulette.domain.repository.RouletteRepository;

import redis.clients.jedis.Jedis;

@Repository
public class RouletteRepositoryImpl implements RouletteRepository {
	
	private Jedis redisClient;

	public RouletteRepositoryImpl(Jedis redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	public String saveRoulette(Roulette roulette) {
		String jsonRoulette = RouletteUtil.convertToJson(roulette);
		return redisClient.set(roulette.getId().getBytes(), jsonRoulette.getBytes());
	}

}
