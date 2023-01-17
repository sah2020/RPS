package me.akmaljon.rps.repository;

import me.akmaljon.rps.entity.GameRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRuleRepository extends JpaRepository<GameRule, Long> {
    GameRule findFirstByFirstChoiceAndSecondChoice(String firstChoice, String secondChoice);
}
