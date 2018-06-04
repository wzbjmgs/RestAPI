package com.uxpsystems;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserBuilder;
import com.uxpsystems.assignment.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", authorities));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void findAllUsersShouldReturnAllUsers() {
        List<User> users = userRepository.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.get(0).getUsername().equals("Jayden"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void findSingleUsersShouldReturnOneUsers() {
        User user = userRepository.findById(1L);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getUsername().equals("Jayden"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createUserShouldReturnSuccess() {
        User newUser = new UserBuilder()
                .id(1L)
                .username("Stark")
                .password("Stark")
                .status("Activated")
                .build();
        User user = userRepository.save(newUser);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getUsername().equals("Stark"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateUserShouldReturnSuccess() {
        User user = userRepository.findById(1L);
        user.setStatus("Deactivated");
        userRepository.updateUser(1L, "Deactivated");
        User updatedUser = userRepository.findById(1L);
        Assert.assertNotNull(updatedUser);
        Assert.assertTrue(updatedUser.getStatus().equals("Deactivated"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteSingleUserShouldNotFoundUser() {
        userRepository.deleteById(1L);
        User user = userRepository.findById(1L);
        Assert.assertNull(user);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteAllUserShouldReturnNullUserSet() {
        userRepository.deleteAll();
        List<User> users = userRepository.findAll();
        Assert.assertTrue(users.size() == 0);
    }
}
