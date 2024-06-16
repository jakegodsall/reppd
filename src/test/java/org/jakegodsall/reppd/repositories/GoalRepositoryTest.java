package org.jakegodsall.reppd.repositories;

import jakarta.validation.ConstraintViolationException;
import org.jakegodsall.reppd.entities.Goal;
import org.jakegodsall.reppd.entities.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GoalRepositoryTest {

    @Autowired
    private GoalRepository goalRepository;

    @Test
    void saveGoal() {
        Goal savedGoal = goalRepository.save(createValidGoal());
        assertThat(savedGoal.getId()).isNotNull();
    }

    // Validation Tests

    @Test
    void saveGoalNullTitle() {
        Goal goalToSave = createValidGoal();
        goalToSave.setTitle(null);

        assertThrows(ConstraintViolationException.class, () -> {
            goalRepository.save(goalToSave);
            goalRepository.flush();
        });
    }

    @Test
    void saveGoalBlankTitle() {
        Goal goalToSave = createValidGoal();
        goalToSave.setTitle("");

        assertThrows(ConstraintViolationException.class, () -> {
            goalRepository.save(goalToSave);
            goalRepository.flush();
        });
    }

    @Test
    void saveGoalTitleTooLong() {
        Goal goalToSave = createValidGoal();
        goalToSave.setTitle(String.valueOf('a').repeat(266));

        assertThrows(ConstraintViolationException.class, () -> {
            goalRepository.save(goalToSave);
            goalRepository.flush();
        });
    }

    @Test
    void saveGoalDescriptionTooLong() {
        Goal goalToSave = createValidGoal();
        goalToSave.setDescription(String.valueOf('a').repeat(1001));

        assertThrows(ConstraintViolationException.class, () -> {
            goalRepository.save(goalToSave);
            goalRepository.flush();
        });
    }

    @Test
    void saveGoalNullStatus() {
        Goal goalToSave = createValidGoal();
        goalToSave.setStatus(null);

        assertThrows(ConstraintViolationException.class, () -> {
            goalRepository.save(goalToSave);
            goalRepository.flush();
        });
    }

    private Goal createValidGoal() {
        return Goal.builder()
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