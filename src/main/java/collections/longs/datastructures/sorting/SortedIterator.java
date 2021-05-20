package collections.longs.datastructures.sorting;

public interface SortedIterator extends SortedView {

    @Override
    boolean hasGreater();

    @Override
    void iterateGreater();

    boolean hasSmaller();

    void iterateSmaller();
}
