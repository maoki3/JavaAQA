public class Task_10 {

    public static void arraySwitching() {

        int[] arr = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr [i] = 0;
            }

            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {

        arraySwitching();
    }
}
