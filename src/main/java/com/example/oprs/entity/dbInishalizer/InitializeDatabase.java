package com.example.oprs.entity.dbInishalizer;


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
        dropAll();
        creatRole();
        creatUsers();
        creatUserRole();
        creatTokens();
        creatRequestInfo();
        init();
    }

    private void dropAll() {
        jdbcTemplate.execute("drop table IF EXISTS user_role  ");
        jdbcTemplate.execute("drop table IF EXISTS Role ");
        jdbcTemplate.execute("drop table IF EXISTS request_Info ");
        jdbcTemplate.execute("drop table IF EXISTS tokens ");
        jdbcTemplate.execute("drop table IF EXISTS user ");
    }

    private void creatRole() {
        jdbcTemplate.execute("create table Role\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\trole_name varchar(225) not null,\n" +
                "\tconstraint role_pk\n" +
                "\tprimary key (id)," +
                "\tCONSTRAINT uc_role_id UNIQUE (role_name));");
    }

    private void creatUsers() {
        jdbcTemplate.execute("create table USER\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tsocial_number bigint not null,\n" +
                "\tfirst_name varchar(225) not null,\n" +
                "\tlast_name varchar(225) not null,\n" +
                "\temail varchar(225) not null,\n" +
                "\tpassword varchar(225) not null,\n" +
                "\tisActive tinyint DEFAULT false,\n" +
                "\tcreatTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                "\tconstraint user_pk\n" +
                "\tprimary key (id),\n" +
                "\tCONSTRAINT uc_email UNIQUE (email)\n" +
                ");");
    }

    private void creatUserRole() {
        jdbcTemplate.execute("create table User_Role\n" +
                "(\n" +
                "\tuser_id int not null,\n" +
                "\trole_id int not null,\n" +
                "\tconstraint role_fk foreign key (role_id) references role(id),\n" +
                "\tconstraint user_fk foreign key (user_id) references user(id),\n" +
                "\tCONSTRAINT uc_user_id__role_id UNIQUE (user_id,role_id)\n" +
                ");");
    }

    private void creatTokens() {
        jdbcTemplate.execute("create table tokens\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\ttoken varchar(225) not null,\n" +
                "\tuser_id int not null,\n" +
                "\tconstraint token_pk\n" +
                "\tprimary key (id)," +
                "\tconstraint token_fk foreign key (user_id) references user(id)\n" +
                ");");
    }

    private void creatRequestInfo() {
        jdbcTemplate.execute("create table request_Info\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tsocial_number bigint not null,\n" +
                "\tfirst_name varchar(225) not null,\n" +
                "\tlast_name varchar(225) not null,\n" +
                "\tgender tinyint not null,\n" +
                "\tbirthDate TIMESTAMP not null,\n" +
                "\tbirthCountry varchar(225) not null,\n" +
                "\tphone_number bigint not null,\n" +
                "\tpost_code int not null,\n" +
                "\temail varchar(225) not null,\n" +
                "\taddress varchar(225) not null,\n" +
                "\told_passport_number varchar(45) ,\n" +
                "\tfromWhom  varchar(45) ,\n" +
                "\tgivenDate TIMESTAMP ,\n" +
                "\texpireDate TIMESTAMP ,\n" +
                "\tphoto varchar(225) not null,\n" +
                "\tpurpose ENUM('NEW','EXPIRED','LOST_OR_DAMAGED'),\n" +
                "\ttoken varchar(225) not null,\n" +
                "\tstatus ENUM('SUBMITTED', 'REJECTED','QUERIED','ISSUED','DELIVERED','DECLINED'),\n" +
                "\tuser_id int ,\n" +
                "\tcreatTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                "\tconstraint requestInfo_pk\n" +
                "\tprimary key (id),\n" +
                "\tconstraint request_Info_fk foreign key (user_id) references user(id)\n" +
                ");");
    }

    private void init() {
        //get from properties file : pass.properties
        String email = properties.getProperty("super.admin.email");
        String password = properties.getProperty("super.admin.password");
        String firstName = properties.getProperty("super.admin.firstName");
        String lastName = properties.getProperty("super.admin.lastName");
        String socialNumber = properties.getProperty("super.admin.socialNumber");
        String encodedPassword = passwordEncoder.encode(password);

        String st = "insert into role(role_name)values(?);";

        String strUser = "insert into user(social_number,first_name,last_name,email,password)values(?,?,?,?,?);";

        String strRole = "insert into user_role (user_id,role_id) values ((select id from user where email=?),\n" +
                "(select id from role where role_name ='ROLE_ADMIN' ));";
        String strRoleUser = "insert into user_role (user_id,role_id) values ((select id from user where email=?),\n" +
                "(select id from role where role_name ='ROLE_USER' ));";
        jdbcTemplate.update(
                st, "ROLE_ADMIN");
        jdbcTemplate.update(
                st, "ROLE_USER");
        jdbcTemplate.update(
                st, "ROLE_OFFICER");
        jdbcTemplate.update(
                st, "ROLE_OVIR_OFFICER");

        jdbcTemplate.update(
                strUser, socialNumber, firstName, lastName, email, encodedPassword);

        jdbcTemplate.update(
                strRole, email);
        jdbcTemplate.update(
                strUser, socialNumber, firstName, lastName, "sahakyan17@gmail.com", encodedPassword);

        jdbcTemplate.update(
                strRoleUser, "sahakyan17@gmail.com");

    }


}
