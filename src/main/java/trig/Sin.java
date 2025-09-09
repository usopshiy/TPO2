package trig;

public class Sin {
    private final static double precision = 0.00001;

    public static double sin(double x) {
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
}
