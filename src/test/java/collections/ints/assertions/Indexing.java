package collections.ints.assertions;


import collections.Collections;
import collections.ints.Set;

import java.util.ArrayList;
import java.util.List;

import static collections.Collections.noSuchInt;

public class Indexing{

    private Indexing() {

    }

    static Key IndexingAssertBuilder(int... subject) {
        return new Builder(subject);
    }

    public interface Assertion {

        Key forValues(int... values);
    }

    public interface Key {

        Assertion hasSmallerKey(int key);

        Assertion hasNoSmallerKey();

        Assertion hasGreaterKey(int key);

        Assertion hasNoGreaterKey();

        Statement holds();
    }

    private static class Builder implements Key, Assertion, Statement {

        private final int[] subject;

        private final List<int[]> valuesToCheck;
        private final List<Assert> assertsToExecute;
        private final List<Integer> expectedValues;

        private Builder(int... subject) {
            this.subject = subject;
            this.valuesToCheck = new ArrayList<>();
            this.assertsToExecute = new ArrayList<>();
            this.expectedValues = new ArrayList<>();
        }

        @Override
        public Key forValues(int... values) {
            valuesToCheck.add(values);
            return this;
        }

        @Override
        public Statement holds() {
            return this;
        }

        @Override
        public Assertion hasSmallerKey(int key) {
            assertsToExecute.add(Assert.SMALLER_VALUE_EQUALS);
            expectedValues.add(key);
            return this;
        }

        @Override
        public Assertion hasNoSmallerKey() {
            assertsToExecute.add(Assert.SMALLER_VALUE_NOT_FOUND);
            expectedValues.add(Collections.noSuchInt());
            return this;
        }

        @Override
        public Assertion hasGreaterKey(int key) {
            assertsToExecute.add(Assert.GREATER_VALUE_EQUALS);
            expectedValues.add(key);
            return this;
        }

        @Override
        public Assertion hasNoGreaterKey() {
            assertsToExecute.add(Assert.GREATER_VALUE_NOT_FOUND);
            expectedValues.add(Collections.noSuchInt());
            return this;
        }

        @Override
        public <S extends Set<S>> boolean holdsFor(SetFactory<S> setFactory) {
            for (int index = 0; index < valuesToCheck.size(); index++) {
                Assert assertion = assertsToExecute.get(index);
                int expectedValue = expectedValues.get(index);
                int[] valuesToQuery = valuesToCheck.get(index);
                if (!assertion.test(setFactory, subject, expectedValue, valuesToQuery)) {
                    return false;
                }
            }
            return true;
        }
    }

    private enum Assert {
        SMALLER_VALUE_EQUALS {
            @Override
            <S extends Set<S>> boolean test(S set, int expected, int value) {
                return set.smaller(value) == expected;
            }
        },
        SMALLER_VALUE_NOT_FOUND {
            @Override
            <S extends Set<S>> boolean test(S set, int expected, int value) {
                return Collections.noSuchInt(set.smaller(value));
            }
        },
        GREATER_VALUE_EQUALS {
            @Override
            <S extends Set<S>> boolean test(S set, int expected, int value) {
                return set.greater(value) == expected;
            }
        },
        GREATER_VALUE_NOT_FOUND {
            @Override
            <S extends Set<S>> boolean test(S set, int expected, int value) {
                return Collections.noSuchInt(set.greater(value));
            }
        };

        <S extends Set<S>> boolean test(SetFactory<S> setFactory, int[] subject, int expected, int[]values) {
            S set = setFactory.create(subject);
            for (int value : values) {
                if (!test(set, expected, value)) {
                    return false;
                }
            }
            return true;
        }

        abstract <S extends Set<S>>  boolean test(S set, int expected, int value);
    }
}
