package com.example.webapp.dao;

import com.example.webapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sacuta V.A.
 */


@Repository
public interface ProfileDao extends JpaRepository<Profile, Long> {

    Profile findByUsername(String username);

    @Query("select s FROM Profile s where s.username =:username")
    Profile myUsernameCustomFind(String username);

}
