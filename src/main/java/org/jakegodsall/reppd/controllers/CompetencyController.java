package org.jakegodsall.reppd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.CompetencyService;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CompetencyController {

    // endpoints
    public static final String API_V1_COMPETENCY = "/api/v1/competency";
    public static final String API_V1_COMPETENCY_DETAIL = API_V1_COMPETENCY + "/{competencyId}";
    public static final String API_V1_COMPETENCY_DAILY_DISCIPLINES = API_V1_COMPETENCY_DETAIL + "/daily-discipline";

    // pagination defaults
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 25;

    private final CompetencyService competencyService;
    private final DailyDisciplineService dailyDisciplineService;

    @GetMapping(API_V1_COMPETENCY)
    public ResponseEntity<Page<CompetencyDTO>> getAllCompetencies(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        Page<CompetencyDTO> competencies = competencyService.getAllCompetencies(pageNumber, pageSize);
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
    public ResponseEntity<Page<DailyDisciplineDTO>> getAllDailyDisciplinesByCompetencyId(@PathVariable("competencyId") UUID competencyId) {
        Page<DailyDisciplineDTO> dailyDisciplines = competencyService.getAllDailyDisciplinesByCompetencyId(
                competencyId,
                DailyDisciplineController.DEFAULT_PAGE_NUMBER,
                DailyDisciplineController.DEFAULT_PAGE_SIZE
        );
        return ResponseEntity.ok(dailyDisciplines);
    }

    @PostMapping("api/v1/competency/{competencyId}/daily-discipline")
    public ResponseEntity<DailyDisciplineDTO> createDailyDisciplineByCompetencyId(
            @PathVariable("competencyId") UUID competencyId,
            @RequestBody DailyDisciplineDTO dailyDisciplineDTO
    ) {
        CompetencyDTO competencyDTO = competencyService.getCompetencyById(competencyId)
                .orElseThrow(NotFoundException::new);

        dailyDisciplineDTO.setCompetency(competencyDTO);
        DailyDisciplineDTO createdDailyDiscipline = dailyDisciplineService.createDailyDiscipline(dailyDisciplineDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDailyDiscipline);
    }
}
