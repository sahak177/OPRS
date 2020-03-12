package com.example.oprs.service;

import com.example.oprs.dto.HistoryDto;
import com.example.oprs.enums.Event;

import java.util.List;

public interface HistoryService {

    public void createHistory(Event event, Long id);

    List<HistoryDto> getHistoryByRequestInfoId(Long id);

    List<HistoryDto> getHistoryByUserId(Long id);
}
