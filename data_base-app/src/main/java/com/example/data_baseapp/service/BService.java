package com.example.data_baseapp.service;

import com.example.data_baseapp.domain.model.B;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface BService {
    Boolean exist(B b);
    B save(B b);

    B getById(Integer id);

    void update(B b);

    B findById(Integer id);

    List<B> getAll();

    void delete(B byId);
}
