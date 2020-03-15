package com.insulinbond.userprofile.service;


import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

/**
 * User Profile Client Service work with MongoDB
 */

@Service
public class UserProfileClientService {

    @Inject
    private UserProfileClientSvc userProfileClientSvc;


    /**
     * List Profile Service to return all user details
     *
     */
    public UserProfileResDO retrieveAllUserDetail() {

        UserProfileResDO userProfileResDO = new UserProfileResDO("123", "Mohit", "Paudyal", "mohit", "mohit@gmail.com", "123");

        return userProfileResDO;

    };


    /**
     * List Profile Service to return a detail of user by id
     */

    public Optional<UserProfileResDO> findById(String username) {
        return null;
    }
}
