package collections.longs.datastructures.iterators;

public interface Aggregation {

    long aggregate(long aggregatedValue, long element);

    default long apply(Iteration iterator, long initialValue) {
        long aggregatedValue = initialValue;
        while (!iterator.isEmpty()) {
            long element = iterator.next();
            aggregatedValue = aggregate(aggregatedValue, element);
        }
        return aggregatedValue;
    }
}
