package org.jakegodsall.reppd.services.impl;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.controllers.CompetencyController;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.mappers.CompetencyMapper;
import org.jakegodsall.reppd.repositories.CompetencyRepository;
import org.jakegodsall.reppd.services.CompetencyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompetencyServiceImpl implements CompetencyService {

    private final CompetencyRepository competencyRepository;
    private final CompetencyMapper competencyMapper;

    @Override
    public Page<CompetencyDTO> getAllCompetencies(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Competency> competencyPage = competencyRepository.findAll(pageRequest);
        return competencyPage.map(competencyMapper::competencyToCompetencyDTO);
    }

    @Override
    public CompetencyDTO createCompetency(CompetencyDTO competencyDTO) {
        Competency competency = competencyMapper.competencyDTOtoCompetency(competencyDTO);
        competency.setCreatedDate(LocalDateTime.now());
        competency.setLastModifiedDate(LocalDateTime.now());
        return competencyMapper.competencyToCompetencyDTO(competencyRepository.save(competency));
    }

    @Override
    public Optional<CompetencyDTO> getCompetencyById(UUID id) {
        return Optional.ofNullable(
                competencyMapper.competencyToCompetencyDTO(
                        competencyRepository.findById(id)
                                .orElse(null)));
    }

    @Override
    public Optional<CompetencyDTO> updateCompetencyById(UUID id, CompetencyDTO competencyDTO) {
        Optional<Competency> existingCompetencyOptional = competencyRepository.findById(id);
        if (existingCompetencyOptional.isPresent()) {
            Competency existingCompetency = existingCompetencyOptional.get();
            existingCompetency.setTitle(competencyDTO.getTitle());
            existingCompetency.setDescription(competencyDTO.getDescription());
            existingCompetency.setStatus(competencyDTO.getStatus());
            existingCompetency.setStartDate(competencyDTO.getStartDate());
            existingCompetency.setEndDate(competencyDTO.getEndDate());

            existingCompetency.setLastModifiedDate(LocalDateTime.now());
            return Optional.of(competencyMapper.competencyToCompetencyDTO(existingCompetency));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCompetencyById(UUID id) {
        if (competencyRepository.existsById(id)) {
            competencyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<DailyDisciplineDTO> getAllDailyDisciplinesByCompetencyId(UUID competencyId) {
        return List.of();
    }

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = CompetencyController.DEFAULT_PAGE_NUMBER;
        }

        if (pageSize != null || pageSize <= 0) {
            queryPageSize = CompetencyController.DEFAULT_PAGE_SIZE;
        } else if (pageSize > 1000) {
            queryPageSize = 1000;
        } else {
            queryPageSize = pageSize;
        }

        return PageRequest.of(queryPageNumber, queryPageSize);
    }
}
