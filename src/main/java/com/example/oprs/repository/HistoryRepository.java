package com.example.oprs.repository;

import com.example.oprs.enums.Event;
import com.example.oprs.dto.HistoryDto;

import java.util.List;

public interface HistoryRepository {
    void createHistory(Event event, Long id);

    List<HistoryDto> getHistoryByRequestInfoId(Long id);

    List<HistoryDto> getHistoryByUserId(Long id);
}
