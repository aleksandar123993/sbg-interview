package com.ivanisevic.sbg.helpers;

import com.ivanisevic.sbg.BaseUnitTest;
import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

import static com.ivanisevic.sbg.helpers.Constants.FILE_ID;
import static com.ivanisevic.sbg.helpers.Constants.WORD;
import static org.assertj.core.api.Assertions.assertThat;

public class ParameterHelperTest extends BaseUnitTest {

    @Test
    public void test_setRequiredParam() {

        String result = ParameterHelper.setRequiredParam("cgccli --token token files update --file_id=fileid",
                Pattern.compile(FILE_ID + WORD));
        assertThat(result).isEqualTo("fileid");
    }

    @Test
    public void test_setOptionalParam() {

        String result = ParameterHelper.setOptionalParam("cgccli --token token files update --file_id=fileid name=name",
                Pattern.compile(" name=" + WORD));
        assertThat(result).isEqualTo("name");
    }

    @Test
    public void test_setArrayParam() {

        List<String> result = ParameterHelper.setArrayParam("cgccli --token token files update --file_id=fileid tags=tag1, tag2, tag3",
                Pattern.compile(" tags=" + WORD));
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains("tag1");
        assertThat(result).contains("tag2");
        assertThat(result).contains("tag3");
    }
}
