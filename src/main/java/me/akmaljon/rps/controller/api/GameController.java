package me.akmaljon.rps.controller.api;

import me.akmaljon.rps.dto.GameRuleCreateDto;
import me.akmaljon.rps.dto.ResponseDto;
import me.akmaljon.rps.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/throw-mock")
    public ResponseEntity<ResponseDto> throwFromMockServer() {
        return ResponseEntity.ok(gameService.throwFromMockServer());
    }

    @GetMapping("/throw-random")
    public ResponseEntity<ResponseDto> throwRandom() {
        return ResponseEntity.ok(gameService.throwRandom());
    }

    @PostMapping("/rule")
    public ResponseEntity<ResponseDto> createRule(@RequestBody GameRuleCreateDto gameRuleCreateDto) {
        return ResponseEntity.ok(gameService.createRule(gameRuleCreateDto));
    }

    @GetMapping("/rule")
    public ResponseEntity<ResponseDto> getRules() {
        return ResponseEntity.ok(gameService.getRules());
    }

    @PostMapping("/play-curb/{choice}")
    public ResponseEntity<ResponseDto> playWithCurb(@PathVariable String choice) {
        return ResponseEntity.ok(gameService.play(choice, true));
    }

    @PostMapping("/play-akmaljon/{choice}")
    public ResponseEntity<ResponseDto> playWithAkmaljon(@PathVariable String choice) {
        return ResponseEntity.ok(gameService.play(choice, false));
    }
}
