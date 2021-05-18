package collections.longs.datastructures.arrays;

import collections.longs.datastructures.Iterator;
import collections.longs.datastructures.MutableSet;
import collections.longs.datastructures.SortedSet;
import collections.longs.datastructures.Sorting;

public class SortedArraySet implements SortedSet, MutableSet {

    private final ResizableArray array;

    private SortedArraySet(ResizableArray array) {
        this.array = array;
    }

    public static SortedArraySet empty() {
        return new SortedArraySet(ResizableArray.empty());
    }

    public static SortedArraySet of(long... elements) {
        int length = Array.sortUnique(elements, elements.length);
        return new SortedArraySet(ResizableArray.of(elements, length));
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
}
