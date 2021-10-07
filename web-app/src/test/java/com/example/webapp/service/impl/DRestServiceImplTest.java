package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.DDto;
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
class DRestServiceImplTest {

    @Value("${test.url.d}")
    private String getURL_for_D;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DRestServiceImpl service;

    @Test
    void shouldReturnDDto_whenSaveDDto() {
        DDto expected = getDDto();
        ResponseEntity<DDto> dDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(getURL_for_D, expected, DDto.class)).thenReturn(dDtoResponseEntity);
        DDto actual = service.save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void whenGetById_shouldReturnDDTo() {
        DDto actual = getDDto();
        Integer id = actual.getId();
        Mockito.when(restTemplate.getForEntity(getURL_for_D + id, DDto.class)).thenReturn(new ResponseEntity<>(actual, HttpStatus.OK));
        DDto expected = service.getById(id);
        assertEquals(actual, expected);
    }

    @Test
    void positiveDeleteDDto() {
        DDto actual = getDDto();
        Integer id = actual.getId();
        Mockito.lenient().doNothing().when(restTemplate).delete(getURL_for_D + id);
    }

    @Test
    void negative_Delete_DDto_by_entity() {
        DDto actual = getDDto();
        Integer id = actual.getId();
        Mockito.doThrow(RuntimeException.class).when(restTemplate).delete(getURL_for_D + id);
        Assert.assertThrows(RuntimeException.class, () -> service.delete(actual.getId()));
    }

    @Test
    void should_update_D() {
        DDto expected = getDDto();
        expected.setName("newName");
        Integer id = expected.getId();
        ResponseEntity<DDto> dDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(getURL_for_D + id, expected, DDto.class)).thenReturn(dDtoResponseEntity);
        DDto actual = service.update(expected, id);
        assertEquals(actual, expected);
    }

    private DDto getDDto() {
        return DDto.builder().name("name").build();
    }
}