package com.example.webapp.service;

import com.example.webapp.model.modelDTO.DDto;

/**
 * @author Sacuta V.A.
 */


public interface DRestService {
    DDto getById(Integer id);

    DDto save(DDto dDto);

    void delete(Integer id);

    DDto update(DDto dDto, Integer id);
}
