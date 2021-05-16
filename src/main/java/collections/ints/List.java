package collections.ints;

import iterators.ints.Iterator;

public interface List<L extends List<L>> {

    boolean isEmpty();

    int length();

    int first();

    int last();

    int get(int index);

    Iterator forwards();

    Iterator backwards();

    int firstIndexOf(int element);

    int lastIndexOf(int element);

    L concat(L suffix);

    L deepCopy();
}
