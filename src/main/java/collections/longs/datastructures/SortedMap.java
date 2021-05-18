package collections.longs.datastructures;

public interface SortedMap extends Map {

    long smallestKey();

    long smallestKeyValue();

    long greatestKey();

    long greatestKeyValue();

    EntryIterator ascending();

    EntryIterator descending();
}
