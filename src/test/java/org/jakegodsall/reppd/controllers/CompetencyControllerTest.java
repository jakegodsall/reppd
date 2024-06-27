package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.services.CompetencyService;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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
@Import(DailyDisciplineService.class)
class CompetencyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CompetencyService competencyService;
    @MockBean
    DailyDisciplineService dailyDisciplineService;

    @Test
    void listAllCompetencies() throws Exception {
        List<CompetencyDTO> competencyDTOList = new ArrayList<>(List.of(
                createValidCompetencyDtoWithoutDailyDisciplines(),
                createValidCompetencyDtoWithoutDailyDisciplines(),
                createValidCompetencyDtoWithoutDailyDisciplines()));

        given(competencyService.getAllCompetencies()).willReturn(competencyDTOList);

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void createCompetency() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setId(null);
        competencyDTO.setVersion(0);

        given(competencyService.createCompetency(any(CompetencyDTO.class))).willReturn(competencyDTO);

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void createCompetency_nullTitle() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setTitle(null);

        given(competencyService.createCompetency(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_blankTitle() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setTitle("");

        given(competencyService.createCompetency(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_titleTooLong() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setTitle(String.valueOf('a').repeat(256));

        given(competencyService.createCompetency(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_descriptionTooLong() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setDescription(String.valueOf('a').repeat(1001));

        given(competencyService.createCompetency(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void createCompetency_nullStatus() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setStatus(null);

        given(competencyService.createCompetency(any(CompetencyDTO.class))).willReturn(competencyDTO);

        MvcResult result = mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void getCompetencyById() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();

        given(competencyService.getCompetencyById(any(UUID.class))).willReturn(Optional.of(competencyDTO));

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY_DETAIL, competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(competencyDTO.getId().toString()));
    }

    @Test
    void getCompetencyById_notFound() throws Exception {
        given(competencyService.getCompetencyById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCompetencyById() throws Exception {
        CompetencyDTO originalCompetencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        CompetencyDTO updatedCompetencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();

        updatedCompetencyDTO.setId(UUID.randomUUID());
        updatedCompetencyDTO.setVersion(0);
        updatedCompetencyDTO.setTitle("Chinese Language");

        updatedCompetencyDTO.setTitle("New Competency");

        given(competencyService.updateCompetencyById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.of(updatedCompetencyDTO));

        mockMvc.perform(put(CompetencyController.API_V1_COMPETENCY_DETAIL, originalCompetencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCompetencyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedCompetencyDTO.getId().toString()))
                .andExpect(jsonPath("$.version").value(0))
                .andExpect(jsonPath("$.title").value(updatedCompetencyDTO.getTitle()));
    }

    @Test
    void updateCompetencyById_notFound() throws Exception {
        CompetencyDTO updatedCompetencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        updatedCompetencyDTO.setId(null);
        updatedCompetencyDTO.setVersion(null);
        updatedCompetencyDTO.setTitle("New Goal");

        given(competencyService.updateCompetencyById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.empty());

        mockMvc.perform(put(CompetencyController.API_V1_COMPETENCY_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCompetencyDTO)))
                .andExpect(status().isNotFound());
    }

    @Disabled
    @Test
    void updateCompetencyPatchById() throws Exception {
        CompetencyDTO originalCompetencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();

        Map<String, Object> goalMap = new HashMap<>();
        goalMap.put("title", "New Goal");

        CompetencyDTO updatedCompetencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        updatedCompetencyDTO.setId(originalCompetencyDTO.getId());
        updatedCompetencyDTO.setTitle("New Goal");

        given(competencyService.updateCompetencyPatchById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.of(updatedCompetencyDTO));

        MvcResult result = mockMvc.perform(patch(CompetencyController.API_V1_COMPETENCY_DETAIL, originalCompetencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalMap)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(originalCompetencyDTO.getId().toString()))
                .andExpect(jsonPath("$.title").value(goalMap.get("title")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Disabled
    @Test
    void updateCompetencyPatchById_notFound() throws Exception {
        given(competencyService.updateCompetencyPatchById(any(UUID.class), any(CompetencyDTO.class))).willReturn(Optional.empty());

        Map<String, Object> goalMap = new HashMap<>();
        goalMap.put("title", "New Goal");

        mockMvc.perform(patch(CompetencyController.API_V1_COMPETENCY_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(goalMap)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCompetencyById() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();

        given(competencyService.deleteCompetencyById(any(UUID.class))).willReturn(true);

        mockMvc.perform(delete(CompetencyController.API_V1_COMPETENCY_DETAIL, competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCompetencyById_notFound() throws Exception{
        given(competencyService.deleteCompetencyById(any(UUID.class))).willReturn(false);

        mockMvc.perform(delete(CompetencyController.API_V1_COMPETENCY_DETAIL, UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void listAllDailyDisciplinesByCompetencyId() throws Exception {
        UUID competencyId = UUID.randomUUID();
        List<DailyDisciplineDTO> dailyDisciplines = createListOfDailyDisciplines();

        given(competencyService.getAllDailyDisciplinesByCompetencyId(competencyId))
                .willReturn(dailyDisciplines);

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY_DAILY_DISCIPLINES, competencyId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void createDailyDisciplineByCompetencyId() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        competencyDTO.setId(UUID.randomUUID()); // Ensure the ID is set
        competencyDTO.setTitle("Chinese Language");

        DailyDisciplineDTO dailyDisciplineToCreate = createListOfDailyDisciplines().get(0);
        dailyDisciplineToCreate.setId(UUID.randomUUID()); // Ensure the ID is set
        dailyDisciplineToCreate.setTitle("Learn 10 new words");
        dailyDisciplineToCreate.setCompetency(competencyDTO);

        given(competencyService.getCompetencyById(competencyDTO.getId())).willReturn(Optional.of(competencyDTO));
        given(dailyDisciplineService.createDailyDiscipline(dailyDisciplineToCreate)).willReturn(dailyDisciplineToCreate);

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY_DAILY_DISCIPLINES, competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dailyDisciplineToCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(dailyDisciplineToCreate.getId().toString()))
                .andExpect(jsonPath("$.title").value(dailyDisciplineToCreate.getTitle()))
                .andExpect(jsonPath("$.competency.id").value(competencyDTO.getId().toString()));
    }

    @Test
    void createDailyDisciplineByCompetencyId_competencyNotFound() throws Exception {
        UUID competencyId = UUID.randomUUID();
        DailyDisciplineDTO dailyDisciplineToCreate = createListOfDailyDisciplines().get(0);

        given(competencyService.getCompetencyById(competencyId)).willReturn(Optional.empty());

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY_DAILY_DISCIPLINES, competencyId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dailyDisciplineToCreate)))
                .andExpect(status().isNotFound());
    }

    private CompetencyDTO createValidCompetencyDtoWithoutDailyDisciplines() {
        return CompetencyDTO.builder()
                .id(UUID.randomUUID())
                .title("Competency")
                .description("This is a test competency")
                .status(Status.ACTIVE)
                .startDate(LocalDateTime.now())
                .build();
    }

    private List<DailyDisciplineDTO> createListOfDailyDisciplines() {
        DailyDisciplineDTO dd1 = DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Daily Discipline 1")
                .description("This is a test discipline")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .competency(createValidCompetencyDtoWithoutDailyDisciplines())
                .build();

        DailyDisciplineDTO dd2 = DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Daily Discipline 2")
                .description("This is a test discipline")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .competency(createValidCompetencyDtoWithoutDailyDisciplines())
                .build();

        DailyDisciplineDTO dd3= DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Daily Discipline 3")
                .description("This is a test discipline")
                .status(Status.ACTIVE)
                .minimumValue(10L)
                .competency(createValidCompetencyDtoWithoutDailyDisciplines())
                .build();

        return List.of(dd1, dd2, dd3);
    }

}