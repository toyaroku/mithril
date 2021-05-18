package collections.ints.sets.bitarray;

import collections.Collections;
import collections.ints.Set;
import iterators.ints.Iterator;
import util.BitUtil;
import util.MaskedBinarySearch;

import java.util.Arrays;

import static collections.Collections.noSuchInt;

public class SparseBitArray implements Set<SparseBitArray> {

    private final IntegerBitSetWithMetaData config;
    private final MaskedBinarySearch indexer;

    private final int wordsInUse;
    private final int lowestAddress;
    private final int highestAddress;
    private final long[] words;

    SparseBitArray(IntegerBitSetWithMetaData config, MaskedBinarySearch indexer, long[] words, int wordsInUse) {
        this.config = config;
        this.indexer = indexer;

        this.words = words;
        this.wordsInUse = wordsInUse;
        if (wordsInUse > 0) {
            lowestAddress = config.extractMetaData(words[0]);
            highestAddress = config.extractMetaData(words[wordsInUse - 1]);
        } else {
            lowestAddress = Collections.noSuchInt();
            highestAddress = Collections.noSuchInt();
        }
    }

    private SparseBitArray(SparseBitArray sparseBitArray) {
        this.config = sparseBitArray.config;
        this.indexer = sparseBitArray.indexer;
        this.words = Arrays.copyOf(sparseBitArray.words, sparseBitArray.wordsInUse);
        this.wordsInUse = sparseBitArray.wordsInUse;
        this.lowestAddress = sparseBitArray.lowestAddress;
        this.highestAddress = sparseBitArray.highestAddress;
    }

    private SparseBitArray newInstance(long[] words, int wordsInUse) {
        return new SparseBitArray(config, indexer, words, wordsInUse);
    }

    private SparseBitArray emptyInstance() {
        return new SparseBitArray(config, indexer, new long[0], 0);
    }

    @Override
    public boolean isEmpty() {
        return wordsInUse == 0;
    }

    @Override
    public int cardinality() {
        int cardinality = 0;
        for (int i = 0; i < words.length; i++) {
            cardinality += Integer.bitCount(config.extractBitSet(words[i]));
        }
        return cardinality;
    }

    @Override
    public int smallest() {
        return isEmpty()
            ? Collections.noSuchInt()
            :  calculateElement(lowestAddress, BitUtil.firstBit(config.extractBitSet(words[0])));
    }

    @Override
    public int greatest() {
        return isEmpty()
            ? Collections.noSuchInt()
            : calculateElement(highestAddress, BitUtil.lastBit(config.extractBitSet(words[wordsInUse - 1])));
    }

    private int calculateElement(int address, int bitIndex) {
        return config.calculateMaxCardinality(address) + bitIndex;
    }

    @Override
    public int smaller(int element) {
        if (isEmpty()) {
            return Collections.noSuchInt();
        }
        int address = config.calculateIndexOfContainingBitSet(element);
        long wordWithAddressOnly = config.withMetaData(0L, address);
        int maybeIndex = indexer.indexOfOrInverseInsertIndex(words, wordsInUse, wordWithAddressOnly);
        if (maybeIndex >= 0) {
            long word = words[maybeIndex];
            long bitSet = config.extractBitSet(word);
            int indexInWord = config.calculateIndexInBitSet(element);
            int previousIndex = BitUtil.previousSetBitInclusive(bitSet,indexInWord - 1);
            if (Collections.noSuchInt(previousIndex)) {
                if (maybeIndex == 0) {
                    return Collections.noSuchInt();
                }
                maybeIndex--;
            } else {
                return config.calculateMaxCardinality(address) + previousIndex;
            }
        } else {
            maybeIndex = -maybeIndex - 2;
            if (maybeIndex == -1) {
                return Collections.noSuchInt();
            }
        }

        if (maybeIndex > wordsInUse - 1) {
            maybeIndex = wordsInUse - 1;
        }
        long word =  words[maybeIndex];
        int actualAddress = config.extractMetaData(word);
        long bitSet = config.extractBitSet(word);
        return config.calculateMaxCardinality(actualAddress) + BitUtil.lastBit(bitSet);
    }

