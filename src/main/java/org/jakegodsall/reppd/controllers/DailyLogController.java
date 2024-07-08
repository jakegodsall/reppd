package org.jakegodsall.reppd.controllers;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.services.DailyLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class DailyLogController {

    // endpoints
    public static final String API_V1_DAILY_LOGS = "/api/v1/daily-logs";
    public static final String API_V1_DAILY_LOG_DETAIL = API_V1_DAILY_LOGS + "/{dailyLogId}";

    // pagination defaults
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 25;

    private final DailyLogService dailyLogService;

    @GetMapping(API_V1_DAILY_LOGS)
    public ResponseEntity<Page<DailyLogDTO>> getAllDailyLogs(
            @RequestParam(required = false) int pageNumber,
            @RequestParam(required = false) int pageSize
    ) {
        Page<DailyLogDTO> dailyLogs = dailyLogService.getAllDailyLogs(pageNumber, pageSize);
        return ResponseEntity.ok(dailyLogs);
    }

    @GetMapping(API_V1_DAILY_LOG_DETAIL)
    public ResponseEntity<DailyLogDTO> getDailyLogById(@PathVariable UUID dailyLogId) {
        return null;
    }

    @PutMapping(API_V1_DAILY_LOG_DETAIL)
    public ResponseEntity<DailyLogDTO> updateDailyLogById(
            @PathVariable UUID dailyLogId,
            @RequestBody DailyLogDTO dailyLogDTO
    ) {
        return null;
    }

    @DeleteMapping(API_V1_DAILY_LOG_DETAIL)
    public ResponseEntity<Void> deleteDailyLogById(@PathVariable UUID dailyLogId) {
        return null;
    }
}
