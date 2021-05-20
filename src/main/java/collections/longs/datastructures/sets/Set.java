package collections.longs.datastructures.sets;

public interface Set {

    boolean isEmpty();

    int cardinality();

    boolean contains(long element);

    SetView elements();

    Set copy();

    MutableSet mutableCopy();
}
