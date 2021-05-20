package collections.longs.datastructures.maps;

import collections.longs.datastructures.sorting.SortedView;

public interface SortedMap extends Map {

    long smallestKey();

    long smallestKeyValue();

    long greatestKey();

    long greatestKeyValue();

    @Override
    SortedMapView definition();

    SortedMapIterator beforeSmallestKey();

    SortedMapIterator afterGreatestKey();

    @Override
    SortedMap copy();

    @Override
    MutableSortedMap mutableCopy();
}
