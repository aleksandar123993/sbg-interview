package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.BaseUnitTest;
import com.ivanisevic.sbg.exceptions.ValidationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicValidatorTest extends BaseUnitTest {

    @Autowired
    private BasicValidator basicValidator;

    @Test(expected = ValidationException.class)
    public void test_validate_where_there_is_no_cgccli() {
        basicValidator.validate("wrong string.");
    }

    @Test(expected = ValidationException.class)
    public void test_validate_where_cgccli_is_not_on_start() {
        basicValidator.validate("string where cgccli is not on start.");
    }

    @Test
    public void test_validate_with_correct_input() {
        basicValidator.validate("cgccli");
    }
}