package org.jakegodsall.reppd.services.impl;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.GoalDto;
import org.jakegodsall.reppd.repositories.GoalRepository;
import org.jakegodsall.reppd.services.GoalService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

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
    public boolean deleteGoalById(UUID id) {
        return true;
    }
}
