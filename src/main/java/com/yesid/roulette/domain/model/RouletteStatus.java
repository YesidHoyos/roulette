package com.yesid.roulette.domain.model;

public enum RouletteStatus {
	CREATED("created"),
    OPEN("open"),
    CLOSED("closed");
	
    public final String value;

    private RouletteStatus(String value) {
        this.value = value;
    }
}
