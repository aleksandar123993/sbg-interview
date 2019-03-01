package com.ivanisevic.sbg.model;

import com.ivanisevic.sbg.helpers.ParameterHelper;
import lombok.Getter;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.DEST;
import static com.ivanisevic.sbg.helpers.Constants.FILES_DOWNLOAD;
import static com.ivanisevic.sbg.helpers.Constants.FILE_ID;
import static com.ivanisevic.sbg.helpers.Constants.WORD;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_DOWNLOAD_AVAILABLE_QUERIES;

@Getter
public class FilesDownloadCommand extends Command {

    private String fileId;
    private String destination;

    public FilesDownloadCommand(String input) {
        name = CommandName.FILES_DOWNLOAD;
        commandRegex = FILES_DOWNLOAD;
        availableQueries = Arrays.asList(FILES_DOWNLOAD_AVAILABLE_QUERIES);
        fileId = ParameterHelper.setRequiredParam(input, Pattern.compile(FILE_ID + WORD));
        destination = ParameterHelper.setRequiredParam(input, Pattern.compile(DEST + WORD));
    }
}
