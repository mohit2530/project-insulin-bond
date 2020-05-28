package com.insulinbond.users;

import com.insulinbond.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, String> {
    public Users findByEmail(String email);

    // Todo: Remove this after better understanding of query
    @Query(value = "SELECT first_name FROM users where first_name=?1", nativeQuery = true)
    public String findByFirst(String firstName);
}
