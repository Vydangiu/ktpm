package bai1;

import org.junit.*;
import static org.junit.Assert.*;

public class MathFuncTest {

    private MathFunc math;

    @Before
    public void setUp() {
        math = new MathFunc();
        System.out.println("Before test");
    }

    @After
    public void tearDown() {
        System.out.println("After test");
    }

    @Test
    public void calls() {
        math.plus(1, 2);
        math.factorial(3);
        assertEquals(2, math.getCalls());
    }

    @Test
    public void factorial() {
        assertEquals(120, math.factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        math.factorial(-1);
    }

    @Ignore
    @Test
    public void todo() {
        assertEquals(5, math.plus(2, 3));
    }
}
