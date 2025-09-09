import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import trig.Sin;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static trig.Csc.csc;


public class CscTest {
    private static final MockedStatic<Sin> mockedSin = Mockito.mockStatic(Sin.class);

    @BeforeAll
    static void setUp() {
        mockedSin.when(() -> Sin.sin(anyDouble())).thenAnswer(invocation -> {
            double arg = invocation.getArgument(0);

            return switch (String.format("%.2f", arg)) {
                case "0.00", "3.14" -> 0.0;
                case "0.39", "2.75" -> 0.38268;
                case "0.79", "2.36" -> 0.7071;
                case "1.18", "1.96" -> 0.92387;
                case "1.57" -> 1.0;
                case "3.53", "5.89" -> -0.38268;
                case "3.93", "5.50" -> -0.7071;
                case "4.32", "5.11" -> -0.92387;
                case "4.71" -> -1.0;
                default -> Math.sin(arg);
            };
        });
    }

    @AfterAll
    static void tearDown() {
        mockedSin.close();
    }

    @ParameterizedTest
    @ValueSource(doubles = {PI/8, PI/4, 3 * PI/8, PI/2, 5 * PI/8, 3 * PI/4, 7 * PI/8})
    public void testPositiveCsc(double x) {
        assertEquals(1/Math.sin(x), csc(x), 1e-4);
        mockedSin.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {9 * PI/8, 5 * PI/4, 11 * PI/8, 3 * PI/2, 13 * PI/8, 7 * PI/4, 15 * PI/8})
    public void testNegativeCsc(double x) {
        assertEquals(1/Math.sin(x), csc(x), 1e-4);
        mockedSin.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }

    @Test
    public void testVoid() {
        assertThrows(IllegalArgumentException.class, () -> csc(0.0));
        mockedSin.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }
}
