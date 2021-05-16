package collections.ints;

import collections.ints.assertions.Assertion;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.Mappers;
import util.SimpleBinaryTestCaseGenerator;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class IntersectsTest {

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1() {
        int[] op1 = {33, 35, 39, 42, 50, 51, 52, 61};
        int[] op2 = {};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2() {
        int[] op1 = {};
        int[] op2 = {170, 171, 179, 180, 184, 187, 190, 191};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternInt() {
        int[] op1 = {291, 297, 299, 302, 304, 308, 309, 310, 315, 318};
        int[] op2 = {288, 291, 294, 304, 306, 308, 309, 316, 319};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDis() {
        int[] op1 = {233, 242, 245, 252, 255};
        int[] op2 = {228, 234, 238, 246, 248};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1Op1() {
        int[] op1 = {164, 166, 168, 179, 181, 187, 188, 453, 459, 460, 463, 471, 472, 474, 477};
        int[] op2 = {};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2Op1() {
        int[] op1 = {256, 269, 270, 273, 274, 276, 278, 281};
        int[] op2 = {237, 240, 243, 245, 246, 248, 251, 252};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternIntOp1() {
        int[] op1 = {97, 102, 103, 107, 109, 115, 117, 123, 124, 127, 360, 367, 368, 370, 371, 376, 380};
        int[] op2 = {97, 99, 103, 106, 109, 110, 112, 119, 123, 127};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDisOp1() {
        int[] op1 = {37, 39, 47, 52, 57, 228, 232, 236, 244, 246, 251};
        int[] op2 = {32, 36, 44, 53, 59};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1Op2() {
        int[] op1 = {128, 134, 135, 136, 137, 139, 148, 149};
        int[] op2 = {258, 260, 276, 278, 282, 287};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2Op2() {
        int[] op1 = {};
        int[] op2 = {32, 34, 35, 36, 41, 48, 59, 61, 256, 258, 262, 263, 273, 275, 283};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternIntOp2() {
        int[] op1 = {98, 102, 103, 104, 111, 114, 119, 122, 123, 127};
        int[] op2 = {97, 98, 102, 103, 105, 110, 114, 124, 126, 127, 228, 231, 232, 234, 237, 239, 250, 255};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDisOp2() {
        int[] op1 = {161, 165, 171, 174, 181, 186, 188};
        int[] op2 = {167, 170, 177, 180, 185, 189, 488, 489, 491, 492, 502, 503, 510};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectsPatternOp1Int() {
        int[] op1 = {288, 292, 295, 296, 302, 309, 311, 419, 421, 422, 425, 430, 431, 432, 440, 441};
        int[] op2 = {416, 418, 421, 422, 427, 432, 435, 439, 440, 447};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectsPatternOp2Int() {
        int[] op1 = {448, 451, 458, 461, 463, 468, 470, 472, 474, 477};
        int[] op2 = {
            161, 164, 169, 172, 173, 174, 181, 190, 448, 450, 453, 454, 461, 463, 464, 468, 470, 471, 472, 479
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testIntersectsPatternIntInt() {
        int[] op1 = {290, 292, 295, 300, 301, 302, 305, 309, 314, 318, 576, 577, 583, 584, 587, 588, 590, 607};
        int[] op2 = {
            289, 290, 292, 295, 296, 301, 302, 304, 308, 312, 314, 319, 583, 584, 585, 587, 588, 593, 595, 596, 597, 601
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectsPatternDisInt() {
        int[] op1 = {293, 296, 303, 308, 544, 545, 548, 550, 554, 560, 563, 567, 568, 570, 571, 574};
        int[] op2 = {288, 290, 291, 295, 314, 316, 552, 554, 556, 559, 563, 564, 565, 569, 571, 574};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectsPatternOp1Dis() {
        int[] op1 = {37, 44, 51, 54, 56, 60, 61, 62, 295, 305, 307, 312, 316, 318};
        int[] op2 = {293, 297, 299, 303, 315};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectsPatternOp2Dis() {
        int[] op1 = {419, 420, 426, 434};
        int[] op2 = {129, 133, 137, 143, 150, 152, 155, 424, 433, 440, 441, 446};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testIntersectsPatternIntDis() {
        int[] op1 = {32, 34, 35, 38, 40, 41, 42, 46, 48, 56, 59, 61, 133, 141, 142, 143, 148};
        int[] op2 = {32, 36, 38, 39, 43, 46, 52, 54, 56, 59, 61, 132, 134, 154, 158, 159};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testIntersectsPatternDisDis() {
        int[] op1 = {32, 41, 47, 53, 59, 262, 264, 268, 278, 281};
        int[] op2 = {43, 48, 50, 54, 56, 58, 259, 261, 266, 276, 280, 287};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1Op1Op1() {
        int[] op1 = {
            130, 131, 135, 147, 151, 152, 155, 158, 162, 164, 168, 175, 178, 181, 189, 354, 362, 363, 365, 374, 381, 383
        };
        int[] op2 = {};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2Op1Op1() {
        int[] op1 = {322, 324, 329, 332, 339, 343, 346, 449, 457, 458, 459, 462, 465, 473, 475};
        int[] op2 = {168, 172, 184, 188, 189, 191};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternIntOp1Op1() {
        int[] op1 = {
            130, 132, 133, 134, 138, 141, 143, 151, 152, 155, 289, 295, 297, 300, 305, 317, 512, 517, 518, 520, 524,
            530, 532
        };
        int[] op2 = {128, 132, 133, 136, 138, 141, 146, 149, 150, 151, 155, 159};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDisOp1Op1() {
        int[] op1 = {179, 186, 187, 191, 288, 290, 298, 301, 302, 303, 304, 448, 453, 454, 456, 459, 473, 478, 479};
        int[] op2 = {168, 171, 183, 184, 190};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1Op2Op1() {
        int[] op1 = {1, 3, 14, 15, 19, 26, 29, 195, 200, 201, 210, 211, 220, 222};
        int[] op2 = {163, 165, 169, 171, 179, 181, 182, 187};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2Op2Op1() {
        int[] op1 = {610, 614, 621, 625, 632, 634, 636};
        int[] op2 = {196, 199, 203, 206, 211, 291, 296, 302, 306, 310, 311, 312, 313};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternIntOp2Op1() {
        int[] op1 = {165, 166, 167, 168, 171, 173, 178, 179, 180, 190, 608, 610, 619, 631, 634, 635, 636, 639};
        int[] op2 = {164, 165, 166, 167, 172, 174, 178, 180, 182, 183, 184, 397, 399, 403, 404, 407, 409, 411};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDisOp2Op1() {
        int[] op1 = {4, 8, 12, 28, 129, 131, 134, 137, 147, 148, 155};
        int[] op2 = {2, 15, 20, 27, 64, 72, 73, 75, 77, 81, 92};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectsPatternOp1IntOp1() {
        int[] op1 = {
            97, 100, 103, 107, 118, 121, 123, 163, 164, 165, 169, 171, 178, 181, 184, 185, 186, 188, 448, 456, 459, 464,
            468, 470, 476, 478
        };
        int[] op2 = {169, 171, 173, 175, 180, 181, 182, 184, 186, 190};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectsPatternOp2IntOp1() {
        int[] op1 = {320, 323, 326, 330, 335, 339, 341, 346, 347, 349, 352, 357, 360, 364, 365, 372, 375, 379};
        int[] op2 = {288, 290, 291, 292, 295, 303, 306, 307, 320, 324, 326, 331, 336, 338, 341, 346, 347, 348};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testIntersectsPatternIntIntOp1() {
        int[] op1 = {
            289, 293, 298, 302, 304, 308, 310, 311, 312, 317, 318, 610, 611, 617, 622, 624, 625, 628, 634, 636, 638,
            832, 837, 846, 847, 853, 861, 862
        };
        int[] op2 = {
            288, 289, 290, 291, 293, 302, 308, 316, 317, 318, 319, 609, 610, 612, 615, 621, 624, 628, 629, 630, 631,
            634, 636
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectsPatternDisIntOp1() {
        int[] op1 = {
            98, 110, 112, 114, 124, 166, 167, 168, 171, 175, 176, 180, 182, 187, 191, 387, 391, 392, 393, 395, 400, 401,
            413
        };
        int[] op2 = {97, 101, 106, 109, 111, 113, 160, 166, 170, 171, 174, 175, 178, 180, 190, 191};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectsPatternOp1DisOp1() {
        int[] op1 = {0, 5, 8, 11, 19, 27, 135, 139, 140, 145, 149, 151, 428, 434, 437, 440, 443, 445, 447};
        int[] op2 = {134, 141, 147, 153};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectsPatternOp2DisOp1() {
        int[] op1 = {449, 468, 471, 473, 476, 711, 713, 715, 719, 725, 731, 735};
        int[] op2 = {257, 258, 259, 260, 270, 272, 284, 452, 458, 462, 464, 479};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testIntersectsPatternIntDisOp1() {
        int[] op1 = {
            257, 258, 261, 262, 263, 270, 271, 272, 274, 282, 285, 286, 453, 455, 458, 746, 756, 759, 762, 763, 764, 767
        };
        int[] op2 = {258, 262, 263, 265, 271, 274, 276, 285, 449, 451, 465, 474};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testIntersectsPatternDisDisOp1() {
        int[] op1 = {
            289, 295, 297, 306, 308, 311, 312, 313, 552, 553, 556, 558, 560, 563, 568, 575, 614, 622, 623, 627, 630,
            633, 637
        };
        int[] op2 = {288, 296, 299, 301, 310, 315, 318, 547, 549, 550, 551, 564, 570, 573, 574};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	     	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1Op1Op2() {
        int[] op1 = {259, 270, 271, 276, 283, 285, 286, 287, 576, 579, 583, 589, 590, 594, 602, 607};
        int[] op2 = {833, 838, 839, 844, 847, 854, 857, 861};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	     	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2Op1Op2() {
        int[] op1 = {290, 295, 298, 304, 309, 314};
        int[] op2 = {234, 241, 243, 244, 245, 251, 253, 254, 545, 551, 557, 558, 561, 564, 565, 566};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternIntOp1Op2() {
        int[] op1 = {5, 6, 7, 8, 11, 20, 24, 26, 30, 136, 142, 144, 145, 146, 147, 150, 159};
        int[] op2 = {5, 6, 13, 17, 23, 26, 31, 170, 175, 177, 179, 184, 185, 186, 189};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDisOp1Op2() {
        int[] op1 = {69, 70, 71, 76, 77, 164, 170, 175, 185, 186, 187, 189, 190};
        int[] op2 = {66, 67, 73, 79, 82, 86, 93, 193, 196, 197, 202, 204, 221};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp1Op2Op2() {
        int[] op1 = {64, 75, 78, 80, 89, 90, 92, 94};
        int[] op2 = {96, 105, 107, 110, 111, 115, 122, 125, 420, 421, 423, 435, 444, 445, 446};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----	-----
     *   overlap?
     */
    @Test
    void testIntersectsPatternOp2Op2Op2() {
        int[] op1 = {};
        int[] op2 = {
            161, 163, 166, 167, 168, 172, 184, 293, 297, 298, 300, 301, 303, 309, 310, 418, 419, 420, 424, 431, 441,
            446, 447
        };

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectsPatternIntOp2Op2() {
        int[] op1 = {257, 259, 270, 272, 273, 276, 277, 278, 287};
        int[] op2 = {
            257, 259, 260, 261, 262, 268, 272, 278, 282, 284, 285, 294, 300, 302, 304, 306, 308, 314, 388, 394, 395,
            396, 406, 411, 415
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectsPatternDisOp2Op2() {
        int[] op1 = {224, 238, 240, 241, 242};
        int[] op2 = {
            226, 227, 246, 247, 249, 250, 387, 395, 401, 403, 404, 408, 414, 482, 487, 490, 493, 497, 508, 509
        };

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectsPatternOp1IntOp2() {
        int[] op1 = {263, 272, 273, 279, 286, 287, 513, 517, 527, 528, 529, 532, 533, 539, 540, 541};
        int[] op2 = {517, 520, 524, 527, 528, 529, 531, 539, 541, 711, 712, 716, 717, 718, 732, 733, 735};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectsPatternOp2IntOp2() {
        int[] op1 = {192, 193, 196, 198, 201, 207, 210, 211, 218, 221};
        int[] op2 = {
            100, 101, 103, 111, 113, 117, 118, 123, 192, 196, 198, 201, 206, 213, 216, 217, 223, 290, 303, 305, 311,
            315, 316, 319
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testIntersectsPatternIntIntOp2() {
        int[] op1 = {0, 4, 5, 7, 8, 9, 11, 17, 24, 25, 28, 38, 39, 41, 47, 55, 56, 58, 59, 61, 62};
        int[] op2 = {
            4, 5, 7, 9, 10, 11, 13, 14, 16, 25, 36, 38, 39, 43, 51, 53, 55, 57, 59, 62, 193, 196, 202, 208, 210, 213,
            220
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectsPatternDisIntOp2() {
        int[] op1 = {173, 180, 181, 186, 187, 188, 353, 355, 356, 362, 366, 367, 377, 378, 380, 383};
        int[] op2 = {
            163, 167, 171, 175, 178, 179, 183, 189, 353, 355, 356, 357, 365, 371, 374, 376, 380, 580, 584, 588, 598,
            600, 602, 604, 606
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectsPatternOp1DisOp2() {
        int[] op1 = {193, 197, 207, 210, 214, 215, 218, 456, 461, 463, 464, 469, 478};
        int[] op2 = {449, 451, 454, 475, 653, 655, 657, 660, 667, 668};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectsPatternOp2DisOp2() {
        int[] op1 = {290, 293, 295, 309, 311};
        int[] op2 = {
            166, 167, 168, 172, 175, 179, 184, 300, 310, 313, 316, 317, 515, 516, 520, 521, 524, 526, 529, 543
        };

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testIntersectsPatternIntDisOp2() {
        int[] op1 = {3, 8, 10, 13, 18, 19, 24, 28, 29, 30, 224, 246, 248, 254};
        int[] op2 = {3, 8, 9, 13, 16, 18, 21, 23, 24, 28, 29, 230, 231, 235, 244, 251, 455, 457, 459, 466, 471, 473};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testIntersectsPatternDisDisOp2() {
        int[] op1 = {204, 208, 211, 213, 214, 216, 223, 359, 367, 372, 373, 376, 381};
        int[] op2 = {195, 199, 205, 210, 215, 353, 356, 360, 370, 377, 547, 562, 564, 565, 568, 569, 570, 573};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectsPatternOp1Op1Int() {
        int[] op1 = {
            65, 67, 69, 75, 86, 89, 130, 132, 146, 151, 154, 156, 158, 192, 193, 195, 201, 203, 206, 207, 208, 216, 220,
            223
        };
        int[] op2 = {192, 193, 199, 200, 201, 207, 208, 211, 213, 217, 220, 222};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectsPatternOp2Op1Int() {
        int[] op1 = {385, 391, 395, 397, 398, 400, 402, 410, 708, 710, 711, 713, 714, 718, 719, 723, 732};
        int[] op2 = {67, 71, 74, 81, 84, 86, 710, 718, 719, 724, 725, 726, 731, 732};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testIntersectsPatternIntOp1Int() {
        int[] op1 = {
            192, 195, 196, 197, 198, 204, 211, 212, 213, 217, 220, 384, 385, 400, 401, 403, 409, 414, 415, 416, 417,
            421, 422, 423, 424, 426, 428, 429, 431, 437
        };
        int[] op2 = {
            192, 197, 199, 200, 204, 205, 207, 212, 214, 221, 419, 421, 422, 423, 426, 429, 431, 437, 438, 442, 444
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testIntersectsPatternDisOp1Int() {
        int[] op1 = {
            4, 7, 15, 20, 21, 25, 64, 65, 71, 73, 79, 93, 357, 358, 362, 365, 366, 368, 369, 372, 379, 381, 382
        };
        int[] op2 = {3, 6, 13, 17, 22, 28, 353, 355, 359, 362, 365, 368, 369, 371, 376, 379};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectsPatternOp1Op2Int() {
        int[] op1 = {290, 291, 297, 298, 303, 306, 309, 640, 647, 651, 653, 656, 660, 661, 664, 668};
        int[] op2 = {582, 583, 587, 590, 598, 603, 604, 605, 640, 649, 651, 653, 655, 660, 663, 664, 666};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectsPatternOp2Op2Int() {
        int[] op1 = {384, 386, 387, 392, 393, 394, 395, 396, 407, 410, 413};
        int[] op2 = {
            160, 162, 164, 170, 174, 182, 184, 188, 323, 324, 325, 326, 333, 334, 335, 351, 386, 387, 388, 390, 393,
            394, 396, 397, 407, 413
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testIntersectsPatternIntOp2Int() {
        int[] op1 = {
            288, 290, 292, 297, 299, 301, 303, 304, 307, 310, 933, 938, 944, 946, 950, 951, 954, 955, 957, 958
        };
        int[] op2 = {
            288, 291, 297, 299, 303, 305, 307, 309, 310, 311, 609, 617, 622, 629, 633, 635, 638, 928, 930, 934, 936,
            939, 942, 946, 950, 951, 955, 957
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testIntersectsPatternDisOp2Int() {
        int[] op1 = {165, 166, 175, 181, 737, 743, 748, 752, 758, 759, 760, 761, 762};
        int[] op2 = {
            179, 180, 184, 449, 450, 452, 455, 459, 461, 474, 479, 736, 740, 743, 748, 752, 758, 760, 766, 767
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testIntersectsPatternOp1IntInt() {
        int[] op1 = {
            229, 230, 231, 237, 239, 247, 251, 254, 418, 421, 426, 430, 432, 434, 438, 440, 442, 445, 480, 481, 485,
            489, 502, 504, 509, 510, 511
        };
        int[] op2 = {417, 418, 421, 426, 431, 438, 439, 440, 445, 481, 490, 493, 494, 502, 507, 508, 509, 510};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testIntersectsPatternOp2IntInt() {
        int[] op1 = {
            418, 420, 421, 423, 427, 432, 437, 439, 441, 442, 447, 672, 676, 680, 684, 686, 688, 692, 694, 697, 698, 700
        };
        int[] op2 = {
            290, 297, 299, 309, 313, 317, 318, 417, 418, 420, 427, 430, 434, 445, 446, 447, 672, 676, 679, 680, 692,
            694, 695, 696, 697, 698, 703
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	 yes
     */
    @Test
    void testIntersectsPatternIntIntInt() {
        int[] op1 = {
            33, 45, 46, 49, 50, 51, 53, 59, 61, 226, 227, 228, 229, 234, 241, 246, 248, 250, 252, 254, 394, 400, 402,
            403, 405, 407, 408, 410, 412
        };
        int[] op2 = {
            33, 37, 42, 43, 46, 49, 50, 52, 57, 58, 61, 226, 229, 230, 240, 242, 247, 248, 250, 253, 254, 384, 385, 386,
            391, 392, 400, 402, 403, 405, 407
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	 yes
     */
    @Test
    void testIntersectsPatternDisIntInt() {
        int[] op1 = {
            296, 297, 303, 304, 311, 315, 515, 516, 518, 522, 528, 530, 531, 534, 536, 537, 540, 642, 645, 646, 647,
            652, 653, 654, 659, 667
        };
        int[] op2 = {
            289, 294, 295, 308, 310, 312, 512, 514, 518, 520, 522, 526, 534, 535, 536, 540, 640, 642, 643, 645, 646,
            647, 652, 654
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testIntersectsPatternOp1DisInt() {
        int[] op1 = {
            129, 137, 150, 151, 153, 155, 289, 291, 303, 304, 309, 314, 512, 514, 520, 521, 525, 528, 530, 532, 539, 542
        };
        int[] op2 = {294, 295, 300, 305, 307, 513, 514, 520, 521, 528, 530, 531, 535, 542, 543};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testIntersectsPatternOp2DisInt() {
        int[] op1 = {293, 294, 306, 308, 311, 319, 513, 514, 515, 518, 525, 527, 529, 530, 532, 533, 541};
        int[] op2 = {
            100, 102, 103, 107, 113, 116, 123, 126, 289, 291, 292, 298, 304, 315, 514, 515, 516, 522, 525, 526, 528,
            529, 532, 533, 536
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	 yes
     */
    @Test
    void testIntersectsPatternIntDisInt() {
        int[] op1 = {
            256, 257, 261, 262, 264, 274, 279, 280, 281, 283, 285, 389, 396, 400, 403, 404, 413, 672, 675, 680, 684,
            689, 693, 696, 697, 699, 700
        };
        int[] op2 = {
            260, 264, 268, 272, 273, 280, 283, 285, 385, 387, 398, 402, 410, 672, 675, 681, 682, 684, 685, 691, 697,
            698, 700
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	 yes
     */
    @Test
    void testIntersectsPatternDisDisInt() {
        int[] op1 = {70, 79, 82, 266, 270, 271, 453, 454, 456, 460, 462, 463, 477, 478, 479};
        int[] op2 = {75, 81, 87, 92, 262, 268, 272, 278, 280, 449, 450, 456, 457, 460, 462, 463, 465, 470, 478};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectsPatternOp1Op1Dis() {
        int[] op1 = {
            292, 293, 295, 304, 305, 312, 313, 317, 327, 328, 329, 331, 335, 347, 351, 357, 358, 359, 366, 367, 382
        };
        int[] op2 = {354, 369, 376, 379, 380};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectsPatternOp2Op1Dis() {
        int[] op1 = {67, 69, 79, 82, 88, 91, 95, 354, 357, 358, 363, 382};
        int[] op2 = {1, 5, 10, 20, 23, 25, 27, 352, 367, 372, 375, 379};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testIntersectsPatternIntOp1Dis() {
        int[] op1 = {
            161, 162, 164, 169, 171, 174, 175, 180, 181, 184, 189, 192, 196, 207, 208, 212, 213, 217, 288, 289, 299,
            304, 319
        };
        int[] op2 = {160, 161, 162, 164, 167, 169, 170, 173, 174, 180, 181, 298, 300, 309, 314};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testIntersectsPatternDisOp1Dis() {
        int[] op1 = {79, 80, 86, 90, 94, 99, 108, 118, 119, 120, 124, 125, 127, 227, 236, 237, 251, 254};
        int[] op2 = {65, 70, 75, 76, 228, 230, 233, 243, 255};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectsPatternOp1Op2Dis() {
        int[] op1 = {79, 86, 87, 88, 89, 91, 92, 95, 384, 387, 396, 405, 406};
        int[] op2 = {256, 260, 261, 263, 264, 271, 279, 280, 385, 389, 399};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectsPatternOp2Op2Dis() {
        int[] op1 = {577, 578, 594, 598, 599, 601};
        int[] op2 = {161, 165, 166, 167, 176, 178, 185, 449, 453, 465, 470, 474, 476, 580, 583, 584, 597};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testIntersectsPatternIntOp2Dis() {
        int[] op1 = {226, 227, 228, 235, 240, 243, 249, 250, 254, 255, 546, 553, 555, 563, 571};
        int[] op2 = {
            224, 227, 228, 230, 231, 232, 235, 236, 237, 241, 242, 250, 384, 388, 394, 396, 397, 402, 409, 415, 544,
            545, 551, 558
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testIntersectsPatternDisOp2Dis() {
        int[] op1 = {256, 261, 263, 269, 287, 774, 782, 783, 786};
        int[] op2 = {258, 264, 265, 272, 276, 283, 482, 484, 485, 487, 502, 507, 773, 777, 795, 796, 797};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testIntersectsPatternOp1IntDis() {
        int[] op1 = {0, 1, 2, 12, 13, 15, 17, 25, 225, 226, 227, 234, 243, 245, 248, 249, 250, 253, 320, 329, 332, 339};
        int[] op2 = {224, 225, 227, 230, 234, 242, 244, 249, 253, 255, 331, 335, 343, 345, 349};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testIntersectsPatternOp2IntDis() {
        int[] op1 = {385, 386, 388, 391, 397, 403, 409, 414, 415, 487, 491, 497, 500, 501, 505, 506};
        int[] op2 = {
            256, 257, 259, 261, 267, 270, 281, 285, 385, 386, 392, 398, 399, 401, 402, 403, 406, 409, 415, 482, 483, 490
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	empty
     */
    @Test
    void testIntersectsPatternIntIntDis() {
        int[] op1 = {
            33, 39, 43, 44, 48, 51, 53, 55, 58, 75, 77, 82, 84, 86, 87, 89, 90, 94, 95, 227, 231, 232, 241, 252
        };
        int[] op2 = {33, 35, 42, 49, 50, 51, 58, 63, 67, 70, 72, 81, 82, 84, 86, 87, 89, 90, 92, 95, 229, 233, 245};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	empty
     */
    @Test
    void testIntersectsPatternDisIntDis() {
        int[] op1 = {9, 10, 17, 24, 26, 28, 64, 69, 70, 72, 73, 76, 79, 82, 87, 93, 326, 328, 333, 345, 346};
        int[] op2 = {0, 2, 3, 5, 27, 29, 69, 71, 72, 74, 75, 76, 82, 85, 87, 90, 327, 330, 331, 338, 343};

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testIntersectsPatternOp1DisDis() {
        int[] op1 = {35, 42, 45, 46, 48, 54, 56, 265, 269, 274, 276, 287, 449, 456, 464, 468, 473, 477};
        int[] op2 = {256, 259, 264, 280, 282, 448, 461, 462, 470, 476};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testIntersectsPatternOp2DisDis() {
        int[] op1 = {514, 516, 523, 528, 532, 533, 737, 739, 744, 746, 761};
        int[] op2 = {293, 296, 301, 303, 304, 307, 313, 317, 515, 518, 519, 521, 534, 539, 736, 741, 743, 752, 766};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	empty
     */
    @Test
    void testIntersectsPatternIntDisDis() {
        int[] op1 = {
            288, 290, 293, 294, 296, 300, 306, 307, 308, 312, 315, 513, 514, 521, 525, 538, 542, 768, 773, 776, 777,
            787, 799
        };
        int[] op2 = {
            288, 293, 294, 299, 300, 301, 306, 307, 308, 310, 316, 512, 516, 522, 529, 533, 537, 771, 779, 781, 783, 784
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	empty
     */
    @Test
    void testIntersectsPatternDisDisDis() {
        int[] op1 = {193, 212, 214, 219, 220, 223, 260, 271, 281, 282, 386, 392, 409, 411};
        int[] op2 = {195, 199, 208, 209, 213, 222, 264, 276, 277, 285, 395, 401, 402, 413};

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----
     *      set2:   -----	     	-----	     	-----	     	-----
     *   overlap?    yes 	     	 yes 	     	 yes
     */
    @Test
    void testIntersectsPatternIntOp1IntOp1IntOp1Op2() {
        int[] op1 = {
            32, 37, 42, 43, 51, 52, 56, 59, 62, 63, 360, 362, 366, 369, 375, 380, 609, 611, 617, 619, 621, 628, 629,
            633, 634, 637, 639, 904, 908, 909, 910, 911, 921, 922, 1091, 1092, 1095, 1101, 1102, 1106, 1113, 1115, 1117,
            1126, 1127, 1130, 1138, 1141, 1142, 1149, 1151
        };
        int[] op2 = {
            32, 35, 37, 38, 50, 51, 52, 60, 63, 611, 617, 620, 621, 627, 628, 631, 633, 634, 636, 1090, 1091, 1092,
            1093, 1095, 1096, 1101, 1109, 1114, 1115, 1117, 1344, 1351, 1353, 1354, 1362, 1363, 1365
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	     	-----	-----
     *      set2:   -----	     	-----	-----	-----	-----
     *   overlap?    yes 	     	 yes 	empty	     	empty
     */
    @Test
    void testIntersectsPatternIntOp1IntDisOp2DisOp1() {
        int[] op1 = {
            227, 229, 237, 240, 241, 243, 245, 246, 248, 251, 252, 453, 459, 462, 467, 475, 478, 577, 578, 580, 581,
            586, 587, 591, 600, 603, 606, 904, 909, 919, 1344, 1345, 1346, 1352, 1366, 1371, 1375, 1537, 1539, 1542,
            1544, 1556, 1565
        };
        int[] op2 = {
            224, 227, 229, 233, 237, 238, 240, 241, 242, 244, 246, 249, 579, 580, 581, 583, 584, 586, 587, 592, 595,
            601, 606, 897, 899, 917, 918, 1026, 1030, 1036, 1040, 1043, 1051, 1353, 1355, 1356, 1358, 1365, 1370
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----	-----
     *      set2:        	-----	-----	     	-----	-----
     *   overlap?        	 yes 	empty	     	empty	empty
     */
    @Test
    void testIntersectsPatternOp1IntDisOp1DisDisOp1() {
        int[] op1 = {
            168, 172, 184, 188, 189, 191, 354, 356, 361, 362, 364, 366, 371, 375, 377, 378, 640, 642, 643, 646, 960,
            963, 974, 976, 979, 984, 1030, 1042, 1046, 1047, 1048, 1050, 1052, 1217, 1218, 1226, 1229, 1230, 1234, 1245,
            1255, 1264, 1267, 1269, 1271, 1274
        };
        int[] op2 = {
            353, 361, 362, 363, 364, 366, 369, 377, 379, 641, 653, 656, 664, 666, 1027, 1034, 1038, 1039, 1040, 1043,
            1049, 1224, 1225, 1227, 1235, 1238, 1239, 1241, 1244
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----	-----
     *      set2:   -----	-----	     	     	-----	     	-----
     *   overlap?    yes 	 yes 	     	     	empty	     	empty
     */
    @Test
    void testIntersectsPatternIntIntOp1Op1DisOp1Dis() {
        int[] op1 = {
            3, 5, 6, 11, 16, 17, 21, 25, 26, 28, 29, 133, 134, 140, 143, 144, 147, 150, 151, 152, 158, 160, 171, 176,
            187, 189, 449, 450, 463, 466, 468, 472, 473, 475, 611, 613, 627, 632, 833, 834, 841, 844, 847, 862, 1027,
            1031, 1038, 1043, 1044
        };
        int[] op2 = {
            1, 5, 6, 7, 8, 11, 13, 24, 28, 29, 129, 133, 134, 135, 144, 146, 150, 151, 159, 618, 620, 629, 630, 636,
            1030, 1035, 1040, 1041, 1046, 1047, 1048
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	     	-----	-----
     *   overlap?        	     	empty	 yes 	     	 yes
     */
    @Test
    void testIntersectsPatternOp2Op2DisIntOp1IntOp2() {
        int[] op1 = {
            709, 718, 721, 724, 898, 901, 902, 904, 911, 915, 919, 920, 921, 923, 925, 1089, 1094, 1097, 1102, 1103,
            1113, 1117, 1119, 1250, 1251, 1253, 1256, 1257, 1259, 1260, 1268, 1270, 1273
        };
        int[] op2 = {
            257, 261, 267, 268, 274, 276, 280, 282, 576, 577, 578, 582, 599, 600, 603, 605, 715, 720, 730, 733, 734,
            901, 902, 909, 912, 913, 916, 919, 920, 922, 923, 925, 926, 1250, 1251, 1253, 1254, 1258, 1259, 1260, 1261,
            1267, 1268, 1269, 1274, 1347, 1348, 1354, 1357, 1360, 1365, 1372
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----	     	-----	-----
     *      set2:   -----	-----	     	-----	     	-----	-----	-----
     *   overlap?   empty	 yes 	     	empty	     	empty	     	empty
     */
    @Test
    void testIntersectsPatternDisIntOp1DisOp1DisOp2DisOp1() {
        int[] op1 = {
            132, 133, 137, 139, 144, 145, 258, 259, 260, 265, 270, 273, 274, 278, 279, 280, 286, 287, 544, 545, 546,
            568, 570, 573, 574, 608, 612, 616, 619, 626, 637, 639, 642, 644, 652, 658, 660, 664, 668, 670, 750, 755,
            766, 1056, 1059, 1063, 1069, 1074, 1084, 1085, 1280, 1290, 1293, 1297, 1307, 1308, 1311
        };
        int[] op2 = {
            129, 131, 138, 146, 147, 158, 159, 256, 263, 265, 270, 271, 274, 277, 279, 281, 285, 286, 611, 620, 621,
            631, 633, 635, 636, 744, 753, 756, 758, 760, 867, 881, 886, 887, 888, 890, 894, 1058, 1060, 1062, 1066,
            1067, 1077, 1081, 1082
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	     	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	-----	-----	-----	     	-----
     *   overlap?        	empty	empty	     	empty	empty	empty
     */
    @Test
    void testIntersectsPatternOp2DisDisOp2DisDisDisOp1Op2() {
        int[] op1 = {
            226, 230, 244, 246, 247, 385, 398, 403, 405, 410, 412, 551, 555, 557, 558, 573, 771, 778, 784, 792, 1032,
            1033, 1036, 1039, 1045, 1053, 1216, 1220, 1228, 1234, 1235, 1236, 1244, 1246
        };
        int[] op2 = {
            128, 129, 139, 153, 158, 159, 234, 241, 242, 248, 388, 389, 401, 404, 408, 486, 487, 490, 491, 499, 507,
            509, 511, 548, 552, 559, 563, 568, 574, 777, 783, 787, 788, 799, 1029, 1034, 1040, 1043, 1049, 1054, 1349,
            1356, 1362, 1364, 1367, 1371
        };

        assertDoesNotIntersect(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----	-----	-----
     *      set2:   -----	-----	     	     	     	-----	-----	     	-----
     *   overlap?   empty	empty	     	     	     	empty	 yes
     */
    @Test
    void testIntersectsPatternDisDisOp1Op1Op1DisIntOp1Op2() {
        int[] op1 = {
            229, 241, 244, 246, 515, 518, 522, 531, 534, 540, 612, 618, 625, 626, 627, 633, 634, 635, 930, 933, 941,
            944, 946, 949, 952, 1216, 1219, 1236, 1241, 1243, 1247, 1280, 1284, 1294, 1304, 1307, 1604, 1610, 1612,
            1613, 1615, 1617, 1622, 1625, 1628, 1630, 1925, 1934, 1938, 1940, 1941, 1942, 1949, 1950
        };
        int[] op2 = {
            227, 234, 240, 247, 249, 253, 254, 519, 520, 526, 530, 1282, 1289, 1292, 1295, 1296, 1303, 1607, 1613, 1614,
            1617, 1619, 1620, 1622, 1625, 1628, 1629, 1631, 2087, 2091, 2097, 2098, 2101, 2103, 2105, 2109
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	     	-----	     	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	-----	     	-----	-----
     *   overlap?   empty	     	     	 yes 	     	     	empty	 yes
     */
    @Test
    void testIntersectsPatternDisOp2Op2IntOp2Op1DisIntOp1() {
        int[] op1 = {
            295, 300, 301, 304, 306, 307, 319, 608, 609, 617, 620, 621, 623, 624, 625, 633, 636, 993, 996, 997, 998,
            1004, 1013, 1014, 1040, 1043, 1050, 1054, 1088, 1095, 1096, 1098, 1099, 1106, 1108, 1109, 1112, 1114, 1115,
            1116, 1346, 1349, 1354, 1355, 1359, 1362, 1367
        };
        int[] op2 = {
            293, 296, 298, 299, 303, 311, 317, 324, 325, 326, 331, 334, 337, 343, 347, 454, 457, 458, 459, 462, 466,
            474, 611, 612, 616, 617, 624, 625, 630, 631, 632, 633, 637, 897, 899, 902, 909, 911, 919, 926, 927, 1038,
            1042, 1048, 1092, 1093, 1094, 1096, 1098, 1099, 1109, 1114, 1115, 1118
        };

        assertIntersects(op1, op2);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	     	-----	-----	     	-----	-----
     *      set2:        	     	-----	-----	     	-----	-----	-----
     *   overlap?        	     	 yes 	     	     	 yes 	     	empty
     */
    @Test
    void testIntersectsPatternOp1Op1IntOp2Op1IntOp2DisOp1() {
        int[] op1 = {
            2, 13, 15, 16, 21, 22, 28, 29, 98, 106, 109, 117, 119, 124, 127, 228, 232, 235, 238, 240, 249, 250, 801,
            802, 803, 814, 816, 825, 827, 830, 1025, 1028, 1030, 1031, 1038, 1041, 1048, 1050, 1051, 1054, 1250, 1254,
            1256, 1258, 1261, 1276, 1278, 1441, 1442, 1454, 1455, 1456, 1463, 1465, 1468
        };
        int[] op2 = {
            225, 228, 231, 232, 233, 240, 244, 249, 252, 253, 545, 551, 552, 553, 559, 566, 569, 1024, 1025, 1028, 1032,
            1034, 1035, 1038, 1039, 1041, 1048, 1051, 1054, 1152, 1153, 1154, 1156, 1163, 1167, 1173, 1174, 1255, 1257,
            1263, 1271, 1272, 1273
        };

        assertIntersects(op1, op2);
    }

    @Test
    void testAllPresetCasesWithRandomValues() {
        long randomSeed = new SecureRandom().nextLong();
        SimpleBinaryTestCaseGenerator.TestCase[] testCases = new SimpleBinaryTestCaseGenerator().generateAllPresets(
            randomSeed);
        for (SimpleBinaryTestCaseGenerator.TestCase testCase : testCases) {
            assertIntersects(testCase);
        }
    }

    @Test
    @Disabled("Test can be enabled and run until failure to find bugs with random data.")
    void intersectsBugCatcher() {
        Random random = new SecureRandom();
        long randomSeed = random.nextLong();
        int numberOfClusters = random.nextInt(20);
        SimpleBinaryTestCaseGenerator.TestCase testCase = new SimpleBinaryTestCaseGenerator().generateRandomCase(
            randomSeed, numberOfClusters);
        assertIntersects(testCase);
    }

    private static void assertIntersects(SimpleBinaryTestCaseGenerator.TestCase testCase) {
        int[] operand1 = testCase.getOperand1();
        int[] operand2 = testCase.getOperand2();
        boolean expectedIntersects = inferIntersects(operand1, operand2);
        if (expectedIntersects) {
            assertIntersects(operand1, operand2);
        } else {
            assertDoesNotIntersect(operand1, operand2);
        }
    }

    private static void assertIntersects(int[] op1, int[] op2) {
        Assertion op1AsSubject = Assertion.assertThat(op1);
        Assertion op2AsSubject = Assertion.assertThat(op1);
        MultiSetTester.forAllSets(
            op1AsSubject.intersects(op2),
            op2AsSubject.intersects(op1)
        );
    }

    private static void assertDoesNotIntersect(int[] op1, int[] op2) {
        Assertion op1AsSubject = Assertion.assertThat(op1);
        Assertion op2AsSubject = Assertion.assertThat(op2);
        MultiSetTester.forAllSets(
            op1AsSubject.doesNotIntersect(op2),
            op2AsSubject.doesNotIntersect(op1)
        );
    }

    private static boolean inferIntersects(int[] set1, int[] set2) {
        TreeSet<Integer> treeSet1 = Mappers.toTreeSet(set1);
        TreeSet<Integer> treeSet2 = Mappers.toTreeSet(set2);
        treeSet1.retainAll(treeSet2);
        return !treeSet1.isEmpty();
    }
}
