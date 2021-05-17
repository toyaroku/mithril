package collections.longs.datastructures;

public interface DataStructure {

    boolean isEmpty();

    int cardinality();

    boolean contains(int element);

    void add(int element);

    void remove(int element);
}
