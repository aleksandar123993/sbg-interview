package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.exceptions.ValidationException;
import com.ivanisevic.sbg.helpers.CommandFactory;
import com.ivanisevic.sbg.model.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ivanisevic.sbg.helpers.Constants.AVAILABLE_COMMANDS;

@Component
@RequiredArgsConstructor
public class CommandValidator {

    private final CommandFactory commandFactory;

    /**
     * Validating if one and only one command is inserted.
     * @param input - full command insert by user
     * @return command requested by user.
     * @throws ValidationException if command name is not inserted or there are more then one command inserted.
     */
    public Command validateAndReturnCommand(String input) {

        List<String> commands = Arrays.asList(AVAILABLE_COMMANDS);

        List<String> usedCommands = commands.stream()
                .filter(command -> Pattern.compile(command).matcher(input).find())
                .collect(Collectors.toList());

        if(usedCommands.size() != 1) {
            throw new ValidationException("You must insert one and only one of the following commands: " +
                    String.join(", ", commands) + "\nType --help <command> for more info.");
        }

        String command = usedCommands.get(0);
        return commandFactory.createCommand(command, input);
    }
}
