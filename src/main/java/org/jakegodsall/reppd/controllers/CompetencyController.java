package org.jakegodsall.reppd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.CompetencyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CompetencyController {

    public static final String API_V1_COMPETENCY = "/api/v1/competency";
    public static final String API_V1_COMPETENCY_DETAIL = API_V1_COMPETENCY + "/{competencyId}";
    public static final String API_V1_COMPETENCY_DAILYDISCIPLINES = API_V1_COMPETENCY_DETAIL + "/daily-disciplines";

    private final CompetencyService competencyService;

    @GetMapping(API_V1_COMPETENCY)
    public ResponseEntity<List<CompetencyDTO>> getAllCompetencies() {
        List<CompetencyDTO> competencies = competencyService.getAllCompetencies();
        return ResponseEntity.ok(competencies);
    }

    @PostMapping(API_V1_COMPETENCY)
    public ResponseEntity<CompetencyDTO> createCompetency(
            @Valid @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO createdCompetencyDTO = competencyService.createCompetency(competencyDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", API_V1_COMPETENCY + createdCompetencyDTO.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<CompetencyDTO> getCompetencyById(@PathVariable("competencyId") UUID competencyId) {
        CompetencyDTO competencyDTO = competencyService.getCompetencyById(competencyId).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(competencyDTO);
    }

    @PutMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<CompetencyDTO> updateCompetencyById(
            @PathVariable("competencyId") UUID competencyId,
            @Valid @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO updatedCompetencyDTO = competencyService.updateCompetencyById(competencyId, competencyDTO)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedCompetencyDTO);
    }

    @PatchMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<CompetencyDTO> updateCompetencyPatchById(
            @PathVariable("goalId") UUID competencyId,
            @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO updatedCompetencyDTO = competencyService.updateCompetencyPatchById(competencyId, competencyDTO)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedCompetencyDTO);
    }

    @DeleteMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<Void> deleteCompetencyById(@PathVariable("competencyId") UUID competencyId) {
        if (!competencyService.deleteCompetencyById(competencyId)) {
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DailyDiscipline>> getAllDailyDisciplinesByCompetencyId(@PathVariable("competencyId") UUID competencyId) {
        List<DailyDiscipline> dailyDisciplines = competencyService.getAllDailyDisciplinesByCompetencyId(competencyId);
        return ResponseEntity.ok(dailyDisciplines);
    }
}
