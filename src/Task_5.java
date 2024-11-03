public class Task_5 {

    public static void amountRange(int a, int b) {

        int sum = a + b;
        boolean inRange;

        if (sum >= 10 && sum <=20) {
            inRange = true;
        } else {
            inRange = false;
        }

        System.out.println(inRange);
    }

    public static void main(String[] args) {

        amountRange(2, 6);
    }
}
