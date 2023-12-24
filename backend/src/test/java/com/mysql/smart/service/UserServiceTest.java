package com.mysql.smart.service;

import com.mysql.smart.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @Resource
    private UserService service;
    @Test
    void register() {
        User user = new User("testUser","testPassword"); // Create a test user

        User registeredUser = service.register(user);
        assertNotNull(registeredUser);
    }

    @Test
    void login() {
        User user = new User("testUser","testPassword"); // Create a test user
        User loggedInUser = service.login(user.getUserName(), user.getPassword());
        assertNotNull(loggedInUser);
        assertEquals(user.getUserName(), loggedInUser.getUserName());

    }
}