package com.insulinbond.userprofile.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User Profile Response Data Object
 */
@Document(collection = "UserProfileResDO")
public class UserProfileResDO {


    /**
     * the serial version uid
     */
    private static final long serialVersionUID = 1L;

    @Id // this will be unique Id in DB
    private String id;

    @NotNull
    @Valid
    private final String firstName;

    @NotNull
    @Valid
    private final String lastName;

    @NotNull
    @Valid
    private final String username;

    @NotNull
    @Valid
    private final String email;

    @NotNull
    @Valid
    private final String password;

    /**
     * Constructor
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param password
     */
    public UserProfileResDO(String id, @NotNull @Valid String firstName, @NotNull @Valid String lastName, @NotNull @Valid String username, @NotNull @Valid String email, @NotNull @Valid String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }



    /**
     * Get values
     */


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

