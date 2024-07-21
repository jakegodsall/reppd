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
    void createCompetency() {
        Competency createdCompetency = competencyRepository.save(createValidCompetencyWithoutDailyDisciplines());
        assertThat(createdCompetency.getId()).isNotNull();
    }

    // Validation Tests

    @Test
    void createCompetency_nullTitle() {
        Competency createdCompetency = createValidCompetencyWithoutDailyDisciplines();
        createdCompetency.setTitle(null);

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(createdCompetency);
            competencyRepository.flush();
        });
    }

    @Test
    void createCompetency_blankTitle() {
        Competency createdCompetency = createValidCompetencyWithoutDailyDisciplines();
        createdCompetency.setTitle("");

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(createdCompetency);
            competencyRepository.flush();
        });
    }

    @Test
    void createCompetency_titleTooLong() {
        Competency createdCompetency = createValidCompetencyWithoutDailyDisciplines();
        createdCompetency.setTitle(String.valueOf('a').repeat(266));

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(createdCompetency);
            competencyRepository.flush();
        });
    }

    @Test
    void createCompetency_descriptionTooLong() {
        Competency createdCompetency = createValidCompetencyWithoutDailyDisciplines();
        createdCompetency.setDescription(String.valueOf('a').repeat(1001));

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(createdCompetency);
            competencyRepository.flush();
        });
    }

    @Test
    void createCompetency_nullStatus() {
        Competency createdCompetency = createValidCompetencyWithoutDailyDisciplines();
        createdCompetency.setStatus(null);

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(createdCompetency);
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