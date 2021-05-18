package collections.longs.datastructures.arrays;

import collections.Collections;
import collections.longs.datastructures.*;
import util.BitUtil;

import java.util.Arrays;

import static collections.Collections.noSuchInt;
import static collections.Collections.noSuchLong;
import static util.BitUtil.BITS_PER_LONG;

public class CompressedBitSet implements MutableBitSet {

    private final CompressedSortedMap bitSets;
    private final EntryCompressor compressor;
    private final int bitSetSize;

    public CompressedBitSet(EntryCompressor compressor) {
        this.bitSets = new CompressedSortedMap(compressor);
        this.compressor = compressor;
        this.bitSetSize = compressor.getValueBits();
    }

    private long calculateElement(long key, int indexInBitSet) {
        return calculateOffset(key) + indexInBitSet;
    }

    private long calculateOffset(long key) {
        // TODO only allow powers of two to use bit operations
        return key * bitSetSize;
    }

    private long calculateBitSetIndex(long element) {
        // TODO only allow powers of two to use bit operations
        return element / bitSetSize;
    }

    private long calculateIndexInBitset(long element) {
        // TODO only allow powers of two to use bit operations
        return element % bitSetSize;
    }

    private long createBitSetSingleton(long indexInBitSet) {
        return BitUtil.withBitSetAtIndex(0L, indexInBitSet);
    }

    @Override
    public long smallest() {
        if (isEmpty()) {
            return noSuchLong();
        }
        long smallestEntry = bitSets.smallestEntry();
        long key = compressor.key(smallestEntry);
        long value = compressor.value(smallestEntry);
        int indexOfFirstBit = BitUtil.firstBit(value);
        return calculateElement(key, indexOfFirstBit);
    }

    @Override
    public long greatest() {
        if (isEmpty()) {
            return noSuchLong();
        }
        long greatestEntry = bitSets.greatestEntry();
        long key = compressor.key(greatestEntry);
        long bitSet = compressor.value(greatestEntry);
        int indexOfFLastBit = BitUtil.lastBit(bitSet);
        return calculateElement(key, indexOfFLastBit);
    }

    @Override
    public BitSetIterator ascending() {
        return isEmpty() ? BitSetIterator.empty() : new Ascending();
    }

    @Override
    public BitSetIterator descending() {
        return isEmpty() ? BitSetIterator.empty() : new Descending();
    }

    @Override
    public void add(long element) {
        long bitSetIndex = calculateBitSetIndex(element);
        long indexInBitSet = calculateIndexInBitset(element);
        long singleton = createBitSetSingleton(indexInBitSet);
        bitSets.update(bitSetIndex, singleton, Updates.logicalOr());
    }

    @Override
    public void remove(long element) {
        long bitSetIndex = calculateBitSetIndex(element);
        bitSets.remove(bitSetIndex);
    }

    @Override
    public boolean isEmpty() {
        return bitSets.isEmpty();
    }

    @Override
    public int cardinality() {
        return (int) bitSets.aggregateValues(0L, Aggregations.sumCardinality());
    }

    @Override
    public boolean contains(long element) {
        long bitSetIndex = calculateBitSetIndex(element);
        long bitSet = bitSets.value(bitSetIndex);
        if (!noSuchLong(bitSet)) {
            long bitIndex = calculateIndexInBitset(element);
            return BitUtil.containsBitAtIndex(bitSet, bitIndex);
        }
        return false;
    }

    @Override
    public Iterator elements() {
        return ascending();
    }

    // TODO resolve code duplication
    private class Ascending implements BitSetIterator {

        private Map.EntryIterator entryIterator;
        private long currentOffset;
        private long currentBitSet;
        private int currentBitIndex;

        private Ascending() {
            entryIterator = bitSets.ascending();
            entryIterator.next();
            currentBitSet = entryIterator.value();
            currentOffset = calculateOffset(entryIterator.key());
            currentBitIndex = -1;
        }

        @Override
        public long currentElement() {
            return currentOffset + currentBitIndex;
        }

        @Override
        public long getBitSet() {
            return currentBitSet;
        }

        @Override
        public long getBitSetIndex() {
            return entryIterator.key();
        }

        @Override
        public long getOffset() {
            return calculateOffset(entryIterator.key());
        }

        @Override
        public boolean hasNext() {
            return entryIterator.hasNext() || currentBitIndex != BitUtil.lastBit(currentBitSet);
        }

        @Override
        public long next() {
            currentBitIndex = BitUtil.nextSetBitInclusive(currentBitSet, currentBitIndex + 1);
            if (noSuchInt(currentBitIndex)) {
                if (entryIterator.hasNext()) {
                    entryIterator.next();
                    currentBitSet = entryIterator.value();
                    currentOffset = calculateOffset(entryIterator.key());
                    currentBitIndex = BitUtil.firstBit(currentBitSet);
                }
            }
            return currentElement();
        }
    }


    private class Descending implements BitSetIterator {

        private Map.EntryIterator entryIterator;
        private long currentOffset;
        private long currentBitSet;
        private int currentBitIndex;

        private Descending() {
            entryIterator = bitSets.descending();
            entryIterator.next();
            currentBitSet = entryIterator.value();
            currentOffset = calculateOffset(entryIterator.key());
            currentBitIndex = BITS_PER_LONG;
        }

        @Override
        public long currentElement() {
            return currentOffset + currentBitIndex;
        }

        @Override
        public long getBitSet() {
            return currentBitSet;
        }

        @Override
        public long getBitSetIndex() {
            return entryIterator.key();
        }

        @Override
        public long getOffset() {
            return calculateOffset(entryIterator.key());
        }

        @Override
        public boolean hasNext() {
            return entryIterator.hasNext() || currentBitIndex != BitUtil.firstBit(currentBitSet);
        }

        @Override
        public long next() {
            // TODO store shifted long to reduce calculations
            currentBitIndex = BitUtil.previousSetBitInclusive(currentBitSet, currentBitIndex - 1);
            if (noSuchInt(currentBitIndex)) {
                if (entryIterator.hasNext()) {
                    entryIterator.next();
                    currentBitSet = entryIterator.value();
                    currentOffset = calculateOffset(entryIterator.key());
                    currentBitIndex = BitUtil.lastBit(currentBitSet);
                }
            }
            return currentElement();
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        }
        BitSetIterator iterator = ascending();
        StringBuilder stringBuilder = new StringBuilder("[ ")
            .append(iterator.next());

        int maxElements = 20;
        while (iterator.hasNext()) {
            stringBuilder
                .append(", ")
                .append(iterator.next());
            if (maxElements-- < 0 && iterator.hasNext()) {
                return stringBuilder.append(" and more... ]").toString();
            }
        }
        return stringBuilder.append(" ]").toString();
    }
}
