package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.GoalDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalService {
    List<GoalDto> getAllGoals();
    GoalDto createGoal(GoalDto goalDto);
    Optional<GoalDto> getGoalById(UUID id);
    Optional<GoalDto> updateGoalById(UUID id, GoalDto goalDto);
    Optional<GoalDto> updateGoalPatchById(UUID id, GoalDto goalDto);
    void deleteGoalById(UUID id);
}
