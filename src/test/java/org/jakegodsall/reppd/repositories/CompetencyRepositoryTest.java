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
        Competency savedGoal = competencyRepository.save(createValidGoal());
        assertThat(savedGoal.getId()).isNotNull();
    }

    // Validation Tests

    @Test
    void saveGoalNullTitle() {
        Competency goalToSave = createValidGoal();
        goalToSave.setTitle(null);

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalBlankTitle() {
        Competency goalToSave = createValidGoal();
        goalToSave.setTitle("");

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalTitleTooLong() {
        Competency goalToSave = createValidGoal();
        goalToSave.setTitle(String.valueOf('a').repeat(266));

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalDescriptionTooLong() {
        Competency goalToSave = createValidGoal();
        goalToSave.setDescription(String.valueOf('a').repeat(1001));

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    @Test
    void saveGoalNullStatus() {
        Competency goalToSave = createValidGoal();
        goalToSave.setStatus(null);

        assertThrows(ConstraintViolationException.class, () -> {
            competencyRepository.save(goalToSave);
            competencyRepository.flush();
        });
    }

    private Competency createValidGoal() {
        return Competency.builder()
                .title("Become a Java developer")
                .description("Become a Java developer")
                .status(Status.ACTIVE)
                .startDate(LocalDateTime.now())
                .smartDetail("Smart Detail")
                .measurableDetail("Measurable Detail")
                .achievableDetail("Achievable Detail")
                .relevantDetail("Relevant Detail")
                .timeboundDetail("Timebound Detail")
                .build();
    }
}