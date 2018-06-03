package com.uxpsystems;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class UxpsystemsApplication implements CommandLineRunner {

    /**The application logger**/
    private static final Logger log = LoggerFactory.getLogger(UxpsystemsApplication.class);

    @Autowired
    private UserService userService;

    public static void main(String[] args) {

        SpringApplication.run(UxpsystemsApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        //can also use data.sql file in templates to initialize the user data

        log.info("Create user in the database...");

        User user1 = new User();
        user1.setUsername("Jayden");
        user1.setPassword("Jayden");
        user1.setStatus("Activated");

        User user2 = new User();
        user2.setUsername("Zessie");
        user2.setPassword("Zessie");
        user2.setStatus("Activated");

        User user3 = new User();
        user3.setUsername("Thorn");
        user3.setPassword("Thorn");
        user3.setStatus("DeActivated");

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
