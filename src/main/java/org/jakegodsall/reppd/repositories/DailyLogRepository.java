package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.jakegodsall.reppd.entities.DailyLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DailyLogRepository extends JpaRepository<DailyLog, UUID> {
    Page<DailyLog> findAllByDailyDisciplineId(UUID id, Pageable pageable);
}
