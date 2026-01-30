package bai4;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentCalculatorTest {

    @Test
    public void testChildPayment() {
        assertEquals(50, PaymentCalculator.calculate("CHILD", 5));
        assertEquals(50, PaymentCalculator.calculate("CHILD", 17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChildInvalidAge() {
        PaymentCalculator.calculate("CHILD", 18);
    }

    @Test
    public void testMalePayment() {
        assertEquals(100, PaymentCalculator.calculate("MALE", 25));
        assertEquals(120, PaymentCalculator.calculate("MALE", 40));
        assertEquals(140, PaymentCalculator.calculate("MALE", 60));
    }

    @Test
    public void testFemalePayment() {
        assertEquals(80, PaymentCalculator.calculate("FEMALE", 25));
        assertEquals(110, PaymentCalculator.calculate("FEMALE", 40));
        assertEquals(140, PaymentCalculator.calculate("FEMALE", 60));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAge() {
        PaymentCalculator.calculate("MALE", -1);
    }
}
