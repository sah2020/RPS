package me.akmaljon.rps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstChoice;
    private String secondChoice;
    private Boolean isFirstChoiceWinner;

    public GameRule(String firstChoice, String secondChoice, Boolean isFirstChoiceWinner) {
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.isFirstChoiceWinner = isFirstChoiceWinner;
    }

    public GameRule() {

    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }

    public Boolean getFirstChoiceWinner() {
        return isFirstChoiceWinner;
    }

    public void setFirstChoiceWinner(Boolean firstChoiceWinner) {
        isFirstChoiceWinner = firstChoiceWinner;
    }
}
