package collections.ints.assertions;

import collections.ints.Set;

public interface SetOutput {

    <S extends Set<S>> S outputFor(SetFactory<S> setFactory);
}
