package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.CDto;
import com.example.data_baseapp.domain.model.C;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.CService;
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
class CRestControllerTest {

    private static final String invalidID = "12.34";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CService service;

    @SneakyThrows
    @Test
    void should_response_status_200_is_create_C() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/c/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCDto())))
                .andExpect(status().isCreated()).andReturn();
        int expectedStatusCode = HttpStatus.CREATED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_Response_Bad_Request400_whenCreating_C_with_InvalidJson() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/c/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_OK_with_Valid_ID_C() {
        C c = service.save(getC());
        MvcResult mvcResult = mockMvc.perform(get("/rest/c/{id}", c.getId()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_Ok_update_C() {
        CDto cDto = mapper.map(service.save(getC()), CDto.class);
        C c = service.save(getC());
        MvcResult mvcResult = mockMvc
                .perform(post("/rest/c/{id}", c.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cDto)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponse400_update_C_with_InvalidID() {
        CDto cDto = getCDto();
        MvcResult mvcResult = mockMvc
                .perform(post("/rest/c/{id}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cDto)))
                .andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIs200_Ok_Delete_with_Valid_id_C() {
        C c = service.save(getC());
        mockMvc.perform(delete("/rest/c/{id}", c.getId()))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void should_ResponseIs400_Bad_Request_Delete_with_Invalid_id_C() {
        MvcResult mvcResult = mockMvc
                .perform(delete("/rest/c/{id}", invalidID))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIsStatus200_Ok_Get_all_C() {
        MvcResult mvcResult = mockMvc.perform(get("/rest/c/all"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    private CDto getCDto() {
        return CDto.builder().name("CD").build();
    }

    private C getC() {
        return C.builder().name("DVD").build();
    }
}