package collections.longs.datastructures;

public interface MutableSortedSet extends SortedSet, MutableSet {

    @Override
    MutableSortedSet copy();
}
