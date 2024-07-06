package org.jakegodsall.reppd.services.impl;

import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.services.DailyLogService;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public class DailyLogServiceImpl implements DailyLogService {
    @Override
    public Page<DailyLogDTO> getAllDailyLogs(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public DailyLogDTO createDailyLog(DailyLogDTO dailyLogDTO) {
        return null;
    }

    @Override
    public Optional<DailyLogDTO> getDailyLogById(UUID dailyLogId) {
        return Optional.empty();
    }

    @Override
    public Optional<DailyLogDTO> updateDailyLogById(UUID dailLogId, DailyLogDTO dailyLogDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDailyLogById(UUID id) {
        return false;
    }

    @Override
    public Page<DailyLogDTO> getAllDailyLogsByDailyDisciplineId(UUID dailyDisciplineId, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
