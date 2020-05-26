package com.insulinbond.users;

import com.insulinbond.authentication.*;
import com.insulinbond.customErrorHandler.*;
import com.insulinbond.exception.UnauthorizedException;
import com.insulinbond.exception.UserCreationException;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
public class UsersControllerImpl implements UsersController {

    private RequestAuthProvider authentication;
    private JwtTokenHandler tokenHandler;
    private PasswordHasher passwordHasher;
    private UsersRepository userRepo;
    private ApiExceptionService apiExceptionService;

    @Inject
    public UsersControllerImpl(RequestAuthProvider authentication, JwtTokenHandler tokenHandler,
                               PasswordHasher passwordHasher, UsersRepository userRepo,
                               ApiExceptionService apiExceptionService) {
        this.authentication = authentication;
        this.tokenHandler = tokenHandler;
        this.passwordHasher = passwordHasher;
        this.userRepo = userRepo;
        this.apiExceptionService = apiExceptionService;
    }

    /**
     * Register the user and save the data to Database
     * @param user
     * @param result
     * @return
     * @throws UserCreationException
     */
    @Override
    public String registerCurrentUser(@Valid @RequestBody Users user, BindingResult result) throws ApiRequestException {
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

        user.setRole("user");
        saveUser(user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole(),
                user.getEmail());
        return user.getEmail();
    }

    /**
     * Logout and end user session
     * @return
     */
    @Override
    public String logOutCurrentUser() {
        authentication.currentUserLogOff();
        return "logout Success";
    }

    /**
     * Take the user information, login and establish the session
     * @param user
     * @return email
     * @throws UnauthorizedException
     */
    @Override
    public String loginCurrentUser(@RequestBody Users user) throws ApiRequestException {
        if (authentication.currentUserSignIn(user.getEmail(), user.getPassword()) ) {
            Users currentUser = authentication.getCurrentUser();
            tokenHandler.createToken(user.getEmail(), currentUser.getRole());

            return user.getEmail();

        } else {
            throw apiExceptionService.throwApiException("Sorry Login could Not completed", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Todo; Will remove => Get user by email
     * @param email
     * @return
     */
    @Override
    public Users getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Save the user in Database
     * @param firstName
     * @param lastName
     * @param password
     * @param role
     * @param email
     * @return save user
     */
    private Users saveUser(String firstName, String lastName, String password, String role, String email){
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        LocalDateTime accountCreatedDateTime = LocalDateTime.now();

        Users user = new Users();
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
