package by.gsu.pms;

import java.util.logging.Logger;
import java.util.stream.Stream;

public class ThreadGenerator {

    private static Logger LOGGER = Logger.getLogger(ThreadGenerator.class.getName());

    private static final int MIN_VALUE = 1;

    private int numberThreads;
    private Action action;
    private int limit;

    private ThreadCalculator[] threads;

    public ThreadGenerator(int number, Action action, int limit) {
        if (limit < number || limit % number != Constants.REMAINDER) {
            throw new IllegalArgumentException(Constants.ARGUMENT_EXCEPTION_MESSAGE);
        }
        this.numberThreads = number;
        this.action = action;
        this.limit = limit;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void execute() {
        threads = new ThreadCalculator[numberThreads];
        int capacity = limit / numberThreads;
        int lowerLimit = 0;
        int upperLimit = 0;
        for (int i = 0; i < numberThreads; i++) {
            lowerLimit = i * capacity + MIN_VALUE;
            upperLimit = i * capacity + capacity;
            threads[i] = new ThreadCalculator(lowerLimit, upperLimit);
            threads[i].start();
        }
    }

    public void getResult() {
        Stream.of(threads)
                .peek(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        LOGGER.warning(Constants.THREAD_EXCEPTION_MESSAGE);
                    }
                })
                .map(ThreadCalculator::getResult)
                .reduce(action.getAction())
                .ifPresent(t -> LOGGER.info(String.valueOf(t)));
    }
}
