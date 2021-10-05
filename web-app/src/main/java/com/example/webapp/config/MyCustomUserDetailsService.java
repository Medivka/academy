package com.example.webapp.config;

import com.example.webapp.dao.RoleDao;
import com.example.webapp.model.Profile;
import com.example.webapp.model.Role;
import com.example.webapp.service.impl.ProfileServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sacuta V.A.
 */


@Service
@Transactional
public class MyCustomUserDetailsService implements UserDetailsService {

    private final RoleDao roleDao;
    private final ProfileServiceImpl profileService;

    public MyCustomUserDetailsService(RoleDao roleDao, ProfileServiceImpl profileService) {
        this.roleDao = roleDao;
        this.profileService = profileService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileService.findByUsername(username);
        if (profile == null) {
            throw new UsernameNotFoundException("profile not found");
        }
        return profile;
    }

    public boolean saveUser(Profile profile) {
        if (!profileService.existsByUsername(profile.getUsername())) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.getById(1L));
            profile.setRoles(roles);
            profileService.save(profile);
            return true;
        }
        return false;
    }
}