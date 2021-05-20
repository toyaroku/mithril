package collections.longs.datastructures.sets;

import collections.longs.datastructures.lists.SortedList;
import collections.longs.datastructures.lists.SortedListIterator;

public class SortedSetIterators {

    public static SortedSetIterator fromSortedListBeforeFirst(SortedList sortedList) {
        return FromSortedList.beforeSmallest(sortedList);
    }

    public static SortedSetIterator fromSortedListAfterGreatest(SortedList sortedList) {
        return FromSortedList.afterGreatest(sortedList);
    }

    private static class FromSortedList implements SortedSetIterator {

        private final SortedListIterator iterator;

        private FromSortedList(SortedListIterator iterator) {
            this.iterator = iterator;
        }

        private static FromSortedList beforeSmallest(SortedList sortedList) {
            return new FromSortedList(sortedList.beforeSmallest());
        }

        private static FromSortedList afterGreatest(SortedList sortedList) {
            return new FromSortedList(sortedList.afterGreatest());
        }

        @Override
        public long element() {
            return iterator.element();
        }

        @Override
        public boolean hasGreater() {
            return iterator.hasGreater();
        }

        @Override
        public void iterateGreater() {
            iterator.iterateGreater();
        }

        @Override
        public boolean hasSmaller() {
            return iterator.hasSmaller();
        }

        @Override
        public void iterateSmaller() {
            iterator.iterateSmaller();
        }
    }
}
