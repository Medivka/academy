package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.BDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BRestServiceImplTest {
    private static final String URL_for_B = "http://data-base-app:8003/rest/b/";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BRestServiceImpl service;

    @Test
    void shouldReturnADto_whenSaveBDto() {
        BDto expected = getBDto();
        ResponseEntity<BDto> bDtoResponseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(URL_for_B, expected, BDto.class)).thenReturn(bDtoResponseEntity);
        BDto actual = service.save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void whenGetById_shouldReturnBDTo() {
        BDto actual = getBDto();
        Integer id = actual.getId();
        Mockito.when(restTemplate.getForEntity(URL_for_B + id, BDto.class)).thenReturn(new ResponseEntity<>(actual, HttpStatus.OK));
        BDto expected = service.getByIdB(id);
        assertEquals(actual, expected);
    }

    @Test
    void positiveDeleteBDto() {
        BDto actual = getBDto();
        Integer id = actual.getId();
        Mockito.lenient().doNothing().when(restTemplate).delete(URL_for_B + id);
    }

    @Test
    void negative_Delete_BDto_by_entity() {
        BDto actual = getBDto();
        Integer id = actual.getId();
        Mockito.doThrow(RuntimeException.class).when(restTemplate).delete(URL_for_B + id);
        Assert.assertThrows(RuntimeException.class, () -> service.delete(actual.getId()));
    }

    private BDto getBDto() {
        return BDto.builder().address("address").build();
    }
}