package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.ADto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ARestServiceImplTest {

    @Value("${test.url.a}")
    private String urlForA;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ARestServiceImpl service;

    @Test
    void shouldReturnADto_whenSaveADto() {
        ADto expected = getADto();
        ResponseEntity<ADto> aDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(urlForA, expected, ADto.class)).thenReturn(aDtoResponseEntity);
        ADto actual = service.save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void whenGetById_shouldReturnADTo() {
        ADto actual = getADto();
        Integer id = actual.getId();
        Mockito.when(restTemplate.getForEntity(urlForA + id, ADto.class)).thenReturn(new ResponseEntity<>(actual, HttpStatus.OK));
        ADto expected = service.getById(id);
        assertEquals(actual, expected);
    }


    @Test
    void positiveDeleteADto() {
        ADto actual = getADto();
        Integer id = actual.getId();
        Mockito.lenient().doNothing().when(restTemplate).delete(urlForA + id);
    }

    @Test
    void negative_Delete_ADto_by_entity() {
        ADto actual = getADto();
        Integer id = actual.getId();
        Mockito.doThrow(RuntimeException.class).when(restTemplate).delete(urlForA + id);
        Assert.assertThrows(RuntimeException.class, () -> service.deleteA(actual.getId()));
    }

    @Test
    void should_update_A() {
        ADto expected = getADto();
        expected.setName("name");
        Integer id = expected.getId();
        ResponseEntity<ADto> aDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(urlForA + id, expected, ADto.class)).thenReturn(aDtoResponseEntity);
        ADto actual = service.update(expected, id);
        assertEquals(actual, expected);
    }

    private ADto getADto() {
        return ADto.builder().name("name").build();
    }
}