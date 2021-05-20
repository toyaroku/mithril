package collections.longs.datastructures.arrays;

import collections.longs.datastructures.bitsets.BitSet;
import collections.longs.datastructures.bitsets.BitSetIterator;
import collections.longs.datastructures.bitsets.BitSetView;
import collections.longs.datastructures.bitsets.MutableBitSet;
import collections.longs.datastructures.iterators.Aggregations;
import collections.longs.datastructures.maps.*;
import collections.longs.datastructures.sets.SetView;
import collections.longs.datastructures.sets.SortedSetIterator;
import collections.longs.datastructures.sorting.SortedSetView;
import util.BitUtil;

import static collections.Collections.noSuchInt;
import static collections.Collections.noSuchLong;

public class CompressedBitSet implements BitSet {

    private final CompressedSortedMap.Mutable bitSets;
    private final EntryCompressor compressor;
    private final int bitSetSize;

    private CompressedBitSet(CompressedSortedMap.Mutable bitSets, EntryCompressor compressor) {
        this.bitSets = bitSets;
        this.compressor = compressor;
        this.bitSetSize = compressor.getValueBits();
    }

    private CompressedBitSet(CompressedBitSet original) {
        this(original.bitSets.mutableCopy(), original.compressor);
    }

    public static CompressedBitSet.Mutable empty(EntryCompressor compressor) {
        return new CompressedBitSet(CompressedSortedMap.empty(compressor), compressor).asMutable();
    }

    public static CompressedBitSet fromSSetView(SetView view, EntryCompressor compressor) {
        Mutable bitSet = empty(compressor);
        while (view.hasNextUnique()) {
            view.iterateNextUnique();
            long value = view.element();
            bitSet.add(value);
        }
        return new CompressedBitSet(bitSet);
    }

    public static CompressedBitSet fromSortedSetView(SortedSetView view, EntryCompressor compressor) {
        SortedSetToMapAdapter sortedSetToMapAdapter = new SortedSetToMapAdapter(view, compressor);
        CompressedSortedMap.Mutable bitSets = CompressedSortedMap.fromSortedView(sortedSetToMapAdapter, compressor);
        return new CompressedBitSet(bitSets, compressor);
    }

