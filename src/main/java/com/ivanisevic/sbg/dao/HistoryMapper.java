package com.ivanisevic.sbg.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryMapper {

    /**
     * Insert history command in database.
     * @param command - specific command inserted by user.
     */
    void addHistoryCommand(String command);

    /**
     * Getting 10 last history commands ordered by date, desc.
     * @return list of commands.
     */
    List<String> getHistoryCommands();

    /**
     * Delete all history commands from db (used for tests)
     */
    void deleteAll();
}
