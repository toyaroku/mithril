package collections.longs.datastructures;

public interface MutableList extends List {

    void add(long element);

    void remove(int index);

    void insert(long element, int index);

    void swap(int index1, int index2);

    long update(int index, long value, Update update);
}
