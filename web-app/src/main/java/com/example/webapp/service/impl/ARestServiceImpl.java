package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.ADto;
import com.example.webapp.service.ARestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@Service
public class ARestServiceImpl implements ARestService {

    @Value("${test.url.a}")
    private String getURL_for_A;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ADto getById(Integer id) {
        ResponseEntity<ADto> response = restTemplate.getForEntity(getURL_for_A + id, ADto.class);
        return response.getBody();
    }

    @Override
    public ADto save(ADto aDto) {
        return restTemplate.postForEntity(getURL_for_A, aDto, ADto.class).getBody();
    }

    @Override
    public void deleteA(Integer id) {
        restTemplate.delete(getURL_for_A + id);
    }

    @Override
    public ADto update(ADto aDto, Integer id) {
        return restTemplate.postForEntity(getURL_for_A + id, aDto, ADto.class).getBody();
    }
}
