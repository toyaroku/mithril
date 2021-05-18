package collections.longs.datastructures;

public interface SortedCollection {

    long smallest();

    long greatest();

    Iterator ascending();

    Iterator descending();
}
