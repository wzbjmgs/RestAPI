package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.dao.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@CacheConfig(cacheNames = "users")
public interface UserService {

    @Cacheable()
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
