package collections.longs.datastructures;

import collections.longs.datastructures.sets.Set;

public interface Operator<S extends Set> {

    S union(S operand1, S operand2);
}
