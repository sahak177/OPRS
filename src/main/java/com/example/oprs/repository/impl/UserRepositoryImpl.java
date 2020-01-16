package com.example.oprs.repository.impl;

import com.example.oprs.model.User;
import com.example.oprs.repository.UserRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private  final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByEmail(String username) {

        String sf1="select * from user where email = ?";

        User user=jdbcTemplate.queryForObject(sf1,
                new Object[] {username},
                new BeanPropertyRowMapper< User >(User.class));

        return user;
    }

    @Override
    public void save(User user) {

        jdbcTemplate.update(
                "INSERT INTO USER(first_name,last_name,email,password) VALUES (?, ?, ?, ?)", user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());

    }
}
