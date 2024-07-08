package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface DailyDisciplineService {
    Page<DailyDisciplineDTO> getAllDailyDisciplines(Integer pageNumber, Integer pageSize);
    DailyDisciplineDTO createDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO);
    Optional<DailyDisciplineDTO> getDailyDisciplineById(UUID dailyDisciplineId);
    Optional<DailyDisciplineDTO> updateDailyDisciplineById(UUID dailyDisciplineId, DailyDisciplineDTO dailyDisciplineDTO);
    boolean deleteDailyDisciplineById(UUID dailyDisciplineId);

    Page<DailyLogDTO> getAllDailyLogsByDailyDisciplineId(UUID dailyDisciplineId, Integer pageNumber, Integer pageSize);

}
