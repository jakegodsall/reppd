package org.jakegodsall.reppd.controllers;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.exceptions.NotFoundException;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class DailyDisciplineController {
    public static final String API_V1_DAILY_DISCIPLINE = "/api/v1/daily-discipline";
    public static final String API_V1_DAILY_DISCIPLINE_DETAIL = API_V1_DAILY_DISCIPLINE + "/{dailyDisciplineId}";

    private final DailyDisciplineService dailyDisciplineService;

    @GetMapping(API_V1_DAILY_DISCIPLINE)
    public ResponseEntity<Page<DailyDisciplineDTO>> getAllDailyDisciplines(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        Page<DailyDisciplineDTO> ddPage = dailyDisciplineService.getAllDailyDisciplines(pageNumber, pageSize);
        return ResponseEntity.ok(ddPage);
    }

//    @PostMapping(API_V1_DAILY_DISCIPLINE)
//    public ResponseEntity<DailyDisciplineDTO> createDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO) {
//        return null;
//    }

    @GetMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<DailyDisciplineDTO> getDailyDisciplineById(@PathVariable UUID dailyDisciplineId) {
        DailyDisciplineDTO dd = dailyDisciplineService.getDailyDisciplineById(dailyDisciplineId)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(dd);
    }

    @PutMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<DailyDisciplineDTO> updateDailyDisciplineById(
            @PathVariable UUID dailyDisciplineId,
            @RequestBody DailyDisciplineDTO dailyDisciplineDTO
    ) {
        DailyDisciplineDTO ddUpdated = dailyDisciplineService.updateDailyDisciplineById(dailyDisciplineId, dailyDisciplineDTO)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(ddUpdated);
    }

    @DeleteMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<Void> deleteDailyDisciplineById(@PathVariable UUID dailyDisciplineId) {
        if (dailyDisciplineService.getDailyDisciplineById(dailyDisciplineId).isEmpty()) {
            throw new NotFoundException();
        }
        dailyDisciplineService.deleteDailyDisciplineById(dailyDisciplineId);
        return ResponseEntity.noContent().build();
    }
}
