import static log.Ln.ln;
import static log.Log10.log_10;
import static log.Log2.log_2;
import static log.Log5.log_5;
import static trig.Cos.cos;
import static trig.Cot.cot;
import static trig.Csc.csc;
import static trig.Sec.sec;
import static trig.Sin.sin;

public class FunctionSystem {
    public static double f(double x) {
        if (x <= 0) {
            return (((((sin(x) / sin(x)) + csc(x)) + (Math.pow(cot(x), 2))) + sec(x)) - cos(x));
        }
        else {
            return Math.pow(((((log_10(x) + log_5(x)) - log_2(x)) * log_2(x)) + (log_2(x) / (ln(x) / log_5(x)))), 3);
        }
    }
}
