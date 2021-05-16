package collections.ints.sets.bitarray;

import iterators.ints.Iterator;
import util.BitUtil;

import static collections.Collections.noSuchElement;

class DecreasingIterator implements Iterator {

    private final BitSetWithMetaDataCalculator calculator;
    private final long[] words;
    private final int wordsInUse;

    int currentWordIndex;
    int currentOffset;
    int currentBitSet;
    int currentBitIndex;

    DecreasingIterator(long[] words, int wordsInUse, BitSetWithMetaDataCalculator calculator) {
        this.words = words;
        this.wordsInUse = wordsInUse;
        this.calculator = calculator;

        if (wordsInUse > 0) {
            currentWordIndex = wordsInUse - 1;
            long word = words[currentWordIndex];
            int address = calculator.extractMetaData(word);
            currentOffset = calculator.calculateMaxCardinalityF(address);
            currentBitSet = calculator.extractBitSet(word);
            currentBitIndex = BitUtil.lastBit(currentBitSet);
        } else {
            currentBitIndex = noSuchElement();
        }
    }

    @Override
    public boolean hasNext() {
        return !noSuchElement(currentBitIndex);
    }

    @Override
    public int next() {
        int currentElement = currentOffset + currentBitIndex;
        currentBitIndex = BitUtil.previousSetBitInclusive(currentBitSet, currentBitIndex - 1);
        if (noSuchElement(currentBitIndex)) {
            if (currentWordIndex > 0) {
                long word = words[--currentWordIndex];
                int address  = calculator.extractMetaData(word);
                currentOffset = calculator.calculateMaxCardinalityF(address);
                currentBitSet = calculator.extractBitSet(word);
                currentBitIndex = BitUtil.lastBit(currentBitSet);
            }
        }
        return currentElement;
    }
}
