package collections.longs.datastructures.sets;

import collections.longs.datastructures.iterators.LongElementContainer;

public interface SetView extends View, LongElementContainer {

    boolean hasNextUnique();

    void iterateNextUnique();
}
