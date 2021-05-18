package collections.longs.datastructures.arrays;

import collections.longs.datastructures.*;

public class SortedResizableArray implements SortedList {

    private static final Sorting ASCENDING = Sorting.ASCENDING;

    private final ResizableArray.Mutable array;

    private SortedResizableArray(ResizableArray.Mutable array) {
        this.array = array;
    }

    private SortedResizableArray(SortedResizableArray original) {
        this(original.array.copy());
    }

    public static SortedResizableArray.Mutable empty() {
        return new SortedResizableArray(ResizableArray.Mutable.empty()).asMutable();
    }

    public static SortedResizableArray.Mutable of(long... elements) {
        return new SortedResizableArray(ResizableArray.Mutable.of(elements)).asMutable();
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
    public Iterator elements() {
        return array.elements();
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
    public Iterator ascending() {
        return array.byIncreasingIndex();
    }

    @Override
    public Iterator descending() {
        return array.byDecreasingIndex();
    }

    @Override
    public SortedResizableArray copy() {
        return new SortedResizableArray(this);
    }

    @Override
    public MutableSortedList mutableCopy() {
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
    }
}
