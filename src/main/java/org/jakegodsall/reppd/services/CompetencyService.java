package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.CompetencyDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetencyService {
    List<CompetencyDTO> getAllGoals();
    CompetencyDTO createGoal(CompetencyDTO competencyDTO);
    Optional<CompetencyDTO> getGoalById(UUID id);
    Optional<CompetencyDTO> updateGoalById(UUID id, CompetencyDTO competencyDTO);
    Optional<CompetencyDTO> updateGoalPatchById(UUID id, CompetencyDTO competencyDTO);
    boolean deleteGoalById(UUID id);
}
