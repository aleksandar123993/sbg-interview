package com.ivanisevic.sbg.helpers;

import com.ivanisevic.sbg.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterHelper {

    /**
     * Validate and return path params.
     * @param input - full user input.
     * @param pattern - RegEx pattern for parsing input
     * @return value of required (path) param.
     * @throws ValidationException if required param is not set.
     */
    public static String setRequiredParam(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return input.substring(matcher.start(), matcher.end()).split("=")[1];
        }
        else {
            throw new ValidationException("You must set required param in format: " + pattern);
        }
    }

    /**
     * Helper for request body for files update command
     * @param input - full user input
     * @param pattern - RegEx pattern for parsing input
     * @return value of optional param (null if not set)
     */
    public static String setOptionalParam(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return input.substring(matcher.start(), matcher.end()).split("=")[1];
        }
        return null;
    }

    /**
     * Set array of params
     * @param input - full user input
     * @param pattern - RegEx pattern for parsing input
     * @return array of optional params
     */
    public static List<String> setArrayParam(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        List<String> list = new ArrayList<>();
        if(matcher.find()){
            list.addAll(Arrays.asList(input.substring(matcher.start()).split("=")[1].split(",[ ]?")));
        }
        return list;
    }
}
