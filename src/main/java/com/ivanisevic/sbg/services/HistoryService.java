package com.ivanisevic.sbg.services;

import java.util.List;

public interface HistoryService {

    /**
     * Proxy adding command to database.
     * @param command - specific command
     */
    void addHistoryCommand(String command);

    /**
     * Proxy getting command from database.
     * @return list of command names.
     */
    List<String> getHistoryCommands();
}
