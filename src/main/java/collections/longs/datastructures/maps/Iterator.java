package collections.longs.datastructures.maps;

public interface Iterator {

    boolean hasNext();

    long nextKey();

    long nextKeySmallerThan(long threshold);

    long nextKeySmallerThanOrEqualTo(long threshold);

    long nextKeyGreaterThan(long threshold);

    long nextKeyGreaterThanOrEqualTo(long threshold);

    long currentKey();

    long currentValue();
}
