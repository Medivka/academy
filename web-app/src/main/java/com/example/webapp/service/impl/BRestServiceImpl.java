package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.ADto;
import com.example.webapp.model.modelDTO.BDto;
import com.example.webapp.service.BRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@Service
public class BRestServiceImpl implements BRestService {

    private static final String URL_for_B = "http://data-base-app:8003/rest/b/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BDto getByIdB(Integer id) {
        ResponseEntity response = restTemplate.getForEntity(URL_for_B + id, BDto.class);
        return (BDto) response.getBody();
    }

    @Override
    public BDto save(BDto bDto) {
        return restTemplate.postForEntity(URL_for_B, bDto, BDto.class).getBody();
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(URL_for_B + id);
    }

    @Override
    public BDto update(BDto bDto, Integer id) {
        return restTemplate.postForEntity(URL_for_B+id, bDto, BDto.class).getBody();
    }
}