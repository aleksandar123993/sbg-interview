package com.ivanisevic.sbg.helpers;

import com.ivanisevic.sbg.exceptions.ValidationException;
import com.ivanisevic.sbg.model.Command;
import com.ivanisevic.sbg.model.FilesDownloadCommand;
import com.ivanisevic.sbg.model.FilesListCommand;
import com.ivanisevic.sbg.model.FilesStatCommand;
import com.ivanisevic.sbg.model.FilesUpdateCommand;
import com.ivanisevic.sbg.model.ProjectsListCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.FILES_DOWNLOAD;
import static com.ivanisevic.sbg.helpers.Constants.FILES_LIST;
import static com.ivanisevic.sbg.helpers.Constants.FILES_STAT;
import static com.ivanisevic.sbg.helpers.Constants.FILES_UPDATE;
import static com.ivanisevic.sbg.helpers.Constants.PROJECTS_LIST;

@Component
public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommand(String name, String input) {

        Command command;

        switch (name) {
            case PROJECTS_LIST:
                command = new ProjectsListCommand();
                break;
            case FILES_LIST:
                command = new FilesListCommand();
                break;
            case FILES_STAT:
                command = new FilesStatCommand(input);
                break;
            case FILES_UPDATE:
                command = new FilesUpdateCommand(input);
                break;
            case FILES_DOWNLOAD:
                command = new FilesDownloadCommand(input);
                break;
            default:
                throw new ValidationException("Wrong command.");
        }
        command.setQueries(populateQueryParams(command, input));
        return command;
    }

    /**
     * Populating query parameters from user input for specific command.
     * @param command - specific command
     * @param input - full user input
     * @return map of key values (query name - query value)
     */
    private Map<String, String> populateQueryParams(Command command, String input) {

        Map<String, String> queries = new HashMap<>();
        command.getAvailableQueries().forEach(query -> {
            Matcher matcher = Pattern.compile(query).matcher(input);
            if(matcher.find()){
                String[] matched = input.substring(matcher.start(), matcher.end()).split("=");
                queries.put(matched[0].split("--")[1], matched[1]);
            }
        });
        return queries;
    }
}
