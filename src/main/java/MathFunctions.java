public class MathFunctions implements Cloneable {
    private final static double precision = 0.00001;

    public double sin(double x) {
        double result = 0.0;
        int n = 1;
        double term = x;
        while ( Math.abs(term) > precision ){
            result += term;
            term *= -( (x/(n+1)) * (x/(n+2)) );
            n+= 2;
        }

        return result;
    }

    public double cos(double x) {
        return sin(Math.PI / 2 - x);
    }

    public double cot(double x) {
        return cos(x) / sin(x);
    }

    public double sec(double x) {
        if (cos(x) == 0) throw new IllegalArgumentException();
        return 1.0 / cos(x);
    }

    public double csc(double x) {
        return 1.0 / sin(x);
    }


    public double ln(double x) {
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

    public double log_2(double x) {
        return ln(x) / ln(2.0);
    }

    public double log_5(double x) {
        return ln(x) / ln(5.0);
    }

    public double log_10(double x) {
        return ln(x) / ln(10.0);
    }


    public double f(double x) {
        if (x <= 0) {
            return ((((1 + csc(x)) + (Math.pow(cot(x), 2))) + sec(x)) - cos(x));
        }
        else {
            return Math.pow(((((log_10(x) + log_5(x)) - log_2(x)) * log_2(x)) + (log_2(x) / (ln(x) / log_5(x)))), 3);
        }
    }

    @Override
    public MathFunctions clone() throws CloneNotSupportedException {
        return (MathFunctions) super.clone();
    }
}