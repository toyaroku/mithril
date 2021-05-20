package collections.longs.datastructures.sets;

import collections.longs.datastructures.sorting.SortedCollection;
import collections.longs.datastructures.sorting.SortedSetView;

public interface SortedSet extends Set, SortedCollection {

    @Override
    SortedSetView elements();

    @Override
    SortedSetIterator beforeSmallest();

    @Override
    SortedSetIterator afterGreatest();

    @Override
    SortedSet copy();

    @Override
    MutableSet mutableCopy();
}
