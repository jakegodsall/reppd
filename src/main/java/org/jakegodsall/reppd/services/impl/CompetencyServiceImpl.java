package org.jakegodsall.reppd.services.impl;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.entities.DailyDiscipline;
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
    public List<CompetencyDTO> getAllCompetencies() {
        return List.of();
    }

    @Override
    public CompetencyDTO createCompetency(CompetencyDTO competencyDTO) {
        return null;
    }

    @Override
    public Optional<CompetencyDTO> getCompetencyById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<CompetencyDTO> updateCompetencyById(UUID id, CompetencyDTO competencyDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteCompetencyById(UUID id) {
        return false;
    }

    @Override
    public List<DailyDisciplineDTO> getAllDailyDisciplinesByCompetencyId(UUID competencyId) {
        return List.of();
    }


}
