package log;

import static log.Ln.ln;

public class Log10 {
    public static double log_10(double x) {
        return ln(x) / ln(10.0);
    }
}
