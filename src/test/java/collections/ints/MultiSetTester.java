package collections.ints;

import collections.ints.assertions.Statement;

import javax.swing.plaf.nimbus.State;

public class MultiSetTester {

    public static void forAllSets(Statement statement) {
        forAllSets(statement, 0);
    }

    private static void forAllSets(Statement statement, int statementIndex) {
        // test sparse bit array with default settings, constructed from sorted array
        if (!statement.holdsFor(SparseBitArrayFactories::fromSortedArray)) {
            throw new AssertionError(
                String.format("Statement at index %d did not hold for a certain set.", statementIndex)
            );
        }
    }

    public static void forAllSets(Statement statement, Statement... secondaryChecks) {
        int statementIndex = 0;
        // primary check
        forAllSets(statement, statementIndex);
        // secondary checks
        for (Statement secondaryCheck : secondaryChecks) {
            statementIndex++;
            forAllSets(secondaryCheck, statementIndex);
        }
    }
}
