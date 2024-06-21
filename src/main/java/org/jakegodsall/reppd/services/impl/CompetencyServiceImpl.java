package org.jakegodsall.reppd.services.impl;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.repositories.CompetencyRepository;
import org.jakegodsall.reppd.services.CompetencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompetencyServiceImpl implements CompetencyService {

    private final CompetencyRepository competencyRepository;

    @Override
    public List<CompetencyDTO> getAllGoals() {
        return List.of();
    }

    @Override
    public CompetencyDTO createGoal(CompetencyDTO competencyDTO) {
        return null;
    }

    @Override
    public Optional<CompetencyDTO> getGoalById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<CompetencyDTO> updateGoalById(UUID id, CompetencyDTO competencyDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<CompetencyDTO> updateGoalPatchById(UUID id, CompetencyDTO competencyDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteGoalById(UUID id) {
        return true;
    }
}
