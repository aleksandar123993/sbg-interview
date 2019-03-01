package com.ivanisevic.sbg.services;

import com.ivanisevic.sbg.model.Command;

public interface CommandExecutor {

    /**
     * Executes specific command.
     * @param command - specific command object
     * @param token - authorization token
     */
    void executeCommand(Command command, String token);
}
