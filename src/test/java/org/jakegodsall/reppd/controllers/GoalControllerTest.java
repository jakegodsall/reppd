package org.jakegodsall.reppd.controllers;

import org.jakegodsall.reppd.services.GoalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class GoalControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GoalService goalService;

    @Test
    void getAllGoals() {

    }

    @Test
    void createGoal() {
    }

    @Test
    void getGoalById() {
    }

    @Test
    void updateGoalById() {
    }

    @Test
    void updateGoalPatchById() {
    }

    @Test
    void deleteGoalById() {
    }
}