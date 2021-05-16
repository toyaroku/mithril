package collections.ints.sets.bitarray;

import util.BitUtil;
import util.MaskedBinarySearch;

public class Factory {

    private final IntegerBitSetWithMetaData addressCalculator;
    private final MaskedBinarySearch wordIndexerByAddress;

    private Factory(IntegerBitSetWithMetaData addressCalculator) {
        this.addressCalculator = addressCalculator;
        this.wordIndexerByAddress = addressCalculator.createMetaDataBinarySearch();
    }

    public static Factory v1() {
        return new Factory(IntegerBitSetWithMetaData.bitSetLeastSignificant());
    }

    public static Factory v2() {
        return new Factory(IntegerBitSetWithMetaData.bitSetMostSignificant());
    }

    public SparseBitArray empty() {
        return new SparseBitArray(addressCalculator, wordIndexerByAddress, new long[0], 0);
    }

    public SparseBitArray fromSortedArray(int... sortedElements) {
        int lastAddress = -1;
        int size = 0;
        for (int i = 0; i < sortedElements.length; i++) {
            int element = sortedElements[i];
            int address = addressCalculator.calculateIndexOfContainingBitSet(element);
            assert address >= lastAddress;
            if (address > lastAddress) {
                lastAddress = address;
                size++;
            }
        }
        if (size == 0) {
            return empty();
        }
        long[] words = new long[size];
        int index = -1;
        lastAddress = -1;
        for (int i = 0; i < sortedElements.length; i++) {
            int element = sortedElements[i];
            int address = addressCalculator.calculateIndexOfContainingBitSet(element);
            assert address >= lastAddress;
            int indexInBitSet = addressCalculator.calculateIndexInBitSet(element);
            if (address > lastAddress) {
                long addressOnly = addressCalculator.withMetaData(0L, address);
                long word = addressCalculator.withBitSetAtIndex(addressOnly, indexInBitSet);
                words[++index] = word;
                lastAddress = address;
            } else {
                long word = words[index];
                words[index] = addressCalculator.withBitSetAtIndex(word, indexInBitSet);
            }
        }
        return new SparseBitArray(addressCalculator, wordIndexerByAddress, words, size);
    }
}
