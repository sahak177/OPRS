package com.example.oprs.repository.impl;

import com.example.oprs.mappers.RoleMapper;
import com.example.oprs.pojo.Role;
import com.example.oprs.pojo.RoleType;
import com.example.oprs.pojo.User;
import com.example.oprs.repository.UserRepository;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@PropertySource("classpath:pass.properties")
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final Environment properties;


    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, Environment properties) {
        this.jdbcTemplate = jdbcTemplate;
        this.properties = properties;
    }

    @Override
    public User getUserByEmail(String username) {

        String sf1 = "select * from user where email = ?";
        User user = jdbcTemplate.queryForObject(sf1,
                new Object[]{username},
                new BeanPropertyRowMapper<User>(User.class));

        String rol = "SELECT * FROM ROLE inner JOIN USER_ROLE ON ROLE.ID=USER_ROLE.ROLE_ID AND USER_ID=? ";
        List<Role> roles = jdbcTemplate.query(
                rol,
                new Object[]{user.getId()},
                new RoleMapper());
        user.setRoles(roles);

        String tok = "SELECT * FROM Tokens where user_id=? ";
        List<String> tokens = jdbcTemplate.query(
                tok,
                new Object[]{user.getId()},
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String token = rs.getString("token");
                        return token;
                    }
                });
        user.setTokens(tokens);

        return user;
    }

    @Override
    public boolean save(User user) {
        String defaultRole = RoleType.ROLE_USER.name();
        String str = "insert into user(social_number,first_name,last_name,email,password) values (?, ?, ?, ?, ?)";

        String strRole = "insert into user_role values ((select id from user where email=?)," +
                "( select id from role where role =?) );";

        jdbcTemplate.update(
                str, user.getSocialSecurityNumber(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        jdbcTemplate.update(
                strRole, user.getEmail(), defaultRole);
        return true;
    }

    @Override
    public boolean saveFromAdmin(User officer) {

        String str = "insert into user(social_number,first_name,last_name,email,password) values (?, ?, ?, ?, ?)";

        String strRole = "insert into user_role values ((select id from user where email=?)," +
                "( select id from role where role =?) );";

        jdbcTemplate.update(
                str, officer.getSocialSecurityNumber(), officer.getFirstName(), officer.getLastName(), officer.getEmail(), officer.getPassword());
        for (Role role : officer.getRoles()) {
            jdbcTemplate.update(
                    strRole, officer.getEmail(), role.getRoleName());
        }

        return true;
    }

    @Override
    public boolean updatePassword(String encodedNewPassword, String currentUser) {
        String str = "UPDATE user SET password=? where email=? ";
        jdbcTemplate.update(
                str, encodedNewPassword, currentUser);
        return true;
    }

    public boolean addToken(Long userId, String token) {
        String strToken = "insert into tokens (token,user_id) values (?,?);";
        jdbcTemplate.update(
                strToken, token, userId);
        return false;
    }
}
