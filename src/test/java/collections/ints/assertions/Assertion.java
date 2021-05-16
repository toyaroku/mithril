package collections.ints.assertions;

import collections.ints.Set;

public class Assertion {

    final int[] subject;

    private Assertion(int[] subject) {
        this.subject = subject;
    }

    public static Assertion assertThat(int... setElements) {
        return new Assertion(setElements);
    }

    public ExtensiveContainment containsExactly(int... elements) {
        assertSorted(elements);
        return new ExtensiveContainment(new SetOutput() {
            @Override
            public <S extends Set<S>> S outputFor(SetFactory<S> setFactory) {
                return setFactory.create(elements);
            }
        }, elements);
    }

    private static void assertSorted(int... elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i] >= elements[i + 1]) {
                throw new IllegalArgumentException("Expected set should be expressed as ascending integers.");
            }
        }
    }

    public Indexing.Key indexing() {
        return Indexing.IndexingAssertBuilder(subject);
    }

    public BinaryOperation union(int... elements) {
        return BinaryOperation.union(subject, elements);
    }

    public BinaryOperation intersection(int... elements) {
        return BinaryOperation.intersection(subject, elements);
    }

    public BinaryOperation difference(int... elements) {
        return BinaryOperation.difference(subject, elements);
    }

    public BinaryStatement equivalent(int... elements) {
        return BinaryProperty.equivalent(subject, elements);
    }

    public BinaryStatement notEquivalent(int... elements) {
        return BinaryProperty.notEquivalent(subject, elements);
    }

    public BinaryStatement intersects(int... elements) {
        return BinaryProperty.intersects(subject, elements);
    }

    public BinaryStatement doesNotIntersect(int... elements) {
        return BinaryProperty.doesNotIntersect(subject, elements);
    }

    public BinaryStatement subset(int... elements) {
        return BinaryProperty.subset(subject, elements);
    }

    public BinaryStatement noSubset(int... elements) {
        return BinaryProperty.noSubSet(subject, elements);
    }
}
