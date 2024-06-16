package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class GoalControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GoalService goalService;

    @Test
    void testGetAllGoals() throws Exception {
        List<GoalDto> goalDtoList = new ArrayList<>(List.of(
                createValidGoalDto(),
                createValidGoalDto(),
                createValidGoalDto()));

        given(goalService.getAllGoals()).willReturn(goalDtoList);

        mockMvc.perform(get(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testCreateGoal() throws Exception {
        GoalDto goalDto = createValidGoalDto();
        goalDto.setId(null);
        goalDto.setVersion(0);

        given(goalService.createGoal(any(GoalDto.class))).willReturn(goalDto);

        mockMvc.perform(post(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testCreateGoalNullTitle() throws Exception {
        GoalDto goalDto = createValidGoalDto();
        goalDto.setTitle(null);

        given(goalService.createGoal(any(GoalDto.class))).willReturn(goalDto);

        MvcResult result = mockMvc.perform(post(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testCreateGoalBlankTitle() throws Exception {
        GoalDto goalDto = createValidGoalDto();
        goalDto.setTitle("");

        given(goalService.createGoal(any(GoalDto.class))).willReturn(goalDto);

        MvcResult result = mockMvc.perform(post(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testCreateGoalTitleTooLong() throws Exception {
        GoalDto goalDto = createValidGoalDto();
        goalDto.setTitle(String.valueOf('a').repeat(256));

        given(goalService.createGoal(any(GoalDto.class))).willReturn(goalDto);

        MvcResult result = mockMvc.perform(post(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testDescriptionTooLong() throws Exception {
        GoalDto goalDto = createValidGoalDto();
        goalDto.setDescription(String.valueOf('a').repeat(1001));

        given(goalService.createGoal(any(GoalDto.class))).willReturn(goalDto);

        MvcResult result = mockMvc.perform(post(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testCreateGoalNullStatus() throws Exception {
        GoalDto goalDto = createValidGoalDto();
        goalDto.setStatus(null);

        given(goalService.createGoal(any(GoalDto.class))).willReturn(goalDto);

        MvcResult result = mockMvc.perform(post(GoalController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testGetGoalById() throws Exception {
        GoalDto goalDto = createValidGoalDto();

        given(goalService.getGoalById(any(UUID.class))).willReturn(Optional.of(goalDto));

        mockMvc.perform(get(GoalController.API_V1_GOAL_DETAIL, goalDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(goalDto.getId().toString()));
    }

    @Test
    void testGetGoalByIdNotFound() throws Exception {
        given(goalService.getGoalById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(GoalController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateGoalById() throws Exception {
        GoalDto originalGoalDto = createValidGoalDto();

        GoalDto updatedGoalDto = createValidGoalDto();
        updatedGoalDto.setId(originalGoalDto.getId());
        updatedGoalDto.setTitle("New Goal");

        given(goalService.updateGoalById(any(UUID.class), any(GoalDto.class))).willReturn(Optional.of(updatedGoalDto));

        mockMvc.perform(put(GoalController.API_V1_GOAL_DETAIL, originalGoalDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedGoalDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(originalGoalDto.getId().toString()))
                .andExpect(jsonPath("$.title").value(updatedGoalDto.getTitle()));
    }

    @Test
    void testUpdateGoalByIdNotFound() throws Exception {
        GoalDto updatedGoalDto = createValidGoalDto();
        updatedGoalDto.setId(null);
        updatedGoalDto.setTitle("New Goal");

        given(goalService.updateGoalById(any(UUID.class), any(GoalDto.class))).willReturn(Optional.empty());

        mockMvc.perform(put(GoalController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedGoalDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateGoalPatchById() throws Exception {
        GoalDto originalGoalDto = createValidGoalDto();

        Map<String, Object> goalMap = new HashMap<>();
        goalMap.put("title", "New Goal");

        GoalDto updatedGoalDto = originalGoalDto;
        updatedGoalDto.setTitle("New Goal");

        given(goalService.updateGoalPatchById(any(UUID.class), any(GoalDto.class))).willReturn(Optional.of(updatedGoalDto));

        mockMvc.perform(patch(GoalController.API_V1_GOAL_DETAIL, originalGoalDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalMap)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(originalGoalDto.getId().toString()))
                .andExpect(jsonPath("$.title").value(goalMap.get("title")));
    }

    @Test
    void testUpdateGoalPatchByIdNotFound() throws Exception {
        given(goalService.updateGoalPatchById(any(UUID.class), any(GoalDto.class))).willReturn(Optional.empty());

        Map<String, Object> goalMap = new HashMap<>();
        goalMap.put("title", "New Goal");

        mockMvc.perform(patch(GoalController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalMap)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteGoalById() throws Exception {
        GoalDto goalDto = createValidGoalDto();

        given(goalService.deleteGoalById(any(UUID.class))).willReturn(true);

        mockMvc.perform(delete(GoalController.API_V1_GOAL_DETAIL, goalDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteGoalByIdNotFound() throws Exception{
        given(goalService.deleteGoalById(any(UUID.class))).willReturn(false);

        mockMvc.perform(delete(GoalController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private GoalDto createValidGoalDto() {
        return GoalDto.builder()
                .id(UUID.randomUUID())
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