package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DailyDisciplineController.class)
class DailyDisciplineControllerTest {

    private final Integer DEFAULT_PAGE_NUMBER = 1;
    private final Integer DEFAULT_PAGE_SIZE = 25;

    @MockBean
    private DailyDisciplineService dailyDisciplineService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDailyDisciplines() throws Exception {
        Page<DailyDisciplineDTO> page = createPageOfDailyDisciplines();

        System.out.println(page.getContent());

        given(dailyDisciplineService.getAllDailyDisciplines(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE)).willReturn(page);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(page.getContent().size())))
                .andReturn();
    }

    @Test
    void getAllDailyDisciplines_emptyList() throws Exception {
        Page<DailyDisciplineDTO> page = createPageOfDailyDisciplines();
        page.getContent().clear();

        given(dailyDisciplineService.getAllDailyDisciplines(null, null)).willReturn(page);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getDailyDisciplineById() throws Exception {
        DailyDisciplineDTO dailyDiscipline = createListOfDailyDisciplines().get(0);

        given(dailyDisciplineService.getDailyDisciplineById(any(UUID.class)))
                .willReturn(Optional.of(dailyDiscipline));

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, dailyDiscipline.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getDailyDisciplineById_notFound() throws Exception {
        given(dailyDisciplineService.getDailyDisciplineById(any(UUID.class)))
                .willReturn(Optional.empty());

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateDailyDisciplineById() throws Exception {
        DailyDisciplineDTO originalDailyDisciplineDTO = createListOfDailyDisciplines().get(0);
        DailyDisciplineDTO updateDailyDisciplineDTO = createListOfDailyDisciplines().get(0);
        updateDailyDisciplineDTO.setId(UUID.randomUUID());
        updateDailyDisciplineDTO.setVersion(1);
        updateDailyDisciplineDTO.setTitle("New Daily Discipline");

        given(dailyDisciplineService.updateDailyDisciplineById(any(UUID.class), any(DailyDisciplineDTO.class)))
                .willReturn(Optional.of(updateDailyDisciplineDTO));

        mockMvc.perform(put(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, originalDailyDisciplineDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDailyDisciplineDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updateDailyDisciplineDTO.getId()))
                .andExpect(jsonPath("$.version").value(1))
                .andExpect(jsonPath("$.title").value(updateDailyDisciplineDTO.getTitle()));
    }

    @Test
    void updateDailyDisciplineById_notFound() throws Exception {
        DailyDisciplineDTO updateDailyDisciplineDTO = createListOfDailyDisciplines().get(0);
        updateDailyDisciplineDTO.setId(null);
        updateDailyDisciplineDTO.setVersion(null);
        updateDailyDisciplineDTO.setTitle("New Daily Discipline");

        given(dailyDisciplineService.updateDailyDisciplineById(any(UUID.class), any(DailyDisciplineDTO.class)))
            .willReturn(Optional.empty());

        mockMvc.perform(put(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDailyDisciplineDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteDailyDisciplineById() throws Exception {
        DailyDisciplineDTO dailyDisciplineDTOToDelete = createListOfDailyDisciplines().get(0);

        given(dailyDisciplineService.deleteDailyDisciplineById(any(UUID.class)))
            .willReturn(true);

        mockMvc.perform(delete(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, dailyDisciplineDTOToDelete.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteDailyDisciplineById_notFound() throws Exception {
        given(dailyDisciplineService.deleteDailyDisciplineById(any(UUID.class)))
            .willReturn(false);

        mockMvc.perform(delete(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private List<DailyDisciplineDTO> createListOfDailyDisciplines() {
        DailyDisciplineDTO dd1 = DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Daily Discipline 1")
                .description("This is a test discipline")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .build();

        DailyDisciplineDTO dd2 = DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Daily Discipline 2")
                .description("This is a test discipline")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .build();

        DailyDisciplineDTO dd3 = DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Daily Discipline 3")
                .description("This is a test discipline")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .build();

        return List.of(dd1, dd2, dd3);
    }

    private Page<DailyDisciplineDTO> createPageOfDailyDisciplines() {
        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return new PageImpl<>(createListOfDailyDisciplines(), pageable, 100);
    }
}