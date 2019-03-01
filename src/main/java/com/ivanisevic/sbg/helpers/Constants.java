package com.ivanisevic.sbg.helpers;

import java.util.HashMap;
import java.util.Map;

import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_DOWNLOAD_AVAILABLE_QUERIES;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_LISTS_AVAILABLE_QUERIES;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_STAT_AVAILABLE_QUERIES;
import static com.ivanisevic.sbg.helpers.QueryParameters.FILES_UPDATE_AVAILABLE_QUERIES;
import static com.ivanisevic.sbg.helpers.QueryParameters.PROJECTS_LISTS_AVAILABLE_QUERIES;

public class Constants {

    public static final String BASIC_COMMAND = "^cgccli";
    public static final String TOKEN_COMMAND = " --token ";
    public static final String HELP_COMMAND = " --help";
    public static final String HISTORY_COMMAND = " --history";
    public static final String FILE_ID = " --file_id=";
    public static final String DEST = " --dest=";
    public static final String WORD = "[a-zA-Z0-9/._-]+";
    public static final String TOKEN_PATTERN = TOKEN_COMMAND + WORD;
    public static final String NUMBER = "[0-9]+";

    public static final String PROJECTS_LIST = " projects list";
    public static final String FILES_LIST = " files list";
    public static final String FILES_STAT = " files stat";
    public static final String FILES_UPDATE = " files update";
    public static final String FILES_DOWNLOAD = " files download";

    public static final String[] AVAILABLE_COMMANDS = {PROJECTS_LIST, FILES_LIST, FILES_STAT, FILES_UPDATE, FILES_DOWNLOAD};

    public static final String GENERAL_HELP_MESSAGE = "You need to insert command in following pattern: \"cgccli --token <TOKEN> command" +
            "\nAvailable commands: [" + String.join(", ", AVAILABLE_COMMANDS) + "]. Type --help <COMMAND> for more info.";

    private static final Map<String, String> commandHelpMap = new HashMap<>();

    public static final String PROJECTS_LISTS_HELP_MESSAGE = "Command for fetching projects.\n" +
            "Available queries: " + String.join(", ", PROJECTS_LISTS_AVAILABLE_QUERIES);
    public static final String FILES_LISTS_HELP_MESSAGE = "Command for fetching files.\n" +
            "Available queries: " + String.join(", ", FILES_LISTS_AVAILABLE_QUERIES);
    public static final String FILES_STAT_HELP_MESSAGE = "Command for fetching files stat.\n" +
            "Available queries: " + String.join(", ", FILES_STAT_AVAILABLE_QUERIES) +
            "\nRequired path param: file_id (" + FILE_ID + WORD + " )";
    public static final String FILES_UPDATE_HELP_MESSAGE = "Command for updating specific file.\n" +
            "Available queries: " + String.join(", ", FILES_UPDATE_AVAILABLE_QUERIES) +
            "\nRequired path param: file_id (" + FILE_ID + WORD + " )" +
            "\nOptional request body params: [ name, tags, metadata.{field} ], request params needs to be insert in pattern: " +
            " name=<value> tags=<value1>, <value2>, ... metadata.library_id=<value>";

    public static final String FILES_DOWNLOAD_HELP_MESSAGE = "Command for downloading specific file.\n" +
            "Available queries: " + String.join(", ", FILES_DOWNLOAD_AVAILABLE_QUERIES) +
            "\nRequired path param: file_id (" + FILE_ID + WORD + " )" +
            "\nRequired param: destination (" + DEST + WORD + " )";

    public static Map<String, String> getCommandHelpMap() {
        commandHelpMap.put(PROJECTS_LIST, PROJECTS_LISTS_HELP_MESSAGE);
        commandHelpMap.put(FILES_LIST, FILES_LISTS_HELP_MESSAGE);
        commandHelpMap.put(FILES_STAT, FILES_STAT_HELP_MESSAGE);
        commandHelpMap.put(FILES_UPDATE, FILES_UPDATE_HELP_MESSAGE);
        commandHelpMap.put(FILES_DOWNLOAD, FILES_DOWNLOAD_HELP_MESSAGE);
        return commandHelpMap;
    }
}
