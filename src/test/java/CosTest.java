
import log.Ln;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import trig.Sin;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static trig.Cos.cos;

public class CosTest {
    private static final MockedStatic<Sin> mocked = Mockito.mockStatic(Sin.class);

    @BeforeAll
    static void setUp() {
        mocked.when(() -> Sin.sin(anyDouble())).thenAnswer(invocation -> {
            double arg = invocation.getArgument(0);

            return switch (String.format("%.2f", arg)) {
                case "0.00" -> 0.0;
                case "0.79", "2.36" -> 0.70710;
                case "1.57" -> 1.0;
                case "3.14" -> -7.72785;
                case "3.93", "5.50" -> -0.70710;
                case "4.71" -> -1.0;
                case "6.28" -> -5.49438;
                default -> Math.sin(arg);
            };
        });
    }

    @AfterAll
    static void closeMock() {
        mocked.close();
    }

    @AfterEach
    void clear() {
        mocked.clearInvocations();
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, PI/4, PI/2})
    public void testPositiveDown(double value) {
        assertEquals(Math.cos(value), cos(value), 1e-4);
        mocked.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {3 * PI/4, PI})
    public void testNegativeDown(double value) {
        assertEquals(Math.cos(value), cos(value), 1e-4);
        mocked.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {5 * PI/4, 3 * PI/2})
    public void testNegativeUp(double value) {
        assertEquals(Math.cos(value), cos(value), 1e-4);
        mocked.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {7 * PI/4, 2 * PI})
    public void testPositiveUp(double value) {
        assertEquals(Math.cos(value), cos(value), 1e-4);
        mocked.verify(() -> Sin.sin(anyDouble()), atLeastOnce());
    }
}
