public class Task_9 {

    public static void leapYear(int year) {

        boolean leap = false;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = true;
                }
            }
        }

        System.out.println(leap);
    }

    public static void main(String[] args) {

        leapYear(2024);
    }
}
