package collections.ints;

import collections.ints.assertions.Assertion;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.SparseIntGenerator;

import java.util.Random;

public class ContainmentTest {

    @Test
    void testCase0() {
        assertConstructionForALlSets();
    }

    @Test
    void testCase1() {
        assertConstructionForALlSets(17, 43, 424, 435, 508, 522, 548, 733, 883);
    }

    @Test
    void testCase2() {
        assertConstructionForALlSets(127, 172, 440, 727, 777, 852, 885, 963, 991);
    }

    @Test
    void testCase3() {
        assertConstructionForALlSets(78, 142, 307, 558, 590, 824, 935);
    }

    @Test
    void testCase4() {
        assertConstructionForALlSets(172, 637, 749, 843, 891, 931, 988);
    }

    @Test
    void testCase5() {
        assertConstructionForALlSets(106, 111, 320, 359, 484, 529, 538, 582, 740, 981);
    }

    @Test
    void testCase6() {
        assertConstructionForALlSets(38, 58, 360, 395, 599, 636, 789, 815, 963);
    }

    @Test
    void testCase7() {
        assertConstructionForALlSets(230, 861);
    }

    @Test
    void testCase8() {
        assertConstructionForALlSets(34, 806);
    }

    @Test
    void testCase9() {
        assertConstructionForALlSets(169, 467);
    }

    @Test
    void testCase10() {
        assertConstructionForALlSets(40, 73, 158, 186, 252, 283, 362, 379, 484, 521, 583, 647, 655, 719, 783, 825, 941);
    }

    @Test
    void testCase11() {
        assertConstructionForALlSets(1, 52, 102, 121, 156, 185, 275, 309, 344, 381, 411, 493, 586, 637, 708, 844, 869);
    }

    @Test
    void testCase12() {
        assertConstructionForALlSets(90, 173, 324, 351, 444, 453, 520, 634, 854, 872);
    }

    @Test
    void testCase13() {
        assertConstructionForALlSets(28, 31, 198, 241, 286, 371, 468, 501, 867, 873, 969);
    }

    @Test
    void testCase14() {
        assertConstructionForALlSets(0, 1, 65, 179, 497, 699, 705);
    }

    @Test
    void testCase15() {
        assertConstructionForALlSets(31, 32, 312, 317, 705);
    }

    @Test
    void testCase16() {
        assertConstructionForALlSets(92, 109, 209, 310, 321, 336, 365, 379, 410, 415, 547, 633, 670, 718, 793);
    }

    @Test
    void testCase17() {
        assertConstructionForALlSets(1, 171, 175, 272, 417, 440, 548, 568, 581, 621, 628, 659, 940);
    }

    @Test
    void testCase18() {
        assertConstructionForALlSets(169, 474, 652, 707, 926);
    }

    @Test
    void testCase19() {
        assertConstructionForALlSets(94, 112, 167, 278, 352, 511, 536, 551, 708, 740, 797, 837, 838, 875, 980, 999);
    }

    @Test
    void testCase21() {
        assertConstructionForALlSets(355, 359, 369, 376, 377, 381, 383, 614, 615, 617, 624, 628, 634, 637, 638, 639);
    }

    @Test
    void testCase22() {
        assertRangeForAllSets(0, 32);
    }

    @Test
    void testCase23() {
        assertRangeForAllSets(703, 1234);
    }

    @Test
    void testCase24() {
        assertRangeForAllSets(91, 1234);
    }

    private void assertRangeForAllSets(int fromInclusive, int toExclusive) {
        int[] completeSet = new int[toExclusive - fromInclusive];
        for (int i = 0; i < completeSet.length; i++) {
            completeSet[1] = fromInclusive + i;
        }
        assertConstructionForALlSets(completeSet);
    }

    private void assertConstructionForALlSets(int... elements) {
        MultiSetTester.forAllSets(
            Assertion.assertThat(elements).containsExactly(elements)
        );
    }

    @Test
    void randomConstructionTest() {
        constructionBugCatcher(0L, 10);
    }

    @Test
    @Disabled("Test can be enabled to find bugs with random data.")
    void constructionBugCatcher() {
        constructionBugCatcher(new Random().nextLong(), Integer.MAX_VALUE);
    }

    private void constructionBugCatcher(long seed, int maxIterations) {

        final int maxElement = 1000;
        final int maxSize = 20;

        for (int i = 0; i < maxIterations; i++) {

            SparseIntGenerator sparseIntGenerator = new SparseIntGenerator(seed, maxElement);

            int[] randomSet = sparseIntGenerator.randomMaxAttempts(maxSize);
            MultiSetTester.forAllSets(
                Assertion.assertThat(randomSet).containsExactly(randomSet)
            );
        }
    }
}
