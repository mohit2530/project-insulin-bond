package com.insulinbond.users;

import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.exception.UnauthorizedException;
import com.insulinbond.exception.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("genill/api/")
public interface UsersController {

    /**
     * Register the user
     * @param user
     * @param result
     * @return
     * @throws UserCreationException
     */
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
    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public String logOutCurrentUser();

    /**
     * Take the user information and login and establish the session
     * @param user
     * @return
     * @throws UnauthorizedException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String loginCurrentUser(@RequestBody Users user) throws ApiRequestException;

}
