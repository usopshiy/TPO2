
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;


public class FuncTest {
    private static final double DELTA = 1e-5;
    private final MathFunctions mathFunctions = new MathFunctions();


    @ParameterizedTest
    @CsvFileSource(resources = "cos.csv", numLinesToSkip = 1)
    public void testMockedCos(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.cos(anyDouble())).thenReturn(ctrlValue);
        assertEquals(expected, mocked.f(x), DELTA);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "cot.csv", numLinesToSkip = 1)
    public void testMockedCot(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.cot(anyDouble())).thenReturn(ctrlValue);
        assertEquals(expected, mocked.f(x), DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "sec.csv", numLinesToSkip = 1)
    public void testMockedSec(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.sec(anyDouble())).thenReturn(ctrlValue);
        assertEquals(expected, mocked.f(x), DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csc.csv", numLinesToSkip = 1)
    public void testMockedCsc(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.csc(anyDouble())).thenReturn(ctrlValue);
        assertEquals(trig.Csc.csc(x), ctrlValue, DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "sin.csv", numLinesToSkip = 1)
    public void testMockedSin(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.sin(anyDouble())).thenReturn(ctrlValue);
        assertEquals(expected, FunctionSystem.f(x), DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "log_2.csv", numLinesToSkip = 1)
    public void testMockedLog2(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.log_2(x)).thenReturn(ctrlValue);
        assertEquals(expected, mocked.f(x), DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "log_5.csv", numLinesToSkip = 1)
    public void testMockedLog5(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.log_5(x)).thenReturn(ctrlValue);
        assertEquals(expected, mocked.f(x), DELTA);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "log_10.csv", numLinesToSkip = 1)
    public void testMockedLog10(double x, double ctrlValue, double expected) {
        MathFunctions mocked = Mockito.spy(mathFunctions);
        Mockito.when(mocked.log_10(x)).thenReturn(ctrlValue);
        assertEquals(expected, mocked.f(x), DELTA);
    }

}