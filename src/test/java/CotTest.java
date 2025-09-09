import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import trig.Cos;
import trig.Cot;
import trig.Sin;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static trig.Cot.cot;

public class CotTest {
    private static final MockedStatic<Cos> mockedCos = Mockito.mockStatic(Cos.class);
    private static final MockedStatic<Sin> mockedSin = Mockito.mockStatic(Sin.class);

    @BeforeAll
    static void setUp() {
        mockedCos.when(() -> Cos.cos(anyDouble())).thenAnswer(invocation -> {
            double arg = invocation.getArgument(0);

            return switch (String.format("%.2f", arg)) {
                case "0.39" -> 0.92387;
                case "0.79" -> 0.70710;
                case "1.18" -> 0.38268;
                case "1.57" -> 0.0;
                case "1.96" -> -0.38268;
                case "2.36" -> -0.70710;
                case "2.75" -> -0.92387;
                default -> Math.cos(arg);
            };
        });

        mockedSin.when(() -> Sin.sin(anyDouble())).thenAnswer(invocation -> {
            double arg = invocation.getArgument(0);

            return switch (String.format("%.2f", arg)) {
                case "0.00" -> 0.0;
                case "0.39", "2.75" -> 0.38268;
                case "0.79", "2.36" -> 0.70710;
                case "1.18", "1.96" -> 0.92387;
                case "1.57" -> 1.0;
                default -> Math.sin(arg);
            };
        });
    }

    @AfterAll
    static void tearDown() {
        mockedCos.close();
        mockedSin.close();
    }

    @ParameterizedTest
    @ValueSource(doubles = {PI/8, PI/4, 3*PI/8, PI/2, 5*PI/8, 3*PI/4, 7*PI/8})
    public void testCot(double value) {
        assertEquals(Math.cos(value)/Math.sin(value), cot(value), 1e-4);
        mockedSin.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
        mockedCos.verify(() -> Cos.cos(anyDouble()), atLeastOnce());
    }

    @Test
    public void testError() {
        assertThrows(IllegalArgumentException.class, () -> cot(0));
    }
}
