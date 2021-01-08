package com.yesid.roulette.infrastructure.repository.persistence;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.yesid.roulette.domain.model.BettingInformation;
import com.yesid.roulette.domain.repository.BetRepository;
import com.yesid.roulette.infrastructure.repository.util.BettingInformationUtil;

import redis.clients.jedis.Jedis;

@Repository
public class BetRepositoryImpl implements BetRepository {
	
	private Jedis redisClient;

	public BetRepositoryImpl(Jedis redisClient) {
		this.redisClient = redisClient;
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
