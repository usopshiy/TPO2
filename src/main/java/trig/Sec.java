package trig;

public class Sec {
    public static double sec(double x) {
        if (Cos.cos(x) == 0) throw new IllegalArgumentException("Function is not determinable in x=" + x);
        return 1.0 / Cos.cos(x);
    }
}
