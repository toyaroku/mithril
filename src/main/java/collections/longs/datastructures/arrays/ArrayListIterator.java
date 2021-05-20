package collections.longs.datastructures.arrays;

import collections.longs.datastructures.lists.ListIterator;

public class ArrayListIterator implements ListIterator {

    private final int length;
    private final long[] array;

    private int index;

    private ArrayListIterator(long[] array, int length) {
        this.length = length;
        this.array = array;
    }

    public static ArrayListIterator beforeFirstIndex(long[] array, int length) {
        ArrayListIterator iterator = new ArrayListIterator(array, length);
        iterator.index = -1;
        return iterator;
    }

    public static ArrayListIterator afterLastIndex(long[] array, int length) {
        ArrayListIterator iterator = new ArrayListIterator(array, length);
        iterator.index = length;
        return iterator;
    }

    public static ListIterator fromIndex(long[] array, int length, int index) {
        ArrayListIterator iterator = new ArrayListIterator(array, length);
        iterator.setPosition(index);
        return iterator;
    }

    @Override
    public boolean hasPreviousIndex() {
        return index > 0;
    }

    @Override
    public void iteratePreviousIndex() {
        index--;
    }

    @Override
    public boolean hasNextIndex() {
        return index < length - 1;
    }

    @Override
    public void iterateNextIndex() {
        index++;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public long element() {
        return array[index];
    }

    public void setPosition(int index) {
        this.index = index;
    }
}
