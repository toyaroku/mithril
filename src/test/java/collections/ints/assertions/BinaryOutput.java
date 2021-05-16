package collections.ints.assertions;

import collections.ints.Set;

public interface BinaryOutput {

    <S extends Set<S>> boolean outputFor(SetFactory<S> setFactory);
}
