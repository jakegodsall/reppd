package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DailyDisciplineService {
    List<DailyDisciplineDTO> getAllDailyDisciplines();
    DailyDisciplineDTO createDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO);
    Optional<DailyDisciplineDTO> getDailyDisciplineById(UUID id);
    Optional<DailyDisciplineDTO> updateDailyDisciplineById(UUID id, DailyDisciplineDTO dailyDisciplineDTO);
    boolean deleteDailyDisciplineById(UUID id);
}
