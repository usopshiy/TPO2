import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import trig.Cos;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static trig.Csc.csc;
import static trig.Sec.sec;

public class SecTest {
    private static final MockedStatic<Cos> mockedCos = Mockito.mockStatic(Cos.class);

    @BeforeAll
    static void setUp() {
        mockedCos.when(() -> Cos.cos(anyDouble())).thenAnswer(invocation -> {
            double arg = invocation.getArgument(0);

            return switch (String.format("%.2f", arg)) {
                case "0.00" -> 1.0;
                case "0.39", "5.89" -> 0.92387;
                case "0.79", "5.50" -> 0.7071;
                case "1.18", "5.11" -> 0.38268;
                case "1.57", "4.71" -> 0.0;
                case "1.96", "4.32" -> -0.38268;
                case "2.36", "3.93" -> -0.70710;
                case "2.75", "3.53" -> -0.92387;
                case "3.14" -> -1.0;
                default -> Math.cos(arg);
            };
        });
    }

    @AfterAll
    static void tearDown() {
        mockedCos.close();
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, PI/8, PI/4, 3 * PI/8, 13 * PI/8, 7 * PI/4})
    public void testPositiveSec(double x) {
        assertEquals(1/Math.cos(x), sec(x), 1e-4);
        mockedCos.verify(() -> Cos.cos(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {5 * PI/8, 3 * PI/4, 7 * PI/8, PI, 9 * PI/8, 5 * PI/4, 11 * PI/8})
    public void testNegativeSec(double x) {
        assertEquals(1/Math.cos(x), sec(x), 1e-4);
        mockedCos.verify(() -> Cos.cos(anyDouble()), atLeastOnce());
    }

    @org.junit.jupiter.api.Test
    public void testVoid() {
        assertThrows(IllegalArgumentException.class, () -> sec(PI/2));
        mockedCos.verify(() -> Cos.cos(anyDouble()), atLeastOnce());
    }
}
