package collections.ints;

import collections.ints.assertions.Assertion;
import org.junit.jupiter.api.Test;

public class EquivalenceAndSubsetTest {

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [11].
     * The third set is obtained from the second by adding:
     *     [30, 31]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset0() {
        int[] subject = {5, 11, 19, 32};
        int[] subset = {5, 19, 32};
        int[] incomparable = {5, 19, 30, 31, 32};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [12, 25, 28].
     * The third set is obtained from the second by adding:
     *     [8, 19]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset1() {
        int[] subject = {12, 15, 24, 25, 28, 31};
        int[] subset = {15, 24, 31};
        int[] incomparable = {8, 15, 19, 24, 31};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [12, 15, 18].
     * The third set is obtained from the second by adding:
     *     [3, 4]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset2() {
        int[] subject = {12, 15, 18, 23, 25, 32};
        int[] subset = {23, 25, 32};
        int[] incomparable = {3, 4, 23, 25, 32};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [4, 15, 25].
     * The third set is obtained from the second by adding:
     *     [0, 22]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset3() {
        int[] subject = {4, 15, 25};
        int[] subset = {};
        int[] incomparable = {0, 22};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [1, 26, 27].
     * The third set is obtained from the second by adding:
     *     [5, 14, 20, 25]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset4() {
        int[] subject = {1, 26, 27};
        int[] subset = {};
        int[] incomparable = {5, 14, 20, 25};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [15, 30].
     * The third set is obtained from the second by adding:
     *     [23, 54, 62]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset5() {
        int[] subject = {10, 27, 38, 42, 47, 54, 55};
        int[] subset = {27, 42, 54, 55};
        int[] incomparable = {8, 25, 27, 28, 42, 54, 55};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [14].
     * The third set is obtained from the second by adding:
     *     [8, 12, 35, 63]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset6() {
        int[] subject = {14};
        int[] subset = {};
        int[] incomparable = {8, 12, 35, 63};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [42].
     * The third set is obtained from the second by adding:
     *     [41, 48]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset7() {
        int[] subject = {3, 18, 30, 42};
        int[] subset = {3, 18, 30};
        int[] incomparable = {3, 18, 30, 41, 48};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [38, 51, 52].
     * The third set is obtained from the second by adding:
     *     [27, 63]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset8() {
        int[] subject = {17, 36, 38, 47, 49, 51, 52, 55};
        int[] subset = {17, 36, 47, 49, 55};
        int[] incomparable = {17, 27, 36, 47, 49, 55, 63};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [57].
     * The third set is obtained from the second by adding:
     *     [28, 40]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset9() {
        int[] subject = {57};
        int[] subset = {};
        int[] incomparable = {28, 40};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [886, 7370, 7900].
     * The third set is obtained from the second by adding:
     *     [6111, 6315, 6476]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset10() {
        int[] subject = {886, 1250, 7370, 7900};
        int[] subset = {1250};
        int[] incomparable = {1250, 6111, 6315, 6476};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [47, 5202, 6609].
     * The third set is obtained from the second by adding:
     *     [1228, 6192]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset11() {
        int[] subject = {47, 1628, 3550, 3639, 5202, 6609, 7985};
        int[] subset = {1628, 3550, 3639, 7985};
        int[] incomparable = {1228, 1628, 3550, 3639, 6192, 7985};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [2509, 7487].
     * The third set is obtained from the second by adding:
     *     [339, 1837, 8743, 9734]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset12() {
        int[] subject = {687, 2509, 3930, 7487, 9549};
        int[] subset = {687, 3930, 9549};
        int[] incomparable = {339, 687, 1837, 3930, 8743, 9549, 9734};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [909, 5903].
     * The third set is obtained from the second by adding:
     *     [3588, 4032, 6660, 8715]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset13() {
        int[] subject = {716, 909, 3094, 3274, 4880, 5903, 8719};
        int[] subset = {716, 3094, 3274, 4880, 8719};
        int[] incomparable = {716, 3094, 3274, 3588, 4032, 4880, 6660, 8715, 8719};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [1208, 2187].
     * The third set is obtained from the second by adding:
     *     [6055, 6736, 7062]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset14() {
        int[] subject = {1208, 1847, 2187, 7069};
        int[] subset = {1847, 7069};
        int[] incomparable = {1847, 6055, 6736, 7062, 7069};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [7335].
     * The third set is obtained from the second by adding:
     *     [268, 8195]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset15() {
        int[] subject = {1286, 7335};
        int[] subset = {1286};
        int[] incomparable = {268, 1286, 8195};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [903, 1998, 8153].
     * The third set is obtained from the second by adding:
     *     [2161, 8224, 9690]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset16() {
        int[] subject = {903, 1998, 2348, 5492, 6010, 8153};
        int[] subset = {2348, 5492, 6010};
        int[] incomparable = {2161, 2348, 5492, 6010, 8224, 9690};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [2014, 6666].
     * The third set is obtained from the second by adding:
     *     [4686, 6936]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset17() {
        int[] subject = {2014, 2273, 4588, 6666};
        int[] subset = {2273, 4588};
        int[] incomparable = {2273, 4588, 4686, 6936};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [6158].
     * The third set is obtained from the second by adding:
     *     [3043, 4969]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset18() {
        int[] subject = {159, 797, 3472, 4005, 4065, 4078, 4834, 6158, 6173, 6565, 7711, 9665};
        int[] subset = {159, 797, 3472, 4005, 4065, 4078, 4834, 6173, 6565, 7711, 9665};
        int[] incomparable = {159, 797, 3043, 3472, 4005, 4065, 4078, 4834, 4969, 6173, 6565, 7711, 9665};

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [9190].
     * The third set is obtained from the second by adding:
     *     [4438, 4593, 9451]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset19() {
        int[] subject = {746, 1248, 1541, 2311, 2453, 2740, 2843, 3556, 4829, 5808, 6760, 8912, 9190, 9631};
        int[] subset = {746, 1248, 1541, 2311, 2453, 2740, 2843, 3556, 4829, 5808, 6760, 8912, 9631};
        int[] incomparable = {
            746, 1248, 1541, 2311, 2453, 2740, 2843, 3556, 4438, 4593, 4829, 5808, 6760, 8912, 9451, 9631
        };

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [4419, 7995].
     * The third set is obtained from the second by adding:
     *     [3642, 8700, 9038]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset20() {
        int[] subject = {
            45, 333, 590, 657, 732, 855, 1034, 1276, 1379, 1437, 1480, 1547, 1562, 1611, 1660, 1681, 1755, 1801, 2024,
            2167, 2300, 2467, 2950, 3009, 3168, 3183, 3285, 3385, 3728, 3808, 4008, 4064, 4127, 4193, 4212, 4330, 4419,
            4445, 4528, 4579, 4598, 4601, 4659, 4828, 5269, 5386, 5390, 5630, 5682, 5737, 5742, 5898, 5948, 5952, 5981,
            6046, 6199, 6246, 6367, 6641, 6660, 6742, 6877, 6888, 6970, 7180, 7434, 7757, 7830, 7862, 7995, 8106, 8172,
            8425, 8460, 8630, 8660, 8668, 8726, 9075, 9362, 9381, 9570, 9761, 9784, 9944
        };
        int[] subset = {
            45, 333, 590, 657, 732, 855, 1034, 1276, 1379, 1437, 1480, 1547, 1562, 1611, 1660, 1681, 1755, 1801, 2024,
            2167, 2300, 2467, 2950, 3009, 3168, 3183, 3285, 3385, 3728, 3808, 4008, 4064, 4127, 4193, 4212, 4330, 4445,
            4528, 4579, 4598, 4601, 4659, 4828, 5269, 5386, 5390, 5630, 5682, 5737, 5742, 5898, 5948, 5952, 5981, 6046,
            6199, 6246, 6367, 6641, 6660, 6742, 6877, 6888, 6970, 7180, 7434, 7757, 7830, 7862, 8106, 8172, 8425, 8460,
            8630, 8660, 8668, 8726, 9075, 9362, 9381, 9570, 9761, 9784, 9944
        };
        int[] incomparable = {
            45, 333, 590, 657, 732, 855, 1034, 1276, 1379, 1437, 1480, 1547, 1562, 1611, 1660, 1681, 1755, 1801, 2024,
            2167, 2300, 2467, 2950, 3009, 3168, 3183, 3285, 3385, 3642, 3728, 3808, 4008, 4064, 4127, 4193, 4212, 4330,
            4445, 4528, 4579, 4598, 4601, 4659, 4828, 5269, 5386, 5390, 5630, 5682, 5737, 5742, 5898, 5948, 5952, 5981,
            6046, 6199, 6246, 6367, 6641, 6660, 6742, 6877, 6888, 6970, 7180, 7434, 7757, 7830, 7862, 8106, 8172, 8425,
            8460, 8630, 8660, 8668, 8700, 8726, 9038, 9075, 9362, 9381, 9570, 9761, 9784, 9944
        };

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [5807, 7220].
     * The third set is obtained from the second by adding:
     *     [1473, 1524, 5551, 7289]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset21() {
        int[] subject = {
            1308, 1380, 1930, 2413, 3049, 3853, 4274, 5397, 5428, 5807, 5954, 7220, 7894, 9000, 9399, 9847
        };
        int[] subset = {1308, 1380, 1930, 2413, 3049, 3853, 4274, 5397, 5428, 5954, 7894, 9000, 9399, 9847};
        int[] incomparable = {
            1308, 1380, 1473, 1524, 1930, 2413, 3049, 3853, 4274, 5397, 5428, 5551, 5954, 7289, 7894, 9000, 9399, 9847
        };

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [3543, 3912, 4009].
     * The third set is obtained from the second by adding:
     *     [493, 603, 9694, 9723]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset22() {
        int[] subject = {
            592, 734, 1324, 1565, 1751, 2057, 2076, 2077, 2098, 2677, 2798, 3543, 3716, 3806, 3884, 3912, 3989, 4009,
            4033, 4062, 4250, 4292, 4497, 4647, 5048, 5059, 5091, 5153, 5241, 5434, 5663, 5769, 6001, 6296, 6427, 7180,
            7431, 7837, 8352, 8801, 9517, 9686, 9817, 9842, 9907
        };
        int[] subset = {
            592, 734, 1324, 1565, 1751, 2057, 2076, 2077, 2098, 2677, 2798, 3716, 3806, 3884, 3989, 4033, 4062, 4250,
            4292, 4497, 4647, 5048, 5059, 5091, 5153, 5241, 5434, 5663, 5769, 6001, 6296, 6427, 7180, 7431, 7837, 8352,
            8801, 9517, 9686, 9817, 9842, 9907
        };
        int[] incomparable = {
            493, 592, 603, 734, 1324, 1565, 1751, 2057, 2076, 2077, 2098, 2677, 2798, 3716, 3806, 3884, 3989, 4033,
            4062, 4250, 4292, 4497, 4647, 5048, 5059, 5091, 5153, 5241, 5434, 5663, 5769, 6001, 6296, 6427, 7180, 7431,
            7837, 8352, 8801, 9517, 9686, 9694, 9723, 9817, 9842, 9907
        };

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [4369, 6532, 9353].
     * The third set is obtained from the second by adding:
     *     [2195, 3264, 3420, 7140]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset23() {
        int[] subject = {
            143, 330, 633, 713, 1026, 1052, 1173, 1294, 1376, 1663, 1956, 2268, 2602, 2608, 2775, 2946, 2964, 2965,
            3266, 3539, 4111, 4161, 4312, 4369, 4391, 4483, 4486, 4537, 4559, 4763, 4809, 4848, 5787, 6096, 6384, 6532,
            7053, 7092, 7109, 7226, 7868, 8003, 8191, 8219, 8233, 8304, 8338, 8343, 9280, 9312, 9353, 9725, 9870, 9944
        };
        int[] subset = {
            143, 330, 633, 713, 1026, 1052, 1173, 1294, 1376, 1663, 1956, 2268, 2602, 2608, 2775, 2946, 2964, 2965,
            3266, 3539, 4111, 4161, 4312, 4391, 4483, 4486, 4537, 4559, 4763, 4809, 4848, 5787, 6096, 6384, 7053, 7092,
            7109, 7226, 7868, 8003, 8191, 8219, 8233, 8304, 8338, 8343, 9280, 9312, 9725, 9870, 9944
        };
        int[] incomparable = {
            143, 330, 633, 713, 1026, 1052, 1173, 1294, 1376, 1663, 1956, 2195, 2268, 2602, 2608, 2775, 2946, 2964,
            2965, 3264, 3266, 3420, 3539, 4111, 4161, 4312, 4391, 4483, 4486, 4537, 4559, 4763, 4809, 4848, 5787, 6096,
            6384, 7053, 7092, 7109, 7140, 7226, 7868, 8003, 8191, 8219, 8233, 8304, 8338, 8343, 9280, 9312, 9725, 9870,
            9944
        };

        assertTestData(subject, subset, incomparable);
    }

    /*
     * Test consists of three sets. The second is obtained from the first by removing:
     *     [4960, 5170, 7262].
     * The third set is obtained from the second by adding:
     *     [2987, 5670, 8857]
     * Note that these elements are NOT contained in the original set.
     */
    @Test
    void testEquivalentAndSubset24() {
        int[] subject = {
            335, 483, 484, 832, 876, 1327, 1415, 1550, 1642, 1647, 1693, 1846, 1939, 1944, 2063, 2167, 2231, 2557, 2675,
            3321, 3710, 4498, 4610, 4960, 5004, 5133, 5170, 5393, 5822, 6040, 6072, 6091, 6179, 6232, 6254, 6291, 6326,
            6332, 6990, 7137, 7262, 7799, 7838, 7860, 8118, 8194, 8197, 8399, 8710, 8726, 9257, 9305, 9758, 9805, 9857
        };
        int[] subset = {
            335, 483, 484, 832, 876, 1327, 1415, 1550, 1642, 1647, 1693, 1846, 1939, 1944, 2063, 2167, 2231, 2557, 2675,
            3321, 3710, 4498, 4610, 5004, 5133, 5393, 5822, 6040, 6072, 6091, 6179, 6232, 6254, 6291, 6326, 6332, 6990,
            7137, 7799, 7838, 7860, 8118, 8194, 8197, 8399, 8710, 8726, 9257, 9305, 9758, 9805, 9857
        };
        int[] incomparable = {
            335, 483, 484, 832, 876, 1327, 1415, 1550, 1642, 1647, 1693, 1846, 1939, 1944, 2063, 2167, 2231, 2557, 2675,
            2987, 3321, 3710, 4498, 4610, 5004, 5133, 5393, 5670, 5822, 6040, 6072, 6091, 6179, 6232, 6254, 6291, 6326,
            6332, 6990, 7137, 7799, 7838, 7860, 8118, 8194, 8197, 8399, 8710, 8726, 8857, 9257, 9305, 9758, 9805, 9857
        };

        assertTestData(subject, subset, incomparable);
    }

    private static void assertTestData(int[] original, int[] subset, int[] incomparable) {
        MultiSetTester.forAllSets(
            // equivalence check and subset check for each set using same argument twice
            Assertion.assertThat(original).equivalent(original),
            Assertion.assertThat(original).subset(original),
            Assertion.assertThat(subset).equivalent(subset),
            Assertion.assertThat(incomparable).equivalent(incomparable),
            Assertion.assertThat(incomparable).subset(incomparable),
            // the remaining combinations are not equivalent
            Assertion.assertThat(original).notEquivalent(subset),
            Assertion.assertThat(subset).notEquivalent(original),
            Assertion.assertThat(original).notEquivalent(incomparable),
            Assertion.assertThat(incomparable).notEquivalent(original),
            Assertion.assertThat(subset).notEquivalent(incomparable),
            Assertion.assertThat(incomparable).notEquivalent(subset),
            // check subset relation
            Assertion.assertThat(subset).subset(original),
            Assertion.assertThat(original).noSubset(subset),
            Assertion.assertThat(original).noSubset(incomparable),
            Assertion.assertThat(incomparable).noSubset(original),
            Assertion.assertThat(subset).subset(incomparable),
            Assertion.assertThat(incomparable).noSubset(subset)
        );
    }
}
