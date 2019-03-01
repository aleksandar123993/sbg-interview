package com.ivanisevic.sbg.model;

import java.util.Arrays;

import static com.ivanisevic.sbg.helpers.Constants.FILES_LIST;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_LISTS_AVAILABLE_QUERIES;

public class FilesListCommand extends Command {

    public FilesListCommand() {
        name = CommandName.FILES_LIST;
        commandRegex = FILES_LIST;
        availableQueries = Arrays.asList(FILES_LISTS_AVAILABLE_QUERIES);
    }
}
