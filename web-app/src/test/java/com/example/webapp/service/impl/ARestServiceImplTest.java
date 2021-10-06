package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.ADto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;


@ExtendWith(MockitoExtension.class)
class ARestServiceImplTest {

    private static final String URL_for_A = "http://data-base-app:8003/rest/a/";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ARestServiceImpl service;

    @Test
    void shouldReturnADto_whenSaveADto() {
        ADto expected = getADto();
        ResponseEntity<ADto> aDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(URL_for_A, expected, ADto.class)).thenReturn(aDtoResponseEntity);
        ADto actual = service.save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void whenGetById_shouldReturnADTo() {
        ADto actual = getADto();
        Integer id = actual.getId();
        Mockito.when(restTemplate.getForEntity(URL_for_A + id, ADto.class)).thenReturn(new ResponseEntity<>(actual, HttpStatus.OK));
        ADto expected = service.getById(id);
        assertEquals(actual, expected);
    }


    @Test
    void positiveDeleteADto() {
        ADto actual = getADto();
        Integer id = actual.getId();
        Mockito.lenient().doNothing().when(restTemplate).delete(URL_for_A + id);
    }

    @Test
    void negative_Delete_ADto_by_entity() {
        ADto actual = getADto();
        Integer id = actual.getId();
        Mockito.doThrow(RuntimeException.class).when(restTemplate).delete(URL_for_A + id);
        Assert.assertThrows(RuntimeException.class, () -> service.deleteA(actual.getId()));
    }
    @Test
    void should_update_A() {
        ADto expected = getADto();
        expected.setName("name");
        Integer id=expected.getId();
        ResponseEntity<ADto> aDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(URL_for_A+id, expected, ADto.class)).thenReturn(aDtoResponseEntity);
        ADto actual = service.update(expected,id);
        assertEquals(actual, expected);
    }



    private ADto getADto() {
        return ADto.builder().name("name").build();
    }
}