package util;

import collections.Collections;

import static collections.Collections.noSuchInt;

public class MaskedBinarySearch {

    private final long mask;

    public MaskedBinarySearch(long mask) {
        this.mask = mask;
    }

    public int indexOf(long[] array, int length, long key) {
        int index = indexOfOrInverseInsertIndex(array, length, key);
        if (index < 0 || index >= length) {
            return Collections.noSuchInt();
        }
        return index;
    }

    public int indexOfOrInverseInsertIndex(long[] array, int length, long key) {
        return indexOfOrInverseInsertIndex(mask, array, length, key);
    }

    public static int indexOfOrInverseInsertIndex(long mask, long[] array, int length, long key) {

        long actualKey = key & mask;

        int low = 0;
        int high = length - 1;

        while(low <= high) {
            int mid = low + high >>> 1;
            long midVal = array[mid] & mask;
            if (midVal < actualKey) {
                low = mid + 1;
            } else {
                if (midVal <= actualKey) {
                    return mid;
                }
                high = mid - 1;
            }
        }

        return -(low + 1);
    }
}
