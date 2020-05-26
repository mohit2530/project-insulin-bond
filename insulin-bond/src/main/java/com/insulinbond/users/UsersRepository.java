package com.insulinbond.users;

import com.insulinbond.users.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    public Users findByEmail(String email);
}
