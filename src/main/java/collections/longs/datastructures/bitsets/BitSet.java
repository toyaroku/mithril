package collections.longs.datastructures.bitsets;

import collections.longs.datastructures.sets.SortedSet;

public interface BitSet extends SortedSet {

    BitSetView increasingBitSets();

    @Override
    BitSetIterator beforeSmallest();

    @Override
    BitSetIterator afterGreatest();

    @Override
    BitSet copy();

    @Override
    MutableBitSet mutableCopy();
}
