package com.example.data_baseapp.dao.springdata;

import com.example.data_baseapp.domain.model.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sacuta V.A.
 */

@Repository
public interface ADao extends JpaRepository<A,Integer> {
    @Query("select s FROM A s where s.name =:name")
    A findMyByName(String name) ;
}
