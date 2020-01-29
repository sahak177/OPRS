package com.example.oprs.repository;

import com.example.oprs.model.User;
import com.example.oprs.repository.impl.UserRepositoryImpl;
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
public class TestUserRepositoryImpl {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testInit() {
        Long socialNumber= 22222222L;
        String email = "Bruce17@gmail.com";
        String password = "EncodedPassword";
        String firstName = "Bruce";
        String lastName = "Lee";

        User user = new User();
        user.setSocialNumber(socialNumber);
        user.setEmail(email);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);

        Assert.assertTrue(userRepository.save(user));
    }


    @Test
    public void testFindByUserName() {

        String email = "James@gmail.com";
        String firstName = "James";

        Assert.assertTrue(userRepository.getUserByEmail(email).getFirstName().equals(firstName));
    }

}
