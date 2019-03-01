package com.ivanisevic.sbg.services.impl;

import com.ivanisevic.sbg.dao.HistoryMapper;
import com.ivanisevic.sbg.services.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;

    @Override
    public void addHistoryCommand(String command) {
        log.debug("Adding history command: {}", command);
        historyMapper.addHistoryCommand(command);
    }

    @Override
    public List<String> getHistoryCommands() {
        log.debug("Getting history commands.");
        return historyMapper.getHistoryCommands();
    }
}
