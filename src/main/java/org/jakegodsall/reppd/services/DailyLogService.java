package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.entities.DailyLog;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface DailyLogService {
    Page<DailyLogDTO> getAllDailyLogs(Integer pageNumber, Integer pageSize);
    DailyLogDTO createDailyLog(DailyLogDTO dailyLogDTO);
    Optional<DailyLogDTO> getDailyLogById(UUID dailyLogId);
    Optional<DailyLogDTO> updateDailyLogById(UUID dailLogId, DailyLogDTO dailyLogDTO);
    boolean deleteDailyLogById(UUID id);

    Page<DailyLogDTO> getAllDailyLogsByDailyDisciplineId(UUID dailyDisciplineId, Integer pageNumber, Integer pageSize);
}
