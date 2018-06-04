package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.dao.User;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserService {

    @Secured({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    List<User> findAllUsers();

    @Secured({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    User findById(long id);

    @Secured("ROLE_ADMIN")
    User saveUser(User user);

    @Secured("ROLE_ADMIN")
    void updateUser(User user);

    @Secured("ROLE_ADMIN")
    void deleteUserById(long id);

    @Secured("ROLE_ADMIN")
    void deleteAllUsers();

    @Secured("ROLE_ADMIN")
    boolean isUserExist(User user);
}
