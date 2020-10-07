public class arcsin {
    static final double eps = 1e-6;
    public static double arcsin(double x) {
        double result = 0, prevSum = 1;
        for(int n = 0; Math.abs(result - prevSum) > eps; n++) {
            prevSum = result;
            result += factorial(2 * n) / Math.pow(4, n) / Math.pow(factorial(n), 2) / (2 * n + 1) * Math.pow(x, 2 * n + 1);
        }
        return result;
    }

    private static int factorial(int x) {
        if(x <= 1)
            return 1;
        else
            return x * (factorial(x - 1));
    }

}