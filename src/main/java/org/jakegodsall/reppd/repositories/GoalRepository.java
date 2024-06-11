package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {
}
