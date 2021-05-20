package collections.longs.datastructures.lists;

public interface MutableSortedList extends SortedList, MutableList {

    @Override
    MutableSortedList copy();
}
