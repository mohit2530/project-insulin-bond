package com.insulinbond.users;

import com.insulinbond.authentication.PasswordHasher;
import com.insulinbond.users.model.User;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * User Helper Service, this will help the mapping other user classes to main Controller Implementation
 */
@Service
public class UserHelperService {

    private PasswordHasher passwordHasher;
    private UserRepository userRepo;

    @Inject
    public UserHelperService(PasswordHasher passwordHasher, UserRepository userRepo) {
        this.passwordHasher = passwordHasher;
        this.userRepo = userRepo;
    }


    /**
     * It will check if the given password is valid and matched with the store data
     *
     * @param email
     * @param password
     * @return
     */
    public User getValidUserWithPassword(String email, String password) {
        User user = userRepo.findUserByEmailAddress(email);
        if (user != null) {
            String storeSalt = user.getSalt();
            String storePassword = user.getPassword();
            String hashedPasword = passwordHasher.computeHash(password, Base64.decode(storeSalt));
            if (storePassword.equals(hashedPasword)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Help to change Password
     *
     * @param user
     * @param newPassword
     * @param email
     */
    public void changePassword(User user, String newPassword, String email) {
        user = userRepo.findUserByEmailAddress(email);
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(newPassword, salt);
        String saltString = new String(Base64.encode(salt));

        if (user != null) {
            user.setPassword(hashedPassword);
            user.setSalt(saltString);
            userRepo.save(user);
        }
    }
}
