package collections.ints.assertions;

import collections.ints.Set;

public interface SetFactory<S extends Set<S>> {

    S create(int... elements);
}
