package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.BDto;
import com.example.webapp.model.modelDTO.CDto;
import com.example.webapp.service.CRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@Service
public class CRestServiceImpl implements CRestService {

    private static final String URL_for_C = "http://data-base-app:8003/rest/c/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CDto getById(Integer id) {
        ResponseEntity response = restTemplate.getForEntity(URL_for_C + id, CDto.class);
        return (CDto) response.getBody();
    }

    @Override
    public CDto save(CDto cDto) {
        return restTemplate.postForEntity(URL_for_C, cDto, CDto.class).getBody();
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(URL_for_C + id);
    }

    @Override
    public CDto update(CDto cDto, Integer id) {
        return restTemplate.postForEntity(URL_for_C+id, cDto, CDto.class).getBody();

    }
}