package com.example.webapp.service;


import com.example.webapp.model.Profile;

import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface ProfileService {

    boolean existsByUsername(String username);

    Profile findByUsername(String username);

    void createNewProfile(String username, String password);

    void save(Profile profile);

    void delete(Long id);

    void update(Profile profile);

    Profile findByID(Long id);

    List<Profile> getAll();

    void getRoleAdmin(Profile profile);
}
