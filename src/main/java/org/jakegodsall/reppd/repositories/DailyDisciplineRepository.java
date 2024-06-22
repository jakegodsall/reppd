package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DailyDisciplineRepository extends JpaRepository<DailyDiscipline, UUID> {
}
