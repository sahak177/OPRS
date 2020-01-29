package com.example.oprs.service;

import com.example.oprs.model.User;
import com.example.oprs.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/data.sql")
public class TestUserServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testInit() {
        Long socialNumber= 22222222L;
        String email = "Bruce@gmail.com";
        String password = "EncodedPassword";
        String firstName = "Bruce";
        String lastName = "Lee";

        User user = new User();
        user.setSocialNumber(socialNumber);
        user.setEmail(email);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);

        Assert.assertTrue(userService.add(user));
    }


    @Test(expected = NullPointerException.class)
    public void testAddByNull() {
        User user = new User();
        userService.add(user);
    }

    @Test
    public void testFindByUserName() {

        String email = "James@gmail.com";
        String firstName = "James";

        Assert.assertTrue(userService.getUserByEmail(email).getFirstName().equals(firstName));
    }
}
