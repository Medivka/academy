package com.example.webapp.service.impl;

import com.example.webapp.model.modelDTO.BDto;
import com.example.webapp.service.BRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sacuta V.A.
 */

@Service
public class BRestServiceImpl implements BRestService {

    @Value("${test.url.b}")
    private String getURL_for_B;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BDto getByIdB(Integer id) {
        ResponseEntity response = restTemplate.getForEntity(getURL_for_B + id, BDto.class);
        return (BDto) response.getBody();
    }

    @Override
    public BDto save(BDto bDto) {
        return restTemplate.postForEntity(getURL_for_B, bDto, BDto.class).getBody();
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(getURL_for_B + id);
    }

    @Override
    public BDto update(BDto bDto, Integer id) {
        return restTemplate.postForEntity(getURL_for_B + id, bDto, BDto.class).getBody();
    }
}