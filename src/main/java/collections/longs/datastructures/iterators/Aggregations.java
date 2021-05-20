package collections.longs.datastructures.iterators;

import collections.longs.datastructures.iterators.Aggregation;

public class Aggregations {

    private static final Aggregation SUM_CARDINALITY = ((agg, el) -> agg + Long.bitCount(el));

    public static Aggregation sumCardinality() {
        return SUM_CARDINALITY;
    }
}
