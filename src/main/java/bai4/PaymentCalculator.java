package bai4;

public class PaymentCalculator {

    public static int calculate(String type, int age) {

        if (age < 0 || age > 145) {
            throw new IllegalArgumentException("Age is not valid");
        }

        switch (type) {
            case "CHILD":
                if (age > 17) {
                    throw new IllegalArgumentException("Child age must be 0-17");
                }
                return 50;

            case "MALE":
                if (age < 18) {
                    throw new IllegalArgumentException("Male age must be >= 18");
                }
                if (age <= 35) return 100;
                if (age <= 50) return 120;
                return 140;

            case "FEMALE":
                if (age < 18) {
                    throw new IllegalArgumentException("Female age must be >= 18");
                }
                if (age <= 35) return 80;
                if (age <= 50) return 110;
                return 140;

            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }
}
