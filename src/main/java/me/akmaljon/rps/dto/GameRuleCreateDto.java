package me.akmaljon.rps.dto;

public class GameRuleCreateDto {
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
