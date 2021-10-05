package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.ADto;
import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.AService;
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
class ARestControllerTest {

    private static final String invalidID = "12.34";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AService aService;

    @SneakyThrows
    @Test
    void should_response_status_200_is_create_A() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/a/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getADto())))
                .andExpect(status().isCreated()).andReturn();
        int expectedStatusCode = HttpStatus.CREATED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_Response_Bad_Request400_whenCreating_A_with_InvalidJson() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/a/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_OK_with_Valid_ID_A() {
        A a = aService.save(getA());
        MvcResult mvcResult = mockMvc.perform(get("/rest/a/{id}", a.getId()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_Ok_update_A() {
        ADto aDto = mapper.map(aService.save(getA()), ADto.class);
        A a = aService.save(getA());
        MvcResult mvcResult = mockMvc
                .perform(put("/rest/a/{id}", a.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aDto)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponse400_update_A_with_InvalidID() {
        ADto aDto = getADto();
        MvcResult mvcResult = mockMvc
                .perform(put("/rest/a/{id}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aDto)))
                .andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIs200_Ok_Delete_with_Valid_id_A() {
        A a = aService.save(getA());
        mockMvc.perform(delete("/rest/a/{id}", a.getId()))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void should_ResponseIs400_Bad_Request_Delete_with_Invalid_id_A() {
        MvcResult mvcResult = mockMvc
                .perform(delete("/rest/a/{id}", invalidID))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIsStatus200_Ok_Get_all_A() {
        MvcResult mvcResult = mockMvc.perform(get("/rest/a/all"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    private ADto getADto() {
        return ADto.builder().name("test").build();
    }

    private A getA() {
        return A.builder().name("test").build();
    }
}