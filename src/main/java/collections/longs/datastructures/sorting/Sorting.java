package collections.longs.datastructures.sorting;

public enum Sorting {

    EMPTY {
        @Override
        public boolean holds(long element, long nextElement) {
            return false;
        }

        @Override
        public Sorting and(Sorting sorting) {
            return sorting;
        }

        @Override
        public int find(long element, long[] elements, int fromIncl, int toExcl) {
            return -1;
        }
    },
    STALE {
        @Override
        public boolean holds(long element, long nextElement) {
            return element == nextElement;
        }

        @Override
        public Sorting and(Sorting sorting) {
            return sorting == EMPTY ? this : sorting;
        }

        @Override
        public int find(long element, long[] elements, int fromIncl, int toExcl) {
            long singleValue = elements[fromIncl];
            if (holds(singleValue, element)) {
                return fromIncl;
            }
            if (ASCENDING.holds(element, singleValue)) {
                return fromIncl - 1;
            }
            return toExcl;
        }
    },
    ASCENDING {
        @Override
        public boolean holds(long element, long nextElement) {
            return element < nextElement;
        }

        @Override
        public Sorting and(Sorting sorting) {
            if (sorting == DESCENDING || sorting == NOT_SORTED) {
                return NOT_SORTED;
            }
            return this;
        }

        @Override
        public int find(long element, long[] elements, int fromIncl, int toExcl) {
            return ASCENDING.binarySearch(element, elements, fromIncl, toExcl);
        }
    },
    DESCENDING {
        @Override
        public boolean holds(long element, long nextElement) {
            return element > nextElement;
        }

        @Override
        public Sorting and(Sorting sorting) {
            if (sorting == ASCENDING || sorting == NOT_SORTED) {
                return NOT_SORTED;
            }
            return this;
        }

        @Override
        public int find(long element, long[] elements, int fromIncl, int toExcl) {
            return DESCENDING.binarySearch(element, elements, fromIncl, toExcl);
        }
    },
    NOT_SORTED {
        @Override
        public boolean holds(long element, long nextElement) {
            return true;
        }

        @Override
        public Sorting and(Sorting sorting) {
            return this;
        }

        @Override
        public int find(long element, long[] elements, int fromIncl, int toExcl) {
            for (int i = fromIncl; i < toExcl; i++) {
                if (elements[i] == element) {
                    return i;
                }
            }
            return toExcl;
        }
    };

    public static Sorting infer(long[] elements, int length) {
        return infer(elements, 0, length);
    }

    public static Sorting infer(long[] elements, int from, int length) {
        if (length <= 1) {
            return STALE;
        }
        Sorting sorting = STALE;
        for (int i = from; i < length; i++) {
            Sorting elementSorting = infer(elements[i], elements[i + 1]);
            sorting = sorting.and(elementSorting);
            if (sorting == NOT_SORTED) {
                return NOT_SORTED;
            }
        }
        return sorting;
    }

    public static Sorting infer(long element, long nextElement) {
        long diff = nextElement - element;
        if (diff > 0) {
            return ASCENDING;
        } else if (diff < 0) {
            return DESCENDING;
        }
        return STALE;
    }

    public abstract boolean holds(long element, long nextElement);

    public abstract Sorting and(Sorting sorting);

    public abstract int find(long element, long[] elements, int fromIncl, int toExcl);

    public int find(long element, long[] elements, int length) {
        int index = find(element, elements, 0, length);
        if (index == length) {
            return asOutputInsertIndex(index);
        }
        return index;
    }

    public static boolean isPresent(int index) {
        return index >= 0;
    }

    public static int asInsertIndex(int index) {
        assert !isPresent(index);
        return -index - 1;
    }

    private static int asOutputInsertIndex(int insertIndex) {
        assert isPresent(insertIndex);
        return -(insertIndex + 1);
    }

    private int binarySearch(long element, long[] elements, int fromIncl, int toExcl) {
        assert this != NOT_SORTED;
        int lowerIndex = fromIncl;
        int upperIndex = toExcl - 1;
        while (lowerIndex <= upperIndex) {
            int middleIndex = lowerIndex + upperIndex >>> 1;
            long middleValue = elements[middleIndex];
            if (holds(middleValue, element)) {
                lowerIndex = middleIndex + 1;
            } else {
                if (middleValue == element) {
                    return middleIndex;
                }
                upperIndex = middleIndex - 1;
            }
        }
        return Sorting.asOutputInsertIndex(lowerIndex);
    }
}
