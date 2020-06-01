package com.insulinbond.users;

import com.insulinbond.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * User Repository
 *
 * Created by Anish on May 28, 2020
 */

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *  retrieve a user from email
     *
     * @param email
     * @return User object
     */
    public User findByEmail(String email);

    /**
     * Find by contextID will be use in every call beside login and sign up
     * @param contextId
     * @return
     */
    public User findByContextId(UUID contextId);

    /**
     * TODO : REMOVE THIS AFTER UNDERSTANDING
     * retrieve a user by first name
     *
     * @param firstName
     * @return User object
     */
    @Query(value = "SELECT first_name FROM users where first_name=?1", nativeQuery = true)
    public String findUserByFirstName(String firstName);
}
