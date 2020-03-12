package com.example.oprs.mappers;

import com.example.oprs.dao.ApplicationInfo;
import com.example.oprs.enums.Purpose;
import com.example.oprs.enums.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDbMapper implements RowMapper<ApplicationInfo> {

    public ApplicationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        ApplicationInfo req = new ApplicationInfo();
        req.setId(rs.getLong("id"));
        req.setSocialSecurityNumber(rs.getString("social_number"));
        req.setFirstName(rs.getString("first_name"));
        req.setLastName(rs.getString("last_name"));
        req.setGender(rs.getBoolean("gender"));
        req.setDateOfBirth(rs.getDate("birthDate"));
        req.setCountryOfBirth(rs.getString("birthCountry"));
        req.setAddress(rs.getString("address"));
        req.setEmail(rs.getString("email"));
        req.setPostCode(rs.getString("post_code"));
        req.setPhoneNumber(rs.getString("phone_number"));
        req.setOldPassportNumber(rs.getString("old_passport_number"));
        req.setLostPassportNumber(rs.getString("lost_passport_number"));
        req.setGivenDate(rs.getDate("givenDate"));
        req.setExpireDate(rs.getDate("expireDate"));
        req.setFromWhom(rs.getString("fromWhom"));
        req.setPurpose(Purpose.valueOf(rs.getString("purpose")));
        req.setPhotoUrl(rs.getString("photo"));
        req.setStatus(Status.valueOf(rs.getString("status")));
        req.setToken(rs.getString("token"));
        req.setUserId(rs.getLong("user_id"));
        req.setCreateDate(rs.getDate("createTime"));
        return req;
    }
}
