package com.ivanisevic.sbg.model;

import java.util.Arrays;

import static com.ivanisevic.sbg.helpers.Constants.PROJECTS_LIST;
import static com.ivanisevic.sbg.helpers.QueryParameters.PROJECTS_LISTS_AVAILABLE_QUERIES;

public class ProjectsListCommand extends Command {

    public ProjectsListCommand() {
         name = CommandName.PROJECTS_LIST;
         commandRegex = PROJECTS_LIST;
         availableQueries = Arrays.asList(PROJECTS_LISTS_AVAILABLE_QUERIES);
    }
}
