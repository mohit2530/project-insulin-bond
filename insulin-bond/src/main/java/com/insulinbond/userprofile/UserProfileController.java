package com.insulinbond.userprofile;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * User profile controller
 */

@RestController
@CrossOrigin
@RequestMapping(value = "user/profile")
public interface UserProfileController {

    @RequestMapping(value = "/list", method = {RequestMethod.POST},
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserProfileRes retrieveUserProfile();
}
