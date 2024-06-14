package org.jakegodsall.reppd.controllers;

import org.jakegodsall.reppd.dtos.GoalDto;
import org.jakegodsall.reppd.repositories.GoalRepository;
import org.jakegodsall.reppd.services.GoalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class GoalControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GoalService goalService;

    @Autowired
    GoalRepository goalRepository;

    @Test
    void testGetAllGoals() {

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
}