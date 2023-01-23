package me.akmaljon.rps.dto;

import java.io.Serializable;

public class GameRuleCreateDto implements Serializable {
    private String firstChoice;
    private String secondChoice;
    private String winner;

    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public String getWinner() {
        return winner;
    }
}
