package com.insulinbond.userprofile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The Response Object
 */
public class UserProfileRes {

    /**
     * the serial version uid
     */
    private static final long serialVersionUID = 1L;


    @NotNull
    @Valid
    private String firstName;

    @NotNull
    @Valid
    private String lastName;

    @NotNull
    @Valid
    private String username;

    @NotNull
    @Valid
    private String email;

    @NotNull
    @Valid
    private String password;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