    public static CompressedBitSet fromView(BitSetView view, EntryCompressor compressor) {
        BitSetToMapViewAdapter bitSetToMapViewAdapter = new BitSetToMapViewAdapter(view, compressor);
        CompressedSortedMap.Mutable bitSets = CompressedSortedMap.fromSortedView(bitSetToMapViewAdapter, compressor);
        return new CompressedBitSet(bitSets, compressor);
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
    public BitSetView increasingBitSets() {
        return new NonEmptyBitSetIterator(bitSets.beforeSmallestKey(), compressor);
    }

    @Override
    public BitSetIterator beforeSmallest() {
        return new NonEmptyBitSetIterator(bitSets.beforeSmallestKey(), compressor);
    }

    @Override
    public BitSetIterator afterGreatest() {
        return new NonEmptyBitSetIterator(bitSets.afterGreatestKey(), compressor);
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
    public SortedSetView elements() {
        return new ElementIterator(new NonEmptyBitSetIterator(bitSets.beforeSmallestKey(), compressor));
    }

    private Mutable asMutable() {
        return new Mutable(bitSets, compressor);
    }

    public class Mutable extends CompressedBitSet implements MutableBitSet {

        private Mutable(CompressedSortedMap.Mutable bitSets, EntryCompressor compressor) {
            super(bitSets, compressor);
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
        BitSetIterator iterator = this.beforeSmallest();
        StringBuilder stringBuilder = new StringBuilder("[ ")
            .append(iterator.next());

        int maxElements = 20;
        while (iterator.isEmpty()) {
            stringBuilder
                .append(", ")
                .append(iterator.next());
            if (maxElements-- < 0 && iterator.isEmpty()) {
                return stringBuilder.append(" and more... ]").toString();
            }
        }
        return stringBuilder.append(" ]").toString();
    }

    private static class NonEmptyBitSetIterator implements BitSetView, BitSetIterator {

        private final SortedMapIterator entryIterator;
        private final EntryCompressor compressor;

        private long offset;

        private NonEmptyBitSetIterator(SortedMapIterator iterator, EntryCompressor compressor) {
            this.entryIterator = iterator;
            this.compressor = compressor;
        }

        @Override
        public long bitSet() {
            return entryIterator.value();
        }

        @Override
        public long bitSetOffset() {
            return offset;
        }

        @Override
        public int bitSetLength() {
            return compressor.getValueBits();
        }

        @Override
        public boolean hasNextBitSet() {
            return entryIterator.hasGreater();
        }

        @Override
        public void iterateNextBitSet() {
            entryIterator.iterateGreater();
            recalculateOffset();
        }

        @Override
        public boolean hasPreviousBitSet() {
            return entryIterator.hasSmaller();
        }

        @Override
        public void iteratePreviousBitSet() {
            entryIterator.iterateSmaller();
            recalculateOffset();
        }

        @Override
        public boolean hasGreater() {
            return entryIterator.hasGreater();
        }

        @Override
        public void iterateGreater() {
            entryIterator.iterateGreater();
        }

        private void recalculateOffset() {
            // TODO extend entry compressor to not repeat this?
            offset = entryIterator.key() * compressor.getValueBits();
        }
    }

    private static class ElementIterator implements SortedSetView, SortedSetIterator {

        private final NonEmptyBitSetIterator bitSetIterator;

        // TODO store shifted word for efficiency
        private int previousBitIndex;
        private int currentBitIndex;
        private int nextBitIndex;
        private boolean hasNext;
        private boolean hasPrevious;

        private long currentValue;

        private ElementIterator(NonEmptyBitSetIterator bitSetIterator) {
            this.bitSetIterator = bitSetIterator;
        }

        @Override
        public long element() {
            return currentValue;
        }

        @Override
        public boolean hasGreater() {
            return hasNext;
        }

        @Override
        public void iterateGreater() {
            if (noSuchInt(nextBitIndex)) {
                bitSetIterator.iterateNextBitSet();
                currentBitIndex = BitUtil.firstBit(bitSetIterator.bitSet());
                previousBitIndex = noSuchInt();
            } else {
                previousBitIndex = currentBitIndex;
                currentBitIndex = nextBitIndex;
            }
            nextBitIndex = BitUtil.nextSetBitInclusive(bitSetIterator.bitSet(), currentBitIndex + 1);
            hasPrevious = true;
            recalculateCurrentValue();
        }

        @Override
        public boolean hasSmaller() {
            return hasPrevious;
        }

        @Override
        public void iterateSmaller() {
            if (noSuchInt(previousBitIndex)) {
                bitSetIterator.iteratePreviousBitSet();
                currentBitIndex = BitUtil.lastBit(bitSetIterator.bitSet());
                nextBitIndex = noSuchInt();
            } else {
                nextBitIndex = currentBitIndex;
                currentBitIndex = previousBitIndex;
            }
            previousBitIndex = BitUtil.previousSetBitInclusive(bitSetIterator.bitSet(), currentBitIndex - 1);
            hasPrevious = bitSetIterator.hasPreviousBitSet() || !noSuchInt(previousBitIndex);
            hasNext = true;
            recalculateCurrentValue();
        }

        private void recalculateCurrentValue() {
            currentValue = bitSetIterator.bitSetOffset() + currentBitIndex;
        }

        @Override
        public boolean hasNextUnique() {
            return hasGreater();
        }

        @Override
        public void iterateNextUnique() {
            iterateGreater();
        }
    }

    private static class BitSetToMapViewAdapter implements MapView, SortedMapView {

        private final EntryCompressor compressor;
        private final BitSetView bitSetView;

        private long key;

        private BitSetToMapViewAdapter(BitSetView bitSetView, EntryCompressor compressor) {
            this.bitSetView = bitSetView;
            this.compressor = compressor;
        }

        @Override
        public long key() {
            return key;
        }

        @Override
        public long value() {
            return bitSetView.bitSet();
        }

        @Override
        public boolean hasNextEntry() {
            return bitSetView.hasGreater();
        }

        @Override
        public void iterateNextEntry() {
            bitSetView.iterateGreater();
            recalculateKey();
        }

        @Override
        public boolean hasGreater() {
            return bitSetView.hasGreater();
        }

        @Override
        public void iterateGreater() {
            bitSetView.iterateGreater();
            recalculateKey();
        }

        private void recalculateKey() {
            key = bitSetView.bitSetOffset() / compressor.getValueBits();
        }
    }

    private static class SortedSetToMapAdapter implements SortedMapView {

        private final EntryCompressor compressor;
        private final SortedSetView setView;

        private long bitSet;
        private long offset;
        private boolean hasNext;

        private SortedSetToMapAdapter(SortedSetView setView, EntryCompressor compressor) {
            this.setView = setView;
            this.compressor = compressor;
            this.hasNext = setView.hasGreater();
        }

        @Override
        public long key() {
            return offset;
        }

        @Override
        public long value() {
            return bitSet;
        }

        @Override
        public boolean hasGreater() {
            return hasNext;
        }

        @Override
        public void iterateGreater() {
            bitSet = 0L;
            long element = setView.element();
            long offset = element / compressor.getValueBits();
            long bitIndex = element % compressor.getValueBits();
            long bitset = BitUtil.withBitSetAtIndex(bitSet, bitIndex);
            long offsetIt = offset;
            while (offset == offsetIt && hasNext) {
                element = setView.element();
                bitIndex = element % compressor.getValueBits();
                bitset = BitUtil.withBitSetAtIndex(bitSet, bitIndex);
                if (setView.hasGreater()) {
                    setView.iterateGreater();
                    offsetIt = element / compressor.getValueBits();
                } else {
                    hasNext = false;
                }
            }
            this.bitSet = bitset;
            this.offset = offset;
        }
    }

}
