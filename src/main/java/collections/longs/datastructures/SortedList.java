package collections.longs.datastructures;

public interface SortedList extends List, SortedCollection{

    @Override
    SortedList copy();

    @Override
    MutableSortedList mutableCopy();
}
