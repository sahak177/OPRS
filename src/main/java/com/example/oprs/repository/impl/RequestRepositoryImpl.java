package com.example.oprs.repository.impl;

import com.example.oprs.mappers.RequestMapper;
import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.pojo.User;
import com.example.oprs.repository.RequestRepository;
import com.example.oprs.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestRepositoryImpl implements RequestRepository {

    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;

    public RequestRepositoryImpl(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }


    @Override
    public boolean addRequest(RequestInfo req, String userEmail) {
        String defaultStatus = "SUBMITTED";
        User user = userService.getUserByEmail(userEmail);
        userService.addToken(user.getId(), req.getToken());

        String str = "insert into request_Info(social_number,first_name,last_name,gender,birthDate," +
                "birthCountry,phone_number,post_code,email,address,old_passport_number,fromWhom,givenDate," +
                "expireDate,photo,purpose,token,status,user_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(str, req.getSocialSecurityNumber(), req.getFirstName(), req.getLastName(), req.getGender()
                , req.getDateOfBirth(), req.getCountryOfBirth(), req.getPhoneNumber(), req.getPostCode(),
                req.getEmail(), req.getAddress(), req.getOldPassportNumber(), req.getFromWhom(), req.getGivenDate()
                , req.getExpireDate(), req.getPhoto(), req.getPurpose().name(), req.getToken(), defaultStatus
                , user.getId());
        return true;
    }


    @Override
    public List<RequestInfo> getRequestByToken(String token) {
        String bigQuery = "SELECT * FROM request_info where token=? ";
        List<RequestInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{token},
                new RequestMapper());
        return requests;
    }

    @Override
    public List<RequestInfo> getRequestById(Long id) {
        String bigQuery = "SELECT * FROM request_info where id=?";
        List<RequestInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{id},
                new RequestMapper());

        return requests;
    }

    @Override
    public List<RequestInfo> getRequestByStatus(String status) {

        String bigQuery = "SELECT * FROM request_info where status=? order by creatTime";
        List<RequestInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{status},
                new RequestMapper());

        return requests;
    }


    @Override
    public List<RequestInfo> getRequestBySSN(Long socialSecurityNumber) {

        String bigQuery = "SELECT * FROM request_info where social_number=?";
        List<RequestInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{socialSecurityNumber},
                new RequestMapper());

        return requests;
    }

    @Override
    public List<RequestInfo> getRequestByName(String name) {

        String bigQuery = "SELECT * FROM request_info where first_name=? order by creatTime";
        List<RequestInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{name},
                new RequestMapper());

        return requests;
    }


}
