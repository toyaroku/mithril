package collections.longs.datastructures.arrays;

import collections.longs.datastructures.*;

public class SortedArraySet implements SortedSet {

    private final ResizableArray.Mutable array;

    private SortedArraySet(ResizableArray.Mutable array) {
        this.array = array;
    }

    private SortedArraySet(SortedArraySet original) {
        this(original.array.copy());
    }

    public static SortedArraySet empty() {
        return new SortedArraySet(ResizableArray.empty());
    }

    public static SortedArraySet of(long... elements) {
        int length = Array.sortUnique(elements, elements.length);
        return new SortedArraySet(ResizableArray.of(elements, length));
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int cardinality() {
        return array.length();
    }

    @Override
    public boolean contains(long element) {
        int index = array.find(element);
        return Sorting.isPresent(index);
    }

    @Override
    public Iterator elements() {
        return array.elements();
    }

    @Override
    public SortedArraySet copy() {
        return new SortedArraySet(array.mutableCopy());
    }

    @Override
    public SortedArraySet.Mutable mutableCopy() {
        return copy().asMutable();
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

    private Mutable asMutable() {
        return new Mutable(array);
    }

    public class Mutable extends SortedArraySet implements MutableSet {

        private Mutable(ResizableArray.Mutable array) {
            super(array);
        }

        private Mutable(Mutable original) {
            super(original);
        }

        @Override
        public void add(long element) {
            int index = array.find(element);
            if (!Sorting.isPresent(index)) {
                array.insert(element, Sorting.asInsertIndex(index));
            }
        }

        @Override
        public void remove(long element) {
            int index = array.find(element);
            if (Sorting.isPresent(index)) {
                array.remove(index);
            }
        }

        @Override
        public Mutable copy() {
            return new Mutable(this);
        }
    }
}
