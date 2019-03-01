package com.ivanisevic.sbg.services;

import com.ivanisevic.sbg.BaseUnitTest;
import com.ivanisevic.sbg.dao.HistoryMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryServiceTest extends BaseUnitTest {

    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    HistoryService historyService;

    @Before
    public void setUp() {
        historyMapper.deleteAll();
    }

    @Test
    public void test_addingHistoryCommand() {
        assertThat(historyMapper.getHistoryCommands().size()).isEqualTo(0);
        historyService.addHistoryCommand("test");
        assertThat(historyMapper.getHistoryCommands().size()).isEqualTo(1);
    }

    @Test
    public void test_gettingHistoryCommands() {
        historyService.addHistoryCommand("first");
        historyService.addHistoryCommand("second");
        historyService.addHistoryCommand("third");
        assertThat(historyMapper.getHistoryCommands().size()).isEqualTo(3);
        List<String> commands = historyService.getHistoryCommands();
        assertThat(commands.size()).isEqualTo(3);
        assertThat(commands.get(0)).isEqualTo("third");
        assertThat(commands.get(1)).isEqualTo("second");
        assertThat(commands.get(2)).isEqualTo("first");
    }
}
