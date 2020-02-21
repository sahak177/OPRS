package com.example.oprs.repository.impl;

import com.example.oprs.mappers.HistoryMapper;
import com.example.oprs.pojo.Event;
import com.example.oprs.pojo.History;
import com.example.oprs.repository.HistoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public HistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createHistory(Event event, Long requestId) {
        String historyQuery = "insert into history(user_id, request_info_id,event) " +
                "values((select user_id from request_info where id=?)," +
                "?,?);";
        jdbcTemplate.update(historyQuery, requestId, requestId, event.name());
    }

    @Override
    public List<History> getHistoryByRequestInfoId(Long id) {

        String Query = "SELECT * FROM history where request_info_id=? order by createTime";

        List<History> histories = jdbcTemplate.query(
                Query,
                new Object[]{id},
                new HistoryMapper());
        return histories;
    }

    @Override
    public List<History> getHistoryByUserId(Long id) {

        String Query = "SELECT * FROM history where user_id=? order by createTime";

        List<History> histories = jdbcTemplate.query(
                Query,
                new Object[]{id},
                new HistoryMapper());

        return histories;

    }
}
