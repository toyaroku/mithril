package collections.longs.datastructures.lists;

public interface ListIterator extends ListView {

    @Override
    boolean hasNextIndex();

    @Override
    void iterateNextIndex();

    @Override
    int index();

    boolean hasPreviousIndex();

    void iteratePreviousIndex();
}
