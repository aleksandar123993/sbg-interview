package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.TOKEN_COMMAND;
import static com.ivanisevic.sbg.helpers.Constants.TOKEN_PATTERN;

@Component
public class TokenValidator {

    /**
     * Validating if token is set.
     * @param input - full command insert by user
     * @return authorization token
     * @throws ValidationException if token is not inserted.
     */
    public String validateAndReturn(String input) {
        Matcher matcher = Pattern.compile(TOKEN_PATTERN).matcher(input);
        if(!matcher.find()) {
            throw new ValidationException("You must insert token in following format: " + TOKEN_PATTERN);
        }
        return input.substring(matcher.start() + TOKEN_COMMAND.length(), matcher.end());
    }
}
