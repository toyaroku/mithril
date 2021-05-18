package collections.longs.datastructures;

public interface MutableSortedList extends SortedList, MutableList {

    @Override
    MutableSortedList copy();
}
