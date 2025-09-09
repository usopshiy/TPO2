import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static trig.Sin.sin;


public class SinTest {
    @ParameterizedTest
    @ValueSource(doubles = {0.0, PI/4, PI/2})
    public void testPositiveUp(double value) {
        assertEquals(Math.sin(value), sin(value), 1e-4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {PI/2 + 1e-10, 3 * PI/4, PI})
    public void testPositiveDown(double value) {
        assertEquals(Math.sin(value), sin(value), 1e-4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {PI + 1e-10, 5 * PI/4, 3 * PI/2})
    public void testNegativeDown(double value) {
        assertEquals(Math.sin(value), sin(value), 1e-4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {3 * PI/2 + 1e-10, 7 * PI/4, 2 * PI})
    public void testNegativeUp(double value) {
        assertEquals(Math.sin(value), sin(value), 1e-4);
    }
}
