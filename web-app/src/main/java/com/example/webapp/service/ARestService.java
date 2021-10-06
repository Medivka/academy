package com.example.webapp.service;

import com.example.webapp.model.modelDTO.ADto;

/**
 * @author Sacuta V.A.
 */


public interface ARestService {
    ADto getById(Integer id);

    ADto save(ADto aDto);

    void deleteA(Integer id);

    ADto update(ADto aDto, Integer id);
}
