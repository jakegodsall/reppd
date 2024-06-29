package org.jakegodsall.reppd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.CompetencyService;
import org.jakegodsall.reppd.services.DailyDisciplineService;
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
    public static final String API_V1_COMPETENCY_DAILY_DISCIPLINES = API_V1_COMPETENCY_DETAIL + "/daily-discipline";

    private final CompetencyService competencyService;
    private final DailyDisciplineService dailyDisciplineService;

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

    @DeleteMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<Void> deleteCompetencyById(@PathVariable("competencyId") UUID competencyId) {
        if (!competencyService.deleteCompetencyById(competencyId)) {
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(API_V1_COMPETENCY_DAILY_DISCIPLINES)
    public ResponseEntity<List<DailyDisciplineDTO>> getAllDailyDisciplinesByCompetencyId(@PathVariable("competencyId") UUID competencyId) {
        List<DailyDisciplineDTO> dailyDisciplines = competencyService.getAllDailyDisciplinesByCompetencyId(competencyId);
        return ResponseEntity.ok(dailyDisciplines);
    }

    @PostMapping(API_V1_COMPETENCY_DAILY_DISCIPLINES)
    public ResponseEntity<DailyDisciplineDTO> createDailyDisciplineByCompetencyId(
            @PathVariable("competencyId") UUID competencyId,
            @RequestBody DailyDisciplineDTO dailyDisciplineDTO
    ) {
        CompetencyDTO competencyDTO = competencyService.getCompetencyById(competencyId).orElseThrow(NotFoundException::new);

        dailyDisciplineDTO.setCompetency(competencyDTO);
        DailyDisciplineDTO createdDailyDiscipline = dailyDisciplineService.createDailyDiscipline(dailyDisciplineDTO);

        return new ResponseEntity<>(createdDailyDiscipline, HttpStatus.CREATED);
    }
}
