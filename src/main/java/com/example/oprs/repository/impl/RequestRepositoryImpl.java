package com.example.oprs.repository.impl;

import com.example.oprs.mappers.RequestMapper;
import com.example.oprs.pojo.ApplicationInfo;
import com.example.oprs.pojo.Event;
import com.example.oprs.pojo.Status;
import com.example.oprs.pojo.User;
import com.example.oprs.repository.RequestRepository;
import com.example.oprs.service.HistoryService;
import com.example.oprs.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestRepositoryImpl implements RequestRepository {
    private final HistoryService historyService;
    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;

    public RequestRepositoryImpl(HistoryService historyService, JdbcTemplate jdbcTemplate, UserService userService) {
        this.historyService = historyService;
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }


    @Override
    public boolean addRequest(ApplicationInfo req, String userEmail) {
        Status defaultStatus = Status.SUBMITTED;
        User user = userService.getUserByEmail(userEmail);
        userService.addToken(user.getId(), req.getToken());

        String str = "insert into request_Info(social_number,first_name,last_name,gender,birthDate," +
                "birthCountry,phone_number,post_code,email,address,   old_passport_number,lost_passport_number,fromWhom,givenDate," +
                "expireDate,photo,purpose,token,status,user_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(str, req.getSocialSecurityNumber(), req.getFirstName(), req.getLastName(), req.getGender()
                , req.getDateOfBirth(), req.getCountryOfBirth(), req.getPhoneNumber(), req.getPostCode(),
                req.getEmail(), req.getAddress(), req.getOldPassportNumber(), req.getLostPassportNumber(), req.getFromWhom(), req.getGivenDate()
                , req.getExpireDate(), req.getPhotoUrl(), req.getPurpose().name(), req.getToken(), defaultStatus.name()
                , user.getId());
        List<ApplicationInfo> requests = getRequestByToken(req.getToken());
        if (!requests.isEmpty()) {
            ApplicationInfo applicationInfo = requests.get(0);
            historyService.createHistory(Event.valueOf(defaultStatus.name()), applicationInfo.getId());
        }
        return true;
    }


    @Override
    public List<ApplicationInfo> getRequestByToken(String token) {
        String bigQuery = "SELECT * FROM request_info where token=? ";
        List<ApplicationInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{token},
                new RequestMapper());
        return requests;
    }

    @Override
    public List<ApplicationInfo> getRequestById(Long id) {
        String bigQuery = "SELECT * FROM request_info where id=?";
        List<ApplicationInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{id},
                new RequestMapper());

        return requests;
    }

    @Override
    public List<ApplicationInfo> getRequestByStatus(String status) {

        String bigQuery = "SELECT * FROM request_info where status=? order by createTime";
        List<ApplicationInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{status},
                new RequestMapper());

        return requests;
    }


    @Override
    public List<ApplicationInfo> getRequestBySSN(Long socialSecurityNumber) {

        String bigQuery = "SELECT * FROM request_info where social_number=?";
        List<ApplicationInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{socialSecurityNumber},
                new RequestMapper());

        return requests;
    }

    @Override
    public List<ApplicationInfo> getRequestByName(String name) {

        String bigQuery = "SELECT * FROM request_info where first_name=? order by createTime";
        List<ApplicationInfo> requests = jdbcTemplate.query(
                bigQuery,
                new Object[]{name},
                new RequestMapper());

        return requests;
    }

    @Override
    public void updateStatus(Status status, Long id) {
        String str = "UPDATE request_Info SET status=? where id=? ";
        jdbcTemplate.update(
                str, status.name(), id);

        historyService.createHistory(Event.valueOf(status.name()), id);
    }

    @Override
    public void updateRequest(ApplicationInfo req) {

        String str = "update request_Info SET social_number=?,first_name=?,last_name=?,gender=?,birthDate=?," +
                "birthCountry=?,phone_number=?,post_code=?,email=?,address=?,old_passport_number=?,lost_passport_number=?,fromWhom=?,givenDate=?," +
                "expireDate=?,photo=?,purpose=?,token=?,status=?,user_id=?,createTime=? where id=?";

        jdbcTemplate.update(str, req.getSocialSecurityNumber(), req.getFirstName(), req.getLastName(), req.getGender()
                , req.getDateOfBirth(), req.getCountryOfBirth(), req.getPhoneNumber(), req.getPostCode(),
                req.getEmail(), req.getAddress(), req.getOldPassportNumber(), req.getLostPassportNumber(), req.getFromWhom(), req.getGivenDate()
                , req.getExpireDate(), req.getPhotoUrl(), req.getPurpose().name(), req.getToken(), req.getStatus().name()
                , req.getUserId(), req.getCreateDate(), req.getId());

        historyService.createHistory(Event.UPDATED, req.getId());
    }


}
