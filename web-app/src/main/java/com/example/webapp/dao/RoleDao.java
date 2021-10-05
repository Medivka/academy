package com.example.webapp.dao;

import com.example.webapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sacuta V.A.
 */


@Repository
public interface RoleDao extends JpaRepository<Role,Long> {
}
