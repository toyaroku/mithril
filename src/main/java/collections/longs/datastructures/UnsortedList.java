package collections.longs.datastructures;

public interface UnsortedList extends List {

    long first();

    long last();

    Iterator byIncreasingIndex();

    Iterator byDecreasingIndex();
}
