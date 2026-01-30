package bai5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    void testEmail() {
        assertTrue("a@gmail.com".matches(".+@.+\\..+"));
        assertFalse("abc.com".matches(".+@.+\\..+"));
    }

    @Test
    void testPhone() {
        assertTrue("0123456789".matches("0\\d{9,11}"));
        assertFalse("12345".matches("0\\d{9,11}"));
    }

    @Test
    void testPassword() {
        assertTrue("12345678".length() >= 8);
        assertFalse("1234".length() >= 8);
    }
}
