package com.yesid.roulette.infrastructure.repository.persistence;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yesid.roulette.domain.model.Roulette;

public class RouletteUtil {

	private static final String CONVERT_TO_ROULETTE_ERROR = "Error convirtiendo json a objeto Roulette";
	private static final String CONVERT_TO_JSON_ERROR = "Error convirtiendo el objeto Roulette a json";

	public static String convertToJson(Roulette roulette) {
		ObjectMapper objectMapper = new ObjectMapper();		 
        try {
            return objectMapper.writeValueAsString(roulette);
        } catch (IOException e) {
            throw new RuntimeException(CONVERT_TO_JSON_ERROR);
        }
	}

	public static Roulette convertToObject(String jsonRoulette) {
		ObjectMapper objectMapper = new ObjectMapper();		 
        try {
            return objectMapper.readValue(jsonRoulette, Roulette.class);
        } catch (IOException e) {
            throw new RuntimeException(CONVERT_TO_ROULETTE_ERROR);
        }
	}
}
