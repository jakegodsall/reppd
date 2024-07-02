package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.entities.enums.Status;
import org.jakegodsall.reppd.services.CompetencyService;
import org.jakegodsall.reppd.services.DailyDisciplineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Page<CompetencyDTO> competencyPage = createPageOfCompetenciesWithoutDailyDisciplines();

        given(competencyService.getAllCompetencies(CompetencyController.DEFAULT_PAGE_NUMBER, CompetencyController.DEFAULT_PAGE_SIZE)).willReturn(competencyPage);

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    void listAllCompetencies_emptyPage() throws Exception {
        Page<CompetencyDTO> competencyPage = Page.empty();

        given(competencyService.getAllCompetencies(CompetencyController.DEFAULT_PAGE_NUMBER, CompetencyController.DEFAULT_PAGE_SIZE)).willReturn(competencyPage);

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(0)));
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
        Page<DailyDisciplineDTO> dailyDisciplines = createPageOfDailyDisciplines();

        given(competencyService.getAllDailyDisciplinesByCompetencyId(competencyId, DailyDisciplineController.DEFAULT_PAGE_NUMBER, DailyDisciplineController.DEFAULT_PAGE_SIZE))
                .willReturn(dailyDisciplines);

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY_DAILY_DISCIPLINES, competencyId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    @Test
    void listAllDailyDisciplinesByCompetencyId_emptyPage() throws Exception {
        UUID competencyId = UUID.randomUUID();
        Page<DailyDisciplineDTO> dailyDisciplines = Page.empty();

        given(competencyService.getAllDailyDisciplinesByCompetencyId(competencyId, DailyDisciplineController.DEFAULT_PAGE_NUMBER, DailyDisciplineController.DEFAULT_PAGE_SIZE))
                .willReturn(dailyDisciplines);

        mockMvc.perform(get(CompetencyController.API_V1_COMPETENCY_DAILY_DISCIPLINES, competencyId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(0)));
    }

    @Test
    void createDailyDisciplineByCompetencyId() throws Exception {
        CompetencyDTO competencyDTO = CompetencyDTO.builder()
                .id(UUID.randomUUID())
                .title("Chinese Language")
                .description("Learn to speak Chinese fluently")
                .status(Status.ACTIVE)
                .createdDate(LocalDateTime.now())

                .lastModifiedDate(LocalDateTime.now())
                .startDate(LocalDateTime.now())
                .version(0)
                .build();

        DailyDisciplineDTO dailyDisciplineToCreate = DailyDisciplineDTO.builder()
                .id(UUID.randomUUID())
                .title("Learn 10 new words")
                .minimumValue(10L)
                .description("Learn 10 new words using example sentences in context")
                .status(Status.ACTIVE)
                .competency(competencyDTO)

                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(0)
                .build();

        given(competencyService.getCompetencyById(any(UUID.class)))
                .willReturn(Optional.of(competencyDTO));
        given(dailyDisciplineService.createDailyDiscipline(any(DailyDisciplineDTO.class)))
                .willReturn(dailyDisciplineToCreate);


        String serialisedJson = objectMapper.writeValueAsString(dailyDisciplineToCreate);
        System.out.println("Request body JSON: " + serialisedJson);

        MvcResult result = mockMvc.perform(post("/api/v1/competencies/{competencyId}/daily-disciplines", competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialisedJson))
                .andExpect(status().isCreated()).andReturn();

        String responseContent = result.getResponse().getContentAsString();

        System.out.println("Response JSON: " + responseContent);

        mockMvc.perform(post("/api/v1/competencies/{competencyId}/daily-disciplines", competencyDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialisedJson))
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

    @Test
    void createDailyDisciplineByCompetencyId_dailyDisciplineTitleTooLong() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        DailyDisciplineDTO dailyDisciplineToCreate = createListOfDailyDisciplines().get(0);
        dailyDisciplineToCreate.setTitle(String.valueOf('a').repeat(256));
        dailyDisciplineToCreate.setCompetency(competencyDTO);

        given(competencyService.getCompetencyById(any(UUID.class)))
                .willReturn(Optional.of(competencyDTO));
        given(dailyDisciplineService.createDailyDiscipline(any(DailyDisciplineDTO.class)))
            .willReturn(dailyDisciplineToCreate);

        String serialisedJson = objectMapper.writeValueAsString(dailyDisciplineToCreate);

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialisedJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createDailyDisciplineByCompetencyId_dailyDisciplineNotTitleBlank() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        DailyDisciplineDTO dailyDisciplineToCreate = createListOfDailyDisciplines().get(0);
        dailyDisciplineToCreate.setTitle("");
        dailyDisciplineToCreate.setCompetency(competencyDTO);

        given(competencyService.getCompetencyById(any(UUID.class)))
            .willReturn(Optional.of(competencyDTO));
        given(dailyDisciplineService.createDailyDiscipline(any(DailyDisciplineDTO.class)))
                .willReturn(dailyDisciplineToCreate);

        String serialisedJson = objectMapper.writeValueAsString(dailyDisciplineToCreate);

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialisedJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createDailyDisciplineByCompetencyId_dailyDisciplineDescriptionTooLong() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        DailyDisciplineDTO dailyDisciplineToCreate = createListOfDailyDisciplines().get(0);
        dailyDisciplineToCreate.setTitle(String.valueOf('a').repeat(1001));
        dailyDisciplineToCreate.setCompetency(competencyDTO);

        given(competencyService.getCompetencyById(any(UUID.class)))
            .willReturn(Optional.of(competencyDTO));
        given(dailyDisciplineService.createDailyDiscipline(any(DailyDisciplineDTO.class)))
            .willReturn(dailyDisciplineToCreate);

        String serialisedJson = objectMapper.writeValueAsString(dailyDisciplineToCreate);

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialisedJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createDailyDisciplineByCompetencyId_dailyDisciplineStatusNull() throws Exception {
        CompetencyDTO competencyDTO = createValidCompetencyDtoWithoutDailyDisciplines();
        DailyDisciplineDTO dailyDisciplineToCreate = createListOfDailyDisciplines().get(0);
        dailyDisciplineToCreate.setStatus(null);
        dailyDisciplineToCreate.setCompetency(competencyDTO);

        given(competencyService.getCompetencyById(any(UUID.class)))
            .willReturn(Optional.of(competencyDTO));
        given(dailyDisciplineService.createDailyDiscipline(any(DailyDisciplineDTO.class)))
            .willReturn(dailyDisciplineToCreate);

        String serialisedJson = objectMapper.writeValueAsString(dailyDisciplineToCreate);

        mockMvc.perform(post(CompetencyController.API_V1_COMPETENCY)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serialisedJson))
                .andExpect(status().isBadRequest());
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

    private List<CompetencyDTO> createListValidCompetencyDtoWithoutDailyDisciplines() {
        return List.of(
                CompetencyDTO.builder().id(UUID.randomUUID()).title("Competency 1").version(0).createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build(),
                CompetencyDTO.builder().id(UUID.randomUUID()).title("Competency 2").version(0).createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build(),
                CompetencyDTO.builder().id(UUID.randomUUID()).title("Competency 3").version(0).createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build(),
                CompetencyDTO.builder().id(UUID.randomUUID()).title("Competency 4").version(0).createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build());
    }

    private Page<CompetencyDTO> createPageOfCompetenciesWithoutDailyDisciplines() {
        List<CompetencyDTO> competencies = createListValidCompetencyDtoWithoutDailyDisciplines();
        Pageable pageable = PageRequest.of(CompetencyController.DEFAULT_PAGE_NUMBER, CompetencyController.DEFAULT_PAGE_SIZE);
        return new PageImpl<>(competencies, pageable, competencies.size());
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

    private Page<DailyDisciplineDTO> createPageOfDailyDisciplines() {
        List<DailyDisciplineDTO> dailyDisciplines = createListOfDailyDisciplines();
        Pageable pageable = PageRequest.of(DailyDisciplineController.DEFAULT_PAGE_NUMBER, DailyDisciplineController.DEFAULT_PAGE_SIZE);
        return new PageImpl<>(dailyDisciplines, pageable, dailyDisciplines.size());
    }
}