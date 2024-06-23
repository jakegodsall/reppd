package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DailyDisciplineRepositoryTest {
    @Autowired
    private CompetencyRepository competencyRepository;
    @Autowired
    private DailyDisciplineRepository dailyDisciplineRepository;

    @Test
    public void findAllDailyDisciplinesByCompetencyId() {
        Competency competency = competencyRepository.findAll().get(0);

        List<DailyDiscipline> dailyDisciplines = dailyDisciplineRepository.findAllByCompetencyId(competency.getId());

        System.out.println(dailyDisciplines);
    }
}