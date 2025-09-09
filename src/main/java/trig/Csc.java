package trig;

public class Csc {
    public static double csc(double x) {
        if (Sin.sin(x) == 0) throw new IllegalArgumentException("Function is not determinable in x=" + x);
        return 1.0 / Sin.sin(x);
    }
}
