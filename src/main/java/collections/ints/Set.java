package collections.ints;

import iterators.ints.Iterator;

public interface Set<S extends Set<S>> {

    boolean isEmpty();

    int cardinality();

    int smallest();

    int greatest();

    int smaller(int element);

    int greater(int element);

    Iterator increasing();

    Iterator decreasing();

    boolean contains(int element);

    boolean equivalent(S other);

    boolean intersects(S other);

    boolean subSet(S other);

    S union(S other);

    S intersection(S other);

    S difference(S other);

    S deepCopy();
}
