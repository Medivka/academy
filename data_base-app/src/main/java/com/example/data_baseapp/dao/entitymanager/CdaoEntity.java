package com.example.data_baseapp.dao.entitymanager;

import com.example.data_baseapp.domain.model.C;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface CdaoEntity {
    void save(C c);

    void update(C c);

    C findById(Integer id);

    void delete(C c);

    List<C> getGuestList();
}
