package com.example.oprs.repository;

import com.example.oprs.pojo.Event;
import com.example.oprs.pojo.History;

import java.util.List;

public interface HistoryRepository {
    void createHistory(Event event, Long id);

    List<History> getHistoryByRequestInfoId(Long id);

    List<History> getHistoryByUserId(Long id);
}
