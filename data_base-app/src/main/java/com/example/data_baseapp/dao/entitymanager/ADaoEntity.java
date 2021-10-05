package com.example.data_baseapp.dao.entitymanager;

import com.example.data_baseapp.domain.model.A;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface ADaoEntity {
    void save(A a);

    void update(A a);

    A findById(Integer id);

    void delete(A a);

    List<A> getGuestList();
}
