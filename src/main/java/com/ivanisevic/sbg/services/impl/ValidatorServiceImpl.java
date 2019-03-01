package com.ivanisevic.sbg.services.impl;

import com.ivanisevic.sbg.services.ValidatorService;
import com.ivanisevic.sbg.exceptions.ValidationException;
import com.ivanisevic.sbg.model.Command;
import com.ivanisevic.sbg.validators.BasicValidator;
import com.ivanisevic.sbg.validators.CommandValidator;
import com.ivanisevic.sbg.validators.HelpValidator;
import com.ivanisevic.sbg.validators.HistoryValidator;
import com.ivanisevic.sbg.validators.TokenValidator;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {

    private final HelpValidator helpValidator;
    private final BasicValidator basicValidator;
    private final CommandValidator commandValidator;
    private final HistoryValidator historyValidator;
    private final TokenValidator tokenValidator;

    @Override
    public Tuple2<Command, String> validate(String input) {
        try {
            basicValidator.validate(input);
            if(helpValidator.help(input)) {
                return null;
            }
            if(historyValidator.history(input)){
                return null;
            }
            String token = tokenValidator.validateAndReturn(input);
            Command command = commandValidator.validateAndReturnCommand(input);
            return new Tuple2<>(command, token);
        }
        catch (ValidationException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}