package com.example.data_baseapp.service;

import com.example.data_baseapp.domain.model.A;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface AService {

    A save(A a);

    void delete(A a);

    A getById(Integer id);

    A update(A a);

    A findById(Integer id);

    List<A> getAll();

    Boolean exist(A a);

    A findByName(String name);
}
