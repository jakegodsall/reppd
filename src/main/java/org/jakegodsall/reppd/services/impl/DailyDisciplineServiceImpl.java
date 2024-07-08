package org.jakegodsall.reppd.services.impl;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DailyDisciplineServiceImpl implements DailyDisciplineService {

    @Override
    public Page<DailyDisciplineDTO> getAllDailyDisciplines(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public DailyDisciplineDTO createDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO) {
        return null;
    }


    @Override
    public Optional<DailyDisciplineDTO> getDailyDisciplineById(UUID dailyDisciplineId) {
        return Optional.empty();
    }

    @Override
    public Optional<DailyDisciplineDTO> updateDailyDisciplineById(UUID dailyDisciplineId, DailyDisciplineDTO dailyDisciplineDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDailyDisciplineById(UUID dailyDisciplineId) {
        return false;
    }

    @Override
    public Page<DailyLogDTO> getAllDailyLogsByDailyDisciplineId(UUID dailyDisciplineId, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
