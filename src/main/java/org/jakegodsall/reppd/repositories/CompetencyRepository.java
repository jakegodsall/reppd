package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.entities.Competency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetencyRepository extends JpaRepository<Competency, UUID> {
    Page<Competency> findAll(Pageable pageable);
}
