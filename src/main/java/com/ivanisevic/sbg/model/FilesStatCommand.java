package com.ivanisevic.sbg.model;

import com.ivanisevic.sbg.helpers.ParameterHelper;
import com.ivanisevic.sbg.model.Command;
import com.ivanisevic.sbg.model.CommandName;
import lombok.Getter;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.FILES_STAT;
import static com.ivanisevic.sbg.helpers.Constants.FILE_ID;
import static com.ivanisevic.sbg.helpers.Constants.WORD;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_STAT_AVAILABLE_QUERIES;

@Getter
public class FilesStatCommand extends Command {

    private String fileId;

    public FilesStatCommand(String input) {
        name = CommandName.FILES_STAT;
        commandRegex = FILES_STAT;
        availableQueries = Arrays.asList(FILES_STAT_AVAILABLE_QUERIES);
        fileId = ParameterHelper.setRequiredParam(input, Pattern.compile(FILE_ID + WORD));
    }
}
