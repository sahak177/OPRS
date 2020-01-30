package com.example.oprs.repository.impl;


import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:pass.properties")
public class InitializeDatabase {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final Environment properties;

    public InitializeDatabase(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, Environment properties) {

        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.properties = properties;
        run();
    }

    private void run() {
        //get from properties file : pass.properties
        String email= properties.getProperty("super.admin.email");
        String password = properties.getProperty("super.admin.password");
        String firstName= properties.getProperty("super.admin.firstName");
        String lastName= properties.getProperty("super.admin.lastName");
        String socialNumber= properties.getProperty("super.admin.socialNumber");
        String encodedPassword = passwordEncoder.encode(password);

        //'USER_ROLE'
        jdbcTemplate.execute("drop table IF EXISTS user_role  ");
        jdbcTemplate.execute("drop table IF EXISTS Role ");
        jdbcTemplate.execute("drop table IF EXISTS user ");

        jdbcTemplate.execute("create table Role\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\trole_name varchar(225) not null,\n" +
                "\tconstraint role_pk\n" +
                "\tprimary key (id)," +
                "\tCONSTRAINT uc_role_id UNIQUE (role_name));");

        jdbcTemplate.execute("create table USER\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tsocial_number bigint not null,\n" +
                "\tfirst_name varchar(225) not null,\n" +
                "\tlast_name varchar(225) not null,\n" +
                "\temail varchar(225) not null,\n" +
                "\tpassword varchar(225) not null,\n" +
                "\tconstraint user_pk\n" +
                "\t\tprimary key (id),\n" +
                "\tCONSTRAINT uc_email UNIQUE (email)\n" +
                ");");

        jdbcTemplate.execute("create table User_Role\n" +
                "(\n" +
                "\tuser_id int not null,\n" +
                "\trole_id int not null,\n" +
                "\tconstraint role_fk foreign key (role_id) references role(id),\n" +
                "\tconstraint user_fk foreign key (user_id) references user(id),\n" +
                "\tCONSTRAINT uc_user_id__role_id UNIQUE (user_id,role_id)\n" +
                ");");


        String st = "insert into role(role_name)values(?);";

        String strUser = "insert into user(social_number,first_name,last_name,email,password)values(?,?,?,?,?);";

        String strRole = "insert into user_role (user_id,role_id) values ((select id from user where email=?),\n" +
                "(select id from role where role_name ='ADMIN' ));";

        jdbcTemplate.update(
                st, "ADMIN");

        jdbcTemplate.update(
                st, "USER_ROLE");



        jdbcTemplate.update(
                strUser, socialNumber, firstName, lastName, email, encodedPassword);

        jdbcTemplate.update(
                strRole, email);

    }
}
