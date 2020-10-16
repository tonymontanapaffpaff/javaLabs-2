package by.gsu.pms;

import java.util.function.BinaryOperator;

public enum Action {
    SUM((i1, i2) -> i1 + i2),
    SUB((i1, i2) -> i1 - i2),
    MUL((i1, i2) -> i1 * i2),
    EXIT;

    private final BinaryOperator<Integer> op;

    Action(BinaryOperator<Integer> op) {
        this.op = op;
    }

    Action() {
        op = null;
    }

    public BinaryOperator<Integer> getAction() {
        return op;
    }
}