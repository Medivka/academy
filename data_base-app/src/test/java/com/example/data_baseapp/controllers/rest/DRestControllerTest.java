package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.DDto;
import com.example.data_baseapp.domain.model.D;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.DService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties"
)
@AutoConfigureMockMvc
class DRestControllerTest {
    private static final String invalidID = "12.34";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DService service;

    @SneakyThrows
    @Test
    void should_response_status_200_is_create_D() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/d/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getDDto())))
                .andExpect(status().isCreated()).andReturn();
        int expectedStatusCode = HttpStatus.CREATED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_Response_Bad_Request400_whenCreating_D_with_InvalidJson() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/d/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_OK_with_Valid_ID_D() {
        D d = service.save(getD());
        MvcResult mvcResult = mockMvc.perform(get("/rest/d/{id}", d.getId()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_Ok_update_D() {
        DDto dDto = mapper.map(service.save(getD()), DDto.class);
        D d = service.save(getD());
        MvcResult mvcResult = mockMvc
                .perform(post("/rest/d/{id}", d.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dDto)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponse400_update_D_with_InvalidID() {
        DDto dDto = getDDto();
        MvcResult mvcResult = mockMvc
                .perform(post("/rest/d/{id}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dDto)))
                .andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIs200_Ok_Delete_with_Valid_id_D() {
        D d = service.save(getD());
        mockMvc.perform(delete("/rest/d/{id}", d.getId()))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void should_ResponseIs400_Bad_Request_Delete_with_Invalid_id_D() {
        MvcResult mvcResult = mockMvc
                .perform(delete("/rest/d/{id}", invalidID))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIsStatus200_Ok_Get_all_D() {
        MvcResult mvcResult = mockMvc.perform(get("/rest/d/all"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    private DDto getDDto() {
        return DDto.builder().name("CD").build();
    }

    private D getD() {
        return D.builder().name("DVD").build();
    }
}