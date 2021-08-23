package org.velichko.finalproject.logic.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PasswordHashGeneratorTest {
    public static final String EXPECTED_VALID_VALUE = "VGhpcyBpcyB0aGUgdGVzdA==";
    public static final String EXPECTED_NOT_VALID_VALUE = "VGhpcyBpcyB0aGUgdGVadA==";
    public static final String SOURCE_STRING = "This is the test";

    @Test
    public void testIsHashed() {
        String actual = PasswordHashGenerator.encodePassword(SOURCE_STRING);
        assertEquals(actual, EXPECTED_VALID_VALUE);
    }
    @Test
    public void testIsNotHashed() {
        String actual = PasswordHashGenerator.encodePassword(SOURCE_STRING);
        assertNotEquals(actual, EXPECTED_NOT_VALID_VALUE);
    }
}