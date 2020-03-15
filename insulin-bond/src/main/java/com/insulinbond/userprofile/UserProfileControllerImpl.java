package com.insulinbond.userprofile;

import com.insulinbond.userprofile.service.UserProfileClientService;
import com.insulinbond.userprofile.service.UserProfileResDO;

import javax.inject.Inject;

/**
 * Implementation of the user profile service
 */
public class UserProfileControllerImpl implements UserProfileController {

    @Inject
    UserProfileClientService userProfileClientService;


    @Override
    public UserProfileRes retrieveUserProfile() {

        UserProfileResDO userProfileResDO = userProfileClientService.retrieveAllUserDetail();
        UserProfileRes userProfileRes = null;

        userProfileRes.setFirstName(userProfileResDO.getFirstName());
        userProfileRes.setLastName(userProfileResDO.getLastName());
        userProfileRes.setEmail(userProfileResDO.getEmail());
        userProfileRes.setPassword(userProfileResDO.getPassword());
        userProfileRes.setUsername(userProfileResDO.getUsername());

        return userProfileRes;

    }



}