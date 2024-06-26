package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CompetencyService {
    Page<CompetencyDTO> getAllCompetencies(Integer pageNumber, Integer pageSize);
    CompetencyDTO createCompetency(CompetencyDTO competencyDTO);
    Optional<CompetencyDTO> getCompetencyById(UUID id);
    Optional<CompetencyDTO> updateCompetencyById(UUID id, CompetencyDTO competencyDTO);
    boolean deleteCompetencyById(UUID id);

    Page<DailyDisciplineDTO> getAllDailyDisciplinesByCompetencyId(UUID competencyId, Integer pageNumber, Integer pageSize);
}
