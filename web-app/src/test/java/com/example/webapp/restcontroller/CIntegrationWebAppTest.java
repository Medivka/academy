package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.BDto;
import com.example.webapp.model.modelDTO.CDto;
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
public class CIntegrationWebAppTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    private CDto getCDto() {
        Integer id = 1;
        return CDto.builder()
                .id(id)
                .name("name")
                .age(23)
                .car("BMW")
                .memory(300)
                .build();
    }


    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_withValidId() {
        CDto dto = getCDto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/c/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/c/" + id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus401_whenGetByIdCdtoWithOutAuthorize() {
        CDto dto = getCDto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/c/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/c/" + id))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.UNAUTHORIZED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldReturnStatus200_WhenSaveCDtoWithValidJson() {
        CDto expected = getCDto();
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/c/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/c/")
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
    void shouldReturnStatus4xx_WhenSaveCDtoWithInValidJson() {
        WireMock.stubFor(WireMock.post("/rest/c/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/c/")
                        .with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }


    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_when_UpdateCDtoWithValidJson() {
        CDto expected = getCDto();
        expected.setName("new name");
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/c/" + expected.getId()).willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/c/" + expected.getId())
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
        CDto expected = getCDto();
        Integer id = expected.getId();
        WireMock.stubFor(WireMock.delete("/rest/c/" + expected.getId()).willReturn(WireMock.aResponse().withStatus(200)));
        MvcResult mvcResult = mockMvc.perform(delete("/web/c/{id}", id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }
}

