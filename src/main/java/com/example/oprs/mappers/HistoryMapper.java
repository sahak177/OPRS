package com.example.oprs.mappers;

import com.example.oprs.pojo.Event;
import com.example.oprs.pojo.History;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HistoryMapper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {
        History history=new History();
        history.setEvent(Event.valueOf(rs.getString("event")));
        history.setRequestInfoId(rs.getLong("Request_Info_id"));
        history.setUserId(rs.getLong("User_id"));
        history.setCreateDate(rs.getDate("createTime"));
        return history;
    }
}