    @Override
    public int greater(int element) {
        if (isEmpty()) {
            return Collections.noSuchInt();
        }
        int address = config.calculateIndexOfContainingBitSet(element);
        long wordWithAddressOnly = config.withMetaData(0L, address);
        int maybeIndex = indexer.indexOfOrInverseInsertIndex(words, wordsInUse, wordWithAddressOnly);
        if (maybeIndex >= 0) {
            long word = words[maybeIndex];
            long bitSet = config.extractBitSet(word);
            int indexInWord = config.calculateIndexInBitSet(element);
            int nextIndex = BitUtil.nextSetBitInclusive(bitSet, indexInWord + 1);
            if (Collections.noSuchInt(nextIndex)) {
                if (maybeIndex == wordsInUse - 1) {
                    return Collections.noSuchInt();
                }
                maybeIndex++;
            } else {
                return config.calculateMaxCardinality(address) + nextIndex;
            }
        } else {
            maybeIndex = -maybeIndex - 1;
            if (maybeIndex == wordsInUse) {
                return Collections.noSuchInt();
            }
        }

        long word = words[maybeIndex];
        long bitSet = config.extractBitSet(word);
        int actualAddress = config.extractMetaData(word);
        return config.calculateMaxCardinality(actualAddress) + BitUtil.firstBit(bitSet);
    }

    @Override
    public Iterator increasing() {
        return new IncreasingIterator(words, wordsInUse, config);
    }

    @Override
    public Iterator decreasing() {
        return new DecreasingIterator(words, wordsInUse, config);
    }

    @Override
    public boolean contains(int element) {
        int address = config.calculateIndexOfContainingBitSet(element);
        if (!mightContainAddress(address)) {
            return false;
        }
        long rawAddress = config.withMetaData(0L, address);
        int wordIndex = indexer.indexOf(words, wordsInUse, rawAddress);
        if (Collections.noSuchInt(wordIndex)) {
            return false;
        }
        int bitSet = config .extractBitSet(words[wordIndex]);
        int bitIndex = config.calculateIndexInBitSet(element);
        return BitUtil.containsBitAtIndex(bitSet, bitIndex);
    }

