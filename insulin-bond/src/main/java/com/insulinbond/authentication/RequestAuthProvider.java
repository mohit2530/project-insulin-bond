package com.insulinbond.authentication;

import com.insulinbond.users.UserHelperService;
import com.insulinbond.users.model.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class RequestAuthProvider implements AuthProvider {
    private HttpServletRequest request;
    private UserHelperService usersHelperService;
    public final static String USER_KEY = "appCurrentUser";

    @Inject
    public RequestAuthProvider(HttpServletRequest request, UserHelperService usersHelperService) {
        this.request = request;
        this.usersHelperService = usersHelperService;
    }

    /**
     * Returns true if a current user is logged in.
     *
     * @return true if user is logged in
     */
    @Override
    public boolean isCurrentUserLoggedIn() {
        return (request.getAttribute(USER_KEY) != null);
    }

    /**
     * Returns the currently signed in user.
     *
     * @return the currently signed in user
     */
    @Override
    public User getCurrentUser() {
        return (User) request.getAttribute(USER_KEY);
    }

    /**
     * Signs in a user using the given email and password
     *
     * @param email
     * @param password the given password
     * @return true if user was successfully signed in
     */
    @Override
    public boolean currentUserSignIn(String email, String password) {
        User authenticatedUser = usersHelperService.getValidUserWithPassword(email, password);
        if (authenticatedUser != null) {
            request.setAttribute(USER_KEY, authenticatedUser);
            return true;
        }
        return false;
    }

    /**
     * Sign out the currently signed in user
     */
    @Override
    public void currentUserLogOff() {
        request.removeAttribute(USER_KEY);

    }

	/**
	 * Change the current signed in user's password.
	 * @param existingPassword the current user's current password
	 * @param newPassword the new password for the current user
	 * @return true, if successful
	 */
    @Override
    public boolean changeCurrentUserPassword(String existingPassword, String newPassword, String email) {
        User userFromSession = (User) request.getAttribute(USER_KEY);
        if (userFromSession == null) {
            return false;
        }
        User userFromDb = usersHelperService.getValidUserWithPassword(userFromSession.getEmail(), existingPassword);
        if (userFromDb != null && userFromDb.getEmail().equals(userFromDb.getEmail())) {
            usersHelperService.changePassword(userFromSession, newPassword, email);
            return true;
        }
        return false;
    }

	/**
	 * Checks to see if the current user has one of the given roles
	 * @param roles the roles to check for
	 * @return true, if the user has one of the roles
	 */
    @Override
    public boolean isCurrentUserWithRole(String[] roles) {
        User currentUser = getCurrentUser();
        if (currentUser != null && roles != null) {
            return Arrays.asList(roles).contains(currentUser.getRole());
        }
        return false;
    }
}
