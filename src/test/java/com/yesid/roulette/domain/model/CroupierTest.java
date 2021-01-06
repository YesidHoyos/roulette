package com.yesid.roulette.domain.model;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.yesid.roulette.domain.repository.RouletteRepository;

@SpringBootTest
class CroupierTest {
	
	private static final String ROULETTE_NOT_YET_CREATED = "La ruleta aún no ha sido creada";
	private static final String ROULETTE_ALREADY_OPEN = "La ruleta ya ha sido abierta anteriormente";
	private static final String ROULETTE_CLOSED = "La ruleta ha sido cerrada anteriormente";

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
	
	@Test
	void openRouletteSuccess() {
		Roulette roulette = new Roulette("123ABC", RouletteStatus.CREATED);
		String statusCode = "OK";		
		when(rouletteRepository.findRoulette(Mockito.anyString())).thenReturn(Optional.of(roulette));
		when(rouletteRepository.updateRoulette(Mockito.any())).thenReturn(statusCode);
		String rouletteStatus = croupier.openRoulette(roulette.getId());
		Assertions.assertEquals(RouletteStatus.OPEN.value, rouletteStatus);
	}
	
	@Test
	void openRouletteNotCreated() {
		String rouletteId = "123ABC";
		String statusCode = "OK";
		when(rouletteRepository.findRoulette(Mockito.anyString())).thenReturn(Optional.empty());
		when(rouletteRepository.updateRoulette(Mockito.any())).thenReturn(statusCode);
		try {
			croupier.openRoulette(rouletteId);
			fail();
		} catch (Exception e) {
			Assertions.assertEquals(ROULETTE_NOT_YET_CREATED, e.getMessage());
		}
	}
	
	@Test
	void openRouletteAlreadyOpen() {
		Roulette roulette = new Roulette("123ABC", RouletteStatus.OPEN);
		String statusCode = "OK";		
		when(rouletteRepository.findRoulette(Mockito.anyString())).thenReturn(Optional.of(roulette));
		when(rouletteRepository.updateRoulette(Mockito.any())).thenReturn(statusCode);
		try {
			croupier.openRoulette(roulette.getId());
			fail();
		} catch (Exception e) {
			Assertions.assertEquals(ROULETTE_ALREADY_OPEN, e.getMessage());
		}
	}
	
	@Test
	void openRouletteClosed() {
		Roulette roulette = new Roulette("123ABC", RouletteStatus.CLOSED);
		String statusCode = "OK";
		when(rouletteRepository.findRoulette(Mockito.anyString())).thenReturn(Optional.of(roulette));
		when(rouletteRepository.updateRoulette(Mockito.any())).thenReturn(statusCode);
		try {
			croupier.openRoulette(roulette.getId());
			fail();
		} catch (Exception e) {
			Assertions.assertEquals(ROULETTE_CLOSED, e.getMessage());
		}
	}

}
