package src;

public class Task_13 {

    public static void diagonalArray() {

        int[][] arr = new int [6][6];

        for (int i = 0; i < 6; i++) {
            arr [i][i] = 1;
            arr [i][5-i] = 1;
        }

        for (int i = 0; i < 6; i++) {
            for (int j =0; j < 6; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        diagonalArray();
    }
}
