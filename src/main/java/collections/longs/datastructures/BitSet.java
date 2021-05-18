package collections.longs.datastructures;

public interface BitSet extends SortedSet {

    @Override
    BitSetIterator ascending();

    @Override
    BitSetIterator descending();
}
