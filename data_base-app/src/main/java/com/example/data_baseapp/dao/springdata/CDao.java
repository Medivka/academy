package com.example.data_baseapp.dao.springdata;

import com.example.data_baseapp.domain.model.C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sacuta V.A.
 */

@Repository
public interface CDao extends JpaRepository<C, Integer> {
}
