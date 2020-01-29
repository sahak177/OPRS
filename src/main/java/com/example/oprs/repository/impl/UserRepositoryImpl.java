package com.example.oprs.repository.impl;

import com.example.oprs.model.Role;
import com.example.oprs.model.User;
import com.example.oprs.repository.UserRepository;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private  final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByEmail(String username) {

        String sf1="select * from user where email = ?";
        User user= jdbcTemplate.queryForObject(sf1,
                new Object[] {username},
                new BeanPropertyRowMapper< User >(User.class));
        String sf2="SELECT * FROM ROLE inner JOIN USER_ROLE ON ROLE.ID=USER_ROLE.ROLE_ID AND USER_ID=? ";
        List<Role> roles = jdbcTemplate.query(
                sf2,
                new Object[] {user.getId()},
                new RowMapper<Role>() {
                    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Role c = new Role();
                        c.setId(rs.getLong(1));
                        c.setRoleName(rs.getString(2));
                        return c;
                    }
                });
        user.setRoles(roles);

        return user;
    }

    @Override
    public boolean save(User user) {

        String str="insert into user(social_number,first_name,last_name,email,password) values (?, ?, ?, ?, ?)";

        String strRole="insert into user_role values ((select id from user where email=?)," +
                "( select id from role where role_name ='USER_ROLE') );";

        jdbcTemplate.update(
                str,user.getSocialNumber(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
        jdbcTemplate.update(
                strRole, user.getEmail());
         return true;
    }
}
