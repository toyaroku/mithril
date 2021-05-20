package collections.longs.datastructures.maps;

import collections.longs.datastructures.arrays.SortedResizableArray;

import static util.BitUtil.BITS_PER_LONG;

public class EntryCompressor {

    private final int keyBits;
    private final int valueBits;
    private final long keyMask;
    private final long valueMask;

    public EntryCompressor(int keyBits) {
        this.keyBits = keyBits;
        this.valueBits = BITS_PER_LONG - keyBits;
        // key is encoded at the least significant part, allowing sorting on keys
        this.keyMask = -1L << keyBits;
        this.valueMask = ~keyMask;
    }

    public long value(long entry) {
        return entry & valueMask;
    }

    public long key(long entry) {
        return (entry & keyMask) >> valueBits;
    }

    public long create(long key, long entry) {
        return (key << valueBits) | entry;
    }

    public long withKey(long entry, long key) {
        return (entry & valueMask) | (key << valueBits);
    }

    public long keyEntry(long key) {
        return withKey(0L, key);
    }

    public long withValue(long entry, long value) {
        return (value << valueBits) | (entry & keyMask);
    }

    public int getKeyBits() {
        return keyBits;
    }

    public int getValueBits() {
        return valueBits;
    }
}
