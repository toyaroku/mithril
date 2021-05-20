package collections.longs.datastructures.arrays;

import collections.longs.datastructures.maps.Update;

import java.util.Arrays;

public class Array {

    public static void swap(long[] elements, int index1, int index2) {
        elements[index1] = (elements[index1] + elements[index2]) - (elements[index2] = elements[index1]);
    }

    public static void reverse(long[] elements, int length) {
        reverse(elements, 0, length);
    }

    public static void reverse(long[] elements, int fromIncl, int toExcl) {
        int middleIndex = (toExcl - fromIncl) >> 1;
        for (int i = 0; i < middleIndex; i++) {
            swap(elements, i, toExcl - i - 1);
        }
    }

    public static int sortUnique(long[] elements, int length) {
        if (length <= 1) {
            return length;
        }
        Arrays.sort(elements);
        int nextIndex = 1;
        for (int i = 1; i < elements.length; i++) {
            long currentElement = elements[i];
            long previousElement = elements[nextIndex - 1];
            if (currentElement != previousElement) {
                if (nextIndex != i) {
                    elements[nextIndex] = currentElement;
                }
                nextIndex++;
            }
        }
        return nextIndex;
    }

    public static void bulkUpdate(long[] array, int length, Update update, long operand) {
        for (int i = 0; i < length; i++) {
            array[i] = update.apply(array[i], operand);
        }
    }
}
