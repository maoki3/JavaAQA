package src;

public class Task_7 {

    public static void negativeOrPositiveNumber(int number) {

        boolean input;

        if (number >= 0) {
            input = true;
        } else {
            input = false;
        }

        System.out.println(input);
    }

    public static void main(String[] args) {

        negativeOrPositiveNumber(0);
    }
}
