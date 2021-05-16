package collections.ints.assertions;

import collections.ints.Set;
import iterators.ints.Iterator;

import java.util.Arrays;

public class ExtensiveContainment implements Statement {

    private final SetOutput setOutput;
    private final int[] expectedResult;

    public ExtensiveContainment(SetOutput setOutput, int[] expectedResult) {
        this.setOutput = setOutput;
        this.expectedResult = expectedResult;
    }

    @Override
    public <S extends Set<S>> boolean holdsFor(SetFactory<S> setFactory) {
        S output = setOutput.outputFor(setFactory);
        if (!containsExactly(output, expectedResult)) {
            return false;
        }
        //assert extensiveChek(output, setFactory);
        return true;
    }

    private <S extends Set<S>> boolean extensiveChek(S output, SetFactory<S> setFactory) {
        S expectedSet = setFactory.create(expectedResult);
        if (!containsExactly(expectedSet, expectedResult)) {
            return false;
        }
        if (!checkBinaryRelations(output, expectedSet)) {
            return false;
        }
        return checkBinaryOperations(output, expectedSet, expectedResult);
    }

    public static boolean containsExactly(Set<?> set, int... expected) {
        if (set.cardinality() != expected.length) {
            return false;
        }
        if (expected.length == 0 && !set.isEmpty()) {
            return false;
        }
        for (int i = 0; i < expected.length; i++) {
            int element = expected[i];
            if (!set.contains(element)) {
                return false;
            }
        }
        if (expected.length > 0) {
            if (set.smallest() != expected[0]) {
                return false;
            }
            if (set.greatest() != expected[expected.length - 1]) {
                return false;
            }
        }
        return checkIteration(set, expected);
    }

    private static boolean checkIteration(Set<?> set, int... ascendingElements) {
        int index = 0;
        Iterator ascendingIterator = set.increasing();
        while (ascendingIterator.hasNext()) {
            if (index == ascendingElements.length) {
                return false;
            }
            int actual = ascendingIterator.next();
            int expected = ascendingElements[index];
            if (actual != expected) {
                return false;
            }
            index++;
        }
        if (index < ascendingElements.length - 1) {
            return false;
        }
        Iterator descendingIterator = set.decreasing();
        while (descendingIterator.hasNext()) {
            if (--index < 0) {
                return false;
            }
            int actual = descendingIterator.next();
            int expected = ascendingElements[index];
            if (actual != expected) {
                return false;
            }
        }
        if (index > 0) {
            return false;
        }
        return true;
    }

    private static <S extends Set<S>> boolean checkBinaryRelations(S set, S expectedEqualSet) {
        if (!set.intersects(expectedEqualSet)) {
            return false;
        }
        if (!set.subSet(expectedEqualSet)) {
            return false;
        }
        if (!expectedEqualSet.subSet(set)) {
            return false;
        }
        if (!set.equivalent(expectedEqualSet)) {
            return false;
        }
        return true;
    }

    private static <S extends Set<S>> boolean checkBinaryOperations(S set, S expectedEqualSet, int... expectedElements) {
        S union = set.union(expectedEqualSet);
        if (!containsExactly(union, expectedElements)) {
            return false;
        }
        S intersection = set.intersection(expectedEqualSet);
        if (!containsExactly(intersection, expectedElements)) {
            return false;
        }
        S difference = set.difference(expectedEqualSet);
        if (!containsExactly(difference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Equality: output %s ≃?= %s", setOutput, Arrays.toString(expectedResult));
    }
}
