package com.yesid.roulette.infrastructure.repository.persistence;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yesid.roulette.domain.model.Roulette;

public class RouletteUtil {

	public static String convertToJson(Roulette roulette) {
		ObjectMapper objectMapper = new ObjectMapper();		 
        try {
            return objectMapper.writeValueAsString(roulette);
        } catch (IOException e) {
            throw new RuntimeException("Error convirtiendo el objeto Roulette a json");
        }
	}
}
