package org.jakegodsall.reppd.bootstrap;

import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.repositories.CompetencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInitialiser implements CommandLineRunner {

    private final CompetencyRepository competencyRepository;

    @Override
    public void run(String... args) throws Exception {
       createAndPersistGoals();
    }

    private void createAndPersistGoals() {
        if (competencyRepository.count() == 0) {
            Competency goal1 = Competency.builder()
                    .title("Python Programming")
                    .description("Building web applications with Python.")
                    .status(Status.ACTIVE)
                    .startDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Competency goal2 = Competency.builder()
                    .title("Running")
                    .description(null)
                    .status(Status.ACTIVE)
                    .startDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Competency goal3 = Competency.builder()
                    .title("Relationship")
                    .description("Everything towards building the best relationship.")
                    .status(Status.ACTIVE)
                    .startDate(LocalDateTime.now())
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            competencyRepository.saveAll(List.of(goal1, goal2, goal3));
        }
    }
}
