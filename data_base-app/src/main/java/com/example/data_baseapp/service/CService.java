package com.example.data_baseapp.service;

import com.example.data_baseapp.domain.model.C;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface CService {
    C save(C c);

    void delete(C c);

    C getById(Integer id);

    void update(C c);

    C findById(Integer id);

    List<C> getAll();

    Boolean exist(C c);
}
