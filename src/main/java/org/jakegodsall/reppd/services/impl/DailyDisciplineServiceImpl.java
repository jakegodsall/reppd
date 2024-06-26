package org.jakegodsall.reppd.services.impl;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<DailyDisciplineDTO> getDailyDisciplineById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<DailyDisciplineDTO> updateDailyDisciplineById(UUID id, DailyDisciplineDTO dailyDisciplineDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDailyDisciplineById(UUID id) {
        return false;
    }
}