    @Override
    public boolean equivalent(SparseBitArray other) {
        if (lowestAddress != other.lowestAddress) {
            return false;
        }
        if (highestAddress != other.highestAddress) {
            return false;
        }
        if (wordsInUse != other.wordsInUse) {
            return false;
        }
        for (int index = 0; index < wordsInUse; index++) {
            if (words[index] != other.words[index]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean intersects(SparseBitArray other) {
        assert config.isEquivalentTo(other.config) : "Sets are created using different settings, this is not supported.";
        if (isEmpty() || other.isEmpty()) {
            return false;
        }
        if (highestAddress < other.lowestAddress || other.highestAddress < lowestAddress) {
            return false;
        }

        int address1 = lowestAddress;
        int address2 = other.lowestAddress;

        int index1 = 0;
        int index2 = 0;

        while (index1 < wordsInUse && index2 < other.wordsInUse) {
            while (address1 < address2 && index1 < wordsInUse) {
                if (++index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                }
            }
            while (address1 > address2 && index2 < other.wordsInUse) {
                if (++index2 < other.wordsInUse) {
                    address2 =config.extractMetaData(other.words[index2]);
                }
            }
            if (address1 == address2) {
                long word1 = words[index1++];
                long word2 = other.words[index2++];
                long intersectedWord = word1 & word2;
                if (config.extractBitSet(intersectedWord) != 0L) {
                    return true;
                }
                if (index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                }
                if (index2 < other.wordsInUse) {
                    address2 = config.extractMetaData(other.words[index2]);
                }
            }
        }

        return false;
    }

    @Override
    public boolean subSet(SparseBitArray other) {
        assert config.isEquivalentTo(other.config) : "Sets are created using different settings, this is not supported.";
        if (other.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            return true;
        }
        if (lowestAddress < other.lowestAddress || highestAddress > other.highestAddress) {
            return false;
        }

        int address1 = lowestAddress;
        int address2 = other.lowestAddress;

        int index1 = 0;
        int index2 = 0;

        while (index1 < wordsInUse && index2 < other.wordsInUse) {
            if (address1 < address2) {
                return false;
            }
            while (address1 > address2 && index2 < other.wordsInUse) {
                if (++index2 < other.wordsInUse) {
                    address2 =config.extractMetaData(other.words[index2]);
                }
            }
            if (address1 == address2) {
                long word1 = words[index1++];
                long word2 = other.words[index2++];
                long intersectedWord = word1 & word2;
                if (intersectedWord != word1) {
                    return false;
                }
                if (index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                }
                if (index2 < other.wordsInUse) {
                    address2 = config.extractMetaData(other.words[index2]);
                }
            }
        }

        return true;
    }

    @Override
    public SparseBitArray union(SparseBitArray other) {
        assert config.isEquivalentTo(other.config) : "Sets are created using different settings, this is not supported.";
        if (isEmpty()) {
            return other;
        }
        if (other.isEmpty()) {
            return this;
        }

        long[] union = new long[wordsInUse + other.wordsInUse];

        int address1 = lowestAddress;
        int address2 = other.lowestAddress;

        int index1 = 0;
        int index2 = 0;

        int size = 0;
        while (index1 < wordsInUse || index2 < other.wordsInUse) {
            while ((address1 < address2 || index2 >= other.wordsInUse) && index1 < wordsInUse) {
                long word = words[index1++];
                union[size++] = word;
                if (index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                } else {
                    address1 = Integer.MAX_VALUE;
                }
            }
            while ((address1 > address2 || index1 >= wordsInUse) && index2 < other.wordsInUse) {
                long word = other.words[index2++];
                union[size++] = word;
                if (index2 < other.wordsInUse) {
                    address2 = config.extractMetaData(other.words[index2]);
                } else {
                    address2 = Integer.MAX_VALUE;
                }
            }
            if (address1 == address2 && index1 < wordsInUse && index2 < other.wordsInUse) {
                long word1 = words[index1++];
                long word2 = other.words[index2++];
                union[size++] = word1 | word2;
                if (index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                }
                if (index2 < other.wordsInUse) {
                    address2 = config.extractMetaData(other.words[index2]);
                }
            }
        }

        return newInstance(union, size);
    }

    @Override
    public SparseBitArray intersection(SparseBitArray other) {
        assert config.isEquivalentTo(other.config) : "Sets are created using different settings, this is not supported.";
        if (isEmpty() || other.isEmpty()) {
            return emptyInstance();
        }
        if (highestAddress < other.lowestAddress || other.highestAddress < lowestAddress) {
            return emptyInstance();
        }

        int size = Math.min(wordsInUse, other.wordsInUse);
        long[] intersection = new long[size];

        int address1 = lowestAddress;
        int address2 = other.lowestAddress;

        int index1 = 0;
        int index2 = 0;

        int intersectionIndex = 0;
        while (index1 < wordsInUse && index2 < other.wordsInUse) {
            while (address1 < address2 && index1 < wordsInUse) {
                if (++index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                }
            }
            while (address1 > address2 && index2 < other.wordsInUse) {
                if (++index2 < other.wordsInUse) {
                    address2 =config.extractMetaData(other.words[index2]);
                }
            }
            if (address1 == address2) {
                long word1 = words[index1++];
                long word2 = other.words[index2++];
                long intersectedWord = word1 & word2;
                if (config.extractBitSet(intersectedWord) != 0L) {
                    intersection[intersectionIndex++] = intersectedWord;
                }
                if (index1 < wordsInUse) {
                    address1 = config.extractMetaData(words[index1]);
                }
                if (index2 < other.wordsInUse) {
                    address2 = config.extractMetaData(other.words[index2]);
                }
            }
        }

        if (intersectionIndex == 0) {
            return emptyInstance();
        }

        return newInstance(intersection, intersectionIndex);
    }

    @Override
    public SparseBitArray difference(SparseBitArray other) {
        assert config.isEquivalentTo(other.config) : "Sets are created using different settings, this is not supported.";
        if (isEmpty()) {
            return emptyInstance();
        }
        if (other.isEmpty()) {
            return this;
        }

        long[] difference = new long[wordsInUse];
        int size = 0;

        int index1 = 0;
        int index2 = 0;

        long word2 = other.words[0];
        int address2 = other.lowestAddress;

        while (index1 < wordsInUse) {
            long word1 = words[index1];
            int address1 = config.extractMetaData(word1);
            while (address2 < address1 && index2 < other.wordsInUse) {
                word2 = other.words[index2++];
                address2 = config.extractMetaData(word2);
            }
            if (address2 == address1) {
                long differenceWord = word1 & ~(word2);
                if (config.extractBitSet(differenceWord) != 0) {
                    difference[size++] = config.withMetaData(differenceWord, address1);
                }
            } else  {
                difference[size++] = word1;
            }
            index1++;
        }

        if (size == 0) {
            return emptyInstance();
        }

        return newInstance(difference, size);
    }

    @Override
    public SparseBitArray deepCopy() {
        return new SparseBitArray(
            this
        );
    }

    private boolean mightContainAddress(int address) {
        return words.length > 0 && lowestAddress <= address && address <= highestAddress;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        Iterator iterator = increasing();
        stringBuilder.append(iterator.next());
        int maxElementsToPrint = 20;
        while (iterator.hasNext() && maxElementsToPrint-- > 0) {
            stringBuilder.append(", ");
            stringBuilder.append(iterator.next());
        }
        if (iterator.hasNext()) {
            stringBuilder.append(", and more ...");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
