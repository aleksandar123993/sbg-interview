package com.ivanisevic.sbg.validators;

import com.ivanisevic.sbg.BaseUnitTest;
import com.ivanisevic.sbg.exceptions.ValidationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenValidatorTest extends BaseUnitTest {

    @Autowired
    TokenValidator tokenValidator;

    @Test(expected = ValidationException.class)
    public void test_validateAndReturn_exception() {
        tokenValidator.validateAndReturn("without token.");
    }

    @Test
    public void test_validateAndReturn_correct() {
        String token = tokenValidator.validateAndReturn("cgccli --token t0k3n");
        assertThat(token).isEqualTo("t0k3n");
    }
}
