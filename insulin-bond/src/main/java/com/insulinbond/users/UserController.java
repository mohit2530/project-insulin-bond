package com.insulinbond.users;

import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.exception.UnauthorizedException;
import com.insulinbond.exception.UserCreationException;
import com.insulinbond.shared.EndPointConnection;
import com.insulinbond.users.model.UserLogin;
import com.insulinbond.users.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserController Interface
 *
 * Created by Anish on May 28, 2020
 */

@RestController
@ApiOperation(value = "Api to retrieve user data")
public interface UserController extends EndPointConnection {
    /**
     * Register the user
     * @param user
     * @param result
     * @return
     * @throws UserCreationException
     */
    @ApiOperation(notes = "Register Current User",value = "User object")
    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String registerCurrentUser(@Valid @RequestBody User user, BindingResult result) throws ApiRequestException;


    /**
     * Todo: need to properly implement this into using a POST request
     * Log out the current user
     *
     */
    @ApiOperation(value = "Logout the current user")
    @RequestMapping(value ="/{contextId}/logout", method = RequestMethod.GET)
    public void logOutCurrentUser(@PathVariable String contextId);

    /**
     * Take the user information and login and establish the session
     * @param user
     * @return
     * @throws UnauthorizedException
     */
    @ApiOperation(notes = "Login the current user", value = "User object")
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<?> loginCurrentUser(@RequestBody UserLogin user) throws ApiRequestException;

    /**
     * Retrieve user by Email address
     *
     * @param email
     * @return user object
     */
    @ApiOperation(value = "Retrieve user by email address", notes = "User object")
    @RequestMapping(value = "{contextId}/email/{email}", method = RequestMethod.GET)
    public User retrieveUserByEmail(@PathVariable String contextId, @PathVariable String email) throws ApiRequestException ;

    /**
     * Retrieve user by first name
     *
     * @param firstName
     * @return String first name
     */
    @ApiOperation(value = "Retrieve user by first name", notes = "User object")
    @RequestMapping(value = "/firstname/{firstName}", method = RequestMethod.GET)
    public String retrieveUserByFirstName(@PathVariable String firstName);

}
