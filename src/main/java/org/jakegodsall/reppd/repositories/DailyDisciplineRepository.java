package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DailyDisciplineRepository extends JpaRepository<DailyDiscipline, UUID> {
    Page<DailyDiscipline> findAllByCompetencyId(UUID competencyId, Pageable pageable);
}
