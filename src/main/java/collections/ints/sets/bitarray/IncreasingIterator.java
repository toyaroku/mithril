package collections.ints.sets.bitarray;

import collections.Collections;
import iterators.ints.Iterator;
import util.BitUtil;

import static collections.Collections.noSuchInt;

class IncreasingIterator implements Iterator {

    private final IntegerBitSetWithMetaData calculator;
    private final long[] words;
    private final int wordsInUse;

    int currentWordIndex;
    int currentOffset;
    int currentBitSet;
    int currentBitIndex;

    IncreasingIterator(long[] words, int wordsInUse, IntegerBitSetWithMetaData calculator) {
        this.words = words;
        this.wordsInUse = wordsInUse;
        this.calculator = calculator;

        if (wordsInUse > 0) {
            long word = words[0];
            int address = calculator.extractMetaData(word);
            currentWordIndex = 0;
            currentOffset = calculator.calculateMaxCardinality(address);
            currentBitSet = calculator.extractBitSet(word);
            currentBitIndex = BitUtil.firstBit(currentBitSet);
        } else {
            currentBitIndex = Collections.noSuchInt();
        }
    }

    @Override
    public boolean hasNext() {
        return !Collections.noSuchInt(currentBitIndex);
    }

    @Override
    public int next() {
        int currentElement = currentOffset + currentBitIndex;
        currentBitIndex = BitUtil.nextSetBitInclusive(currentBitSet, currentBitIndex + 1);
        if (Collections.noSuchInt(currentBitIndex)) {
            if (currentWordIndex < wordsInUse - 1) {
                long word = words[++currentWordIndex];
                int address  = calculator.extractMetaData(word);
                currentOffset = calculator.calculateMaxCardinality(address);
                currentBitSet = calculator.extractBitSet(word);
                currentBitIndex = BitUtil.firstBit(currentBitSet);
            }
        }
        return currentElement;
    }
}
