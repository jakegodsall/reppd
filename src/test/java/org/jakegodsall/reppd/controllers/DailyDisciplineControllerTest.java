package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.entities.DailyLog;
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

import java.time.LocalDateTime;
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
    void getAllDailyDisciplines_withParams() throws Exception {
        Page<DailyDisciplineDTO> page = createPageOfDailyDisciplines();
        given(dailyDisciplineService.getAllDailyDisciplines(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE))
                .willReturn(page);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE)
                .accept(MediaType.APPLICATION_JSON)
                .param("pageNumber", String.valueOf(DEFAULT_PAGE_NUMBER))
                .param("pageSize", String.valueOf(DEFAULT_PAGE_SIZE)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(page.getNumberOfElements())));
    }

    @Test
    void getAllDailyDisciplines_noParams() throws Exception {
        Page<DailyDisciplineDTO> page = createPageOfDailyDisciplines();
        given(dailyDisciplineService.getAllDailyDisciplines(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE))
                .willReturn(page);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(page.getNumberOfElements())));
    }

    @Test
    void getAllDailyDisciplines_emptyPage() throws Exception {
        Page<DailyDisciplineDTO> page = Page.empty();

        given(dailyDisciplineService.getAllDailyDisciplines(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE))
                .willReturn(page);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE)
                .accept(MediaType.APPLICATION_JSON)
                .param("pageNumber", String.valueOf(DEFAULT_PAGE_NUMBER))
                .param("pageSize", String.valueOf(DEFAULT_PAGE_SIZE)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").isEmpty());
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
        updateDailyDisciplineDTO.setId(originalDailyDisciplineDTO.getId());
        updateDailyDisciplineDTO.setVersion(1);
        updateDailyDisciplineDTO.setTitle("New Daily Discipline");

        given(dailyDisciplineService.updateDailyDisciplineById(any(UUID.class), any(DailyDisciplineDTO.class)))
                .willReturn(Optional.of(updateDailyDisciplineDTO));

        mockMvc.perform(put(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DETAIL, originalDailyDisciplineDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDailyDisciplineDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updateDailyDisciplineDTO.getId().toString()))
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

    @Test
    void getDailyLogsByDailyDisciplineId() throws Exception {
        DailyDisciplineDTO dailyDisciplineDTO = createListOfDailyDisciplines().get(0);
        Page<DailyLogDTO> page = createPageOfDailyLogs();

        given(dailyDisciplineService.getAllDailyLogsByDailyDisciplineId(
                dailyDisciplineDTO.getId(),
                DEFAULT_PAGE_NUMBER,
                DEFAULT_PAGE_SIZE))
                .willReturn(page);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DAILY_LOGS, dailyDisciplineDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    @Test
    void getDailyLogsByDailyDisciplineId_dailyDisciplineIdNotFound() throws Exception {
        UUID dailyDisciplineId = UUID.randomUUID();

        given(dailyDisciplineService.getAllDailyLogsByDailyDisciplineId(
                dailyDisciplineId, null, null))
                .willReturn(null);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DAILY_LOGS, dailyDisciplineId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDailyLogsByDailyDisciplineId_emptyPage() throws Exception {
        UUID dailyDisciplineId = UUID.randomUUID();
        Page<DailyLogDTO> dailyDisciplines = Page.empty();

        given(dailyDisciplineService.getAllDailyLogsByDailyDisciplineId(
                dailyDisciplineId, null, null)).willReturn(dailyDisciplines);

        mockMvc.perform(get(DailyDisciplineController.API_V1_DAILY_DISCIPLINE_DAILY_LOGS, dailyDisciplineId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(0)));
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

    private List<DailyLogDTO> createListOfDailyLogs() {
        DailyLogDTO dl1 = DailyLogDTO.builder()
                .date(LocalDateTime.now())
                .value(1L)
                .build();

        DailyLogDTO dl2 = DailyLogDTO.builder()
                .date(LocalDateTime.now())
                .value(2L)
                .build();

        DailyLogDTO dl3 = DailyLogDTO.builder()
                .date(LocalDateTime.now())
                .value(3L)
                .build();

        return List.of(dl1, dl2, dl3);
    }

    private Page<DailyLogDTO> createPageOfDailyLogs() {
        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return new PageImpl<>(createListOfDailyLogs(), pageable, 100);

    }
}