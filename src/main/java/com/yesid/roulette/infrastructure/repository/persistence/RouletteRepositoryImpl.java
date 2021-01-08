package com.yesid.roulette.infrastructure.repository.persistence;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.yesid.roulette.domain.model.BettingInformation;
import com.yesid.roulette.domain.model.Roulette;
import com.yesid.roulette.domain.repository.RouletteRepository;
import com.yesid.roulette.infrastructure.repository.util.BettingInformationUtil;
import com.yesid.roulette.infrastructure.repository.util.RouletteUtil;

import redis.clients.jedis.Jedis;

@Repository
public class RouletteRepositoryImpl implements RouletteRepository {
	
	private static final String ROULETTE_INITIAL_KEY = "roulette-";
	private Jedis redisClient;

	public RouletteRepositoryImpl(Jedis redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	public String saveRoulette(Roulette roulette) {
		String key = ROULETTE_INITIAL_KEY.concat(roulette.getId());
		String jsonRoulette = RouletteUtil.convertToJson(roulette);
		return redisClient.set(key.getBytes(), jsonRoulette.getBytes());
	}

	@Override
	public Optional<Roulette> findRoulette(String rouletteId) {
		String key = ROULETTE_INITIAL_KEY.concat(rouletteId);
		byte[] rouletteBytes = redisClient.get(key.getBytes());
		if(rouletteBytes == null || rouletteBytes.length == 0) {
			return Optional.empty();
		}
		Roulette roulette = RouletteUtil.convertToObject(new String(rouletteBytes));
		return Optional.of(roulette);
	}

	@Override
	public String updateRoulette(Roulette roulette) {
		return saveRoulette(roulette);
	}

	@Override
	public Long saveBet(BettingInformation bettingInformation) {
		String key = bettingInformation.getRouletteId().concat("bet");
		String jsonBettingInformation = BettingInformationUtil.convertToJson(bettingInformation);
		return redisClient.sadd(key.getBytes(), jsonBettingInformation.getBytes());
	}

	@Override
	public Set<BettingInformation> getAllBets(String rouletteId) {
		String key = rouletteId.concat("bet");
		Set<byte[]> bettingsInformationBytes = redisClient.smembers(key.getBytes());
		Set<BettingInformation> bettingsInformation = new HashSet<>();
		bettingsInformationBytes.forEach(bettingInformation -> 
			bettingsInformation.add(BettingInformationUtil.convertToObject(new String(bettingInformation)))
		);
		return bettingsInformation;
	}

	@Override
	public Set<Roulette> getAllRoulettes() {
		byte[] pattern = "roulette-*".getBytes();
		Set<byte[]> keys = redisClient.keys(pattern);
		Set<Roulette> roulettes = new HashSet<>();
		keys.forEach(key -> {
			String rouletteId = new String(key).substring(9);
			roulettes.add(findRoulette(rouletteId).get());
		});
		return roulettes;
	}

}
