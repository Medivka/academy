package com.example.data_baseapp.dao.entitymanager;

import com.example.data_baseapp.domain.model.D;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface DDaoEntity {
    void save(D d);

    void update(D d);

    D findById(Integer id);

    void delete(D d);

    List<D> getGuestList();
}
