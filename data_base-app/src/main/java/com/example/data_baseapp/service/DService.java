package com.example.data_baseapp.service;

import com.example.data_baseapp.domain.model.D;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface DService {
    D save(D d);

    void delete(D d);

    D getById(Integer id);

    void update(D d);

    D findById(Integer id);

    List<D> getAll();
    Boolean exist(D d);
}
