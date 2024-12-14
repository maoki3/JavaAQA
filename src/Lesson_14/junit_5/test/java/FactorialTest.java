import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @DisplayName("Факториал нуля.")
    @Test
    void testFactorialOfZero() {
        assertEquals(1, Factorial.factorial(0)); // Факториал "0" равен "1".
    }

    @DisplayName("Факториал положительного числа.")
    @Test
    void testFactorialOfPositiveNumber() {
        assertEquals(3628800, Factorial.factorial(10)); // Факториал "10" равен "3628800"
        assertEquals(720, Factorial.factorial(6)); // Факториал "6" равен "720"
    }

    @DisplayName("Факториал отрицательного числа.")
    @RepeatedTest(2)
    void testFactorialOfNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));
        assertEquals("Число не может быть отрицательным.", exception.getMessage());
    }
}
