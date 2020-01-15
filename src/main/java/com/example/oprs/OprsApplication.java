package com.example.oprs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class OprsApplication implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public OprsApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(OprsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        jdbcTemplate.execute("create table IF NOT EXISTS user\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tfirst_name varchar(225) not null,\n" +
                "\tlast_name varchar(225) not null,\n" +
                "\temail varchar(225) not null,\n" +
                "\tpassword varchar(225) not null,\n" +
                "\tconstraint user_pk\n" +
                "\t\tprimary key (id)\n" +
                ");\n" +
                "                ");


    }

}
