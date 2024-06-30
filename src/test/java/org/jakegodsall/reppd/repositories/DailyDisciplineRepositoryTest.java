package org.jakegodsall.reppd.repositories;

import org.jakegodsall.reppd.controllers.DailyDisciplineController;
import org.jakegodsall.reppd.entities.Competency;
import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        Pageable pageable = PageRequest.of(DailyDisciplineController.DEFAULT_PAGE_NUMBER, DailyDisciplineController.DEFAULT_PAGE_SIZE);

        Page<DailyDiscipline> dailyDisciplines = dailyDisciplineRepository.findAllByCompetencyId(competency.getId(), pageable);

        System.out.println(dailyDisciplines);
    }
}