package trig;

public class Cot {
    public static double cot(double x) {
        if (Sin.sin(x) == 0) throw new IllegalArgumentException("Function is not determinable in x=" + x);
        return Cos.cos(x) / Sin.sin(x);
    }
}
