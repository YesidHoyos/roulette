package com.yesid.roulette.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yesid.roulette.domain.model.Croupier;
import com.yesid.roulette.domain.repository.RouletteRepository;

import redis.clients.jedis.Jedis;

@Configuration
public class BeanFactory {

	@Autowired
	private RouletteRepository rouletteRepository;
	
	@Bean
	public Jedis redisBean(@Value("${yesid.redis.server.ip}") String redisServer, @Value("${yesid.redis.server.port}") int redisPort) {
		return new Jedis(redisServer, redisPort);
	}
	
	@Bean
	public Croupier croupierBean() {
		return new Croupier(rouletteRepository);
	}
}
