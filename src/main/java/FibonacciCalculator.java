public class FibonacciCalculator {
    public int calc(int num){
        if (num < 0)
            throw new IllegalArgumentException("should be >= 0");
        else if (num == 0)
            return 0;
        else if (num == 1)
            return 1;
        else
            return calc(num - 1) + calc(num - 2);
    }
}
