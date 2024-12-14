import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialNgTest {

    @Test
    public void testFactorialOfZero() {
        assertEquals(Factorial.factorial(0), 1);
    } // Факториал "0" равен "1".

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(Factorial.factorial(6), 720);
    } // Факториал "6" равен "720"

    @Test
    public void testFactorialOfNegativeNumber() {
        try {
            Factorial.factorial(-1);
            // Если метод не выбросит исключение, то тест должен завершиться с ошибкой
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Ожидаемое поведение
        }
    }
}
