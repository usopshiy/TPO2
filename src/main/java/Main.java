import log.Ln;
import log.Log10;
import log.Log2;
import log.Log5;
import trig.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static void generateCsv(String fileName, java.util.function.Function<Double,
            Double> module, double start, double step, double count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {
            writer.write("X, Result (x)\n");
            double x = start;
            for (int i = 0; i < count; i++) {
                writer.write(x + ", " + module.apply(x) + ";\n");
                x += step;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        generateCsv("OutCsv/ln.csv", Ln::ln, 0.1, 0.15, 100);
        generateCsv("OutCsv/log_2.csv", Log2::log_2, 0.1, 0.15, 100);
        generateCsv("OutCsv/log_5.csv", Log5::log_5, 0.1, 0.15, 100);
        generateCsv("OutCsv/log_10.csv", Log10::log_10, 0.1, 0.15, 100);
        generateCsv("OutCsv/sin.csv", Sin::sin, 0, 0.15, 100);
        generateCsv("OutCsv/cos.csv", Cos::cos, 0, 0.15, 100);
        generateCsv("OutCsv/cot.csv", Cot::cot, 0.1, 0.15, 100);
        generateCsv("OutCsv/csc.csv", Csc::csc, 0.1, 0.15, 100);
        generateCsv("OutCsv/sec.csv", Sec::sec, 0.1, 0.15, 100);
        generateCsv("OutCsv/func.csv", FunctionSystem::f, -9.9, 0.25, 200);
    }
}
