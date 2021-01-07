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
	
	private Jedis redisClient;

	public RouletteRepositoryImpl(Jedis redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	public String saveRoulette(Roulette roulette) {
		String jsonRoulette = RouletteUtil.convertToJson(roulette);
		return redisClient.set(roulette.getId().getBytes(), jsonRoulette.getBytes());
	}

	@Override
	public Optional<Roulette> findRoulette(String rouletteId) {
		byte[] rouletteBytes = redisClient.get(rouletteId.getBytes());
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

}
