package by.gsu.pms;

public class ThreadCalculator extends Thread {
    private int lowerLimit;
    private int upperLimit;

    private int result = 1;

    public ThreadCalculator(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        System.out.println(ThreadCalculator.currentThread());
        for (int i = lowerLimit; i <= upperLimit; i++) {
            if (i % 2 == 0) {
                result *= i;
            }
        }
    }
}
