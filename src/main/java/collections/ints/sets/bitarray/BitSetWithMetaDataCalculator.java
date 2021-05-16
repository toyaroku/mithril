package collections.ints.sets.bitarray;

import util.BitUtil;
import util.MaskedBinarySearch;

public class BitSetWithMetaDataCalculator {

    // hard-coded for now
    private static final int BITS_PER_INT = 32;
    private static final long META_DATA_MASK = -1L << BITS_PER_INT;

    int extractBitSet(long word) {
        return BitUtil.lowerHalf(word);
    }

    int extractMetaData(long word) {
        return BitUtil.upperHalf(word);
    }

    long withMetaData(long word, int address) {
        return BitUtil.withUpperHalf(word, address);
    }

    public long getMetaDataMask() {
        return META_DATA_MASK;
    }

    int calculateIndexOfContainingBitSet(int element) {
        return element / BITS_PER_INT;
    }

    int calculateIndexInBitSet(int element) {
        return element % BITS_PER_INT;
    }

    int calculateMaxCardinalityF(int numberOfBitSets) {
        return numberOfBitSets * BITS_PER_INT;
    }

    public MaskedBinarySearch createMetaDataBinarySearch() {
        return new MaskedBinarySearch(META_DATA_MASK);
    }

    public boolean isEquivalentTo(BitSetWithMetaDataCalculator other) {
        // config is hard coded for now
        return true;
    }
}
