package collections.ints;

import collections.ints.sets.bitarray.Factory;
import collections.ints.sets.bitarray.SparseBitArray;

public class SparseBitArrayFactories {

    public static SparseBitArray fromSortedArray(int... elements) {
        return new Factory().fromSortedArray(elements);
    }
}
