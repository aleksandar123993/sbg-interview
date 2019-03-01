package com.ivanisevic.sbg;

import com.ivanisevic.sbg.reader.CommandLineListener;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class BaseUnitTest {

    @MockBean
    CommandLineListener commandLineListener;
}
