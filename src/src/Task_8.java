public class Task_8 {

    public static void manyString(String text, int count) {

        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static void main(String[] args) {

        manyString("Хорошего дня!", 3);
    }
}
