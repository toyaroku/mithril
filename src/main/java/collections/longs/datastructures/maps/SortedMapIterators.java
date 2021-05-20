package collections.longs.datastructures.maps;

import collections.longs.datastructures.lists.SortedListIterator;

public class SortedMapIterators {

    public static CompressedSortedMapIterator fromCompressedSortedList(SortedListIterator iterator, EntryCompressor compressor) {
        return new FromCompressedList(iterator, compressor);
    }

    private static class FromCompressedList implements CompressedSortedMapIterator {

        private final SortedListIterator iterator;
        private final EntryCompressor compressor;

        private FromCompressedList(SortedListIterator iterator, EntryCompressor compressor) {
            this.iterator = iterator;
            this.compressor = compressor;
        }

        @Override
        public long key() {
            return compressor.key(iterator.element());
        }

        @Override
        public long value() {
            return compressor.value(iterator.element());
        }

        @Override
        public boolean hasNextEntry() {
            return iterator.hasGreater();
        }

        @Override
        public void iterateNextEntry() {
            iterator.iterateNextIndex();
        }

        @Override
        public boolean hasGreater() {
            return iterator.hasGreater();
        }

        @Override
        public void iterateGreater() {
            iterator.iterateGreater();
        }

        @Override
        public boolean hasSmaller() {
            return iterator.hasSmaller();
        }

        @Override
        public void iterateSmaller() {
            iterator.iterateSmaller();
        }

        @Override
        public EntryCompressor compressor() {
            return compressor;
        }
    }
}
