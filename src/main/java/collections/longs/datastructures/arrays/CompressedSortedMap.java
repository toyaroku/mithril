package collections.longs.datastructures.arrays;

import collections.longs.datastructures.iterators.*;
import collections.longs.datastructures.lists.ListIterator;
import collections.longs.datastructures.lists.ListView;
import collections.longs.datastructures.lists.SortedListIterator;
import collections.longs.datastructures.lists.SortedListView;
import collections.longs.datastructures.maps.*;
import collections.longs.datastructures.sorting.Sorting;

import static collections.Collections.noSuchLong;

public class CompressedSortedMap implements SortedMap {

    private final EntryCompressor compressor;
    private final SortedResizableArray.Mutable array;

    CompressedSortedMap(EntryCompressor compressor) {
        this.array = SortedResizableArray.empty();
        this.compressor = compressor;
    }

    private CompressedSortedMap(SortedResizableArray.Mutable array, EntryCompressor compressor) {
        this.array = array;
        this.compressor = compressor;
    }

    private CompressedSortedMap(CompressedSortedMap original) {
        this.array = original.array.copy();
        this.compressor = original.compressor;
    }

    public static Mutable fromMapView(MapView view, EntryCompressor compressor) {
        ViewAdapter viewAdapter = new ViewAdapter(view, compressor);
        SortedResizableArray.Mutable array = SortedResizableArray.fromListView(viewAdapter);
        return new CompressedSortedMap(array, compressor).asMutable();
    }

    public static Mutable fromSortedView(SortedMapView view, EntryCompressor compressor) {
        SortedViewAdapter sortedViewAdapter = new SortedViewAdapter(view, compressor);
        SortedResizableArray.Mutable array = SortedResizableArray.fromSortedView(sortedViewAdapter);
        return new CompressedSortedMap(array, compressor).asMutable();
    }

    public static Mutable empty(EntryCompressor compressor) {
        return new CompressedSortedMap(compressor).asMutable();
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
    public SortedMapView definition() {
        return create(array.beforeSmallest());
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
    public CompressedSortedMapIterator beforeSmallestKey() {
        return create(array.beforeSmallest());
    }

    @Override
    public CompressedSortedMapIterator afterGreatestKey() {
        return create(array.afterGreatest());
    }

    private CompressedSortedMapIterator create(SortedListIterator iterator) {
        return SortedMapIterators.fromCompressedSortedList(iterator, compressor);
    }

    @Override
    public CompressedSortedMap copy() {
        return new CompressedSortedMap(this);
    }

    @Override
    public Mutable mutableCopy() {
        return copy().asMutable();
    }

    private Mutable asMutable() {
        return new Mutable(array, compressor);
    }

    public class Mutable extends CompressedSortedMap implements MutableSortedMap {

        private Mutable(SortedResizableArray.Mutable array, EntryCompressor compressor) {
            super(array, compressor);
        }

        private Mutable(Mutable map) {
            super(map);
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

        @Override
        public Mutable copy() {
            return new Mutable(this);
        }
    }

    @Override
    public String toString() {
        int maxSize = 20;
        StringBuilder stringBuilder = new StringBuilder("[ ");
        ListIterator iterator = array.beforeFirstIndex();
        for (int i = 0; i < maxSize && iterator.hasNextIndex(); i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            iterator.iterateNextIndex();
            long element = iterator.element();
            stringBuilder.append(String.format("(%d -> %d)", compressor.key(element), compressor.value(element)));
        }
        if (iterator.hasNextIndex()) {
            stringBuilder.append("... and more");
        }
        return stringBuilder.append(" ]").toString();
    }

    private static class SortedViewAdapter implements SortedListView  {

        private final SortedMapView mapView;
        private final EntryCompressor entryCompressor;

        private int index;
        private long entry;

        private SortedViewAdapter(SortedMapView mapView, EntryCompressor entryCompressor) {
            this.mapView = mapView;
            this.entryCompressor = entryCompressor;
            this.index = -1;
            this.entry = 0L;
        }

        @Override
        public long element() {
            return entry;
        }

        @Override
        public int index() {
            return index;
        }

        @Override
        public boolean hasGreater() {
            return mapView.hasGreater();
        }

        @Override
        public void iterateGreater() {
            mapView.iterateGreater();
            entry = entryCompressor.create(mapView.key(), mapView.value());
            index++;
        }
    }

    private static class ViewAdapter implements ListView {

        private final MapView mapView;
        private final EntryCompressor entryCompressor;

        private int index;
        private long entry;

        private ViewAdapter(MapView mapView, EntryCompressor entryCompressor) {
            this.mapView = mapView;
            this.entryCompressor = entryCompressor;
            this.index = -1;
            this.entry = 0L;
        }

        @Override
        public long element() {
            return entry;
        }

        @Override
        public int index() {
            return index;
        }

        @Override
        public boolean hasNextIndex() {
            return mapView.hasNextEntry();
        }

        @Override
        public void iterateNextIndex() {
            mapView.iterateNextEntry();
            entry = entryCompressor.create(mapView.key(), mapView.value());
            index++;
        }
    }
}
