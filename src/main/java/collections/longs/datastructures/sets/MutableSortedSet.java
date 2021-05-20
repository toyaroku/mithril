package collections.longs.datastructures.sets;

public interface MutableSortedSet extends SortedSet, MutableSet {

    @Override
    MutableSortedSet copy();
}
