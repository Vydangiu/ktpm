package bai3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitAnnotationsExample.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("So test da chay: " + result.getRunCount());
        System.out.println("So test that bai: " + result.getFailureCount());
        System.out.println("Test thanh cong: " + result.wasSuccessful());
    }
}
