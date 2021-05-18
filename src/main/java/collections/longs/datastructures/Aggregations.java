package collections.longs.datastructures;

import util.BitUtil;

public class Aggregations {

    private static final Aggregation SUM_CARDINALITY = ((agg, el) -> agg + Long.bitCount(el));

    public static Aggregation sumCardinality() {
        return SUM_CARDINALITY;
    }
}
