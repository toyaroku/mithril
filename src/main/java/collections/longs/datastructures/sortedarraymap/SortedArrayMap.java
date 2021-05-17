package collections.longs.datastructures.sortedarraymap;

import collections.Collections;
import collections.longs.datastructures.Iterator;
import collections.longs.datastructures.Map;

import static collections.Collections.noSuchLong;
import static util.BitUtil.BITS_PER_LONG;
import static util.MaskedBinarySearch.indexOfOrInverseInsertIndex;

public class SortedArrayMap implements Map {

    private final int bitsPerKey;
    private final int bitsPerValue;
    private final long keyMask;
    private final long valueMask;

    private int cardinality;
    private long[] entries;

    SortedArrayMap(int bitsPerKey) {
        this.bitsPerKey = bitsPerKey;
        this.bitsPerValue = BITS_PER_LONG - bitsPerKey;
        // key is encoded at the least significant part
        this.keyMask = -1L >> bitsPerKey;
        this.valueMask = -1L << bitsPerValue;
    }

    @Override
    public int cardinality() {
        return cardinality;
    }

    @Override
    public long getMaxSupportedKey() {
        return 1 >> bitsPerKey;
    }

    @Override
    public long getMaxSupportedValue() {
        return 1 >> bitsPerValue;
    }

    @Override
    public boolean isEmpty() {
        return cardinality == 0;
    }

    @Override
    public boolean containsKey(long key) {
        return indexOfOrInverseInsertIndex(keyMask, entries, cardinality, key) >= 0;
    }

    @Override
    public long getValue(long key) {
        int entryIndex = indexOfOrInverseInsertIndex(keyMask, entries, cardinality, key);
        if (entryIndex < 0) {
            return Collections.noSuchInteger();
        }
        return (entries[entryIndex] & valueMask) >> bitsPerKey;
    }

    @Override
    public Iterator ascending() {
        return new It(Order.LEFT_TO_RIGHT);
    }

    @Override
    public Iterator descending() {
        return new It(Order.RIGHT_TO_LEFT);
    }

    private enum Order {
        LEFT_TO_RIGHT {
            @Override
            boolean hasNext(int currentIndex, int arrayLength) {
                return currentIndex < arrayLength - 1;
            }

            @Override
            int nextIndex(int currentIndex, int arrayLength) {
                return hasNext(currentIndex, arrayLength) ? currentIndex + 1 : Collections.noSuchInteger();
            }

            @Override
            int initialIndex(int arrayLength) {
                return -1;
            }
        },
        RIGHT_TO_LEFT {
            @Override
            boolean hasNext(int currentIndex, int arrayLength) {
                return currentIndex > 0;
            }

            @Override
            int nextIndex(int currentIndex, int arrayLength) {
                return hasNext(currentIndex, arrayLength) ? currentIndex - 1 : Collections.noSuchInteger();
            }

            @Override
            int initialIndex(int arrayLength) {
                return arrayLength;
            }
        };

        abstract boolean hasNext(int currentIndex, int arrayLength);

        abstract int nextIndex(int currentIndex, int arrayLength);

        abstract int initialIndex(int arrayLength);
    }

    private enum Relation {
        SMALLER_THAN {
            @Override
            boolean test(long key, long comp) {
                return key < comp;
            }
        },
        SMALLER_THAN_OR_EQUALS {
            @Override
            boolean test(long key, long comp) {
                return key <= comp;
            }
        },
        GREATER_THAN {
            @Override
            boolean test(long key, long comp) {
                return key > comp;
            }
        },
        GREATER_THAN_OR_EQUALS {
            @Override
            boolean test(long key, long comp) {
                return key >= comp;
            }
        };

        abstract boolean test(long key, long comp);
    }

    private class It implements Iterator {

        private final Order iterationOrder;
        private int currentIndex;
        private long currentKey;

        private It(Order iterationOrder) {
            this.iterationOrder = iterationOrder;
            this.currentIndex = iterationOrder.initialIndex(cardinality);
            this.currentKey = noSuchLong();
        }

        @Override
        public boolean hasNext() {
            return iterationOrder.hasNext(currentIndex, cardinality);
        }

        @Override
        public long nextKey() {
            currentIndex = iterationOrder.nextIndex(currentIndex, cardinality);
            currentKey = Collections.noSuchInteger(currentIndex) ? Collections.noSuchInteger() : keyMask & entries[currentIndex];
            return currentKey;
        }

        private long findNextKeyMatching(Relation relation, long threshold) {
            long nextKey = nextKey();
            while (!noSuchLong(nextKey) && !relation.test(nextKey, threshold)) {
                nextKey = nextKey();
            }
            return currentKey;
        }

        @Override
        public long nextKeySmallerThan(long threshold) {
            return findNextKeyMatching(Relation.SMALLER_THAN, threshold);
        }

        @Override
        public long nextKeySmallerThanOrEqualTo(long threshold) {
            return findNextKeyMatching(Relation.SMALLER_THAN_OR_EQUALS, threshold);
        }

        @Override
        public long nextKeyGreaterThan(long threshold) {
            return findNextKeyMatching(Relation.GREATER_THAN, threshold);
        }

        @Override
        public long nextKeyGreaterThanOrEqualTo(long threshold) {
            return findNextKeyMatching(Relation.GREATER_THAN_OR_EQUALS, threshold);
        }

        @Override
        public long currentKey() {
            return currentKey;
        }

        @Override
        public long currentValue() {
            return (entries[currentIndex] & valueMask) >> bitsPerKey;
        }
    }
}
