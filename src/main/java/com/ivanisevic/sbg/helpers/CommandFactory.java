package com.ivanisevic.sbg.helpers;

import com.ivanisevic.sbg.model.Command;

public interface CommandFactory {

    Command createCommand(String name, String input);
}
