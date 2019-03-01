package com.ivanisevic.sbg.validators;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.AVAILABLE_COMMANDS;
import static com.ivanisevic.sbg.helpers.Constants.GENERAL_HELP_MESSAGE;
import static com.ivanisevic.sbg.helpers.Constants.HELP_COMMAND;
import static com.ivanisevic.sbg.helpers.Constants.getCommandHelpMap;

@Component
public class HelpValidator {

    /**
     * Validating if help command is inserted. Printing list of commands if only help is requested.
     * Printing command description if help for specific command is requested.
     * @param input - full command insert by user
     * @return true if help is requested, false otherwise
     */
    public boolean help(String input) {
        Matcher matcher = Pattern.compile(HELP_COMMAND).matcher(input);
        if(matcher.find()) {
            Optional<String> command = Arrays.asList(AVAILABLE_COMMANDS).stream().filter(cmnd -> {
                Matcher match = Pattern.compile(cmnd).matcher(input);
                return match.find();
            }).findAny();

            if(command.isPresent()) {
                System.out.println(getCommandHelpMap().get(command.get()));
                return true;
            }

            System.out.println(GENERAL_HELP_MESSAGE);
            return true;
        }
        return false;
    }
}
