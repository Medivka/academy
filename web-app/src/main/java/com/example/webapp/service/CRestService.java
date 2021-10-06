package com.example.webapp.service;

import com.example.webapp.model.modelDTO.CDto;

/**
 * @author Sacuta V.A.
 */


public interface CRestService {
    CDto getById(Integer id);

    CDto save(CDto cDto);

    void delete(Integer id);

    CDto update(CDto cDto, Integer id);
}
