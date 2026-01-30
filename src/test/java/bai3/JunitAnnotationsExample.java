package bai3;


import org.junit.*;

public class JunitAnnotationsExample {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass: Chay 1 lan truoc tat ca test");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("AfterClass: Chay 1 lan sau tat ca test");
    }

    @Before
    public void before() {
        System.out.println("Before: Chay truoc moi test case");
    }

    @After
    public void after() {
        System.out.println("After: Chay sau moi test case");
    }

    @Test
    public void testCase1() {
        System.out.println("Test case 1 dang chay");
        Assert.assertTrue(true);
    }

    @Test
    public void testCase2() {
        System.out.println("Test case 2 dang chay");
        Assert.assertEquals(10, 5 + 5);
    }

    @Ignore("Test nay tam thoi bo qua")
    @Test
    public void testCaseIgnored() {
        System.out.println("Test case nay bi ignore");
    }
}
