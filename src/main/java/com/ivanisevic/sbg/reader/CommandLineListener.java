package com.ivanisevic.sbg.reader;

import com.ivanisevic.sbg.model.Command;
import com.ivanisevic.sbg.services.CommandExecutor;
import com.ivanisevic.sbg.services.HistoryService;
import com.ivanisevic.sbg.services.ValidatorService;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CommandLineListener implements CommandLineRunner {

    private final ValidatorService validatorService;
    private final HistoryService historyService;
    private final CommandExecutor commandExecutor;
    private BufferedReader reader;

    @Override
    public void run(String... args) {

        while (true) {
            System.out.println("\nInsert command: \t");
            reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = reader.readLine();
                Tuple2<Command, String> tuple2 = validatorService.validate(input);
                if (tuple2 != null) {
                    commandExecutor.executeCommand(tuple2._1, tuple2._2);
                }
                historyService.addHistoryCommand(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
