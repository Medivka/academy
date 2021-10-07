package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.CDto;
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
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CRestServiceImplTest {

    @Value("${test.url.c}")
    private String getURL_for_C ;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CRestServiceImpl service;

    @Test
    void shouldReturnCDto_whenSaveCDto() {
        CDto expected = getCDto();
        ResponseEntity<CDto> cDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(getURL_for_C, expected, CDto.class)).thenReturn(cDtoResponseEntity);
        CDto actual = service.save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void whenGetById_shouldReturnCDTo() {
        CDto actual = getCDto();
        Integer id = actual.getId();
        Mockito.when(restTemplate.getForEntity(getURL_for_C + id, CDto.class)).thenReturn(new ResponseEntity<>(actual, HttpStatus.OK));
        CDto expected = service.getById(id);
        assertEquals(actual, expected);
    }

    @Test
    void positiveDeleteCDto() {
        CDto actual = getCDto();
        Integer id = actual.getId();
        Mockito.lenient().doNothing().when(restTemplate).delete(getURL_for_C + id);
    }

    @Test
    void negative_Delete_CDto_by_entity() {
        CDto actual = getCDto();
        Integer id = actual.getId();
        Mockito.doThrow(RuntimeException.class).when(restTemplate).delete(getURL_for_C + id);
        Assert.assertThrows(RuntimeException.class, () -> service.delete(actual.getId()));
    }

    @Test
    void should_update_C() {
        CDto expected = getCDto();
        expected.setName("newName");
        Integer id = expected.getId();
        ResponseEntity<CDto> cDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(getURL_for_C + id, expected, CDto.class)).thenReturn(cDtoResponseEntity);
        CDto actual = service.update(expected, id);
        assertEquals(actual, expected);
    }

    private CDto getCDto() {
        return CDto.builder().name("name").build();
    }
}