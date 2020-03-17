package com.insulinbond.users;

import com.insulinbond.authentication.UnauthorizedException;
import com.insulinbond.authentication.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("genill/api")
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
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String register(@Valid @RequestBody Users user, BindingResult result) throws UserCreationException;


    /**
     * User logout
     * @return
     */
    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public String logOut();

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
    public String login(@RequestBody Users user) throws UnauthorizedException;

    /**
     * Todo: will remove Get user By Username
     * @param username
     * @return
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public Users getUserByUserName(@PathVariable String username);
}
