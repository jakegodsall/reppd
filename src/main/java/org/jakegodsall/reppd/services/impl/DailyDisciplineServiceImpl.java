package org.jakegodsall.reppd.services.impl;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DailyDisciplineServiceImpl implements DailyDisciplineService {
    @Override
    public List<DailyDisciplineDTO> getAllDailyDisciplines() {
        return List.of();
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
