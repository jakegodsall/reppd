package org.jakegodsall.reppd.services.impl;

import org.jakegodsall.reppd.GoalDto;
import org.jakegodsall.reppd.services.GoalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GoalServiceImpl implements GoalService {
    @Override
    public List<GoalDto> getAllGoals() {
        return List.of();
    }

    @Override
    public GoalDto createGoal(GoalDto goalDto) {
        return null;
    }

    @Override
    public Optional<GoalDto> getGoalById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<GoalDto> updateGoalById(UUID id, GoalDto goalDto) {
        return Optional.empty();
    }

    @Override
    public Optional<GoalDto> updateGoalPatchById(UUID id, GoalDto goalDto) {
        return Optional.empty();
    }

    @Override
    public void deleteGoalById(UUID id) {

    }
}
