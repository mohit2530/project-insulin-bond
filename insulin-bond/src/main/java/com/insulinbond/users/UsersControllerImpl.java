package com.insulinbond.users;

import com.insulinbond.authentication.*;
import org.bouncycastle.util.encoders.Base64;
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

    @Inject
    public UsersControllerImpl(RequestAuthProvider authentication, JwtTokenHandler tokenHandler,
                               PasswordHasher passwordHasher, UsersRepository userRepo) {
        this.authentication = authentication;
        this.tokenHandler = tokenHandler;
        this.passwordHasher = passwordHasher;
        this.userRepo = userRepo;
    }

    /**
     * Register the user and save the data to Database
     * @param user
     * @param result
     * @return
     * @throws UserCreationException
     */
    @Override
    public String register(@Valid @RequestBody  Users user, BindingResult result) throws UserCreationException {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        }
        user.setRole("user");
        saveUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getRole(),
                user.getEmail());
        return user.getUsername();
    }

    /**
     * Logout and end user session
     * @return
     */
    @Override
    public String logOut() {
        authentication.currentUserLogOff();
        return "logout Success";
    }

    /**
     * Take the user information, login and establish the session
     * @param user
     * @return username
     * @throws UnauthorizedException
     */
    @Override
    public String login(@RequestBody Users user) throws UnauthorizedException {
        if (authentication.currentUserSignIn(user.getUsername(), user.getPassword()) ) {
            Users currentUser = authentication.getCurrentUser();
            tokenHandler.createToken(user.getUsername(), currentUser.getRole());

            return user.getUsername();

        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * Todo; Will remove => Get user by username
     * @param username
     * @return
     */
    @Override
    public Users getUserByUserName(String username) {
        return userRepo.findByUsername(username);
    }

    /**
     * Save the user in Database
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param role
     * @param email
     * @return save user
     */
    private Users saveUser(String firstName, String lastName, String userName, String password, String role, String email){
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        LocalDateTime accountCreatedDateTime = LocalDateTime.now();

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(hashedPassword);
        user.setSalt(saltString);
        user.setAccountCreatedDateTime(accountCreatedDateTime);
        user.setRole(role);
        user.setEmail(email);

        return userRepo.save(user);
    }
}
