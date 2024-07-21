package org.jakegodsall.reppd.controllers;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing daily disciplines and their related daily logs.
 */
@RequiredArgsConstructor
@RestController
public class DailyDisciplineController {

    // Endpoints
    public static final String API_V1_DAILY_DISCIPLINE = "/api/v1/daily-disciplines";
    public static final String API_V1_DAILY_DISCIPLINE_DETAIL = API_V1_DAILY_DISCIPLINE + "/{dailyDisciplineId}";
    public static final String API_V1_DAILY_DISCIPLINE_DAILY_LOGS = API_V1_DAILY_DISCIPLINE_DETAIL + "/daily-logs";

    // Pagination Defaults
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 25;

    private final DailyDisciplineService dailyDisciplineService;

    /**
     * Retrieves all daily disciplines with pagination.
     *
     * @param pageNumber the page number to retrieve, defaults to 1
     * @param pageSize the size of the page, defaults to 25
     * @return a ResponseEntity containing a page of DailyDisciplineDTOs
     */
    @GetMapping(API_V1_DAILY_DISCIPLINE)
    public ResponseEntity<Page<DailyDisciplineDTO>> getAllDailyDisciplines(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        Page<DailyDisciplineDTO> ddPage = dailyDisciplineService.getAllDailyDisciplines(pageNumber, pageSize);
        return ResponseEntity.ok(ddPage);
    }

    /**
     * Retrieves a daily discipline by its ID.
     *
     * @param dailyDisciplineId the ID of the daily discipline to retrieve
     * @return a ResponseEntity containing the DailyDisciplineDTO
     * @throws NotFoundException if the daily discipline is not found
     */
    @GetMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<DailyDisciplineDTO> getDailyDisciplineById(@PathVariable UUID dailyDisciplineId) {
        DailyDisciplineDTO dd = dailyDisciplineService.getDailyDisciplineById(dailyDisciplineId)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(dd);
    }

    /**
     * Updates a daily discipline by its ID.
     *
     * @param dailyDisciplineId the ID of the daily discipline to update
     * @param dailyDisciplineDTO the updated daily discipline data
     * @return a ResponseEntity containing the updated DailyDisciplineDTO
     * @throws NotFoundException if the daily discipline is not found
     */
    @PutMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<DailyDisciplineDTO> updateDailyDisciplineById(
            @PathVariable UUID dailyDisciplineId,
            @RequestBody DailyDisciplineDTO dailyDisciplineDTO
    ) {
        DailyDisciplineDTO ddUpdated = dailyDisciplineService.updateDailyDisciplineById(dailyDisciplineId, dailyDisciplineDTO)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(ddUpdated);
    }

    /**
     * Deletes a daily discipline by its ID.
     *
     * @param dailyDisciplineId the ID of the daily discipline to delete
     * @return a ResponseEntity with no content status
     * @throws NotFoundException if the daily discipline is not found
     */
    @DeleteMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<Void> deleteDailyDisciplineById(@PathVariable UUID dailyDisciplineId) {
        if (dailyDisciplineService.getDailyDisciplineById(dailyDisciplineId).isEmpty()) {
            throw new NotFoundException();
        }
        dailyDisciplineService.deleteDailyDisciplineById(dailyDisciplineId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all daily logs associated with a specific daily discipline.
     *
     * @param dailyDisciplineId the ID of the daily discipline
     * @return a ResponseEntity containing a page of DailyLogDTOs
     */
    @GetMapping(API_V1_DAILY_DISCIPLINE_DAILY_LOGS)
    public ResponseEntity<Page<DailyLogDTO>> getDailyLogsByDailyDisciplineId(@PathVariable UUID dailyDisciplineId) {
        return null;
    }
}
