package org.jakegodsall.reppd.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.repositories.CompetencyRepository;
import org.jakegodsall.reppd.repositories.DailyDisciplineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Component
public class DataInitialiser implements CommandLineRunner {

    private final CompetencyRepository competencyRepository;
    private final DailyDisciplineRepository dailyDisciplineRepository;

    @Override
    public void run(String... args) throws Exception {
        if (competencyRepository.count() == 0) {
            createRunningCompetencyWithDailyDisciplines();
            createPythonCompetencyWithDailyDisciplines();
        }
    }

    private void createRunningCompetencyWithDailyDisciplines() {
        Competency runningCompetency = Competency.builder()
                .title("Running")
                .description("Make running a part of your identity.")
                .status(Status.ACTIVE)
                .startDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline1 = DailyDiscipline.builder()
                .title("Morning Run")
                .description("Run 5 km every morning")
                .status(Status.ACTIVE)
                .minimumValue(5L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline2 = DailyDiscipline.builder()
                .title("Evening Sprint")
                .description("Sprint intervals for 30 minutes every evening")
                .status(Status.ACTIVE)
                .minimumValue(30L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline3 = DailyDiscipline.builder()
                .title("Hill Training")
                .description("Run uphill for strength training once a week")
                .status(Status.ACTIVE)
                .minimumValue(1L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline4 = DailyDiscipline.builder()
                .title("Long Distance Run")
                .description("Run 10 km every Sunday")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline5 = DailyDiscipline.builder()
                .title("Recovery Run")
                .description("Easy pace run for 3 km after intense workouts")
                .status(Status.ACTIVE)
                .minimumValue(3L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline6 = DailyDiscipline.builder()
                .title("Tempo Run")
                .description("Run at a tempo pace for 20 minutes")
                .status(Status.ACTIVE)
                .minimumValue(20L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline7 = DailyDiscipline.builder()
                .title("Track Workouts")
                .description("Track workouts for speed development")
                .status(Status.ACTIVE)
                .minimumValue(5L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline8 = DailyDiscipline.builder()
                .title("Fartlek Training")
                .description("Speed play run with variable pace")
                .status(Status.ACTIVE)
                .minimumValue(30L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline9 = DailyDiscipline.builder()
                .title("Trail Run")
                .description("Run on trails for endurance and agility")
                .status(Status.ACTIVE)
                .minimumValue(7L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline runningDiscipline10 = DailyDiscipline.builder()
                .title("Interval Training")
                .description("High-intensity interval training for 40 minutes")
                .status(Status.ACTIVE)
                .minimumValue(40L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        runningCompetency.addAllDailyDisciplines(List.of(
            runningDiscipline1,
            runningDiscipline2,
            runningDiscipline3,
            runningDiscipline4,
            runningDiscipline5,
            runningDiscipline6,
            runningDiscipline7,
            runningDiscipline8,
            runningDiscipline9,
            runningDiscipline10
        ));

        competencyRepository.save(runningCompetency);
    }

    private void createPythonCompetencyWithDailyDisciplines() {
        Competency pythonCompetency = Competency.builder()
                .title("Python Programming")
                .description("Building web applications with Python.")
                .status(Status.ACTIVE)
                .startDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline1 = DailyDiscipline.builder()
                .title("Morning Coding Session")
                .description("Code for 1 hour every morning")
                .status(Status.ACTIVE)
                .minimumValue(60L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline2 = DailyDiscipline.builder()
                .title("Evening Algorithm Practice")
                .description("Solve 2 algorithm problems every evening")
                .status(Status.ACTIVE)
                .minimumValue(2L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline3 = DailyDiscipline.builder()
                .title("Weekend Project Work")
                .description("Work on side projects for 3 hours every weekend")
                .status(Status.ACTIVE)
                .minimumValue(180L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline4 = DailyDiscipline.builder()
                .title("Daily Code Review")
                .description("Review code for 30 minutes each day")
                .status(Status.ACTIVE)
                .minimumValue(30L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline5 = DailyDiscipline.builder()
                .title("Study New Libraries")
                .description("Study and implement new libraries for 45 minutes")
                .status(Status.ACTIVE)
                .minimumValue(45L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline6 = DailyDiscipline.builder()
                .title("Practice Data Structures")
                .description("Practice data structures for 1 hour")
                .status(Status.ACTIVE)
                .minimumValue(60L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline7 = DailyDiscipline.builder()
                .title("Weekly Code Challenges")
                .description("Complete weekly code challenges")
                .status(Status.ACTIVE)
                .minimumValue(1L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline8 = DailyDiscipline.builder()
                .title("Daily Documentation")
                .description("Write documentation for 30 minutes")
                .status(Status.ACTIVE)
                .minimumValue(30L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline9 = DailyDiscipline.builder()
                .title("Code Optimization")
                .description("Optimize existing code for 40 minutes")
                .status(Status.ACTIVE)
                .minimumValue(40L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        DailyDiscipline pythonDiscipline10 = DailyDiscipline.builder()
                .title("Debugging Practice")
                .description("Practice debugging for 50 minutes")
                .status(Status.ACTIVE)
                .minimumValue(50L)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        pythonCompetency.addAllDailyDisciplines(List.of(
                pythonDiscipline1,
                pythonDiscipline2,
                pythonDiscipline3,
                pythonDiscipline4,
                pythonDiscipline5,
                pythonDiscipline6,
                pythonDiscipline7,
                pythonDiscipline8,
                pythonDiscipline9,
                pythonDiscipline10
        ));

        competencyRepository.save(pythonCompetency);
    }
}
