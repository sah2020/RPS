package me.akmaljon.rps.dto;

import java.io.Serializable;

public class GameResultDto implements Serializable {
    private String playersChoice;
    private String opponentsChoice;
    private String result;

    public GameResultDto() {
    }

    public GameResultDto(String playersChoice, String opponentsChoice, String result) {
        this.playersChoice = playersChoice;
        this.opponentsChoice = opponentsChoice;
        this.result = result;
    }

    public void setPlayersChoice(String playersChoice) {
        this.playersChoice = playersChoice;
    }

    public void setOpponentsChoice(String opponentsChoice) {
        this.opponentsChoice = opponentsChoice;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPlayersChoice() {
        return playersChoice;
    }

    public String getOpponentsChoice() {
        return opponentsChoice;
    }

    public String getResult() {
        return result;
    }
}
