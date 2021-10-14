package com.example.webapp.restcontroller;


import com.example.webapp.model.modelDTO.ADto;
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


@TestPropertySource(
        locations = {"classpath:application-integration.properties", "classpath:wiremock-endpoints.properties"})
@AutoConfigureWireMock(port = DYNAMIC_PORT)
@SpringBootTest
@AutoConfigureMockMvc
class AIntegrationWebAppTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private ADto getADto() {
        Integer id = 1;
        return ADto.builder()
                .id(id)
                .name("name")
                .apple("apple")
                .tree("dub")
                .enable("yes")
                .password("security")
                .build();
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_withValidId() {
        ADto dto = getADto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/a/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/a/" + id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldResponseStatus401_whenGetByIdADtoWithOutAuthorize() {
        ADto dto = getADto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/a/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/a/" + id))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.UNAUTHORIZED.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void shouldReturnStatus200_WhenSaveADtoWithValidJson() {
        ADto expected = getADto();
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/a/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/a/")
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
    void shouldReturnStatus4xx_WhenSaveADtoWithInValidJson() {
        WireMock.stubFor(WireMock.post("/rest/a/").willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/a/")
                        .with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }


    @SneakyThrows
    @Test
    void shouldResponseStatus404_WhenIncorrectAddress() {
        ADto dto = getADto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/a/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/rust" + id).with(user("admin").password("admin").roles("AUTHOR"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError()).andReturn();
        int expectedStatusCode = HttpStatus.NOT_FOUND.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @SneakyThrows
    @Test
    void ShouldReturnTrueJson_whenRequestADto() {
        ADto dto = getADto();
        Integer id = dto.getId();
        String bDtoJSON = objectMapper.writeValueAsString(dto);
        WireMock.stubFor(WireMock.get("/rest/a/" + dto.getId()).willReturn(WireMock.okJson(bDtoJSON)));
        MvcResult mvcResult = mockMvc.perform(get("/web/a/" + id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String expectedJson = mvcResult.getResponse().getContentAsString();
        assertThat(expectedJson).isEqualTo(bDtoJSON);
    }



    @SneakyThrows
    @Test
    void shouldResponseStatus200_Ok_when_UpdateADtoWithValidJson() {
        ADto expected = getADto();
        expected.setName("new name");
        String validJson = objectMapper.writeValueAsString(expected);
        WireMock.stubFor(WireMock.post("/rest/a/" + expected.getId()).willReturn(WireMock.ok()));
        MvcResult mvcResult = mockMvc.perform(post("/web/a/" + expected.getId())
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
        ADto expected = getADto();
        Integer id = expected.getId();
        WireMock.stubFor(WireMock.delete("/rest/a/" + expected.getId()).willReturn(WireMock.aResponse().withStatus(200)));
        MvcResult mvcResult = mockMvc.perform(delete("/web/a/{id}", id)
                        .with(user("admin").password("admin").roles("AUTHOR")))
                .andExpect(status().isOk()).andReturn();
        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = mvcResult.getResponse().getStatus();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }
}
