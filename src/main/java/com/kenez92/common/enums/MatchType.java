package com.kenez92.common.enums;

public enum MatchType {
    HOME_TEAM("1"), //1
    DRAW("0"),           //0
    AWAY_TEAM("2"); //2
    private final String type;

    MatchType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
