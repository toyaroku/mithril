package collections.longs.datastructures.sets;

public interface MutableSet extends Set {

    void add(long element);

    void remove(long element);

    @Override
    MutableSet copy();
}
