package collections.longs.datastructures.arrays;

import collections.longs.datastructures.*;
import util.BitUtil;

import static collections.Collections.noSuchInt;
import static collections.Collections.noSuchLong;
import static util.BitUtil.BITS_PER_LONG;

public class CompressedBitSet implements BitSet {

    private final CompressedSortedMap.Mutable bitSets;
    private final EntryCompressor compressor;
    private final int bitSetSize;

    private CompressedBitSet(CompressedSortedMap.Mutable bitSets, EntryCompressor compressor, int bitSetSize) {
        this.bitSets = bitSets;
        this.compressor = compressor;
        this.bitSetSize = bitSetSize;
    }

    private CompressedBitSet(CompressedBitSet original) {
        this(original.bitSets.mutableCopy(), original.compressor, original.bitSetSize);
    }

    public CompressedBitSet(EntryCompressor compressor) {
        this.bitSets = CompressedSortedMap.empty(compressor);
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
        return isEmpty() ? BitSetIterator.empty() : new Iterator(Order.ASCENDING);
    }

    @Override
    public BitSetIterator descending() {
        return isEmpty() ? BitSetIterator.empty() : new Iterator(Order.DESCENDING);
    }

    @Override
    public CompressedBitSet copy() {
        return new CompressedBitSet(this);
    }

    @Override
    public CompressedBitSet.Mutable mutableCopy() {
        return copy().asMutable();
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
    public collections.longs.datastructures.Iterator elements() {
        return ascending();
    }

    private enum Order {
        ASCENDING {
            @Override
            Map.EntryIterator entryIterator(CompressedSortedMap map) {
                return map.ascending();
            }

            @Override
            int initialBitIndex() {
                return -1;
            }

            @Override
            boolean hasNextBit(long currentBitSet, int currentBitIndex) {
                return currentBitIndex < BitUtil.lastBit(currentBitSet);
            }

            @Override
            int nextBitIndex(long currentBitSet, int currentBitIndex) {
                return BitUtil.nextSetBitInclusive(currentBitSet, currentBitIndex + 1);
            }

            @Override
            int firstBitIndex(long currentBitSet) {
                return BitUtil.firstBit(currentBitSet);
            }
        },
        DESCENDING {
            @Override
            Map.EntryIterator entryIterator(CompressedSortedMap map) {
                return map.descending();
            }

            @Override
            int initialBitIndex() {
                return BITS_PER_LONG;
            }

            @Override
            boolean hasNextBit(long currentBitSet, int currentBitIndex) {
                return currentBitIndex > BitUtil.firstBit(currentBitSet);
            }

            @Override
            int nextBitIndex(long currentBitSet, int currentBitIndex) {
                return BitUtil.previousSetBitInclusive(currentBitSet, currentBitIndex - 1);
            }

            @Override
            int firstBitIndex(long currentBitSet) {
                return BitUtil.lastBit(currentBitSet);
            }
        };

        abstract Map.EntryIterator entryIterator(CompressedSortedMap map);

        abstract int initialBitIndex();

        abstract boolean hasNextBit(long currentBitSet, int currentBitIndex);

        abstract int nextBitIndex(long currentBitSet, int currentBitIndex);

        abstract int firstBitIndex(long currentBitSet);
    }

    private class Iterator implements BitSetIterator {

        private final Order order;
        private final Map.EntryIterator entryIterator;

        private long currentOffset;
        private long currentBitSet;
        private int currentBitIndex;

        private Iterator(Order order) {
            this.order = order;
            this.entryIterator = order.entryIterator(bitSets);

            entryIterator.next();
            currentBitSet = entryIterator.value();
            currentOffset = calculateOffset(entryIterator.key());
            currentBitIndex = order.initialBitIndex();
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
        public long getSmallestPossibleValue() {
            return currentOffset;
        }

        @Override
        public int getBitSetSize() {
            return bitSetSize;
        }

        @Override
        public boolean hasNext() {
            return entryIterator.hasNext() || order.hasNextBit(currentBitSet, currentBitIndex);
        }

        @Override
        public long next() {
            currentBitIndex = order.nextBitIndex(currentBitSet, currentBitIndex);
            if (noSuchInt(currentBitIndex)) {
                if (entryIterator.hasNext()) {
                    entryIterator.next();
                    currentBitSet = entryIterator.value();
                    currentOffset = calculateOffset(entryIterator.key());
                    currentBitIndex = order.firstBitIndex(currentBitSet);
                }
            }
            return currentElement();
        }
    }

    private Mutable asMutable() {
        return new Mutable(bitSets, compressor, bitSetSize);
    }

    public class Mutable extends CompressedBitSet implements MutableBitSet {

        private Mutable(CompressedSortedMap.Mutable bitSets, EntryCompressor compressor, int bitSetSize) {
            super(bitSets, compressor, bitSetSize);
        }

        private Mutable(Mutable original) {
            super(original);
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
        public Mutable copy() {
            return new Mutable(this);
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
