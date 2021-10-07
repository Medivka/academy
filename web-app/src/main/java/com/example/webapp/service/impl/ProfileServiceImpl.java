package com.example.webapp.service.impl;


import com.example.webapp.dao.ProfileDao;
import com.example.webapp.dao.RoleDao;
import com.example.webapp.model.Profile;
import com.example.webapp.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ProfileServiceImpl implements ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);
    private final ProfileDao profileDao;
    private final RoleDao roleDao;


    public ProfileServiceImpl(ProfileDao profileDao, RoleDao roleDao) {
        this.profileDao = profileDao;
        this.roleDao = roleDao;
    }


    @Override
    public boolean existsByUsername(String username) {
        if (findByUsername(username) == null) {
            return false;
        } else return true;
    }

    @Override
    public Profile findByUsername(String username) {
        LOGGER.info("find by username:  " + username);
        return profileDao.findByUsername(username);
    }

    @Override
    public void createNewProfile(String username, String password) {
        profileDao.save(Profile.builder()
                .username(username)
                .password(password)
                .build());
        LOGGER.info("create new profile  " + username);
    }

    @Override
    public void save(Profile profile) {
        if (!existsByUsername(profile.getUsername())) {
            profileDao.save(profile);
            LOGGER.info(String.format("save profile : %s  username: %s", profile.getId(), profile.getUsername()));
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete profile  " + id);
        profileDao.delete(profileDao.getById(id));
    }

    @Override
    public void update(Profile profile) {

        LOGGER.info("update profile  " + profile.getId());
        Profile profileInDB = profileDao.getById(profile.getId());
        profileInDB.setUsername(profile.getUsername());
        profileInDB.setPassword(profile.getPassword());
        profileDao.save(profileInDB);
    }

    @Override
    public Profile findByID(Long id) {
        LOGGER.info("findByID profile  " + id);
        return profileDao.getById(id);
    }

    @Override
    public List<Profile> getAll() {
        LOGGER.info("getAll profile  ");
        return profileDao.findAll();
    }

    @Override
    public void getRoleAdmin(Profile profile) {
        profile.getRoles().add(roleDao.getById(2l));
    }
}
