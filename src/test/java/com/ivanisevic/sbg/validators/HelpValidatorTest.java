package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.BaseUnitTest;
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

import static com.ivanisevic.sbg.helpers.Constants.FILES_DOWNLOAD_HELP_MESSAGE;
import static com.ivanisevic.sbg.helpers.Constants.FILES_LISTS_HELP_MESSAGE;
import static com.ivanisevic.sbg.helpers.Constants.FILES_STAT_HELP_MESSAGE;
import static com.ivanisevic.sbg.helpers.Constants.FILES_UPDATE_HELP_MESSAGE;
import static com.ivanisevic.sbg.helpers.Constants.GENERAL_HELP_MESSAGE;
import static com.ivanisevic.sbg.helpers.Constants.PROJECTS_LISTS_HELP_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class HelpValidatorTest extends BaseUnitTest {

    @Autowired
    HelpValidator helpValidator;

    private final String output = "help";

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
    public void test_help_validator_without_help_command() {
        boolean requestedHelp = helpValidator.help("without help command");
        assertThat(requestedHelp).isFalse();
    }

    @Test
    public void test_help_without_specific_command() {
        boolean requestedHelp = helpValidator.help("cgccli --help");
        assertThat(requestedHelp).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            String[] help_message = GENERAL_HELP_MESSAGE.split("\n");
            assertThat(outputList).contains(help_message[0]);
            assertThat(outputList).contains(help_message[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_help_with_projects_list_command() {
        boolean requestedHelp = helpValidator.help("cgccli --help projects list");
        assertThat(requestedHelp).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            String[] help_message = PROJECTS_LISTS_HELP_MESSAGE.split("\n");
            assertThat(outputList).contains(help_message[0]);
            assertThat(outputList).contains(help_message[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_help_with_files_list_command() {
        boolean requestedHelp = helpValidator.help("cgccli --help files list");
        assertThat(requestedHelp).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            String[] help_message = FILES_LISTS_HELP_MESSAGE.split("\n");
            assertThat(outputList).contains(help_message[0]);
            assertThat(outputList).contains(help_message[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_help_with_files_stat_command() {
        boolean requestedHelp = helpValidator.help("cgccli --help files stat");
        assertThat(requestedHelp).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            String[] help_message = FILES_STAT_HELP_MESSAGE.split("\n");
            assertThat(outputList).contains(help_message[0]);
            assertThat(outputList).contains(help_message[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_help_with_files_update_command() {
        boolean requestedHelp = helpValidator.help("cgccli --help files update");
        assertThat(requestedHelp).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            String[] help_message = FILES_UPDATE_HELP_MESSAGE.split("\n");
            assertThat(outputList).contains(help_message[0]);
            assertThat(outputList).contains(help_message[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_help_with_files_download_command() {
        boolean requestedHelp = helpValidator.help("cgccli --help files download");
        assertThat(requestedHelp).isTrue();
        try {
            List<String> outputList = Files.readAllLines(Paths.get(output));
            String[] help_message = FILES_DOWNLOAD_HELP_MESSAGE.split("\n");
            assertThat(outputList).contains(help_message[0]);
            assertThat(outputList).contains(help_message[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
