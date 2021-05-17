package collections.longs.datastructures;

public interface Map {

    int cardinality();

    long getMaxSupportedKey();

    long getMaxSupportedValue();

    boolean isEmpty();

    boolean containsKey(long key);

    long getValue(long key);

    Iterator ascending();

    Iterator descending();
}