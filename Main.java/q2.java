import java.io.*;
import java.util.*;

public class q2 {

    static double area(double[] x, double[] y, int n, double h,
                       int orientation) {
        double[] nx = new double[n];
        double[] ny = new double[n];

        for (int i = 0; i < n; i++) {
            int prev = (i - 1 + n) % n;
            int next = (i + 1) % n;

            // Shift the vertical edge inward.
            if (Math.abs(x[prev] - x[i]) < 1e-9) {
                double dy = y[i] - y[prev];
                nx[i] = x[i] - orientation * Math.signum(dy) * h;
            } else {
                double dy = y[next] - y[i];
                nx[i] = x[i] - orientation * Math.signum(dy) * h;
            }

            // Shift the horizontal edge inward.
            if (Math.abs(y[prev] - y[i]) < 1e-9) {
                double dx = x[i] - x[prev];
                ny[i] = y[i] + orientation * Math.signum(dx) * h;
            } else {
                double dx = x[next] - x[i];
                ny[i] = y[i] + orientation * Math.signum(dx) * h;
            }
        }

        double sum = 0;

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            sum += nx[i] * ny[j] - nx[j] * ny[i];
        }

        return Math.abs(sum) / 2.0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st =
                new StringTokenizer(br.readLine());

            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        double signedArea = 0;
        double minEdge = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;

            signedArea += x[i] * y[j] - x[j] * y[i];

            double length = Math.abs(x[i] - x[j])
                          + Math.abs(y[i] - y[j]);

            minEdge = Math.min(minEdge, length);
        }

        int orientation = signedArea > 0 ? 1 : -1;

        int maxStep = (int) Math.floor(
            (minEdge - 0.1) * 5 + 1e-9);

        double maxVolume = 0;

        for (int step = 1; step <= maxStep; step++) {
            double h = step / 10.0;

            double a = area(x, y, n, h, orientation);
            double volume = a * h;

            maxVolume = Math.max(maxVolume, volume);
        }

        System.out.printf(Locale.US, "%.2f%n", maxVolume);
    }
}