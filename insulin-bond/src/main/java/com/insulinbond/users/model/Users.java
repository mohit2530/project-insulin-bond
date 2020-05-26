package com.insulinbond.users.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.tomcat.jni.Local;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * The user class with data base integration alongside mongo db database.
 */
@Document(collection="users")
public class Users {

    /**
     * The user first name
     */
    @ApiModelProperty(required = true)
    @NotNull
    @Valid
    private String firstName;

    /**
     * The user last name
     */
    @ApiModelProperty(required = true)
    @NotNull
    @Valid
    private String lastName;

    /**
     * The user email
     */
    @ApiModelProperty(required = true)
    @NotNull
    @Valid
    @Indexed(unique = true, sparse = true)
    private String email;

    /**
     * The user role [ could be admin, user ]
     */
    private String role;

    /**
     * The user password.
     */
    @ApiModelProperty(required = true)
    @NotNull
    @Valid
    private String password;

    /**
     * The user password salt
     */
    private String salt;

    private LocalDateTime accountCreatedDateTime;

    /**
     * Get and Set below here for the above values
     *
     */

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDateTime getAccountCreatedDateTime() {
        return accountCreatedDateTime;
    }

    public void setAccountCreatedDateTime(LocalDateTime accountCreatedDateTime) {
        this.accountCreatedDateTime = accountCreatedDateTime;
    }
}
