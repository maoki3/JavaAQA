package src;

public class Task_11 {

    public static void emptyArray() {

        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        for (int num : arr) {
            System.out.println(num + " ");
        }
    }

    public static void main(String[] args) {

        emptyArray();
    }
}
