package com.insulinbond.users;

import com.insulinbond.authentication.JwtTokenHandler;
import com.insulinbond.authentication.PasswordHasher;
import com.insulinbond.authentication.RequestAuthProvider;
import com.insulinbond.customErrorHandler.ApiExceptionService;
import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.exception.UnauthorizedException;
import com.insulinbond.exception.UserCreationException;
import com.insulinbond.users.model.UserLogin;
import com.insulinbond.users.model.User;
import java.time.LocalDateTime;
import javax.validation.Valid;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * UserControllerImplementation Class
 *
 * Created by Anish on May 28, 2020
 */

@Service
public class UserControllerImpl implements UserController {

    private RequestAuthProvider authentication;
    private JwtTokenHandler tokenHandler;
    private PasswordHasher passwordHasher;
    private UserRepository userRepo;
    private ApiExceptionService apiExceptionService;

    /**
     * Constructor
     */
    public UserControllerImpl(RequestAuthProvider authentication, JwtTokenHandler tokenHandler,
                               PasswordHasher passwordHasher, UserRepository userRepo,
                               ApiExceptionService apiExceptionService) {
        this.authentication = authentication;
        this.tokenHandler = tokenHandler;
        this.passwordHasher = passwordHasher;
        this.userRepo = userRepo;
        this.apiExceptionService = apiExceptionService;
    }

    /**
     * Register the user and save the data to Database
     *
     * @param user
     * @param result
     * @return the email address
     * @throws UserCreationException
     */
    @Override
    public String registerCurrentUser(@Valid @RequestBody User user, BindingResult result) throws ApiRequestException {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw apiExceptionService.throwApiException(errorMessages, HttpStatus.BAD_REQUEST);
        }
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw apiExceptionService.throwApiException("Email Already Exist", HttpStatus.BAD_REQUEST);
        }
        saveUserInDatabase(user.getFirstName(), user.getLastName(), user.getPassword(), "user",
                user.getEmail());
        return user.getEmail();
    }

    /**
     * Logout and end user session
     *
     */
    @Override
    public void logOutCurrentUser() {
        authentication.currentUserLogOff();
    }

    /**
     * Take the user information, login and establish the session
     * @param user
     * @return email
     * @throws UnauthorizedException
     */
    @Override
    public String loginCurrentUser(@RequestBody UserLogin user) throws ApiRequestException {
        if (authentication.currentUserSignIn(user.getEmail(), user.getPassword()) ) {
            User currentUser = authentication.getCurrentUser();
            tokenHandler.createToken(user.getEmail(), currentUser.getRole());
            return user.getEmail();
        } else {
            throw apiExceptionService.throwApiException("Login Failed.", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Retrieve the user by the first name
     *
     * @param firstName
     * @return
     */
    @Override
    public String retrieveUserByFirstName(@PathVariable String firstName) {
        return userRepo.findUserByFirstName(firstName);
    }

    /**
     * TODO: REMOVE WHEN UNDERSTOOD
     * Retrieve a user by the email
     *
     * @param email
     * @return user object
     */
    @Override
    public User retrieveUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Save the user in database
     *
     * @param firstName
     * @param lastName
     * @param password
     * @param role
     * @param email
     * @return user object
     */
    private User saveUserInDatabase(String firstName, String lastName, String password, String role, String email){
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        LocalDateTime accountCreatedDateTime = LocalDateTime.now();

        User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(hashedPassword);
            user.setSalt(saltString);
            user.setAccountCreatedDateTime(accountCreatedDateTime);
            user.setRole(role);
            user.setEmail(email);
        return userRepo.save(user);
    }
}
