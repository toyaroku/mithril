package collections;

public final class Collections {

    private static final int NO_SUCH_ELEMENT_INT = -1;
    private static final long NO_SUCH_ELEMENT_LONG = -1;

    public static int noSuchInt() {
        return NO_SUCH_ELEMENT_INT;
    }

    public static long noSuchLong() {
        return NO_SUCH_ELEMENT_LONG;
    }

    public static boolean noSuchInt(int index) {
        return index == NO_SUCH_ELEMENT_INT;
    }

    public static boolean noSuchLong(long element) {
        return element == NO_SUCH_ELEMENT_LONG;
    }
}
