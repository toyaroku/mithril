package collections.ints.assertions;

import collections.ints.Set;
import org.junit.jupiter.api.Assertions;

public interface Statement {

    <S extends Set<S>> boolean holdsFor(SetFactory<S> setFactory);

    default <S extends Set<S>> void assertHoldsFor(SetFactory<S> setFactory) {
        Assertions.assertTrue(holdsFor(setFactory));
    }
}
