package com.insulinbond.users;

import com.insulinbond.authentication.PasswordHasher;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * User Helper Service, this will help the mapping other user classes to main Controller Implementation
 */
@Service
public class UsersHelperService {

    private PasswordHasher passwordHasher;
    private UsersRepository userRepo;

    @Inject
    public UsersHelperService(PasswordHasher passwordHasher, UsersRepository userRepo) {
        this.passwordHasher = passwordHasher;
        this.userRepo = userRepo;
    }


    /**
     * It will check if the given password is valid and matched with the store data
     *
     * @param username
     * @param password
     * @return
     */
    public Users getValidUserWithPassword(String username, String password) {
        Users user = userRepo.findByUsername(username);
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
     * @param username
     */
    public void changePassword(Users user, String newPassword, String username) {
        user = userRepo.findByUsername(username);
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
