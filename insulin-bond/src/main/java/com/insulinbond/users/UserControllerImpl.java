package com.insulinbond.users;

import com.insulinbond.authorization.MyUserDetailsService;
import com.insulinbond.authorization.PasswordHasher;
import com.insulinbond.authorization.util.JwtUtil;
import com.insulinbond.customErrorHandler.ApiExceptionService;
import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.exception.UnauthorizedException;
import com.insulinbond.exception.UserCreationException;
import com.insulinbond.shared.Shared;
import com.insulinbond.users.model.AuthenticationResponse;
import com.insulinbond.users.model.UserLogin;
import com.insulinbond.users.model.User;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.Valid;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * UserControllerImplementation Class
 * <p>
 * Created by Anish on May 28, 2020
 */

@Service
public class UserControllerImpl extends Shared implements UserController {

    private AuthenticationManager authenticationManager;
    private UserHelperService userHelperService;
    private MyUserDetailsService myUserDetailsService;
    private JwtUtil jwtUtil;
    private PasswordHasher passwordHasher;
    private UserRepository userRepo;
    private ApiExceptionService apiExceptionService;
//    private Shared shared;

    /**
     * Constructor
     */
    public UserControllerImpl(PasswordHasher passwordHasher, UserRepository userRepo,
                              ApiExceptionService apiExceptionService, JwtUtil jwtUtil,
                              MyUserDetailsService myUserDetailsService, UserHelperService userHelperService,
                              AuthenticationManager authenticationManager) {
        this.passwordHasher = passwordHasher;
        this.userRepo = userRepo;
        this.apiExceptionService = apiExceptionService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userHelperService = userHelperService;
        this.myUserDetailsService = myUserDetailsService;
//        this.shared = shared;
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
     */
    @Override
    public void logOutCurrentUser(String contextId) throws ApiRequestException {
        super.checkUserByContext(contextId);
    }

    /**
     * Take the user information, login and establish the session
     *
     * @param login
     * @return email
     * @throws UnauthorizedException
     */
    @Override
    public ResponseEntity<?> loginCurrentUser(@RequestBody UserLogin login) throws ApiRequestException {
        User user = userHelperService.getValidUserWithPassword(login.getEmail(), login.getPassword());
        if (user == null) {
            throw new ApiRequestException("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), user.getPassword()));
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(login.getEmail());

        User addContext = userRepo.findByEmail(login.getEmail());
        UUID context = UUID.randomUUID();
        addContext.setContextId(context);
        userRepo.save(addContext);
        String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, context));
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
    public User retrieveUserByEmail(String contextId, String email) throws ApiRequestException {
        User user = userRepo.findByEmail(email);
        if (!super.checkUserByContext(contextId) || user == null){
            throw new ApiRequestException("Sorry can't find a user", HttpStatus.BAD_REQUEST);
        }
        return user;
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
    private User saveUserInDatabase(String firstName, String lastName, String password, String role, String email) {
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
