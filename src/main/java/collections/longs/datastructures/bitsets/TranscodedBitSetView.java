package collections.longs.datastructures.bitsets;

import static util.BitUtil.BITS_PER_LONG;

public class TranscodedBitSetView implements BitSetView {

    private final BitSetView original;

    private final int transcodedBitSetLength;

    private long transcodedBitSet;
    private long transcodedOffset;

    private long bitsLeftInCurrent;
    private boolean hasNext;

    private TranscodedBitSetView(BitSetView original, int newBitSetLength) {
        this.original = original;
        this.transcodedBitSetLength = newBitSetLength;
    }

    public static BitSetView transcode(BitSetView original, int newBitSetLength) {
        if (original.bitSetLength() == newBitSetLength) {
            return original;
        }
        return new TranscodedBitSetView(original, newBitSetLength);
    }

    @Override
    public boolean hasNextBitSet() {
        return hasNext;
    }

    @Override
    public void iterateNextBitSet() {
        transcodedBitSet = 0L;
        while (transcodedBitSet == 0L) {
            iterate();
        }
    }

    private void iterate() {

        if (bitsLeftInCurrent > 0) {
            transcodedOffset = transcodedOffset + transcodedBitSetLength;
        }

        long currentBitSet = 0L;
        int currentBitIndex = 0;
        int remainingLength = transcodedBitSetLength;

        while (remainingLength > 0 && hasNext) {

            long firstIteratorElement = original.bitSetOffset();
            long missingElements = firstIteratorElement - (transcodedOffset + currentBitIndex);
            if (missingElements > 0) {
                long zeroesToAdd = Math.min(missingElements, remainingLength);
                currentBitIndex += zeroesToAdd;
                remainingLength -= zeroesToAdd;
            } else {

                if (remainingLength < bitsLeftInCurrent) {
                    long srcIndex = original.bitSetLength() - bitsLeftInCurrent;
                    long lastPart = copyRangeToEmptyBitSet(
                        original.bitSet(), srcIndex, remainingLength, currentBitIndex);
                    currentBitSet = currentBitSet | lastPart;
                    bitsLeftInCurrent -= remainingLength;
                    currentBitIndex += remainingLength;
                    remainingLength = 0;
                } else {
                    long srcIndex = original.bitSetLength() - bitsLeftInCurrent;
                    long lastPart = copyRangeToEmptyBitSet(
                        original.bitSet(), srcIndex, bitsLeftInCurrent, currentBitIndex);
                    currentBitSet = currentBitSet | lastPart;
                    currentBitIndex = 0;
                    remainingLength -= bitsLeftInCurrent;
                    bitsLeftInCurrent = 0;
                }

                if (bitsLeftInCurrent == 0 || tailEmpty(original.bitSet(), bitsLeftInCurrent)) {
                    if (!original.hasNextBitSet()) {
                        hasNext = false;
                    }
                    original.iterateNextBitSet();
                    bitsLeftInCurrent = original.bitSetLength();
                }
            }
        }

        this.transcodedBitSet = currentBitSet;
    }

    private boolean tailEmpty(long bitSet, long length) {
        long shift = BITS_PER_LONG - length;
        return  (bitSet >> shift) == 0;
    }

    private long copyRangeToEmptyBitSet(long bitSet, long srcIndex, long length, long destIndex) {
        long shift = BITS_PER_LONG - length;
        return  (bitSet << shift) >>> (shift + srcIndex) << destIndex;
    }

    @Override
    public long bitSet() {
        return transcodedBitSet;
    }

    @Override
    public long bitSetOffset() {
        return transcodedOffset;
    }

    @Override
    public int bitSetLength() {
        return transcodedBitSetLength;
    }
}
