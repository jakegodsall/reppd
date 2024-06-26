package org.jakegodsall.reppd.services;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DailyDisciplineService {
    Page<DailyDisciplineDTO> getAllDailyDisciplines(Integer pageNumber, Integer pageSize);
    DailyDisciplineDTO createDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO);
    Optional<DailyDisciplineDTO> getDailyDisciplineById(UUID id);
    Optional<DailyDisciplineDTO> updateDailyDisciplineById(UUID id, DailyDisciplineDTO dailyDisciplineDTO);
    boolean deleteDailyDisciplineById(UUID id);
}
