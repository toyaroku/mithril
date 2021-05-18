package collections.longs.datastructures;

public interface Set {

    boolean isEmpty();

    int cardinality();

    boolean contains(long element);

    Iterator elements();
}
