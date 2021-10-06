package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.DDto;
import com.example.webapp.service.DRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@Service
public class DRestServiceImpl implements DRestService {

    private static final String URL_for_D = "http://data-base-app:8003/rest/d/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DDto getById(Integer id) {
        ResponseEntity response = restTemplate.getForEntity(URL_for_D + id, DDto.class);
        return (DDto) response.getBody();
    }

    @Override
    public DDto save(DDto dDto) {
        return restTemplate.postForEntity(URL_for_D, dDto, DDto.class).getBody();
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(URL_for_D + id);
    }

    @Override
    public DDto update(DDto dDto, Integer id) {
        return restTemplate.postForEntity(URL_for_D + id, dDto, DDto.class).getBody();
    }
}
