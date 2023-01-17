package me.akmaljon.rps.service;

import me.akmaljon.rps.dto.GameRuleCreateDto;
import me.akmaljon.rps.dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    ResponseDto throwFromMockServer();

    ResponseDto throwRandom();

    ResponseDto createRule(GameRuleCreateDto gameRuleCreateDto);

    ResponseDto getRules();

    ResponseDto play(String choice, boolean playWithCurb);
}
