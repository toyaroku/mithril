package collections.ints;

import org.junit.jupiter.api.Test;

import static collections.ints.assertions.Assertion.assertThat;

public class IndexingTest {

    @Test
    void testIndexingSmallDenseRegionCloseToZero() {
        MultiSetTester.forAllSets(
            assertThat(3, 10, 12, 13, 19).indexing()
                // smaller values
                .hasNoSmallerKey().forValues(0, 1, 2, 3)
                .hasSmallerKey(3).forValues(4, 5, 6, 7, 8, 9, 10)
                .hasSmallerKey(10).forValues(11, 12)
                .hasSmallerKey(12).forValues(13)
                .hasSmallerKey(13).forValues(14, 15, 16, 17, 18, 19)
                .hasSmallerKey(19).forValues(20, 21, 1000)
                // greater values
                .hasNoGreaterKey().forValues(19, 20, 21, 1000)
                .hasGreaterKey(19).forValues(13, 14, 15, 16, 17, 18)
                .hasGreaterKey(13).forValues(12)
                .hasGreaterKey(12).forValues(10, 11)
                .hasGreaterKey(10).forValues(3, 4, 5, 6, 7, 8, 9)
                .hasGreaterKey(3).forValues(0, 1, 2)
                // build test
                .holds()
        );
    }

    @Test
    void testIndexingSmallDenseRegionWithOffsetFromZero() {
        MultiSetTester.forAllSets(
            assertThat(1003, 1010, 1012, 1013, 1019).indexing()
                // smaller values
                .hasNoSmallerKey().forValues(0, 1, 2, 3, 78, 32, 912, 999, 1002)
                .hasSmallerKey(1003).forValues(1004, 1005, 1006, 1007, 1008, 1009, 1010)
                .hasSmallerKey(1010).forValues(1011, 1012)
                .hasSmallerKey(1012).forValues(1013)
                .hasSmallerKey(1013).forValues(1014, 1015, 1016, 1017, 1018, 1019)
                .hasSmallerKey(1019).forValues(1020, 1021, 10_000)
                // greater values
                .hasNoGreaterKey().forValues(1019, 1020, 1021, 10_000)
                .hasGreaterKey(1019).forValues(1013, 1014, 1015, 1016, 1017, 1018)
                .hasGreaterKey(1013).forValues(1012)
                .hasGreaterKey(1012).forValues(1010, 1011)
                .hasGreaterKey(1010).forValues(1003, 1004, 1005, 1006, 1007, 1008, 1009)
                .hasGreaterKey(1003).forValues(1000, 1001, 1002, 71, 41, 10, 8, 9, 3, 1, 0)
                // build test
                .holds()
        );
    }

    @Test
    void testIndexingWithTwoDenseRegions() {
        MultiSetTester.forAllSets(
            assertThat(731, 789, 10_541, 10_542, 10_543).indexing()
                // smaller values
                .hasNoSmallerKey().forValues(731, 730, 720, 429, 192, 93, 51, 43, 20, 10, 2, 1, 0)
                .hasSmallerKey(731).forValues(733, 734, 780, 787, 788, 789)
                .hasSmallerKey(789).forValues(790, 791, 1000, 1011, 1012, 10_241)
                .hasSmallerKey(10_541).forValues(10_542)
                .hasSmallerKey(10_542).forValues(10_543)
                .hasSmallerKey(10_543).forValues(10_544, 10_545, 100_000)
                // greater values
                .hasNoGreaterKey().forValues(10_543, 10_544, 100_000)
                .hasGreaterKey(10_543).forValues(10_542)
                .hasGreaterKey(10_542).forValues(10_541)
                .hasGreaterKey(10_541).forValues(10_540, 10_539, 983, 789)
                .hasGreaterKey(789).forValues(788, 787, 753, 731)
                .hasGreaterKey(731).forValues(730, 730, 720, 429, 192, 93, 51, 43, 20, 10, 2, 1, 0)
                // build test
                .holds()
        );
    }
}
