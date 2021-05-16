package collections.ints.sets.bitarray;

import util.BitUtil;
import util.MaskedBinarySearch;

import static util.BitUtil.BITS_PER_INT;

public class IntegerBitSetWithMetaData {

    private static final int BIT_SZT_SIZE = BITS_PER_INT;
    private static final int BITS_TO_RESEMBLE_BITSET_INDEX = BitUtil.log2Ceil(BIT_SZT_SIZE);
    private static final int BIT_SET_INDEX_MASK = -1 >>> (BIT_SZT_SIZE - BITS_TO_RESEMBLE_BITSET_INDEX);

    private final IndexingType indexingType;
    private final long metaDataMask;

    private IntegerBitSetWithMetaData(IndexingType indexingType) {
        this.indexingType = indexingType;
        this.metaDataMask = indexingType.calculateMetaDataMask();
    }

    static IntegerBitSetWithMetaData bitSetLeastSignificant() {
        return new IntegerBitSetWithMetaData(IndexingType.BITSET_LEAST_SIGNIFICANT);
    }

    static IntegerBitSetWithMetaData bitSetMostSignificant() {
        return new IntegerBitSetWithMetaData(IndexingType.BITSET_MOST_SIGNIFICANT);
    }

    int extractBitSet(long word) {
        return indexingType.extractBitSet(word);
    }

    public long withBitSetAtIndex(long word, int indexToSet) {
        return indexingType.withBitSetAtIndex(word, indexToSet);
    }

    int extractMetaData(long word) {
        return indexingType.extractMetaData(word);
    }

    long withMetaData(long word, int metaData) {
        return indexingType.withMetaData(word, metaData);
    }

    int calculateIndexOfContainingBitSet(int element) {
        return element >> BITS_TO_RESEMBLE_BITSET_INDEX;
    }

    int calculateIndexInBitSet(int element) {
        return element & BIT_SET_INDEX_MASK;
    }

    int calculateMaxCardinality(int numberOfBitSets) {
        return numberOfBitSets << BITS_TO_RESEMBLE_BITSET_INDEX;
    }

    public MaskedBinarySearch createMetaDataBinarySearch() {
        return new MaskedBinarySearch(metaDataMask);
    }

    public boolean isEquivalentTo(IntegerBitSetWithMetaData other) {
        return other.indexingType == indexingType;
    }

    private enum IndexingType {

        BITSET_MOST_SIGNIFICANT {
            @Override
            long withMetaData(long word, int metaData) {
                return BitUtil.withLowerHalf(word, metaData);
            }

            @Override
            int extractMetaData(long word) {
                return BitUtil.lowerHalf(word);
            }

            @Override
            int extractBitSet(long word) {
                return BitUtil.upperHalf(word);
            }

            @Override
            long withBitSetAtIndex(long word, int indexToSet) {
                return BitUtil.withBitSetAtIndex(word, indexToSet + BIT_SZT_SIZE);
            }

            @Override
            long calculateMetaDataMask() {
                return -1L >>> BIT_SZT_SIZE;
            }
        },
        BITSET_LEAST_SIGNIFICANT {
            @Override
            long withMetaData(long word, int metaData) {
                return BitUtil.withUpperHalf(word, metaData);
            }

            @Override
            int extractMetaData(long word) {
                return BitUtil.upperHalf(word);
            }

            @Override
            int extractBitSet(long word) {
                return BitUtil.lowerHalf(word);
            }

            @Override
            long withBitSetAtIndex(long word, int indexToSet) {
                return BitUtil.withBitSetAtIndex(word, indexToSet);
            }

            @Override
            long calculateMetaDataMask() {
                return -1L << BIT_SZT_SIZE;
            }
        };

        abstract long withMetaData(long word, int metaData);

        abstract int extractMetaData(long word);

        abstract int extractBitSet(long word);

        abstract long withBitSetAtIndex(long word, int indexToSet);

        abstract long calculateMetaDataMask();
    }
}
