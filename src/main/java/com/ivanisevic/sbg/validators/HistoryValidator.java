package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.services.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.HISTORY_COMMAND;

@Component
@RequiredArgsConstructor
@Slf4j
public class HistoryValidator {

    private final HistoryService historyService;

    /**
     * Validating if history option inserted.
     * @param input - full command insert by user
     * @return true if history is requested, false otherwise.
     */
    public boolean history(String input) {
        log.debug("Checking history command.");
        Matcher matcher = Pattern.compile(HISTORY_COMMAND).matcher(input);
        if(matcher.find()) {
            List<String> history = historyService.getHistoryCommands();
            System.out.println(String.join("\n", history));
            return true;
        }
        return false;
    }
}
