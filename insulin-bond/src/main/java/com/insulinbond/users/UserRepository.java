package com.insulinbond.users;

import com.insulinbond.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User Repository
 *
 * Created by Anish on May 28, 2020
 */

public interface UserRepository extends JpaRepository<User, String> {

    /**
     *  retrieve a user from email
     *
     * @param email
     * @return User object
     */
    public User findUserByEmailAddress(String email);

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
