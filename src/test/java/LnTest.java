import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static log.Ln.ln;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {

    @Test
    public void testZero() {
        assertEquals(0, ln(1));
    }

    @Test
    public void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> ln(-1));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.3, 0.5, 0.7, 0.9})
    public void testLnLow(double value) {
        assertEquals(Math.log(value), ln(value), 1e-4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4, 5})
    public void testLnHigh(double value) {
        assertEquals(Math.log(value), ln(value), 1e-4);
    }
}
