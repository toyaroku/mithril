package collections.longs.datastructures.arrays;

import collections.longs.datastructures.*;

import static collections.Collections.noSuchLong;

class CompressedSortedMap implements MutableSortedMap {

    private final EntryCompressor compressor;
    private final SortedResizableArray array;

    CompressedSortedMap(EntryCompressor compressor) {
        this.array = SortedResizableArray.empty();
        this.compressor = compressor;
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int size() {
        return array.length();
    }

    @Override
    public boolean containsKey(long key) {
        int index = findArrayIndex(key);
        return Sorting.isPresent(index);
    }

    @Override
    public long value(long key) {
        int index = findArrayIndex(key);
        if (Sorting.isPresent(index)) {
            return compressor.value(array.get(index));
        }
        return noSuchLong();
    }

    @Override
    public long aggregateValues(long initialValue, Aggregation aggregation) {
        if (isEmpty()) {
            return initialValue;
        }
        long aggregatedValue = initialValue;
        for (int i = 0; i < array.length(); i++) {
            long entry = array.get(i);
            long value = compressor.value(entry);
            aggregatedValue = aggregation.aggregate(aggregatedValue, value);
        }
        return aggregatedValue;
    }

    @Override
    public EntryIterator definition() {
        return ascending();
    }

    @Override
    public void define(long key, long value) {
        int index = findArrayIndex(key);
        long entry = compressor.create(key, value);
        if (Sorting.isPresent(index)) {
            array.update(index, entry, Updates.replace());
        } else {
            array.add(entry);
        }
    }

    @Override
    public void remove(long key) {
        int index = findArrayIndex(key);
        if (Sorting.isPresent(index)) {
            array.remove(index);
        }
    }

    @Override
    public void update(long key, long newValue, Update update) {
        int index = findArrayIndex(key);
        if (Sorting.isPresent(index)) {
            long originalEntry = array.get(index);
            long originalValue = compressor.value(originalEntry);
            long updatedValue = update.apply(originalValue, newValue);
            long updatedEntry = compressor.create(key, updatedValue);
            array.update(index, updatedEntry, Updates.replace());
        } else {
            long newEntry = compressor.create(key, newValue);
            array.insert(newEntry, Sorting.asInsertIndex(index));
        }
    }


    private int findArrayIndex(long key) {
        long keyEntry = compressor.keyEntry(key);
        int maybeIndex = array.find(keyEntry);
        if (!Sorting.isPresent(maybeIndex)) {
            int insertIndex = Sorting.asInsertIndex(maybeIndex);
            if (insertIndex < array.length()) {
                long entry = array.get(insertIndex);
                long entryKey = compressor.key(entry);
                if (entryKey == key) {
                    return insertIndex;
                }
            }
        }
        return maybeIndex;
    }

    @Override
    public long smallestKey() {
        return isEmpty() ? noSuchLong() : compressor.key(array.smallest());
    }

    @Override
    public long smallestKeyValue() {
        return isEmpty() ? noSuchLong() : compressor.value(array.smallest());
    }

    long smallestEntry() {
        return isEmpty() ? noSuchLong() : array.smallest();
    }

    @Override
    public long greatestKey() {
        return isEmpty() ? noSuchLong() : compressor.key(array.greatest());
    }

    @Override
    public long greatestKeyValue() {
        return isEmpty() ? noSuchLong() : compressor.value(array.greatest());
    }

    long greatestEntry() {
        return isEmpty() ? noSuchLong() : array.greatest();
    }

    @Override
    public EntryIterator ascending() {
        return new It(array.ascending());
    }

    @Override
    public EntryIterator descending() {
        return new It(array.descending());
    }

    private class It implements EntryIterator {

        private final Iterator iterator;

        private long key;
        private long value;

        private It(Iterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public long next() {
            long entry = iterator.next();
            key = compressor.key(entry);
            value = compressor.value(entry);
            return key;
        }

        @Override
        public long key() {
            return key;
        }

        @Override
        public long value() {
            return value;
        }
    }

    @Override
    public String toString() {
        int maxSize = 20;
        StringBuilder stringBuilder = new StringBuilder("[ ");
        Iterator iterator = array.elements();
        for (int i = 0; i < maxSize && iterator.hasNext(); i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            long element = iterator.next();
            stringBuilder.append(String.format("(%d -> %d)", compressor.key(element), compressor.value(element)));
        }
        if (iterator.hasNext()) {
            stringBuilder.append("... and more");
        }
        return stringBuilder.append(" ]").toString();
    }
}
