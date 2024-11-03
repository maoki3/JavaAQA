package src;

public class Task_2 {
    public static void checkSumSign() {

        int a = 2;
        int b = 6;
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");

        }
    }
    public static void main(String[] args) {

        checkSumSign();
    }
}
