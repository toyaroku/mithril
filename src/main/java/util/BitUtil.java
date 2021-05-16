package util;

import java.util.Arrays;

import static collections.Collections.noSuchElement;

public class BitUtil {

    public static final int BITS_PER_LONG = 64;
    public static final int BITS_PER_INT = 32;

    private BitUtil() {
    }

    public static int log2Ceil(int word) {
        return BITS_PER_INT - Integer.numberOfLeadingZeros(word - 1);
    }

    public static boolean containsBitAtIndex(long word, int index) {
        return (word & (1L << index)) != 0;
    }

    public static boolean containsBitAtIndex(int word, int index) {
        return (word & (1L << index)) != 0;
    }

    public static long withBitSetAtIndex(long word, int index) {
        return word | (1L << index);
    }

    public static long clearBitArIndex(long word, int index) {
        return word & ~(1L << index);
    }

    public static int lastBit(long word) {
        if (word == 0) {
            return noSuchElement();
        }
        int previousIndexFromEnd = Long.numberOfLeadingZeros(word) + 1;
        return BITS_PER_LONG - previousIndexFromEnd;
    }

    public static int lastBit(int word) {
        if (word == 0) {
            return noSuchElement();
        }
        int previousIndexFromEnd = Integer.numberOfLeadingZeros(word) + 1;
        return BITS_PER_INT - previousIndexFromEnd;
    }

    public static int firstBit(long word) {
        if (word == 0) {
            return noSuchElement();
        }
        return Long.numberOfTrailingZeros(word);
    }

    public static int firstBit(int word) {
        if (word == 0) {
            return noSuchElement();
        }
        return Integer.numberOfTrailingZeros(word);
    }

    public static int previousSetBitInclusive(long word, int index) {
        if (index < 0) {
            return noSuchElement();
        }
        long smallerPart = word & (-1L >>> -(index + 1));
        if (smallerPart == 0L) {
            return noSuchElement();
        }
        return lastBit(smallerPart);
    }

    public static int previousSetBitInclusive(int word, int index) {
        if (index < 0) {
            return noSuchElement();
        }
        int smallerPart = word & (-1 >>> -(index + 1));
        if (smallerPart == 0L) {
            return noSuchElement();
        }
        return lastBit(smallerPart);
    }

    public static int nextSetBitInclusive(long word, int index) {
        if (index >= BITS_PER_LONG) {
            return noSuchElement();
        }
        long greaterPart = word & (-1L << index);
        int firstSetBitIndex = firstBit(greaterPart);
        if (firstSetBitIndex >= BITS_PER_LONG) {
            return noSuchElement();
        }
        return firstSetBitIndex;
    }

    public static int nextSetBitInclusive(int word, int index) {
        if (index > BITS_PER_INT - 1) {
            return noSuchElement();
        }
        int greaterPart = word & (-1 << index);
        int firstSetBitIndex = firstBit(greaterPart);
        if (firstSetBitIndex >= BITS_PER_INT) {
            return noSuchElement();
        }
        return firstSetBitIndex;
    }

    public static int[] toArray(long word) {
        if (word == 0L) {
            return new int[0];
        }

        int size = Long.bitCount(word);
        int[] array = new int[size];

        int arrayIndex = 0;
        int bitIndex = -1;
        while (arrayIndex < size) {
            bitIndex = nextSetBitInclusive(word, bitIndex + 1);
            array[arrayIndex++] =  bitIndex;
        }

        return array;
    }

    public static int[] toArray(int word) {
        if (word == 0) {
            return new int[0];
        }

        int size = Integer.bitCount(word);
        int[] array = new int[size];

        int arrayIndex = 0;
        int bitIndex = -1;
        while (arrayIndex < size) {
            bitIndex = nextSetBitInclusive(word, bitIndex + 1);
            array[arrayIndex++] =  bitIndex;
        }

        return array;
    }

    public static int lowerHalf(long word) {
        return (int) word;
    }

    public static long withLowerHalf(long word, int lowerHalf) {
        return word | lowerHalf;
    }

    public static int upperHalf(long word) {
        return (int) (word >> BITS_PER_INT);
    }

    public static long withUpperHalf(long word, int lowerHalf) {
        return word | ((long) lowerHalf << BITS_PER_INT);
    }

    public static void swap(int[] array, int ix1, int ix2) {
        array[ix1] = (array[ix1] + array[ix2]) - (array[ix2] = array[ix1]);
    }

    public static void reverse(int[] array) {
        int ix = 0;
        while (ix < array.length / 2) {
            swap(array, ix, array.length - 1 - ix);
            ix++;
        }
    }

    public static int[] copy(int[] array) {
        return Arrays.copyOf(array, array.length);
    }
}
