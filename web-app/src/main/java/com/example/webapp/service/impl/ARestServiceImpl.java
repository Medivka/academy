package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.ADto;
import com.example.webapp.service.ARestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@Service
public class ARestServiceImpl implements ARestService {

    private static final String URL_for_A = "http://data-base-app:8003/rest/a/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ADto getById(Integer id) {
        ResponseEntity response = restTemplate.getForEntity(URL_for_A + id, ADto.class);
        return (ADto) response.getBody();
    }

    @Override
    public ADto save(ADto aDto) {
        return restTemplate.postForEntity(URL_for_A, aDto, ADto.class).getBody();
    }

    @Override
    public void deleteA(Integer id) {
        restTemplate.delete(URL_for_A + id);
    }

    @Override
    public ADto update(ADto aDto, Integer id) {
        return restTemplate.postForEntity(URL_for_A+id, aDto, ADto.class).getBody();
    }
}
