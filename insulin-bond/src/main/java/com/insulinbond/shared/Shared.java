package com.insulinbond.shared;

import com.insulinbond.customErrorHandler.ApiRequestException;
import com.insulinbond.users.UserRepository;
import com.insulinbond.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service
public class Shared {

    @Inject
    private UserRepository userRepository;

    public Boolean checkUserByContext(String contextId) throws ApiRequestException {
        UUID context = UUID.fromString(contextId);
        User user = userRepository.findByContextId(context);
        if (user == null) {
            throw new ApiRequestException("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
