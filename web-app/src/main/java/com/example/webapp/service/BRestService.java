package com.example.webapp.service;

import com.example.webapp.model.modelDTO.BDto;

/**
 * @author Sacuta V.A.
 */


public interface BRestService {
    BDto getByIdB(Integer id);

    BDto save(BDto bDto);

    void delete(Integer id);

    BDto update(BDto bDto, Integer id);
}
