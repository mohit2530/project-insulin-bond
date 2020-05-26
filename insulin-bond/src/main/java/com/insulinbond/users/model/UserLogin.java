package com.insulinbond.users.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserLogin {

    @ApiModelProperty(required = true)
    @Valid
    @NotNull
    private String email;

    @ApiModelProperty(required = true)
    @Valid
    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
