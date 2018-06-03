package com.uxpsystems;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntergationTest {

    @Autowired
    protected UserService userService;


    @Before
    public void init(){
        Assert.assertNotNull(userService);
    }

    @Test
    public void createNewUser() throws Exception{

        User newUser = new User();
        newUser.setUsername("Stark");
        newUser.setPassword("Stark");
        newUser.setStatus("Activated");

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
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() == 4);
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
