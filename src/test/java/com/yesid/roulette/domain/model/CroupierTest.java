package com.yesid.roulette.domain.model;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.yesid.roulette.domain.repository.RouletteRepository;

@SpringBootTest
class CroupierTest {
	
	@Mock
	RouletteRepository rouletteRepository;
	
	@InjectMocks
	Croupier croupier;
	
	@Test
	void createRouletteSuccess() {
		Roulette roulette = new Roulette("123ABC", RouletteStatus.CREATED);
		String statusCode = "OK";
		when(rouletteRepository.saveRoulette(roulette)).thenReturn(statusCode);
		String rouletteId = croupier.createRoulette();
		Assertions.assertNotNull(rouletteId);
	}

}
