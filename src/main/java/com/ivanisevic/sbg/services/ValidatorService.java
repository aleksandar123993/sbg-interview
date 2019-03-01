package com.ivanisevic.sbg.services;

import com.ivanisevic.sbg.model.Command;
import io.vavr.Tuple2;

public interface ValidatorService {

    /**
     * Validate all needed command parts.
     * @param input - full input from user.
     * @return tuple of command and authorization token.
     */
    Tuple2<Command, String> validate(String input);
}
