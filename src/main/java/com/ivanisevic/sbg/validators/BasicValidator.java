package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.BASIC_COMMAND;

@Component
public class BasicValidator {

    /**
     * Validating if basic command (cgccli) is typed as first word.
     * @param input - full command insert by user
     * @throws ValidationException if cgccli is not typed as first word.
     */
    public void validate(String input) {

        Matcher matcher = Pattern.compile(BASIC_COMMAND).matcher(input);
        if(!matcher.find()){
            throw new ValidationException("Command must start with: " + BASIC_COMMAND.substring(1));
        }
    }
}
