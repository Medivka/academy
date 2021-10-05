package com.example.data_baseapp.dao.entitymanager;

import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.domain.model.B;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface BDaoEntity {

    void save(B b);

    void update(B b);

    B findById(Integer id);

    void delete(B b);

    List<B> getGuestList();
}
