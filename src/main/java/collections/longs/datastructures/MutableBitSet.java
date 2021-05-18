package collections.longs.datastructures;

public interface MutableBitSet extends MutableSet, BitSet {

    @Override
    MutableBitSet copy();
}
