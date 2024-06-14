package org.jakegodsall.reppd.bootstrap;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.entities.Goal;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.repositories.GoalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInitialiser implements CommandLineRunner {

    private final GoalRepository goalRepository;

    @Override
    public void run(String... args) throws Exception {
       createAndPersistGoals();
    }

    private void createAndPersistGoals() {
        if (goalRepository.count() == 0) {
            Goal goal1 = Goal.builder()
                    .title("Get a promotion")
                    .description("Get a promotion")
                    .status(Status.ACTIVE)
                    .startDate(LocalDateTime.now())
                    .smartDetail("Smart Detail")
                    .measurableDetail("Measurable Detail")
                    .achievableDetail("Achievable Detail")
                    .relevantDetail("Relevant Detail")
                    .timeboundDetail("Timebound Detail")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Goal goal2 = Goal.builder()
                    .title("Get a job as a Java developer")
                    .description("Get a job as a Java developer")
                    .status(Status.ACTIVE)
                    .startDate(LocalDateTime.now())
                    .smartDetail("Smart Detail")
                    .measurableDetail("Measurable Detail")
                    .achievableDetail("Achievable Detail")
                    .relevantDetail("Relevant Detail")
                    .timeboundDetail("Timebound Detail")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Goal goal3 = Goal.builder()
                    .title("Run a marathon")
                    .description("Run a marathon")
                    .status(Status.ACTIVE)
                    .startDate(LocalDateTime.now())
                    .smartDetail("Smart Detail")
                    .measurableDetail("Measurable Detail")
                    .achievableDetail("Achievable Detail")
                    .relevantDetail("Relevant Detail")
                    .timeboundDetail("Timebound Detail")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            goalRepository.saveAll(List.of(goal1, goal2, goal3));
        }
    }
}
