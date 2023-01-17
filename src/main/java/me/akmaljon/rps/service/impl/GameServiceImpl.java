package me.akmaljon.rps.service.impl;

import me.akmaljon.rps.dto.GameResultDto;
import me.akmaljon.rps.dto.GameRuleCreateDto;
import me.akmaljon.rps.dto.ResponseDto;
import me.akmaljon.rps.entity.GameRule;
import me.akmaljon.rps.exception.BadRequestException;
import me.akmaljon.rps.exception.ConflictException;
import me.akmaljon.rps.repository.GameRuleRepository;
import me.akmaljon.rps.service.GameService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRuleRepository gameRuleRepository;
    private final RestTemplate restTemplate;

    public GameServiceImpl(GameRuleRepository gameRuleRepository, RestTemplate restTemplate) {
        this.gameRuleRepository = gameRuleRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseDto throwFromMockServer() {
        //send request to third-party API to get their choice
        return restTemplate.getForObject(
                "https://private-anon-6d63673351-curbrockpaperscissors.apiary-mock.com/rps-stage/throw",
                ResponseDto.class);
    }

    @Override
    public ResponseDto throwRandom() {
        String[] choices = {"rock", "paper", "scissors"};

        //generating random number from 0 to 2 included
        Random random = new Random();
        int choice = random.nextInt(3);

        return new ResponseDto(choices[choice]);
    }

    @Override
    public ResponseDto createRule(GameRuleCreateDto gameRuleCreateDto) {
        //validating incoming dto
        if (gameRuleCreateDto.getFirstChoice() == null || gameRuleCreateDto.getSecondChoice() == null || gameRuleCreateDto.getWinner() == null) {
            throw new BadRequestException("Invalid Request");
        }

        String firstChoice = gameRuleCreateDto.getFirstChoice();
        String secondChoice = gameRuleCreateDto.getSecondChoice();
        String winner = gameRuleCreateDto.getWinner();

        //validating winner if it matches either of choices
        if (!(winner.equalsIgnoreCase(firstChoice) || winner.equalsIgnoreCase(secondChoice))) {
            throw new BadRequestException("Winner name is incorrect");
        }

        //sorting choices alphabetically to keep consistency and avoid from duplicate rules
        String[] choices = {firstChoice, secondChoice};
        Arrays.sort(choices);

        boolean isFirstWinner = winner.equalsIgnoreCase(choices[0]);

        //saving to database
        GameRule gameRule = new GameRule(choices[0], choices[1], isFirstWinner);
        try {
            gameRuleRepository.save(gameRule);
        } catch (Exception exception) {
            throw new ConflictException("There is a problem with data sent to database. This data may be against database constraints");
        }
        return new ResponseDto("Saved");
    }

    @Override
    public ResponseDto getRules() {
        //retrieving data from database
        List<GameRule> gameRules = gameRuleRepository.findAll();

        List<String> rulesText = gameRules.stream().map(rule ->
                        rule.getFirstChoiceWinner() ?
                                rule.getId().toString() + ". " + rule.getFirstChoice() + " beats " + rule.getSecondChoice() :
                                rule.getId().toString() + ". " + rule.getSecondChoice() + " beats " + rule.getFirstChoice())
                .collect(Collectors.toList());
        return new ResponseDto(rulesText);
    }

    @Override
    public ResponseDto play(String choice, boolean playWithCurb) {
        //validating incoming choice
        if (!(choice.equalsIgnoreCase("rock") || choice.equalsIgnoreCase("paper") || choice.equalsIgnoreCase("scissors"))) {
            throw new BadRequestException("This object is not found in the game");
        }

        GameResultDto gameResultDto = new GameResultDto();
        gameResultDto.setPlayersChoice(choice);

        ResponseDto responseDto;
        if (playWithCurb) {
            responseDto = this.throwFromMockServer();
        } else {
            responseDto = this.throwRandom();
        }

        if (responseDto.getStatusCode() != 200) {
            throw new ConflictException("Other player cannot play now");
        }
        String secondChoice = String.valueOf(responseDto.getBody());
        gameResultDto.setOpponentsChoice(secondChoice);

        if (choice.equalsIgnoreCase(secondChoice)) {
            gameResultDto.setResult("Draw!");
            return new ResponseDto(gameResultDto);
        }

        //sorting choices alphabetically
        String[] choices = {choice, secondChoice};
        Arrays.sort(choices);

        Boolean isFirstChoiceWinner = choices[0].equals(choice);

        //checking for game rule
        GameRule gameRule = gameRuleRepository.findFirstByFirstChoiceAndSecondChoice(choices[0], choices[1]);
        if (gameRule == null) {
            throw new ConflictException("This game rule is not added to the system!");
        }

        //logical XOR operator: same values result in false
        String result = isFirstChoiceWinner ^ gameRule.getFirstChoiceWinner() ? "You lost(" : "You won)";
        gameResultDto.setResult(result);

        return new ResponseDto(gameResultDto);

    }

    @Override
    public ResponseDto editRule(Long id, GameRuleCreateDto gameRuleCreateDto) {
        if (id == null || gameRuleCreateDto == null) {
            throw new BadRequestException("Request doesn't contain required objects");
        }
        //checking if this rule exists in the database
        boolean exists = gameRuleRepository.existsById(id);
        if (!exists) {
            throw new ConflictException("Game Rule not found");
        }

        //for quick edit implementation, this logic is applied
        //first, delete existing rule
        gameRuleRepository.deleteById(id);

        //second, create new rule
        return this.createRule(gameRuleCreateDto);
    }

    @Override
    public ResponseDto deleteRule(Long id) {
        if (id == null) {
            throw new BadRequestException("Id should not be null");
        }
        gameRuleRepository.deleteById(id);
        return new ResponseDto("Deleted");
    }
}
