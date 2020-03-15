package com.insulinbond.userprofile.service;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


/**
 * UserProfileClientService Interface to handle mongodb connection
 */
public interface UserProfileClientSvc extends MongoRepository<UserProfileResDO, String> {

    /**
     * List Profile Service to return all user details
     *
     */
    public List<UserProfileResDO> retrieveAllUserDetail();


    /**
     * List Profile Service to return a detail of user by id
     *
     */

    public Optional<UserProfileResDO> findById(String username);

}
