package collections.ints.sets.bitarray;

import collections.Collections;
import iterators.ints.Iterator;
import util.BitUtil;

import static collections.Collections.noSuchInteger;

class DecreasingIterator implements Iterator {

    private final IntegerBitSetWithMetaData calculator;
    private final long[] words;
    private final int wordsInUse;

    int currentWordIndex;
    int currentOffset;
    int currentBitSet;
    int currentBitIndex;

    DecreasingIterator(long[] words, int wordsInUse, IntegerBitSetWithMetaData calculator) {
        this.words = words;
        this.wordsInUse = wordsInUse;
        this.calculator = calculator;

        if (wordsInUse > 0) {
            currentWordIndex = wordsInUse - 1;
            long word = words[currentWordIndex];
            int address = calculator.extractMetaData(word);
            currentOffset = calculator.calculateMaxCardinality(address);
            currentBitSet = calculator.extractBitSet(word);
            currentBitIndex = BitUtil.lastBit(currentBitSet);
        } else {
            currentBitIndex = Collections.noSuchInteger();
        }
    }

    @Override
    public boolean hasNext() {
        return !Collections.noSuchInteger(currentBitIndex);
    }

    @Override
    public int next() {
        int currentElement = currentOffset + currentBitIndex;
        currentBitIndex = BitUtil.previousSetBitInclusive(currentBitSet, currentBitIndex - 1);
        if (Collections.noSuchInteger(currentBitIndex)) {
            if (currentWordIndex > 0) {
                long word = words[--currentWordIndex];
                int address  = calculator.extractMetaData(word);
                currentOffset = calculator.calculateMaxCardinality(address);
                currentBitSet = calculator.extractBitSet(word);
                currentBitIndex = BitUtil.lastBit(currentBitSet);
            }
        }
        return currentElement;
    }
}
