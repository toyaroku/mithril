package collections.longs.datastructures.arrays;

import collections.longs.datastructures.lists.*;
import collections.longs.datastructures.maps.Update;
import collections.longs.datastructures.sorting.Sorting;

public class SortedResizableArray implements SortedList {

    private static final Sorting ASCENDING = Sorting.ASCENDING;

    private final ResizableArray.Mutable array;

    private SortedResizableArray(ResizableArray.Mutable array) {
        this.array = array;
    }

    private SortedResizableArray(SortedResizableArray original) {
        this(original.array.copy());
    }

    public static Mutable empty() {
        return new SortedResizableArray(ResizableArray.Mutable.empty()).asMutable();
    }

    public static Mutable fromSortedView(SortedListView view) {
        ListViewAdapter assumedSortedListView = new ListViewAdapter(view);
        SortedResizableArray.Mutable array = SortedResizableArray.fromListView(assumedSortedListView);
        return new SortedResizableArray(array).asMutable();
    }

    public static Mutable fromListView(ListView view) {
        Mutable array = SortedResizableArray.fromListView(view);
        array.sort();
        return new SortedResizableArray(array).asMutable();
    }


    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int length() {
        return array.length();
    }

    @Override
    public long get(int index) {
        return array.get(index);
    }

    @Override
    public int find(long element) {
        return ASCENDING.find(element, array.rawElements(), array.length());
    }

    @Override
    public ListIterator beforeFirstIndex() {
        return array.beforeFirstIndex();
    }

    @Override
    public ListIterator afterLastIndex() {
        return array.afterLastIndex();
    }

    private boolean conflictsWithPrev(int index, long newElement) {
        return index > 0 && array.get(index - 1) > newElement;
    }

    private boolean conflictsWithNext(int index, long newElement) {
        return index < array.length() - 1 && newElement > array.get(index + 1);
    }

    @Override
    public long smallest() {
        return array.first();
    }

    @Override
    public long greatest() {
        return array.last();
    }

    @Override
    public long first() {
        return array.first();
    }

    @Override
    public long last() {
        return array.last();
    }

    @Override
    public SortedListIterator beforeSmallest() {
        return Iterator.beforeSmallest(array);
    }

    @Override
    public SortedListIterator afterGreatest() {
        return Iterator.afterGreatest(array);
    }

    @Override
    public SortedResizableArray copy() {
        return new SortedResizableArray(this);
    }

    @Override
    public Mutable mutableCopy() {
        return null;
    }

    private Mutable asMutable() {
        return new Mutable(array);
    }

    public class Mutable extends SortedResizableArray implements MutableSortedList {

        private Mutable(ResizableArray.Mutable array) {
            super(array);
        }

        @Override
        public void add(long element) {
            int index = find(element);
            if (!Sorting.isPresent(index)) {
                array.insert(element, Sorting.asInsertIndex(index));
            }
        }

        @Override
        public void remove(int index) {
            array.remove(index);
        }

        @Override
        public void insert(long element, int index) {
            if (array.isEmpty() || conflictsWithPrev(index, element) || conflictsWithNext(index, element)) {
                array.add(element);
            } else {
                array.insert(element, index);
            }
        }

        @Override
        public void swap(int index1, int index2) {
            array.reverse(index1, index2 + 1);
        }

        @Override
        public long update(int index, long value, Update update) {
            long updatedValue = array.update(index, value, update);
            if (array.length() > 1) {
                if (conflictsWithPrev(index, updatedValue)) {
                    array.sort(0, index);
                } else if (conflictsWithNext(index, updatedValue)) {
                    array.sort(index, array.length());
                }
            }
            return updatedValue;
        }

        @Override
        public Mutable copy() {
            return new Mutable(array.copy());
        }

        public void sort() {
            array.sort();
        }
    }

    private static class Iterator implements SortedListIterator {

        private final ListIterator iterator;

        private Iterator(ListIterator iterator) {
            this.iterator = iterator;
        }

        private static Iterator beforeSmallest(ResizableArray array) {
            return new Iterator(array.beforeFirstIndex());
        }

        private static Iterator afterGreatest(ResizableArray array) {
            return new Iterator(array.afterLastIndex());
        }

        private static Iterator fromIndex(ResizableArray array, int index) {
            return new Iterator(array.fromIndex(index));
        }

        @Override
        public long element() {
            return iterator.element();
        }

        @Override
        public boolean hasGreater() {
            return iterator.hasNextIndex();
        }

        @Override
        public void iterateGreater() {
            iterator.iterateNextIndex();
        }

        @Override
        public boolean hasSmaller() {
            return iterator.hasPreviousIndex();
        }

        @Override
        public void iterateSmaller() {
            iterator.iteratePreviousIndex();
        }

        @Override
        public boolean hasPreviousIndex() {
            return iterator.hasPreviousIndex();
        }

        @Override
        public void iteratePreviousIndex() {
            iterator.iteratePreviousIndex();
        }

        @Override
        public boolean hasNextIndex() {
            return iterator.hasNextIndex();
        }

        @Override
        public void iterateNextIndex() {
            iterator.iterateNextIndex();
        }

        @Override
        public int index() {
            return iterator.index();
        }
    }

    private static class ListViewAdapter implements ListView {

        private final SortedListView view;

        private ListViewAdapter(SortedListView view) {
            this.view = view;
        }

        @Override
        public long element() {
            return view.element();
        }

        @Override
        public boolean hasNextIndex() {
            return view.hasGreater();
        }

        @Override
        public void iterateNextIndex() {
            view.iterateGreater();
        }

        @Override
        public int index() {
            return view.index();
        }
    }
}
