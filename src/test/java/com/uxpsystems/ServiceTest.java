package com.uxpsystems;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserBuilder;
import com.uxpsystems.assignment.service.UserService;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

    @Autowired
    protected UserService userService;

    @Before
    public void init(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", authorities));
        Assert.assertNotNull(userService);
    }

    @Test
    public void createNewUser() throws Exception{
        User newUser = new UserBuilder()
                .id(1L)
                .username("Stark")
                .password("Stark")
                .status("Activated")
                .build();
        userService.saveUser(newUser);
        User user = userService.findById(4L);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getStatus().equals("Activated"));
        Assert.assertTrue(user.getUsername().equals("Stark"));
    }

    @Test
    public void testGetUserById(){
        User user = userService.findById(1L);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getUsername().equals("Jayden"));
    }

    @Test
    public void testGetAllUser(){
        List<User> users = userService.findAllUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.get(0).getUsername().equals("Jayden"));
    }

    @Test
    public void testUpdateUserStatus(){
        User user = userService.findById(1L);
        user.setStatus("Deactivated");
        userService.updateUser(user);
        User updatedUser = userService.findById(1L);
        Assert.assertNotNull(updatedUser);
        Assert.assertTrue(updatedUser.getStatus().equals("Deactivated"));
    }

    @Test
    public void testDeleteUserById()throws Exception{
        userService.deleteUserById(4L);
        User user = userService.findById(4L);
        Assert.assertNull(user);
    }
}
