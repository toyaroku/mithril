package collections.longs.datastructures;

public interface Map {

    boolean isEmpty();

    int size();

    boolean containsKey(long domainElement);

    long value(long domainElement);

    long aggregateValues(long initialValue, Aggregation aggregation);

    EntryIterator definition();

    interface EntryIterator extends Iterator {

        long key();

        long value();
    }
}