package log;

public class Ln {
    private final static double precision = 0.00001;


    public static double ln(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be positive");
        }

        if (x == 1) {
            return 0.0;
        }

        double result = 0.0;
        double term = (x - 1) / (x + 1);
        double termSquared = term * term;
        double power = term;
        double prev = result;


        result += power;
        power *= termSquared;
        int n = 3;
        while ((Math.abs(result) - Math.abs(prev)) * 2 > precision) {
            prev = result;
            result += power / n;
            power *= termSquared;
            n += 2;
        }

        return 2 * result;
    }
}
