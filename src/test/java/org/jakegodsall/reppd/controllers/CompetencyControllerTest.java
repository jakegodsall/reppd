package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.services.CompetencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class CompetencyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CompetencyService competencyService;

    @Test
    void listAllCompetencies() throws Exception {
        List<CompetencyDTO> competencyDTOList = new ArrayList<>(List.of(
                createValidCompetencyDto(),
                createValidCompetencyDto(),
                createValidCompetencyDto()));

        given(competencyService.getAllGoals()).willReturn(competencyDTOList);

        mockMvc.perform(get(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void createCompetency() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();
        competencyDTO.setId(null);
        competencyDTO.setVersion(0);

        given(competencyService.createGoal(any(CompetencyDTO.class))).willReturn(competencyDTO);

        mockMvc.perform(post(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void createCompetency_nullTitle() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();
        competencyDTO.setTitle(null);

        given(competencyService.createGoal(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_blankTitle() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();
        competencyDTO.setTitle("");

        given(competencyService.createGoal(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_titleTooLong() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();
        competencyDTO.setTitle(String.valueOf('a').repeat(256));

        given(competencyService.createGoal(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_descriptionTooLong() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();
        competencyDTO.setDescription(String.valueOf('a').repeat(1001));

        given(competencyService.createGoal(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_nullStatus() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();
        competencyDTO.setStatus(null);

        given(competencyService.createGoal(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_GOAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void getCompetencyById() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();

        given(competencyService.getGoalById(any(UUID.class))).willReturn(Optional.of(competencyDTO));

        mockMvc.perform(get(CompetencyController.API_V1_GOAL_DETAIL, competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(competencyDTO.getId().toString()));
    }

    @Test
    void getCompetencyById_notFound() throws Exception {
        given(competencyService.getGoalById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(CompetencyController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCompetencyById() throws Exception {
        CompetencyDTO originalCompetencyDTO = createValidCompetencyDto();

        CompetencyDTO updatedCompetencyDTO = createValidCompetencyDto();
        updatedCompetencyDTO.setId(originalCompetencyDTO.getId());
        updatedCompetencyDTO.setTitle("New Competency");

        given(competencyService.updateGoalById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.of(updatedCompetencyDTO));

        mockMvc.perform(put(CompetencyController.API_V1_GOAL_DETAIL, originalCompetencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCompetencyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(originalCompetencyDTO.getId().toString()))
                .andExpect(jsonPath("$.title").value(updatedCompetencyDTO.getTitle()));
    }

    @Test
    void updateCompetencyById_notFound() throws Exception {
        CompetencyDTO updatedCompetencyDTO = createValidCompetencyDto();
        updatedCompetencyDTO.setId(null);
        updatedCompetencyDTO.setTitle("New Goal");

        given(competencyService.updateGoalById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.empty());

        mockMvc.perform(put(CompetencyController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCompetencyDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCompetencyPatchById() throws Exception {
        CompetencyDTO originalCompetencyDTO = createValidCompetencyDto();

        Map<String, Object> goalMap = new HashMap<>();
        goalMap.put("title", "New Goal");

        CompetencyDTO updatedCompetencyDTO = createValidCompetencyDto();
        updatedCompetencyDTO.setId(originalCompetencyDTO.getId());
        updatedCompetencyDTO.setTitle("New Goal");

        given(competencyService.updateGoalPatchById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.of(updatedCompetencyDTO));

        MvcResult result = mockMvc.perform(patch(CompetencyController.API_V1_GOAL_DETAIL, originalCompetencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalMap)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(originalCompetencyDTO.getId().toString()))
                .andExpect(jsonPath("$.title").value(goalMap.get("title")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void updateCompetencyPatchById_notFound() throws Exception {
        given(competencyService.updateGoalPatchById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.empty());

        Map<String, Object> goalMap = new HashMap<>();
        goalMap.put("title", "New Goal");

        mockMvc.perform(patch(CompetencyController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalMap)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCompetencyById() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDto();

        given(competencyService.deleteGoalById(any(UUID.class))).willReturn(true);

        mockMvc.perform(delete(CompetencyController.API_V1_GOAL_DETAIL, competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCompetencyById_notFound() throws Exception{
        given(competencyService.deleteGoalById(any(UUID.class))).willReturn(false);

        mockMvc.perform(delete(CompetencyController.API_V1_GOAL_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private CompetencyDTO createValidCompetencyDto() {
        return CompetencyDTO.builder()
                .id(UUID.randomUUID())
                .title("Become a Java developer")
                .description("Become a Java developer")
                .status(Status.ACTIVE)
                .startDate(LocalDateTime.now())
                .build();
    }
}