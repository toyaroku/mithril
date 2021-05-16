package collections.ints.assertions;

import collections.ints.Set;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class BinaryStatement implements Statement {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryStatement.class);

    private final BinaryOutput test;
    private final boolean expected;

    private BinaryStatement(BinaryOutput test, boolean expected) {
        this.test = test;
        this.expected = expected;
    }

    static BinaryStatement holds(BinaryOutput test) {
        return new BinaryStatement(test, true);
    }

    static BinaryStatement doesNotHold(BinaryOutput test) {
        return new BinaryStatement(test, false);
    }

    @Override
    public <S extends Set<S>> boolean holdsFor(SetFactory<S> setFactory) {
        boolean actual = test.outputFor(setFactory);
        if (actual != expected) {
            return false;
        }
        return true;
    }
}
