package collections.longs.datastructures;

public interface List {

    boolean isEmpty();

    int length();

    long get(int index);

    int find(long element);

    Iterator elements();

    List copy();

    MutableList mutableCopy();
}
