package com.example.oprs.service.impl;

import com.example.oprs.pojo.Event;
import com.example.oprs.pojo.History;
import com.example.oprs.repository.HistoryRepository;
import com.example.oprs.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void createHistory(Event event, Long requestId) {
        historyRepository.createHistory(event, requestId);
    }

    @Override
    public List<History> getHistoryByRequestInfoId(Long id) {

        return historyRepository.getHistoryByRequestInfoId(id);
    }

    @Override
    public List<History> getHistoryByUserId(Long id) {

        return historyRepository.getHistoryByUserId(id);
    }
}
