package collections.longs.datastructures.arrays;

import collections.longs.datastructures.lists.*;
import collections.longs.datastructures.maps.Update;
import collections.longs.datastructures.sorting.Sorting;

import java.util.Arrays;

import static collections.Collections.noSuchLong;

public class ResizableArray implements List {

    private static final int MIN_SIZE = 10;

    private int length;
    private long[] elements;

    private ResizableArray(long[] elements, int length) {
        this.elements = elements;
        this.length = length;
    }

    private ResizableArray(ResizableArray original) {
        this(Arrays.copyOf(original.elements, original.length), original.length);
    }

    public static Mutable empty() {
        return new ResizableArray(new long[0], MIN_SIZE).asMutable();
    }

    public static Mutable fromView(ListView view, long defaultValue) {
        Mutable array = empty();
        while (view.hasNextIndex()) {
            view.iterateNextIndex();
            int index = view.index();
            int gap = index - array.length();
            if (gap > 0) {
                array.addMulti(defaultValue, gap);
            }
            long element = view.element();
            array.add(element);
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public long get(int index) {
        return elements[index];
    }

    @Override
    public int find(long element) {
        return Sorting.NOT_SORTED.find(element, elements, length);
    }

    @Override
    public ListIterator beforeFirstIndex() {
        return ArrayListIterator.beforeFirstIndex(elements, length);
    }

    @Override
    public ListIterator afterLastIndex() {
        return ArrayListIterator.afterLastIndex(elements, length);
    }

    public ListIterator fromIndex(int index) {
        return ArrayListIterator.fromIndex(elements, length, index);
    }

    @Override
    public ResizableArray copy() {
        return new ResizableArray(this);
    }

    @Override
    public ResizableArray.Mutable mutableCopy() {
        return copy().asMutable();
    }

    @Override
    public long first() {
        return isEmpty() ? noSuchLong() : elements[0];
    }

    @Override
    public long last() {
        return isEmpty() ? noSuchLong() : elements[length - 1];
    }

    // TODO avoid this by making Mutable static and pass immutable collection
    private Mutable asMutable() {
        return new Mutable(elements, length);
    }

    public class Mutable extends ResizableArray implements MutableList {

        private Mutable(long[] array, int length) {
            super(array, length);
        }

        @Override
        public Mutable copy() {
            return new Mutable(Arrays.copyOf(elements, length), length);
        }

        @Override
        public void add(long element) {
            expandIfRequired();
            elements[length++] = element;
        }

        public void addMulti(long element, int timesToAdd) {
            expandIfRequired(timesToAdd);
            Arrays.fill(elements, length, length + timesToAdd, element);
            length += timesToAdd;
        }

        @Override
        public void insert(long element, int index) {
            if (index == length) {
                add(element);
            } else {
                expandIfRequired();
                System.arraycopy(elements, index, elements, index + 1, length - index);
                elements[index] = element;
                length++;
            }
        }

        public void sort() {
            Arrays.sort(elements, 0, length);
        }

        public void sort(int fromIncl, int toExcl) {
            Arrays.sort(elements, fromIncl, toExcl);
        }

        public void reverse() {
            reverse(0, length);
        }

        public void reverse(int fromIncl, int toExcl) {
            Array.reverse(elements, fromIncl, toExcl);
        }

        private void expandIfRequired(int elementsToAdd) {
            if (requiresExpansion(elementsToAdd)) {
                elements = Arrays.copyOf(elements, Math.max(MIN_SIZE, length << 1));
            }
        }

        private void expandIfRequired() {
            expandIfRequired(1);
        }

        private boolean requiresExpansion(int elementsToAdd) {
            return length + elementsToAdd > elements.length;
        }

        @Override
        public void remove(int index) {
            if (length - 1 < elements.length >> 1) {
                elements = Arrays.copyOf(elements, elements.length >> 1);
            }
            if (index < length - 1) {
                System.arraycopy(elements, index + 1, elements, index, length - (index + 1));
            }
            length--;
        }

        @Override
        public long update(int index, long operand, Update update) {
            long updatedValue = update.apply(elements[index], operand);
            elements[index] = updatedValue;
            return updatedValue;
        }

        @Override
        public void swap(int index1, int index2) {
            Array.swap(elements, index1, index2);
        }
    }

    // TODO this has to go
    long[] rawElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[ ");
        for (int i = 0; i < length; i++) {
            stringBuilder.append(elements[i]);
            if (i == 20) {
                stringBuilder.append("... and more ]");
                return stringBuilder.toString();
            }
            if (i < length - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.append(" ]").toString();
    }
}
