package org.jakegodsall.reppd.repositories;

import jakarta.validation.ConstraintViolationException;
import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.entities.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompetencyRepositoryTest {

    @Autowired
    private CompetencyRepository competencyRepository;

    @Test
    void saveGoal() {
        Competency savedGoal = competencyRepository.save(createValidCompetencyWithoutDailyDisciplines());
        assertThat(savedGoal.getId()).isNotNull();
    }

    // Validation Tests

    @Test
    void saveGoalNullTitle() {
        Competency goalToSave = createValidCompetencyWithoutDailyDisciplines();
        goalToSave.setTitle(null);

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalBlankTitle() {
        Competency goalToSave = createValidCompetencyWithoutDailyDisciplines();
        goalToSave.setTitle("");

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalTitleTooLong() {
        Competency goalToSave = createValidCompetencyWithoutDailyDisciplines();
        goalToSave.setTitle(String.valueOf('a').repeat(266));

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalDescriptionTooLong() {
        Competency goalToSave = createValidCompetencyWithoutDailyDisciplines();
        goalToSave.setDescription(String.valueOf('a').repeat(1001));

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalNullStatus() {
        Competency goalToSave = createValidCompetencyWithoutDailyDisciplines();
        goalToSave.setStatus(null);

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    private Competency createValidCompetencyWithoutDailyDisciplines() {
        return Competency.builder()
                .title("Competency")
                .description("This is a test competency")
                .status(Status.ACTIVE)
                .startDate(LocalDateTime.now())
                .build();
    }
}