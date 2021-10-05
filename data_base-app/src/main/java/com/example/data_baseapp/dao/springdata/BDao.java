package com.example.data_baseapp.dao.springdata;

import com.example.data_baseapp.domain.model.B;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sacuta V.A.
 */

@Repository
public interface BDao extends JpaRepository<B,Integer> {
}
