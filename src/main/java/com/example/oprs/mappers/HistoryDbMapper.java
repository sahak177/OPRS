package com.example.oprs.mappers;

import com.example.oprs.enums.Event;
import com.example.oprs.dto.HistoryDto;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HistoryDbMapper implements RowMapper<HistoryDto> {
    @Override
    public HistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        HistoryDto historyDto =new HistoryDto();
        historyDto.setEvent(Event.valueOf(rs.getString("event")));
        historyDto.setRequestInfoId(rs.getLong("Request_Info_id"));
        historyDto.setUserId(rs.getLong("User_id"));
        historyDto.setCreateDate(rs.getDate("createTime"));
        return historyDto;
    }
}
