package collections.longs.datastructures.lists;

import collections.longs.datastructures.sorting.SortedCollection;

public interface SortedList extends List, SortedCollection {

    @Override
    SortedListIterator beforeSmallest();

    @Override
    SortedListIterator afterGreatest();

    @Override
    SortedList copy();

    @Override
    MutableSortedList mutableCopy();
}
