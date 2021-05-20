package collections.longs.datastructures.maps;

public class Updates {

    private static final Update LOGICAL_OR = ((contained, operand) -> contained | operand);
    private static final Update REPLACE = ((contained, operand) -> operand);
    private static final Update SUM = ((contained, operand) -> contained + operand);

    public static Update replace() {
        return REPLACE;
    }

    public static Update logicalOr() {
        return LOGICAL_OR;
    }

    public static Update sum() {
        return SUM;
    }
}
