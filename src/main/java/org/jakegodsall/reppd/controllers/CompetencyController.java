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


/**
 * REST controller for managing competencies and their related daily disciplines.
 */
@RequiredArgsConstructor
@RestController
public class CompetencyController {

    // Endpoints
    public static final String API_V1_COMPETENCY = "/api/v1/competencies";
    public static final String API_V1_COMPETENCY_DETAIL = API_V1_COMPETENCY + "/{competencyId}";
    public static final String API_V1_COMPETENCY_DAILY_DISCIPLINES = API_V1_COMPETENCY_DETAIL + "/daily-disciplines";

    // Pagination Defaults
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 25;

    private final CompetencyService competencyService;
    private final DailyDisciplineService dailyDisciplineService;

    /**
     * Retrieves all competencies with pagination.
     *
     * @param pageNumber the page number to retrieve, defaults to 1
     * @param pageSize the size of the page, defaults to 25
     * @return a ResponseEntity containing a page of CompetencyDTOs
     */
    @GetMapping(API_V1_COMPETENCY)
    public ResponseEntity<Page<CompetencyDTO>> getAllCompetencies(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        Page<CompetencyDTO> competencies = competencyService.getAllCompetencies(pageNumber, pageSize);
        return ResponseEntity.ok(competencies);
    }

    /**
     * Creates a new competency.
     *
     * @param competencyDTO the competency data to create
     * @return a ResponseEntity containing the created CompetencyDTO and a location header
     */
    @PostMapping(API_V1_COMPETENCY)
    public ResponseEntity<CompetencyDTO> createCompetency(
            @Valid @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO createdCompetencyDTO = competencyService.createCompetency(competencyDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", API_V1_COMPETENCY + createdCompetencyDTO.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Retrieves a competency by its ID.
     *
     * @param competencyId the ID of the competency to retrieve
     * @return a ResponseEntity containing the CompetencyDTO
     * @throws NotFoundException if the competency is not found
     */
    @GetMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<CompetencyDTO> getCompetencyById(@PathVariable("competencyId") UUID competencyId) {
        CompetencyDTO competencyDTO = competencyService.getCompetencyById(competencyId).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(competencyDTO);
    }

    /**
     * Updates a competency by its ID.
     *
     * @param competencyId the ID of the competency to update
     * @param competencyDTO the updated competency data
     * @return a ResponseEntity containing the updated CompetencyDTO
     * @throws NotFoundException if the competency is not found
     */
    @PutMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<CompetencyDTO> updateCompetencyById(
            @PathVariable("competencyId") UUID competencyId,
            @Valid @RequestBody CompetencyDTO competencyDTO
    ) {
        CompetencyDTO updatedCompetencyDTO = competencyService.updateCompetencyById(competencyId, competencyDTO)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(updatedCompetencyDTO);
    }

    /**
     * Deletes a competency by its ID.
     *
     * @param competencyId the ID of the competency to delete
     * @return a ResponseEntity with no content status
     * @throws NotFoundException if the competency is not found
     */
    @DeleteMapping(API_V1_COMPETENCY_DETAIL)
    public ResponseEntity<Void> deleteCompetencyById(@PathVariable("competencyId") UUID competencyId) {
        if (!competencyService.deleteCompetencyById(competencyId)) {
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all daily disciplines associated with a specific competency.
     *
     * @param competencyId the ID of the competency
     * @return a ResponseEntity containing a page of DailyDisciplineDTOs
     */
    @GetMapping(API_V1_COMPETENCY_DAILY_DISCIPLINES)
    public ResponseEntity<Page<DailyDisciplineDTO>> getAllDailyDisciplinesByCompetencyId(@PathVariable("competencyId") UUID competencyId) {
        Page<DailyDisciplineDTO> dailyDisciplines = competencyService.getAllDailyDisciplinesByCompetencyId(
                competencyId,
                DailyDisciplineController.DEFAULT_PAGE_NUMBER,
                DailyDisciplineController.DEFAULT_PAGE_SIZE
        );
        return ResponseEntity.ok(dailyDisciplines);
    }

    /**
     * Creates a new daily discipline associated with a specific competency.
     *
     * @param competencyId the ID of the competency
     * @param dailyDisciplineDTO the daily discipline data to create
     * @return a ResponseEntity containing the created DailyDisciplineDTO and a created status
     * @throws NotFoundException if the competency is not found
     */
    @PostMapping("api/v1/competencies/{competencyId}/daily-disciplines")
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
