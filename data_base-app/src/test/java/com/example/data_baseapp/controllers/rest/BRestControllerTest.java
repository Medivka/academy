package com.example.data_baseapp.controllers.rest;

import com.example.data_baseapp.domain.dto.BDto;
import com.example.data_baseapp.domain.model.B;
import com.example.data_baseapp.modelmapper.MyModelMapper;
import com.example.data_baseapp.service.BService;
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
class BRestControllerTest {

    private static final String invalidID = "12.34";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BService service;

    @SneakyThrows
    @Test
    void should_response_status_200_is_create_B() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/b/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getBDto())))
                .andExpect(status().isCreated()).andReturn();
        int expectedStatusCode = HttpStatus.CREATED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_Response_Bad_Request400_whenCreating_B_with_InvalidJson() {
        MvcResult mvcResult = mockMvc.perform(post("/rest/b/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_OK_with_Valid_ID_B() {
        B b = service.save(getB());
        MvcResult mvcResult = mockMvc.perform(get("/rest/b/{id}", b.getId()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseStatus200_Ok_update_B() {
        BDto bDto = mapper.map(service.save(getB()), BDto.class);
        B b = service.save(getB());
        MvcResult mvcResult = mockMvc
                .perform(post("/rest/b/{id}", b.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bDto)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponse400_update_B_with_InvalidID() {
        BDto bDto = getBDto();
        MvcResult mvcResult = mockMvc
                .perform(post("/rest/b/{id}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bDto)))
                .andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIs200_Ok_Delete_with_Valid_id_B() {
        B b = service.save(getB());
        mockMvc.perform(delete("/rest/b/{id}", b.getId()))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void should_ResponseIs400_Bad_Request_Delete_with_Invalid_id_B() {
        MvcResult mvcResult = mockMvc
                .perform(delete("/rest/b/{id}", invalidID))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void should_ResponseIsStatus200_Ok_Get_all_B() {
        MvcResult mvcResult = mockMvc.perform(get("/rest/b/all"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    private BDto getBDto() {
        return BDto.builder().address("address").build();
    }

    private B getB() {
        return B.builder().address("address").build();
    }
}