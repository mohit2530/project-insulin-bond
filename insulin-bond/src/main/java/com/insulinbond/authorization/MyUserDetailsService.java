package com.insulinbond.authorization;

import com.insulinbond.users.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;

/*
* MyUserDetailsService implements UserDetailsService, required for Spring Security.
*
* Created by Anish Niroula, May 29 2020
* */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;

    /*
    * Spring Security load User by username, is required to verify the user authentication
    * Takes user repository to check if the user is exist or not.
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.insulinbond.users.model.User user = userRepository.findByEmail(username);
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
