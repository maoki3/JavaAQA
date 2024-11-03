package src;

public class Task_14 {

    public static void main(String[] args) {

        int len = 2;
        int initialValue = 9;
        int[] arr = new int[len];
        createArray(arr, initialValue);

        for (int value : arr) {
            System.out.print(value);
        }
    }

    public static void createArray(int[] arr, int initialValue) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }
    }
}
