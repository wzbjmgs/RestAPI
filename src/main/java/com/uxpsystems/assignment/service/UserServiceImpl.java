package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userService")
@CacheConfig(cacheNames = "users")
@Transactional
public class UserServiceImpl implements UserService {
    private AtomicLong atomicLong = new AtomicLong();

    @Autowired
    private UserRepository userRepository;

    @Cacheable()
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        user.setId(atomicLong.incrementAndGet());
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user.getId(), user.getStatus());
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean isUserExist(User user) {
        User u = userRepository.findById(user.getId());
        return u != null;
    }
}
