package org.jakegodsall.reppd.controllers;

import org.jakegodsall.reppd.dtos.GoalDto;
import org.jakegodsall.reppd.entities.Goal;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.mappers.GoalMapper;
import org.jakegodsall.reppd.repositories.GoalRepository;
import org.jakegodsall.reppd.services.GoalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class GoalControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GoalService goalService;

    @Autowired
    GoalRepository goalRepository;
    @Autowired
    GoalMapper goalMapper;

    @Test
    void testGetAllGoals() throws Exception {
        List<GoalDto> goalDtos = createDummyGoals();

        mockMvc.perform(get(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value((long) goalDtos.size()));
    }

    @Test
    void testCreateGoal() {
    }

    @Test
    void testGetGoalById() {
    }

    @Test
    void testGetGoalByIdNotFound() {
    }

    @Test
    void testUpdateGoalById() {
    }

    @Test
    void testUpdateGoalByIdNotFound() {}

    @Test
    void testUpdateGoalPatchById() {
    }

    @Test
    void testUpdateGoalPatchByIdNotFound() {}

    @Test
    void testDeleteGoalById() {
    }

    @Test
    void testDeleteGoalByIdNotFound() {}

    private List<GoalDto> createDummyGoals() {
        GoalDto goal1 = GoalDto.builder()
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

        GoalDto goal2 = GoalDto.builder()
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

        GoalDto goal3 = GoalDto.builder()
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

        return Arrays.asList(goal1, goal2, goal3);
    }
}