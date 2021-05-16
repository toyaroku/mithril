package collections.ints;

import collections.ints.assertions.Statement;

public class MultiSetTester {

    public static void forAllSets(Statement statement) {
        // test sparse bit array with default settings, constructed from sorted array

        // sparse bit array v1 and v2 (storing integer bit sets on either side of a long).
        forSparseBitArrayIntegerWordV1(statement);
        forSparseBitArrayIntegerWordV2(statement);
    }

    public static void forSparseBitArrayIntegerWordV1(Statement statement) {
        statement.assertHoldsFor(SparseBitArrayFactories::v1FromSortedArray);
    }

    public static void forSparseBitArrayIntegerWordV2(Statement statement) {
        statement.assertHoldsFor(SparseBitArrayFactories::v2FromSortedArray);
    }

    public static void forAllSets(Statement statement, Statement... secondaryChecks) {
        // primary check
        forAllSets(statement);
        // secondary checks
        for (Statement secondaryCheck : secondaryChecks) {
            forAllSets(secondaryCheck);
        }
    }
}
