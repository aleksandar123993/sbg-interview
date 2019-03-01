package com.ivanisevic.sbg.services;

import com.ivanisevic.sbg.BaseUnitTest;
import com.ivanisevic.sbg.api.service.CGCService;
import com.ivanisevic.sbg.model.FilesDownloadCommand;
import com.ivanisevic.sbg.model.FilesListCommand;
import com.ivanisevic.sbg.model.FilesStatCommand;
import com.ivanisevic.sbg.model.FilesUpdateCommand;
import com.ivanisevic.sbg.model.ProjectsListCommand;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommandExecutorTest extends BaseUnitTest {

    @Autowired
    CommandExecutor commandExecutor;

    @MockBean
    CGCService cgcService;

    @MockBean
    FileService fileService;

    @Test
    public void test_executeProjectsList() {
        ProjectsListCommand projectsListCommand = new ProjectsListCommand();
        commandExecutor.executeCommand(projectsListCommand, "token");
        verify(cgcService, times(1)).listProjects("token", projectsListCommand.getQueries());
    }

    @Test
    public void test_executeFilesList() {
        FilesListCommand filesListCommand = new FilesListCommand();
        commandExecutor.executeCommand(filesListCommand, "token");
        verify(cgcService, times(1)).listFiles("token", filesListCommand.getQueries());
    }

    @Test
    public void test_executeFilesStat() {
        FilesStatCommand filesStatCommand
                = new FilesStatCommand("cgccli --token token files stat --file_id=fileid");
        commandExecutor.executeCommand(filesStatCommand, "token");
        verify(cgcService, times(1)).filesStat("token", filesStatCommand.getQueries(),
                filesStatCommand.getFileId());
    }

    @Test
    public void test_executeFilesUpdate() {
        FilesUpdateCommand filesUpdateCommand
                = new FilesUpdateCommand("cgccli --token token files update --file_id=fileid");
        commandExecutor.executeCommand(filesUpdateCommand, "token");
        verify(cgcService, times(1)).filesUpdate("token", filesUpdateCommand.getQueries(),
                filesUpdateCommand.getFileId(), filesUpdateCommand.getRequestBody());
    }

    @Test
    public void test_executeFilesDownload() {
        FilesDownloadCommand filesDownloadCommand
                = new FilesDownloadCommand("cgccli --token token files download --file_id=fileid --dest=dest");
        commandExecutor.executeCommand(filesDownloadCommand, "token");
        verify(cgcService, times(1)).getDownloadUrl("token", filesDownloadCommand.getQueries(),
                filesDownloadCommand.getFileId());
    }
}
