package org.jakegodsall.reppd.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.entities.LogDetails;
import org.jakegodsall.reppd.entities.dailydiscipline.BooleanDailyDiscipline;
import org.jakegodsall.reppd.entities.dailydiscipline.DailyDiscipline;
import org.jakegodsall.reppd.entities.dailydiscipline.NumericDailyDiscipline;
import org.jakegodsall.reppd.entities.dailydiscipline.TimeDailyDiscipline;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.repositories.CompetencyRepository;
import org.jakegodsall.reppd.repositories.DailyDisciplineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInitialiser implements CommandLineRunner {

    private final CompetencyRepository competencyRepository;
    private final DailyDisciplineRepository dailyDisciplineRepository;

    @Override
    public void run(String... args) throws Exception {
       createAndPersistCompetencies();
       createAndPersistDailyDisciplines();
    }

    private void createAndPersistCompetencies() {
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

    @Transactional
    public void createAndPersistDailyDisciplines() {
        if (competencyRepository.count() != 0) {
            Competency competency1 = competencyRepository.findAll().get(0);
            Competency competency2 = competencyRepository.findAll().get(1);
            Competency competency3 = competencyRepository.findAll().get(2);

            BooleanDailyDiscipline dailyDiscipline1 = new BooleanDailyDiscipline();
            dailyDiscipline1.setTitle("Tests written with pytest");
            dailyDiscipline1.setDescription(null);
            dailyDiscipline1.setLogDetails(new LogDetails(null, null, true));
            dailyDiscipline1.setCreatedDate(LocalDateTime.now());
            dailyDiscipline1.setLastModifiedDate(LocalDateTime.now());

            NumericDailyDiscipline dailyDiscipline2 = new NumericDailyDiscipline();
            dailyDiscipline2.setTitle("Tests written with pytest");
            dailyDiscipline2.setDescription(null);
            dailyDiscipline2.setLogDetails(new LogDetails(5L, null, null));
            dailyDiscipline2.setCreatedDate(LocalDateTime.now());
            dailyDiscipline2.setLastModifiedDate(LocalDateTime.now());

            TimeDailyDiscipline dailyDiscipline3 = new TimeDailyDiscipline();
                    dailyDiscipline3.setTitle("Building web apps with Django");
                    dailyDiscipline3.setDescription(null);
                    dailyDiscipline3.setLogDetails(new LogDetails(null, 20L, null));
                    dailyDiscipline3.setCreatedDate(LocalDateTime.now());
                    dailyDiscipline3.setLastModifiedDate(LocalDateTime.now());

            competency1.getDailyDisciplines().addAll(List.of(dailyDiscipline1, dailyDiscipline2, dailyDiscipline3));

            competencyRepository.save(competency1);
            competencyRepository.save(competency2);
            competencyRepository.save(competency3);
        }
    }
}
