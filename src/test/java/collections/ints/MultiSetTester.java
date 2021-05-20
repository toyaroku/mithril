package collections.ints;

import collections.ints.assertions.SetFactory;
import collections.ints.assertions.Statement;
import collections.longs.datastructures.bitsets.BitSetIterator;
import collections.longs.datastructures.maps.EntryCompressor;
import collections.longs.datastructures.arrays.CompressedBitSet;
import iterators.ints.Iterator;

public class MultiSetTester {

    public static void forAllSets(Statement statement) {
        // test sparse bit array with default settings, constructed from sorted array

        // sparse bit array v1 and v2 (storing integer bit sets on either side of a long).
        //forSparseBitArrayIntegerWordV1(statement);
        //forSparseBitArrayIntegerWordV2(statement);
        forV2(statement);
    }

    public static void forSparseBitArrayIntegerWordV1(Statement statement) {
        statement.assertHoldsFor(SparseBitArrayFactories::v1FromSortedArray);
    }

    public static void forSparseBitArrayIntegerWordV2(Statement statement) {
        statement.assertHoldsFor(SparseBitArrayFactories::v2FromSortedArray);
    }

    public static void forV2(Statement statement) {
        statement.assertHoldsFor(v2SetSetFactory(32));
    }

    public static void forAllSets(Statement statement, Statement... secondaryChecks) {
        // primary check
        forAllSets(statement);
        // secondary checks
        for (Statement secondaryCheck : secondaryChecks) {
            forAllSets(secondaryCheck);
        }
    }

    private static SetFactory<V2Set> v2SetSetFactory(int addressBits) {
        return new SetFactory<V2Set>() {
            @Override
            public V2Set create(int... elements) {
                CompressedBitSet compressedBitSet = new CompressedBitSet(new EntryCompressor(addressBits));
                for (int element : elements) {
                    compressedBitSet.add(element);
                    compressedBitSet.toString();
                }
                return new V2Set(compressedBitSet);
            }
        };
    }

    private static class V2Set implements Set<V2Set> {

        private CompressedBitSet bitSet;

        private V2Set(CompressedBitSet bitSet) {
            this.bitSet = bitSet;
        }

        @Override
        public boolean isEmpty() {
            return bitSet.isEmpty();
        }

        @Override
        public int cardinality() {
            return bitSet.cardinality();
        }

        @Override
        public int smallest() {
            return (int) bitSet.smallest();
        }

        @Override
        public int greatest() {
            return (int) bitSet.greatest();
        }

        @Override
        public int smaller(int element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int greater(int element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Iterator increasing() {
            return new Iterator() {

                private BitSetIterator it = bitSet.beforeSmallest();

                @Override
                public boolean hasNext() {
                    return it.isEmpty();
                }

                @Override
                public int next() {
                    it.next();
                    return (int) it.currentElement();
                }
            };
        }

        @Override
        public Iterator decreasing() {
            return new Iterator() {

                private final BitSetIterator it = bitSet.afterGreatest();

                @Override
                public boolean hasNext() {
                    return it.isEmpty();
                }

                @Override
                public int next() {
                    it.next();
                    return (int) it.currentElement();
                }
            };
        }

        @Override
        public boolean contains(int element) {
            return bitSet.contains(element);
        }

        @Override
        public boolean equivalent(V2Set other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean intersects(V2Set other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean subSet(V2Set other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V2Set union(V2Set other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V2Set intersection(V2Set other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V2Set difference(V2Set other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V2Set deepCopy() {
            throw new UnsupportedOperationException();
        }
    }
}
