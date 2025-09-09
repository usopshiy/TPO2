package trig;

public class Cos {
    public static double cos(double x) {
        return Sin.sin(Math.PI / 2 - x);
    }
}
