package com.example.oprs.repository.impl;

import com.example.oprs.model.User;
import com.example.oprs.repository.UserRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

        run();
    }

    private void run() {

        jdbcTemplate.execute("create table IF NOT EXISTS USER\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tfirst_name varchar(225) not null,\n" +
                "\tlast_name varchar(225) not null,\n" +
                "\temail varchar(225) not null,\n" +
                "\tpassword varchar(225) not null,\n" +
                "\tconstraint user_pk\n" +
                "\t\tprimary key (id)\n);");
    }

    @Override
    public User getUserByEmail(String username) {

        String sf1 = "select * from user where email = ?";

        return jdbcTemplate.queryForObject(sf1,
                new Object[]{username},
                new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public boolean save(User user) {
        String str = "insert into user(first_name,last_name,email,password) values (?, ?, ?, ?)";
        jdbcTemplate.update(
                str, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return true;
    }
}
