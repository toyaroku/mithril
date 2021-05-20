package collections.longs.datastructures.util;

public interface GenericView<T> {

    boolean hasNext();

    void next();

    T current();
}
