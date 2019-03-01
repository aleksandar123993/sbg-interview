package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.BaseUnitTest;
import com.ivanisevic.sbg.services.HistoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryValidatorTest extends BaseUnitTest {

    @Autowired
    HistoryValidator historyValidator;

    @Autowired
    HistoryService historyService;

    private final String output = "history";

    @Before
    public void setUp() {
        try {
            System.setOut(new PrintStream(output));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clear() {
        File file = new File(output);
        file.delete();
    }

    @Test
    public void test_history_when_not_set_history_command() {
        boolean historyRequested = historyValidator.history("without history command");
        assertThat(historyRequested).isFalse();
    }

    @Test
    public void test_history_when_set_history_command() {
        historyService.addHistoryCommand("aaa");
        historyService.addHistoryCommand("bbb");
        historyService.addHistoryCommand("ccc");
        historyService.addHistoryCommand("ddd");
        historyService.addHistoryCommand("eee");

        boolean historyRequested = historyValidator.history("cgccli --history");
        assertThat(historyRequested).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            assertThat(outputList.get(0)).contains("eee");
            assertThat(outputList.get(1)).contains("ddd");
            assertThat(outputList.get(2)).contains("ccc");
            assertThat(outputList.get(3)).contains("bbb");
            assertThat(outputList.get(4)).contains("aaa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
