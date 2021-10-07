package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.BDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.tomakehurst.wiremock.core.Options.DYNAMIC_PORT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sacuta V.A.
 */


@TestPropertySource(
        locations = {"classpath:application-integration.properties", "classpath:wiremock-endpoints.properties"})
@AutoConfigureWireMock(port = DYNAMIC_PORT)
@SpringBootTest
@AutoConfigureMockMvc
public class BRestWebAppControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private BDto getBDto() {
        Integer id = 1;
        return BDto.builder()
                .id(id)
                .phone("295655655")
                .address("Minsk")
                .amount(230)
                .build();
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_withValidId() {
        BDto dto = getBDto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/b/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/b/" + id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus401_whenGetByIdWithOutAuthorize() {
        BDto dto = getBDto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/b/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/b/" + id))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.UNAUTHORIZED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldReturnStatus200_WhenSaveBDtoWithValidJson() {
        BDto expected = getBDto();
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/b/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/b/")
                        .with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldReturnStatus4xx_WhenSaveBDtoWithInValidJson() {
        WireMock.stubFor(WireMock.post("/rest/b/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/b/")
                        .with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_when_UpdateWithValidJson() {
        BDto expected = getBDto();
        expected.setAddress("new address");
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/b/" + expected.getId()).willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/b/" + expected.getId())
                        .with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldReturnStatus200_whenDeleteWithValidID() {
        BDto expected = getBDto();
        Integer id = expected.getId();
        WireMock.stubFor(WireMock.delete("/rest/b/" + expected.getId()).willReturn(WireMock.aResponse().withStatus(200)));
        MvcResult mvcResult = mockMvc.perform(delete("/web/b/{id}", id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }
}
