package com.example.webapp.restcontroller;

import com.example.webapp.model.modelDTO.CDto;
import com.example.webapp.model.modelDTO.DDto;
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
public class DIntegrationWebAppTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    private DDto getDDto() {
        Integer id = 1;
        return DDto.builder()
                .id(id)
                .name("name")
                .cinema(23)
                .number(456)
                .hero("HULK")
                .isAlone("no")
                .build();
    }


    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_withValidId() {
        DDto dto = getDDto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/d/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/d/" + id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus401_whenGetByIdDDtoWithOutAuthorize() {
        DDto dto = getDDto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/d/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/d/" + id))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.UNAUTHORIZED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldReturnStatus200_WhenSaveDDtoWithValidJson() {
        DDto expected = getDDto();
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/d/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/d/")
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
    void shouldReturnStatus4xx_WhenSaveDDtoWithInValidJson() {
        WireMock.stubFor(WireMock.post("/rest/d/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/d/")
                        .with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }


    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_when_UpdateDDtoWithValidJson() {
        DDto expected = getDDto();
        expected.setName("new name");
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/d/" + expected.getId()).willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/d/" + expected.getId())
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
        DDto expected = getDDto();
        Integer id = expected.getId();
        WireMock.stubFor(WireMock.delete("/rest/d/" + expected.getId()).willReturn(WireMock.aResponse().withStatus(200)));
        MvcResult mvcResult = mockMvc.perform(delete("/web/d/{id}", id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }
}


