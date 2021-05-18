package collections.longs.datastructures.arrays;

import collections.longs.datastructures.*;

import java.util.Arrays;

import static collections.Collections.noSuchLong;

public class ResizableArray implements UnsortedList, MutableList {

    private static final int MIN_SIZE = 10;

    private int length;
    private long[] elements;

    private ResizableArray(long[] elements, int length) {
        this.elements = elements;
        this.length = length;
    }

    public static ResizableArray empty() {
        return new ResizableArray(new long[0], 0);
    }

    public static ResizableArray of(long[] elements, int length) {
        return new ResizableArray(elements, length);
    }

    public static ResizableArray of(long... elements) {
        return new ResizableArray(elements, elements.length);
    }

    public static ResizableArray deepCopy(ResizableArray other) {
        return new ResizableArray(Arrays.copyOf(other.elements, other.length), other.length);
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
    public Iterator elements() {
        return byIncreasingIndex();
    }

    @Override
    public long first() {
        return isEmpty() ? noSuchLong() : elements[0];
    }

    @Override
    public long last() {
        return isEmpty() ? noSuchLong() : elements[length - 1];
    }

    @Override
    public Iterator byIncreasingIndex() {
        return new It(Order.INCREASING_INDEX);
    }

    @Override
    public Iterator byDecreasingIndex() {
        return new It(Order.DECREASING_INDEX);
    }

    @Override
    public void add(long element) {
        expandIfRequired();
        elements[length++] = element;
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

    private void expandIfRequired() {
        if (requiresExpansion()) {
            elements = Arrays.copyOf(elements, Math.max(MIN_SIZE, length << 1));
        }
    }

    private boolean requiresExpansion() {
        return length >= elements.length;
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

    long[] rawElements() {
        return elements;
    }

    private enum Order {
        INCREASING_INDEX {
            @Override
            boolean hasNextIndex(int currentIndex, int length) {
                return currentIndex < length - 1;
            }

            @Override
            int nextIndex(int currentIndex) {
                return currentIndex + 1;
            }

            @Override
            public int initialIndex(int length) {
                return -1;
            }
        },
        DECREASING_INDEX {
            @Override
            boolean hasNextIndex(int currentIndex, int length) {
                return currentIndex > 0;
            }

            @Override
            int nextIndex(int currentIndex) {
                return currentIndex - 1;
            }

            @Override
            public int initialIndex(int length) {
                return length;
            }
        };

        abstract boolean hasNextIndex(int currentIndex, int length);

        abstract int nextIndex(int currentIndex);

        public abstract int initialIndex(int length);
    }

    private class It implements  Iterator {

        private final Order order;
        private int currentIndex;

        private It(Order order) {
            this.order = order;
            this.currentIndex = order.initialIndex(length);
        }

        @Override
        public boolean hasNext() {
            return order.hasNextIndex(currentIndex, length);
        }

        @Override
        public long next() {
            currentIndex = order.nextIndex(currentIndex);
            return elements[currentIndex];
        }
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
