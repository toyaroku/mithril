package collections.longs.datastructures.lists;

public interface List {

    boolean isEmpty();

    int length();

    long first();

    long last();

    long get(int index);

    int find(long element);

    ListIterator beforeFirstIndex();

    ListIterator afterLastIndex();

    List copy();

    MutableList mutableCopy();
}
