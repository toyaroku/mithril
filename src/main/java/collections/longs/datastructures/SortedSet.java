package collections.longs.datastructures;

public interface SortedSet extends Set, SortedCollection{

    @Override
    SortedSet copy();

    @Override
    MutableSet mutableCopy();
}
