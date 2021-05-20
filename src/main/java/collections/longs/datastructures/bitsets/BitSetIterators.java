package collections.longs.datastructures.bitsets;

import collections.longs.datastructures.maps.CompressedSortedMapIterator;

public class BitSetIterators {

    public static Compressed compressed(CompressedSortedMapIterator iterator) {
        // TODO translate compression when different
        return new Compressed(iterator);
    }

    public static class Compressed implements BitSetIterator {

        private final CompressedSortedMapIterator iterator;

        private int bitIndex;

        private Compressed(CompressedSortedMapIterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public long bitSet() {
            return iterator.value();
        }

        @Override
        public long bitIndex() {
            return 0;
        }

        @Override
        public long bitSetOffset() {
            return iterator.key() * bitSetLength();
        }

        @Override
        public int bitSetLength() {
            return iterator.compressor().getValueBits();
        }

        @Override
        public long value() {
            return ;
        }

        @Override
        public boolean hasGreater() {
            return iterator.hasGreater() || bitIndex ;
        }

        @Override
        public void iterateGreater() {

        }

        @Override
        public boolean hasSmaller() {
            return false;
        }

        @Override
        public void iterateSmaller() {

        }
    }
}
