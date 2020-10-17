package com.insulinbond.shared;

import com.insulinbond.authorization.util.JwtUtil;
import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.users.UserRepository;
import com.insulinbond.users.model.User;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Share or known as common file which can be extends to other service file
 * or Inject when needed
 */
@Service
public class Shared {

    @Inject
    private UserRepository userRepository;

    @Inject
    private JwtUtil jwtUtil;

    /**
     * Check the user by ContextID
     * @param contextId
     * @return
     * @throws ApiRequestException
     */
    public User checkUserByContext(String contextId) throws ApiRequestException {
        UUID context = UUID.fromString(contextId);
        User user = userRepository.findByContextId(context);
        if (user == null) {
            throw new ApiRequestException("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    /**
     * By the http header it will have email in the JWT Token, which can be useful
     * UI doesn't have to pass the email specifically
     * @param request
     * @return
     */
    public String getEmailByToken(HttpServletRequest request){
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String email = null;
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtUtil.extractUsername(jwt);
        }

        return email;
    }

    public String getContextIdFromHeader(HttpServletRequest request) {
        final String context = request.getHeader("Log");
        String contextId = new String(Base64.decode(context));
        return contextId;
    }
}
