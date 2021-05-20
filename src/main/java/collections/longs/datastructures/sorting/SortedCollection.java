package collections.longs.datastructures.sorting;

public interface SortedCollection {

    long smallest();

    long greatest();

    SortedIterator beforeSmallest();

    SortedIterator afterGreatest();
}
