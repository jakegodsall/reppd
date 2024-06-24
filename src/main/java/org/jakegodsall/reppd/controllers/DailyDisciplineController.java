package org.jakegodsall.reppd.controllers;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.repositories.DailyDisciplineRepository;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class DailyDisciplineController {
    private final String API_V1_DAILY_DISCIPLINE = "/api/v1/daily-discipline";
    private final String API_V1_DAILY_DISCIPLINE_DETAIL = API_V1_DAILY_DISCIPLINE + "/{dailyDisciplineId}";

    private final DailyDisciplineService dailyDisciplineService;

    @GetMapping(API_V1_DAILY_DISCIPLINE)
    public ResponseEntity<List<DailyDisciplineDTO>> getAllDailyDisciplines() {
        return null;
    }

    @PostMapping(API_V1_DAILY_DISCIPLINE)
    public ResponseEntity<DailyDisciplineDTO> createDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO) {
        return null;
    }

    @GetMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<DailyDisciplineDTO> getDailyDisciplineById(@PathVariable Long dailyDisciplineId) {
        return null;
    }

    @PutMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<DailyDisciplineDTO> updateDailyDisciplineById(UUID dailyDisciplineId, DailyDisciplineDTO dailyDisciplineDTO) {
        return null;
    }

    @DeleteMapping(API_V1_DAILY_DISCIPLINE_DETAIL)
    public ResponseEntity<Void> deleteDailyDisciplineById(@PathVariable Long dailyDisciplineId) {
        return null;
    }
}
