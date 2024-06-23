package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.CompetencyDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetencyService {
    List<CompetencyDTO> getAllCompetencies();
    CompetencyDTO createCompetency(CompetencyDTO competencyDTO);
    Optional<CompetencyDTO> getCompetencyById(UUID id);
    Optional<CompetencyDTO> updateCompetencyById(UUID id, CompetencyDTO competencyDTO);
    Optional<CompetencyDTO> updateCompetencyPatchById(UUID id, CompetencyDTO competencyDTO);
    boolean deleteCompetencyById(UUID id);
}
