package collections;

public final class Collections {

    private static final int NO_SUCH_ELEMENT = -1;

    public static int noSuchElement() {
        return NO_SUCH_ELEMENT;
    }

    public static boolean noSuchElement(int index) {
        return index == NO_SUCH_ELEMENT;
    }
}
