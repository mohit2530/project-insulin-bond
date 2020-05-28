package com.insulinbond.users;

import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.exception.UnauthorizedException;
import com.insulinbond.exception.UserCreationException;
import com.insulinbond.users.model.UserLogin;
import com.insulinbond.users.model.Users;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("project/ib")
public interface UsersController {

    /**
     * Register the user
     * @param user
     * @param result
     * @return
     * @throws UserCreationException
     */
    @ApiOperation(notes = "Make sure to pass all the required field as body to sign up",
    value = "User Registration")
    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String registerCurrentUser(@Valid @RequestBody Users user, BindingResult result) throws ApiRequestException;


    /**
     * Todo: need to properly implement this into using a POST request
     * User logout
     * @return
     */
    @ApiOperation(value = "Logout")
    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public String logOutCurrentUser();

    /**
     * Take the user information and login and establish the session
     * @param user
     * @return
     * @throws UnauthorizedException
     */
    @ApiOperation(notes = "Takes the Email and Password to login the user", value = "Login User")
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String loginCurrentUser(@RequestBody UserLogin user) throws ApiRequestException;

    /**
     * @param email
     * @return
     */
    @ApiOperation(value = "Find User by Email")
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public Users getUserByEmail(@PathVariable String email);

    // Example
    @RequestMapping(value = "/firstname/{firstName}", method = RequestMethod.GET)
    public String findFirstName(@PathVariable String firstName);

}
