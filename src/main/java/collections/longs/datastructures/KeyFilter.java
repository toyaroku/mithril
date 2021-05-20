package collections.longs.datastructures;

public enum KeyFilter {

    A_OR_B {
        @Override
        boolean retain(long keyA, long keyB) {
            return (keyA | keyB) != 0L;
        }
    },
    A_AND_B {
        @Override
        boolean retain(long keyA, long keyB) {
            return keyA == keyB;
        }
    },
    A_AND_NOT_B {
        @Override
        boolean retain(long keyA, long keyB) {
            return (keyA & ~keyB) != 0L;
        }
    };

    abstract boolean retain(long keyA, long keyB);
}
