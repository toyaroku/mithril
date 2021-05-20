package collections.longs.datastructures.bitsets;

public interface BitSetIterator extends BitSetView {

    boolean hasNextBitSet();

    void iterateNextBitSet();

    boolean hasPreviousBitSet();

    void iteratePreviousBitSet();
}
