package collections.ints.assertions;

import collections.ints.Set;

import java.util.Arrays;

public class BinaryOperation {

    private final Operation operation;
    private final int[] operand1Elements;
    private final int[] operand2Elements;

    private BinaryOperation(Operation operation, int[] operand1, int[] operand2) {
        this.operation = operation;
        this.operand1Elements = operand1;
        this.operand2Elements = operand2;
    }

    static BinaryOperation union(int[] operand1, int[] operand2) {
        return new BinaryOperation(Operation.UNION, operand1, operand2);
    }

    static BinaryOperation intersection(int[] operand1, int[] operand2) {
        return new BinaryOperation(Operation.INTERSECTION, operand1, operand2);
    }

    static BinaryOperation difference(int[] operand1, int[] operand2) {
        return new BinaryOperation(Operation.DIFFERENCE, operand1, operand2);
    }

    public ExtensiveContainment equals(int... expectedResult) {
        return new ExtensiveContainment(new TestExecutor(), expectedResult);
    }

    private class TestExecutor implements SetOutput {

        @Override
        public <S extends Set<S>> S outputFor(SetFactory<S> setFactory) {
            return operation.execute(setFactory, operand1Elements, operand2Elements);
        }

        @Override
        public String toString() {
            return String.format("%s(%n\t%s,%n\t%s%n)",
                operation.name(), Arrays.toString(operand1Elements), Arrays.toString(operand2Elements)
            );
        }
    }

    private enum Operation {

        UNION {
            @Override
            <S extends Set<S>> S execute(S set1, S set2) {
                return set1.union(set2);
            }
        },
        INTERSECTION {
            @Override
            <S extends Set<S>> S execute(S set1, S set2) {
                return set1.intersection(set2);
            }
        },
        DIFFERENCE {
            @Override
            <S extends Set<S>> S execute(S set1, S set2) {
                return set1.difference(set2);
            }
        };

        private  <S extends Set<S>> S execute(SetFactory<S> setFactory, int[] operand1, int[] operand2) {
            S set1 = setFactory.create(operand1);
            S set2 = setFactory.create(operand2);
            return execute(set1, set2);
        }

        abstract <S extends Set<S>> S execute(S set1, S set2);
    }
}
