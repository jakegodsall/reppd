package org.jakegodsall.reppd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jakegodsall.reppd.services.DailyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DailyLogController.class)
class DailyLogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DailyLogService dailyLogService;


}