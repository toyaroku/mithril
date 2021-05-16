package collections.ints;

import collections.ints.sets.bitarray.Factory;
import collections.ints.sets.bitarray.SparseBitArray;

public class SparseBitArrayFactories {

    public static SparseBitArray v1FromSortedArray(int... elements) {
        return Factory.v1().fromSortedArray(elements);
    }

    public static SparseBitArray v2FromSortedArray(int... elements) {
        return Factory.v2().fromSortedArray(elements);
    }
}
