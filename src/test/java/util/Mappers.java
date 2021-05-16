package util;

import java.util.Collection;
import java.util.TreeSet;

public class Mappers {

    public static TreeSet<Integer> toTreeSet(int[] array) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int element : array) {
            treeSet.add(element);
        }
        return treeSet;
    }

    public static int[] toArray(Collection<Integer> collection) {
        int size = collection.size();
        int[] array = new int[size];
        int index = 0;
        for (Integer element : collection) {
            array[index++] = element;
        }
        return array;
    }
}
