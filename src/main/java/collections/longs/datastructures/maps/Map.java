package collections.longs.datastructures.maps;

import collections.longs.datastructures.iterators.Aggregation;

public interface Map {

    boolean isEmpty();

    int size();

    boolean containsKey(long domainElement);

    long value(long domainElement);

    long aggregateValues(long initialValue, Aggregation aggregation);

    MapView definition();

    Map copy();

    MutableMap mutableCopy();

}