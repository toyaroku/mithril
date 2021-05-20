package collections.longs.datastructures.bitsets;

import collections.longs.datastructures.sets.MutableSet;

public interface MutableBitSet extends MutableSet, BitSet {

    @Override
    MutableBitSet copy();
}
