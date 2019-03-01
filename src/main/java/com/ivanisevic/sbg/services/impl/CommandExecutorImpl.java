package com.ivanisevic.sbg.services.impl;

import com.ivanisevic.sbg.api.service.CGCService;
import com.ivanisevic.sbg.services.FileService;
import com.ivanisevic.sbg.model.Command;
import com.ivanisevic.sbg.model.FilesDownloadCommand;
import com.ivanisevic.sbg.model.FilesStatCommand;
import com.ivanisevic.sbg.model.FilesUpdateCommand;
import com.ivanisevic.sbg.responses.FilesDownloadResponse;
import com.ivanisevic.sbg.services.CommandExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandExecutorImpl implements CommandExecutor {

    private final CGCService cgcService;
    private final FileService fileService;

    @Override
    public void executeCommand(Command command, String token) {

        switch (command.getName()){
            case PROJECTS_LIST:
                cgcService.listProjects(token, command.getQueries());
                break;
            case FILES_LIST:
                cgcService.listFiles(token, command.getQueries());
                break;
            case FILES_STAT:
                FilesStatCommand statCommand = (FilesStatCommand) command;
                cgcService.filesStat(token, command.getQueries(), statCommand.getFileId());
                break;
            case FILES_UPDATE:
                FilesUpdateCommand updateCommand = (FilesUpdateCommand) command;
                cgcService.filesUpdate(token, command.getQueries(), updateCommand.getFileId(), updateCommand.getRequestBody());
                break;
            case FILES_DOWNLOAD:
                FilesDownloadCommand downloadCommand = (FilesDownloadCommand) command;
                FilesDownloadResponse response = cgcService.getDownloadUrl(token, command.getQueries(), downloadCommand.getFileId());
                if(response != null){
                    fileService.downloadFile(response.getUrl(), downloadCommand.getDestination());
                }
        }
    }
}
