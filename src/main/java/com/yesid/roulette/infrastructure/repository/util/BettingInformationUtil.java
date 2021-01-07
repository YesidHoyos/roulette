package com.yesid.roulette.infrastructure.repository.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yesid.roulette.domain.model.BettingInformation;

public class BettingInformationUtil {

	private static final String CONVERT_TO_BETTING_INFORMATION_ERROR = "Error convirtiendo json a objeto BettingInformation";
	private static final String CONVERT_TO_JSON_ERROR = "Error convirtiendo el objeto BettingInformation a json";

	public static String convertToJson(BettingInformation bettingInformation) {
		ObjectMapper objectMapper = new ObjectMapper();		 
        try {
            return objectMapper.writeValueAsString(bettingInformation);
        } catch (IOException e) {
            throw new RuntimeException(CONVERT_TO_JSON_ERROR);
        }
	}

	public static BettingInformation convertToObject(String jsonBettingInformation) {
		ObjectMapper objectMapper = new ObjectMapper();		 
        try {
            return objectMapper.readValue(jsonBettingInformation, BettingInformation.class);
        } catch (IOException e) {
            throw new RuntimeException(CONVERT_TO_BETTING_INFORMATION_ERROR);
        }
	}
}
