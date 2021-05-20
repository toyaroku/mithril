package collections.longs.datastructures.maps;

public interface MutableSortedMap extends SortedMap, MutableMap {

    @Override
    MutableSortedMap copy();
}
