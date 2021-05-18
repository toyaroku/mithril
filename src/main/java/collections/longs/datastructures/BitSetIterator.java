package collections.longs.datastructures;

import java.util.NoSuchElementException;

import static collections.Collections.noSuchLong;

public interface BitSetIterator extends Iterator {

    long currentElement();

    long getBitSet();

    long getBitSetIndex();

    long getSmallestPossibleValue();

    int getBitSetSize();

    static BitSetIterator empty() {
        return new Empty();
    }

    class Empty implements BitSetIterator {

        private Empty() {

        }

        @Override
        public long currentElement() {
            throw new NoSuchElementException();
        }

        @Override
        public long getBitSet() {
            throw new NoSuchElementException();
        }

        @Override
        public long getBitSetIndex() {
            throw new NoSuchElementException();
        }

        @Override
        public long getSmallestPossibleValue() {
            throw new NoSuchElementException();
        }

        @Override
        public int getBitSetSize() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public long next() {
            throw new NoSuchElementException();
        }
    }
}
