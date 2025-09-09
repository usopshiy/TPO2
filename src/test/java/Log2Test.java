import log.Ln;
import log.Log2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static log.Log2.log_2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;

public class Log2Test {
    private static final MockedStatic<Ln> mocked = Mockito.mockStatic(Ln.class);

    @BeforeAll
    static void setUp() {
        mocked.when(() -> Ln.ln(anyDouble())).thenAnswer(invocation -> {
            double arg = invocation.getArgument(0);

            return switch (String.valueOf(arg)) {
                case "-1" -> NaN;
                case "0.1" -> -2.30257;
                case "0.3" -> -1.20397;
                case "0.5" -> -0.69314;
                case "0.7" -> -0.35667;
                case "0.9" -> -0.10536;
                case "1.0" -> 0.0;
                case "2.0" -> 0.693147;
                case "3.0" -> 1.098611;
                case "4.0" -> 1.386291;
                case "5.0" -> 1.609432;
                default -> Math.log(arg);
            };
        });
    }

    @AfterAll
    static void closeMock() {
        mocked.close();
    }

    @Test
    public void testZero() {
        assertEquals(0, log_2(1));
        mocked.verify(() -> Ln.ln(anyDouble()), atLeastOnce());
    }

    @Test
    public void testNegative() {
        assertTrue(isNaN(log_2(-1)));
        mocked.verify(() -> Ln.ln(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.3, 0.5, 0.7, 0.9})
    public void testLog10Low(double value) {
        assertEquals(Math.log(value)/Math.log(2), log_2(value), 1e-4);
        mocked.verify(() -> Ln.ln(anyDouble()), atLeastOnce());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4, 5})
    public void testLog10High(double value) {
        assertEquals(Math.log(value)/Math.log(2), log_2(value), 1e-4);
        mocked.verify(() -> Ln.ln(anyDouble()), atLeastOnce());
    }
}
