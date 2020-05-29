package com.insulinbond.authentication;

import com.insulinbond.users.model.User;

public interface AuthProvider {
    /**
     * Returns true if a current user is logged in.
     * @return true if user is logged in
     */
    boolean isCurrentUserLoggedIn();

    /**
     * Returns the currently signed in user.
     * @return the currently signed in user
     */
    User getCurrentUser();

    /**
     * Signs in a user using the given email and password
     * @param email the given email
     * @param password the given password
     * @return true if user was successfully signed in
     */
    boolean currentUserSignIn(String email, String password);

    /**
     * Sign out the currently signed in user
     */
    void currentUserLogOff();

    /**
     * Change the current signed in user's password.
     * @param existingPassword the current user's current password
     * @param newPassword the new password for the current user
     * @return true, if successful
     */
    boolean changeCurrentUserPassword(String existingPassword, String newPassword, String email);

    /**
     * Checks to see if the current user has one of the given roles
     * @param roles the roles to check for
     * @return true, if the user has one of the roles
     */
    boolean isCurrentUserWithRole(String[] roles);
}
