package collections.ints;

import collections.ints.assertions.Assertion;
import collections.ints.assertions.Statement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.Mappers;
import util.SimpleBinaryTestCaseGenerator;
import util.SimpleBinaryTestCaseGenerator.TestCase;

import java.security.SecureRandom;
import java.util.Random;
import java.util.TreeSet;

public class IntersectionTest {

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1() {
        int[] op1 = {36, 38, 41, 47, 55, 56, 57};
        int[] op2 = {};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2() {
        int[] op1 = {};
        int[] op2 = {194, 205, 209, 211, 214, 217, 219};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternInt() {
        int[] op1 = {227, 231, 239, 240, 246, 249, 252, 254, 255};
        int[] op2 = {227, 232, 239, 241, 243, 246, 249, 250, 253, 254};
        int[] intersection = {227, 239, 246, 249, 254};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDis() {
        int[] op1 = {262, 268, 272, 276, 283};
        int[] op2 = {259, 261, 264, 269, 274, 284};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1Op1() {
        int[] op1 = {261, 263, 266, 267, 268, 269, 285, 288, 292, 300, 307, 310, 316};
        int[] op2 = {};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op1() {
        int[] op1 = {193, 198, 199, 200, 203, 218, 223};
        int[] op2 = {161, 165, 169, 171, 176, 181, 188, 191};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternIntOp1() {
        int[] op1 = {227, 231, 233, 237, 239, 240, 251, 255, 480, 487, 489, 490, 491, 496, 497, 506};
        int[] op2 = {224, 225, 231, 233, 237, 240, 251, 252, 253};
        int[] intersection = {231, 233, 237, 240, 251};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDisOp1() {
        int[] op1 = {194, 204, 205, 209, 211, 213, 214, 222, 326, 327, 328, 331, 332, 338, 340};
        int[] op2 = {198, 206, 207, 219, 220, 223};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1Op2() {
        int[] op1 = {194, 197, 198, 201, 208, 212, 218};
        int[] op2 = {420, 424, 426, 430, 433, 440};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op2() {
        int[] op1 = {};
        int[] op2 = {101, 102, 111, 113, 115, 117, 123, 419, 421, 426, 429, 431, 436, 441, 447};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternIntOp2() {
        int[] op1 = {2, 5, 7, 11, 12, 17, 18, 19, 26, 28, 31};
        int[] op2 = {2, 6, 8, 11, 17, 18, 20, 24, 26, 105, 108, 110, 117, 118, 119, 123, 126};
        int[] intersection = {2, 11, 17, 18, 26};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDisOp2() {
        int[] op1 = {203, 204, 205, 209, 217};
        int[] op2 = {194, 197, 199, 206, 208, 214, 485, 488, 491, 492, 497, 500, 502, 505};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectionPatternOp1Int() {
        int[] op1 = {224, 230, 234, 241, 249, 250, 255, 544, 547, 552, 553, 559, 562, 563, 565, 566, 569, 572, 575};
        int[] op2 = {545, 547, 549, 552, 557, 559, 561, 566, 572, 575};
        int[] intersection = {547, 552, 559, 566, 572, 575};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectionPatternOp2Int() {
        int[] op1 = {224, 225, 227, 228, 229, 232, 234, 235, 238, 241, 253};
        int[] op2 = {0, 4, 8, 15, 17, 20, 23, 227, 228, 231, 234, 236, 241, 243, 248, 251, 252, 253};
        int[] intersection = {227, 228, 234, 241, 253};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testIntersectionPatternIntInt() {
        int[] op1 = {64, 65, 68, 70, 71, 72, 73, 76, 82, 83, 90, 95, 160, 162, 163, 168, 170, 178, 180, 185, 187, 191};
        int[] op2 = {64, 70, 71, 72, 73, 76, 86, 92, 93, 95, 160, 162, 164, 166, 168, 174, 180, 182, 186, 191};
        int[] intersection = {64, 70, 71, 72, 73, 76, 95, 160, 162, 168, 180, 191};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectionPatternDisInt() {
        int[] op1 = {97, 101, 111, 112, 115, 124, 418, 419, 422, 425, 430, 431, 435, 438, 439, 440, 441, 445};
        int[] op2 = {100, 117, 119, 120, 127, 417, 418, 421, 422, 426, 431, 439, 441, 442, 447};
        int[] intersection = {418, 422, 431, 439, 441};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp1Dis() {
        int[] op1 = {0, 5, 12, 17, 22, 28, 262, 264, 265, 268, 274, 280, 283};
        int[] op2 = {257, 258, 271, 278, 279, 282};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp2Dis() {
        int[] op1 = {106, 111, 114, 124, 127};
        int[] op2 = {32, 35, 38, 41, 53, 61, 109, 116, 118, 123, 125};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testIntersectionPatternIntDis() {
        int[] op1 = {68, 70, 71, 72, 74, 82, 84, 86, 354, 356, 359, 370, 377, 379};
        int[] op2 = {68, 76, 77, 78, 80, 81, 82, 84, 85, 91, 362, 363, 368, 383};
        int[] intersection = {68, 82, 84};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testIntersectionPatternDisDis() {
        int[] op1 = {289, 294, 298, 316, 317, 319, 385, 392, 396, 398, 402, 404, 406, 410};
        int[] op2 = {296, 299, 301, 302, 303, 308, 389, 395, 401, 407, 412, 415};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1Op1Op1() {
        int[] op1 = {
            224, 232, 234, 235, 236, 237, 239, 262, 264, 267, 269, 270, 285, 286, 287, 290, 292, 294, 295, 297, 317, 319
        };
        int[] op2 = {};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op1Op1() {
        int[] op1 = {320, 335, 337, 338, 349, 351, 645, 648, 650, 652, 653, 657, 659, 662};
        int[] op2 = {289, 293, 294, 298, 307, 308, 310, 313};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternIntOp1Op1() {
        int[] op1 = {
            0, 1, 6, 13, 14, 21, 23, 29, 30, 31, 257, 266, 269, 273, 275, 282, 286, 576, 577, 586, 590, 594, 598, 600,
            603
        };
        int[] op2 = {2, 4, 6, 13, 14, 22, 26, 29, 30};
        int[] intersection = {6, 13, 14, 29, 30};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDisOp1Op1() {
        int[] op1 = {1, 3, 8, 23, 228, 229, 245, 249, 251, 255, 352, 355, 356, 357, 360, 362, 374, 383};
        int[] op2 = {6, 9, 22, 24};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1Op2Op1() {
        int[] op1 = {32, 34, 40, 43, 58, 59, 60, 355, 361, 367, 368, 369, 371, 373, 380};
        int[] op2 = {100, 108, 111, 113, 115, 120, 123};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op2Op1() {
        int[] op1 = {480, 485, 495, 499, 501, 509, 511};
        int[] op2 = {64, 65, 73, 78, 84, 89, 93, 259, 264, 266, 267, 278, 279, 280, 284};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternIntOp2Op1() {
        int[] op1 = {193, 198, 202, 204, 205, 206, 209, 212, 215, 216, 643, 645, 654, 655, 659, 661, 667};
        int[] op2 = {193, 204, 209, 213, 216, 217, 219, 222, 223, 451, 456, 461, 463, 464, 465, 466, 475};
        int[] intersection = {193, 204, 209, 216};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDisOp2Op1() {
        int[] op1 = {166, 172, 183, 189, 190, 481, 488, 490, 491, 494, 497, 509};
        int[] op2 = {171, 173, 178, 184, 185, 188, 227, 229, 235, 236, 238, 241, 242, 251};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectionPatternOp1IntOp1() {
        int[] op1 = {
            226, 229, 235, 239, 243, 246, 252, 546, 550, 551, 557, 559, 561, 562, 569, 570, 572, 770, 772, 775, 780,
            789, 790, 792, 794
        };
        int[] op2 = {547, 550, 551, 552, 554, 557, 559, 560, 562, 569};
        int[] intersection = {550, 551, 557, 559, 562, 569};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectionPatternOp2IntOp1() {
        int[] op1 = {
            421, 422, 425, 426, 427, 430, 433, 436, 438, 439, 443, 445, 485, 489, 490, 501, 503, 504, 509, 510
        };
        int[] op2 = {258, 267, 271, 272, 276, 278, 281, 422, 423, 425, 431, 432, 433, 436, 439, 443};
        int[] intersection = {422, 425, 433, 436, 439, 443};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testIntersectionPatternIntIntOp1() {
        int[] op1 = {
            160, 161, 162, 165, 169, 171, 177, 184, 185, 191, 290, 292, 294, 298, 299, 305, 307, 310, 314, 319, 384,
            395, 400, 402, 405, 415
        };
        int[] op2 = {160, 163, 169, 173, 177, 180, 181, 185, 190, 292, 293, 294, 302, 304, 306, 310, 313, 314, 319};
        int[] intersection = {160, 169, 177, 185, 292, 294, 310, 314, 319};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectionPatternDisIntOp1() {
        int[] op1 = {
            257, 264, 265, 276, 282, 284, 385, 388, 394, 395, 396, 400, 401, 402, 405, 416, 424, 426, 435, 436, 439,
            443, 445
        };
        int[] op2 = {258, 260, 263, 266, 281, 286, 385, 388, 389, 394, 396, 398, 399, 401, 405, 414};
        int[] intersection = {385, 388, 394, 396, 401, 405};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp1DisOp1() {
        int[] op1 = {101, 102, 106, 115, 117, 120, 122, 123, 161, 169, 170, 179, 184, 485, 494, 497, 500, 504, 509};
        int[] op2 = {162, 171, 182, 190, 191};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp2DisOp1() {
        int[] op1 = {353, 356, 357, 360, 364, 375, 378, 646, 650, 652, 656, 659, 669, 670};
        int[] op2 = {32, 33, 44, 45, 53, 54, 56, 60, 359, 361, 362, 368, 373};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testIntersectionPatternIntDisOp1() {
        int[] op1 = {
            291, 292, 294, 297, 304, 312, 313, 314, 316, 319, 577, 591, 595, 600, 603, 645, 646, 647, 652, 655, 659, 661
        };
        int[] op2 = {291, 292, 293, 297, 301, 302, 304, 306, 309, 319, 578, 585, 592, 597, 606, 607};
        int[] intersection = {291, 292, 297, 304, 319};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testIntersectionPatternDisDisOp1() {
        int[] op1 = {101, 105, 109, 116, 118, 122, 123, 193, 206, 212, 217, 219, 416, 425, 434, 438, 441, 445, 447};
        int[] op2 = {97, 107, 110, 113, 114, 119, 125, 208, 221, 222, 223};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	     	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1Op1Op2() {
        int[] op1 = {67, 69, 72, 78, 82, 84, 85, 90, 128, 129, 137, 141, 150, 151, 152, 156};
        int[] op2 = {386, 394, 397, 398, 401, 405, 406, 407};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	     	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op1Op2() {
        int[] op1 = {160, 162, 168, 171, 178, 187, 189};
        int[] op2 = {131, 133, 134, 136, 147, 152, 155, 156, 231, 234, 240, 243, 247, 248, 251};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternIntOp1Op2() {
        int[] op1 = {193, 197, 198, 199, 205, 206, 207, 211, 215, 216, 222, 297, 298, 300, 302, 303, 311, 315};
        int[] op2 = {195, 199, 201, 202, 205, 207, 209, 214, 216, 221, 513, 523, 526, 529, 533, 540, 543};
        int[] intersection = {199, 205, 207, 216};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDisOp1Op2() {
        int[] op1 = {288, 300, 313, 317, 318, 393, 396, 400, 404, 405, 408, 413};
        int[] op2 = {301, 306, 309, 310, 311, 643, 650, 651, 652, 663, 664, 665, 667};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp1Op2Op2() {
        int[] op1 = {195, 202, 205, 207, 211, 213, 215, 220};
        int[] op2 = {291, 297, 298, 301, 303, 314, 316, 512, 516, 517, 524, 529, 532, 536, 541};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op2Op2() {
        int[] op1 = {};
        int[] op2 = {
            288, 289, 302, 306, 307, 311, 314, 315, 514, 519, 524, 525, 531, 532, 536, 801, 811, 813, 816, 818, 827,
            828, 831
        };
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?    yes
     */
    @Test
    void testIntersectionPatternIntOp2Op2() {
        int[] op1 = {34, 36, 37, 44, 45, 49, 51, 52, 58, 59};
        int[] op2 = {
            36, 37, 38, 46, 47, 48, 50, 51, 52, 59, 63, 260, 264, 265, 269, 274, 278, 280, 287, 353, 357, 359, 365, 378
        };
        int[] intersection = {36, 37, 51, 52, 59};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?   empty
     */
    @Test
    void testIntersectionPatternDisOp2Op2() {
        int[] op1 = {97, 105, 109, 114, 124, 127};
        int[] op2 = {98, 101, 110, 112, 119, 161, 171, 172, 176, 178, 183, 187, 189, 355, 360, 365, 366, 367, 371, 380};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectionPatternOp1IntOp2() {
        int[] op1 = {34, 36, 37, 52, 54, 57, 58, 129, 130, 131, 142, 143, 145, 152, 155, 157, 158};
        int[] op2 = {129, 130, 134, 139, 140, 149, 154, 155, 157, 158, 159, 324, 330, 336, 339, 341, 347, 348};
        int[] intersection = {129, 130, 155, 157, 158};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testIntersectionPatternOp2IntOp2() {
        int[] op1 = {324, 326, 333, 341, 345, 348, 350};
        int[] op2 = {
            66, 68, 70, 71, 75, 78, 79, 93, 326, 331, 335, 337, 338, 339, 340, 344, 345, 348, 350, 658, 659, 660, 665,
            668, 669, 671
        };
        int[] intersection = {326, 345, 348, 350};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testIntersectionPatternIntIntOp2() {
        int[] op1 = {36, 37, 39, 42, 48, 54, 57, 58, 60, 70, 73, 75, 82, 87, 88, 91, 92, 93, 94, 95};
        int[] op2 = {
            34, 37, 40, 42, 44, 45, 48, 54, 60, 65, 70, 71, 72, 73, 75, 76, 80, 81, 88, 89, 94, 292, 294, 297, 303,
            305, 309, 311, 315
        };
        int[] intersection = {37, 42, 48, 54, 60, 70, 73, 75, 88, 94};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectionPatternDisIntOp2() {
        int[] op1 = {229, 231, 234, 253, 418, 423, 430, 431, 433, 434, 436, 438, 443};
        int[] op2 = {
            243, 245, 246, 249, 250, 252, 417, 418, 423, 424, 426, 430, 433, 440, 443, 444, 446, 482, 485, 490, 493,
            494, 495, 504, 508
        };
        int[] intersection = {418, 423, 430, 433, 443};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp1DisOp2() {
        int[] op1 = {193, 194, 209, 212, 213, 215, 221, 521, 522, 524, 528, 538};
        int[] op2 = {513, 517, 520, 526, 543, 745, 752, 754, 757, 763, 764, 765, 767};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp2DisOp2() {
        int[] op1 = {65, 69, 76, 83, 84};
        int[] op2 = {32, 35, 41, 49, 53, 55, 56, 67, 68, 81, 85, 86, 91, 226, 228, 230, 240, 243, 251, 252, 255};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testIntersectionPatternIntDisOp2() {
        int[] op1 = {64, 71, 74, 78, 84, 87, 89, 91, 92, 95, 359, 361, 370, 373, 376};
        int[] op2 = {
            68, 70, 71, 72, 74, 78, 79, 84, 85, 87, 90, 93, 353, 374, 379, 382, 549, 553, 555, 556, 564, 566, 567
        };
        int[] intersection = {71, 74, 78, 84, 87};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testIntersectionPatternDisDisOp2() {
        int[] op1 = {288, 299, 307, 316, 421, 427, 428, 440, 444, 445};
        int[] op2 = {293, 301, 305, 306, 318, 417, 422, 433, 446, 676, 684, 688, 696, 700, 703};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectionPatternOp1Op1Int() {
        int[] op1 = {
            37, 41, 47, 48, 49, 51, 62, 63, 325, 328, 333, 335, 336, 338, 344, 350, 545, 546, 551, 555, 556, 557, 560,
            562, 565, 568, 569, 570
        };
        int[] op2 = {545, 551, 555, 561, 565, 566, 567, 569, 570, 573, 574, 575};
        int[] intersection = {545, 551, 555, 565, 569, 570};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectionPatternOp2Op1Int() {
        int[] op1 = {296, 297, 300, 302, 304, 307, 314, 318, 579, 581, 583, 585, 588, 590, 591, 592, 593, 597, 599};
        int[] op2 = {224, 235, 241, 242, 244, 245, 249, 580, 582, 586, 588, 593, 595, 597, 599, 601};
        int[] intersection = {588, 593, 597, 599};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testIntersectionPatternIntOp1Int() {
        int[] op1 = {
            98, 100, 101, 105, 106, 112, 113, 115, 117, 122, 126, 385, 387, 391, 393, 396, 403, 483, 486, 487, 492,
            499, 503, 508, 510
        };
        int[] op2 = {
            98, 100, 104, 106, 109, 112, 115, 118, 119, 126, 482, 486, 487, 492, 493, 496, 499, 500, 503, 509, 510
        };
        int[] intersection = {98, 100, 106, 112, 115, 126, 486, 487, 492, 499, 503, 510};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testIntersectionPatternDisOp1Int() {
        int[] op1 = {
            33, 35, 39, 41, 44, 54, 63, 355, 356, 360, 371, 375, 378, 383, 577, 590, 591, 594, 598, 600, 601, 602, 604,
            607
        };
        int[] op2 = {42, 43, 48, 51, 53, 61, 577, 578, 583, 586, 590, 594, 598, 599, 600, 601, 602, 604};
        int[] intersection = {577, 590, 594, 598, 600, 601, 602, 604};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectionPatternOp1Op2Int() {
        int[] op1 = {295, 301, 304, 306, 309, 310, 316, 317, 800, 802, 806, 807, 812, 814, 817, 818, 819, 831};
        int[] op2 = {490, 494, 495, 496, 497, 502, 802, 803, 806, 809, 813, 814, 819, 831};
        int[] intersection = {802, 806, 814, 819, 831};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testIntersectionPatternOp2Op2Int() {
        int[] op1 = {545, 550, 551, 556, 557, 558, 562, 564, 565, 568, 574};
        int[] op2 = {
            161, 167, 168, 173, 174, 178, 186, 187, 386, 388, 397, 401, 404, 409, 412, 414, 545, 550, 557, 558, 568,
            569, 570, 572, 573, 575
        };
        int[] intersection = {545, 550, 557, 558, 568};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testIntersectionPatternIntOp2Int() {
        int[] op1 = {65, 70, 74, 77, 82, 84, 86, 87, 92, 94, 95, 483, 484, 486, 488, 489, 496, 499, 500, 502, 503, 506};
        int[] op2 = {
            67, 70, 71, 74, 76, 82, 83, 91, 92, 94, 95, 320, 322, 323, 324, 328, 345, 349, 350, 480, 482, 483, 484,
            489, 499, 501, 502, 504, 506
        };
        int[] intersection = {70, 74, 82, 92, 94, 95, 483, 484, 489, 499, 502, 506};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testIntersectionPatternDisOp2Int() {
        int[] op1 = {33, 37, 42, 46, 54, 58, 62, 259, 261, 262, 263, 268, 270, 273, 278, 280, 285};
        int[] op2 = {
            39, 44, 50, 52, 61, 129, 147, 148, 149, 152, 156, 256, 257, 259, 260, 262, 263, 264, 271, 274, 278, 280,
            285
        };
        int[] intersection = {259, 262, 263, 278, 280, 285};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testIntersectionPatternOp1IntInt() {
        int[] op1 = {
            0, 2, 5, 6, 11, 22, 29, 65, 78, 79, 80, 81, 90, 94, 288, 290, 291, 294, 295, 303, 311, 312, 315, 316, 317
        };
        int[] op2 = {67, 69, 73, 79, 80, 81, 83, 85, 89, 92, 94, 288, 290, 291, 296, 310, 311, 313, 314, 315, 316};
        int[] intersection = {79, 80, 81, 94, 288, 290, 291, 311, 315, 316};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testIntersectionPatternOp2IntInt() {
        int[] op1 = {
            450, 451, 460, 462, 465, 469, 470, 475, 477, 481, 484, 485, 486, 492, 495, 496, 498, 504, 506, 508
        };
        int[] op2 = {
            236, 237, 242, 243, 245, 249, 251, 252, 450, 451, 453, 454, 459, 466, 468, 473, 475, 477, 479, 481, 483,
            484, 485, 486, 491, 495, 496, 497, 505
        };
        int[] intersection = {450, 451, 475, 477, 481, 484, 485, 486, 495, 496};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	 yes
     */
    @Test
    void testIntersectionPatternIntIntInt() {
        int[] op1 = {
            160, 163, 170, 171, 172, 177, 178, 183, 185, 188, 190, 291, 296, 301, 302, 304, 305, 306, 309, 310, 317,
            617, 618, 624, 625, 628, 630, 631, 632, 633, 635, 639
        };
        int[] op2 = {
            160, 161, 163, 167, 171, 173, 178, 185, 186, 188, 290, 296, 297, 299, 301, 305, 309, 310, 311, 315, 608,
            613, 615, 617, 618, 620, 625, 626, 628, 632, 633, 634
        };
        int[] intersection = {160, 163, 171, 178, 185, 188, 296, 301, 305, 309, 310, 617, 618, 625, 628, 632, 633};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	 yes
     */
    @Test
    void testIntersectionPatternDisIntInt() {
        int[] op1 = {
            113, 115, 116, 120, 123, 124, 354, 356, 359, 360, 362, 364, 369, 373, 375, 376, 380, 423, 427, 430, 433,
            437, 438, 439, 445, 446, 447
        };
        int[] op2 = {
            97, 104, 111, 112, 114, 353, 355, 358, 359, 360, 361, 366, 373, 375, 379, 380, 419, 421, 427, 433, 437,
            444, 447
        };
        int[] intersection = {359, 360, 373, 375, 380, 427, 433, 437, 447};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testIntersectionPatternOp1DisInt() {
        int[] op1 = {
            32, 38, 42, 43, 46, 48, 54, 56, 128, 130, 147, 149, 321, 327, 333, 335, 337, 339, 340, 342, 343, 351
        };
        int[] op2 = {146, 148, 154, 325, 333, 335, 337, 339, 340, 343, 347, 350, 351};
        int[] intersection = {333, 335, 337, 339, 340, 343, 351};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testIntersectionPatternOp2DisInt() {
        int[] op1 = {264, 266, 279, 280, 286, 287, 577, 583, 584, 585, 586, 591, 592, 594, 601, 602};
        int[] op2 = {
            229, 231, 240, 244, 248, 250, 251, 255, 256, 258, 265, 278, 282, 283, 579, 583, 585, 586, 590, 592, 598,
            602, 604, 606, 607
        };
        int[] intersection = {583, 585, 586, 592, 602};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	 yes
     */
    @Test
    void testIntersectionPatternIntDisInt() {
        int[] op1 = {
            128, 129, 137, 139, 142, 143, 146, 153, 156, 418, 419, 427, 430, 431, 678, 681, 682, 684, 687, 688, 691,
            692, 693, 701
        };
        int[] op2 = {
            129, 131, 134, 137, 142, 143, 147, 151, 153, 156, 416, 425, 426, 428, 435, 672, 678, 681, 684, 687, 691,
            692, 696, 697, 698, 700
        };
        int[] intersection = {129, 137, 142, 143, 153, 156, 678, 681, 684, 687, 691, 692};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	 yes
     */
    @Test
    void testIntersectionPatternDisDisInt() {
        int[] op1 = {
            67, 75, 79, 85, 94, 226, 240, 241, 250, 254, 480, 481, 486, 487, 488, 492, 493, 500, 503, 508, 510
        };
        int[] op2 = {66, 71, 78, 88, 92, 93, 229, 238, 239, 243, 251, 486, 487, 496, 497, 500, 501, 503, 506, 508, 509};
        int[] intersection = {486, 487, 500, 503, 508};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectionPatternOp1Op1Dis() {
        int[] op1 = {
            135, 136, 147, 149, 153, 154, 156, 453, 460, 462, 470, 472, 474, 477, 577, 578, 579, 592, 594, 598
        };
        int[] op2 = {576, 580, 581, 588, 599, 603};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectionPatternOp2Op1Dis() {
        int[] op1 = {384, 390, 391, 401, 403, 412, 462, 464, 466, 470, 472, 474};
        int[] op2 = {96, 97, 100, 102, 112, 113, 117, 453, 454, 460, 463, 467, 471};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testIntersectionPatternIntOp1Dis() {
        int[] op1 = {
            163, 165, 174, 176, 178, 182, 184, 186, 187, 188, 189, 193, 197, 200, 204, 206, 212, 217, 452, 455, 459,
            460, 464, 467, 469, 473
        };
        int[] op2 = {167, 171, 174, 176, 178, 183, 185, 186, 190, 449, 453, 457, 458, 463, 465, 468, 474};
        int[] intersection = {174, 176, 178, 186};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testIntersectionPatternDisOp1Dis() {
        int[] op1 = {131, 133, 138, 139, 143, 158, 385, 391, 392, 398, 403, 413, 415, 608, 617, 618, 619, 635, 637};
        int[] op2 = {135, 146, 151, 152, 153, 159, 609, 610, 612, 623, 628, 630, 639};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectionPatternOp1Op2Dis() {
        int[] op1 = {290, 298, 300, 304, 307, 314, 317, 620, 624, 629, 632, 634, 637};
        int[] op2 = {417, 422, 426, 431, 434, 438, 446, 612, 614, 616, 618, 633};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testIntersectionPatternOp2Op2Dis() {
        int[] op1 = {548, 550, 558, 559, 569, 571};
        int[] op2 = {
            260, 262, 266, 271, 274, 275, 277, 285, 482, 484, 493, 495, 501, 503, 507, 545, 554, 563, 565, 568, 574
        };
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testIntersectionPatternIntOp2Dis() {
        int[] op1 = {160, 162, 167, 168, 169, 170, 174, 183, 188, 190, 687, 693, 696, 697, 698, 701};
        int[] op2 = {
            162, 164, 165, 167, 170, 172, 175, 179, 185, 188, 481, 485, 486, 490, 492, 495, 675, 681, 682, 688, 695,
            700, 702, 703
        };
        int[] intersection = {162, 167, 170, 188};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testIntersectionPatternDisOp2Dis() {
        int[] op1 = {68, 77, 85, 91, 93, 94, 95, 641, 658, 659, 661, 663, 668, 671};
        int[] op2 = {65, 66, 67, 71, 73, 74, 86, 386, 392, 395, 400, 405, 407, 410, 642, 645, 649, 653, 656, 657};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testIntersectionPatternOp1IntDis() {
        int[] op1 = {
            264, 267, 269, 272, 280, 287, 322, 324, 331, 333, 334, 337, 340, 345, 347, 348, 513, 514, 522, 525, 531,
            539
        };
        int[] op2 = {322, 324, 327, 331, 333, 340, 341, 343, 344, 345, 348, 515, 534, 536, 543};
        int[] intersection = {322, 324, 331, 333, 340, 345, 348};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testIntersectionPatternOp2IntDis() {
        int[] op1 = {384, 386, 387, 389, 390, 391, 397, 400, 402, 404, 405, 704, 720, 723, 729, 730, 734};
        int[] op2 = {
            163, 165, 167, 169, 180, 183, 385, 386, 389, 392, 397, 401, 402, 405, 406, 409, 411, 412, 719, 721, 725,
            726, 735
        };
        int[] intersection = {386, 389, 397, 402, 405};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	empty
     */
    @Test
    void testIntersectionPatternIntIntDis() {
        int[] op1 = {
            96, 97, 100, 101, 106, 108, 113, 115, 118, 119, 120, 121, 256, 257, 259, 265, 272, 274, 277, 278, 279,
            286, 287, 363, 369, 372, 383
        };
        int[] op2 = {
            100, 101, 106, 108, 110, 111, 115, 116, 121, 124, 256, 258, 260, 261, 265, 266, 273, 278, 279, 286, 362,
            364, 376, 379, 381
        };
        int[] intersection = {100, 101, 106, 108, 115, 121, 256, 265, 278, 279, 286};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	empty
     */
    @Test
    void testIntersectionPatternDisIntDis() {
        int[] op1 = {
            1, 3, 18, 19, 22, 288, 293, 294, 295, 296, 297, 298, 303, 306, 315, 317, 576, 580, 584, 592, 599, 607
        };
        int[] op2 = {
            8, 21, 28, 29, 31, 288, 289, 294, 295, 298, 300, 313, 314, 315, 318, 579, 587, 588, 590, 591, 601
        };
        int[] intersection = {288, 294, 295, 298, 315};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testIntersectionPatternOp1DisDis() {
        int[] op1 = {43, 46, 47, 48, 52, 59, 61, 163, 171, 175, 184, 449, 451, 453, 455, 462, 463, 474};
        int[] op2 = {167, 170, 173, 176, 189, 450, 452, 458, 459, 466, 475, 477, 478};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testIntersectionPatternOp2DisDis() {
        int[] op1 = {164, 165, 170, 171, 177, 190, 191, 233, 238, 239, 244, 246};
        int[] op2 = {32, 38, 41, 43, 45, 46, 160, 161, 162, 169, 172, 175, 182, 229, 237, 245, 251, 252};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	empty
     */
    @Test
    void testIntersectionPatternIntDisDis() {
        int[] op1 = {
            257, 258, 261, 264, 265, 275, 278, 280, 287, 326, 329, 330, 331, 338, 340, 341, 608, 619, 620, 627, 628,
            633
        };
        int[] op2 = {
            257, 258, 261, 262, 265, 273, 277, 278, 286, 287, 320, 323, 324, 327, 328, 337, 342, 350, 609, 612, 613,
            623, 626, 634
        };
        int[] intersection = {257, 258, 261, 265, 278, 287};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	empty
     */
    @Test
    void testIntersectionPatternDisDisDis() {
        int[] op1 = {227, 240, 242, 249, 251, 450, 464, 469, 471, 475, 477, 613, 618, 619, 625};
        int[] op2 = {224, 226, 233, 235, 236, 243, 245, 448, 455, 463, 465, 470, 626, 632, 639};
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	     	-----
     *      set2:        	     	-----	-----	-----	-----	-----
     *   overlap?        	     	 yes 	empty	empty	     	empty
     */
    @Test
    void testIntersectionPatternOp1Op1IntDisDisOp2Dis() {
        int[] op1 = {
            194, 205, 209, 211, 214, 217, 219, 226, 233, 235, 236, 240, 254, 255, 323, 325, 326, 328, 332, 337, 338,
            343, 345, 346, 347, 385, 395, 397, 403, 408, 585, 587, 588, 606, 607, 780, 781, 783, 788, 797, 799
        };
        int[] op2 = {
            320, 322, 323, 325, 330, 338, 339, 345, 347, 387, 394, 396, 406, 410, 411, 591, 596, 597, 598, 599, 704,
            714, 716, 718, 724, 731, 777, 785, 789, 795, 796
        };
        int[] intersection = {323, 325, 338, 345, 347};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	     	-----	     	     	-----
     *      set2:   -----	-----	-----	     	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testIntersectionPatternOp2DisOp2Op1Op2Op2Op1() {
        int[] op1 = {
            417, 422, 423, 424, 427, 447, 707, 709, 715, 718, 719, 723, 728, 731, 1296, 1298, 1300, 1306, 1310
        };
        int[] op2 = {
            161, 165, 169, 171, 176, 181, 188, 191, 420, 425, 429, 432, 434, 440, 443, 611, 621, 629, 630, 632, 634,
            960, 962, 965, 980, 982, 986, 987, 1088, 1090, 1091, 1096, 1097, 1099, 1108, 1109
        };
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	     	-----	-----	-----	-----
     *   overlap?
     */
    @Test
    void testIntersectionPatternOp2Op2Op1Op2Op2Op2Op2() {
        int[] op1 = {645, 648, 650, 652, 653, 657, 659, 662};
        int[] op2 = {
            289, 293, 294, 298, 307, 308, 310, 313, 320, 335, 337, 338, 349, 351, 736, 754, 759, 762, 763, 767, 832,
            837, 839, 844, 851, 854, 861, 863, 994, 997, 1001, 1006, 1009, 1019, 1020, 1022, 1190, 1191, 1194, 1195,
            1198, 1201, 1203, 1214
        };
        int[] intersection = {};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	     	-----	-----	     	-----
     *      set2:   -----	     	-----	-----	-----	-----	-----
     *   overlap?   empty	     	     	 yes 	 yes 	     	 yes
     */
    @Test
    void testIntersectionPatternDisOp1Op2IntIntOp2Int() {
        int[] op1 = {
            227, 232, 239, 242, 244, 248, 519, 520, 521, 526, 529, 532, 535, 1024, 1026, 1028, 1030, 1035, 1036, 1039,
            1043, 1044, 1051, 1054, 1252, 1254, 1257, 1258, 1262, 1266, 1270, 1272, 1273, 1275, 1279, 1537, 1540, 1541,
            1549, 1557, 1558, 1563, 1567
        };
        int[] op2 = {
            236, 238, 243, 247, 249, 251, 769, 773, 775, 778, 780, 786, 791, 794, 1025, 1026, 1030, 1032, 1035, 1040,
            1042, 1043, 1054, 1055, 1248, 1250, 1251, 1252, 1254, 1257, 1258, 1264, 1273, 1278, 1316, 1317, 1326, 1328,
            1330, 1335, 1342, 1538, 1540, 1543, 1545, 1549, 1551, 1558, 1562, 1565
        };
        int[] intersection = {1026, 1030, 1035, 1043, 1054, 1252, 1254, 1257, 1258, 1273, 1540, 1549, 1558};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	     	-----
     *      set2:        	-----	-----	     	-----	-----	-----
     *   overlap?        	empty	 yes 	     	 yes 	     	empty
     */
    @Test
    void testIntersectionPatternOp1DisIntOp1IntOp2Dis() {
        int[] op1 = {
            43, 44, 46, 54, 55, 58, 63, 65, 67, 71, 79, 84, 95, 196, 197, 201, 203, 205, 208, 209, 215, 217, 221, 227,
            233, 234, 236, 237, 244, 250, 517, 518, 519, 523, 524, 525, 530, 532, 533, 543, 681, 682, 686, 695, 701
        };
        int[] op2 = {
            69, 74, 75, 77, 78, 91, 196, 200, 207, 209, 210, 211, 214, 215, 217, 219, 221, 516, 517, 520, 522, 525,
            526, 533, 535, 541, 543, 641, 652, 653, 656, 665, 666, 667, 674, 688, 692, 696, 698, 700, 703
        };
        int[] intersection = {196, 209, 215, 217, 221, 517, 525, 533, 543};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----	-----	     	-----	-----	-----	-----
     *      set2:        	-----	-----	-----	-----	-----	     	-----
     *   overlap?        	     	 yes 	empty	     	 yes 	     	empty
     */
    @Test
    void testIntersectionPatternOp1Op2IntDisOp2IntOp1DisOp1() {
        int[] op1 = {
            4, 8, 14, 19, 21, 22, 25, 387, 391, 392, 397, 400, 402, 404, 405, 409, 410, 413, 414, 450, 452, 468, 470,
            471, 474, 898, 905, 907, 909, 910, 919, 922, 924, 925, 926, 927, 1216, 1221, 1226, 1233, 1236, 1242, 1245,
            1542, 1544, 1565, 1567, 1828, 1836, 1844, 1845, 1846, 1854
        };
        int[] op2 = {
            194, 204, 206, 207, 214, 215, 220, 386, 387, 391, 393, 398, 400, 403, 407, 412, 413, 414, 453, 456, 459,
            464, 472, 478, 576, 577, 583, 593, 595, 596, 607, 898, 899, 905, 906, 909, 912, 916, 918, 921, 923, 926,
            927, 1536, 1552, 1556
        };
        int[] intersection = {387, 391, 400, 413, 414, 898, 905, 909, 926, 927};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	     	-----	-----	-----	-----	-----
     *      set2:   -----	-----	     	-----	-----	     	-----	-----	-----
     *   overlap?        	empty	     	     	empty	     	empty	 yes 	 yes
     */
    @Test
    void testIntersectionPatternOp2DisOp1Op2DisOp1DisIntInt() {
        int[] op1 = {
            388, 390, 395, 399, 401, 402, 409, 705, 707, 709, 719, 726, 727, 730, 733, 1063, 1073, 1074, 1084, 1317,
            1319, 1320, 1322, 1324, 1326, 1330, 1337, 1536, 1537, 1546, 1560, 1567, 1824, 1827, 1828, 1829, 1833, 1838,
            1847, 1850, 2016, 2017, 2022, 2030, 2032, 2039, 2041, 2045, 2046, 2047
        };
        int[] op2 = {
            258, 260, 264, 277, 280, 284, 287, 385, 389, 392, 404, 408, 411, 800, 802, 803, 807, 820, 827, 828, 1057,
            1072, 1076, 1083, 1087, 1539, 1551, 1557, 1562, 1565, 1824, 1827, 1830, 1832, 1833, 1838, 1839, 1840, 1841,
            1850, 1854, 2017, 2018, 2023, 2026, 2027, 2028, 2031, 2032, 2039, 2041, 2044
        };
        int[] intersection = {1824, 1827, 1833, 1838, 1850, 2017, 2032, 2039, 2041};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	     	-----	-----
     *      set2:   -----	-----	     	     	     	-----	     	     	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testIntersectionPatternDisIntOp1Op1Op1Op2Op1Op1Op2() {
        int[] op1 = {
            161, 164, 165, 166, 168, 179, 180, 256, 257, 260, 262, 268, 269, 271, 274, 275, 287, 325, 329, 335, 337,
            341, 345, 349, 360, 368, 371, 372, 376, 382, 396, 400, 403, 409, 410, 413, 415, 736, 737, 742, 745, 746,
            756, 759, 928, 932, 936, 938, 940, 950, 959
        };
        int[] op2 = {
            173, 182, 185, 190, 191, 256, 260, 261, 265, 268, 269, 271, 275, 279, 283, 286, 287, 579, 590, 594, 597,
            599, 603, 607, 1069, 1070, 1074, 1075, 1076, 1078, 1082
        };
        int[] intersection = {256, 260, 268, 269, 271, 275, 287};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	-----	-----	     	-----	-----	-----
     *      set2:   -----	-----	     	     	     	-----	-----	-----
     *   overlap?        	 yes 	     	     	     	     	empty	empty
     */
    @Test
    void testIntersectionPatternOp2IntOp1Op1Op1Op2DisDisOp1() {
        int[] op1 = {
            160, 161, 167, 169, 170, 171, 180, 182, 183, 188, 191, 194, 205, 208, 217, 220, 222, 257, 261, 264, 266,
            273, 275, 280, 282, 513, 515, 519, 528, 534, 538, 540, 1095, 1096, 1097, 1100, 1104, 1113, 1116, 1280,
            1283, 1289, 1291, 1294, 1295, 1303, 1310, 1505, 1506, 1507, 1509, 1514, 1524, 1526, 1528
        };
        int[] op2 = {
            97, 99, 101, 105, 108, 116, 121, 161, 165, 169, 170, 172, 173, 174, 178, 183, 186, 188, 191, 775, 778, 779,
            780, 786, 796, 797, 798, 1092, 1099, 1108, 1117, 1118, 1288, 1296, 1298, 1299, 1300, 1306, 1307
        };
        int[] intersection = {161, 169, 170, 183, 188, 191};

        assertIntersection(op1, op2, intersection);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	-----	     	-----	-----	-----	-----
     *      set2:   -----	     	     	-----	-----	-----	-----	     	-----
     *   overlap?        	     	     	empty	     	 yes 	 yes 	     	 yes
     */
    @Test
    void testIntersectionPatternOp2Op1Op1DisOp2IntIntOp1Int() {
        int[] op1 = {
            224, 227, 232, 233, 243, 244, 252, 254, 323, 326, 327, 333, 336, 339, 346, 351, 388, 389, 390, 395, 403,
            405, 897, 904, 905, 906, 908, 909, 914, 917, 921, 924, 925, 926, 1062, 1063, 1068, 1070, 1073, 1074, 1075,
            1081, 1084, 1219, 1220, 1222, 1224, 1229, 1230, 1240, 1508, 1512, 1513, 1517, 1518, 1519, 1522, 1525, 1531
        };
        int[] op2 = {
            128, 131, 153, 155, 156, 158, 159, 385, 387, 402, 404, 406, 409, 411, 414, 619, 620, 621, 625, 627, 628,
            629, 636, 897, 899, 905, 906, 908, 911, 917, 921, 922, 925, 926, 1062, 1063, 1065, 1068, 1070, 1073, 1076,
            1079, 1081, 1086, 1508, 1512, 1513, 1515, 1518, 1519, 1521, 1525, 1526, 1530, 1531
        };
        int[] intersection = {
            897, 905, 906, 908, 917, 921, 925, 926, 1062, 1063, 1068, 1070, 1073, 1081, 1508, 1512, 1513, 1518, 1519,
            1525, 1531
        };

        assertIntersection(op1, op2, intersection);
    }

    @Test
    void testAllPresetCasesWithRandomValues() {
        long randomSeed = new SecureRandom().nextLong();
        TestCase[] testCases = new SimpleBinaryTestCaseGenerator().generateAllPresets(randomSeed);
        for (TestCase testCase : testCases) {
            assertIntersection(testCase);
        }
    }

    @Test
    @Disabled("Test can be enabled and run until failure to find bugs with random data.")
    void intersectionBugCatcher() {
        Random random = new SecureRandom();
        long randomSeed = random.nextLong();
        int numberOfClusters = random.nextInt(20);
        TestCase testCase = new SimpleBinaryTestCaseGenerator().generateRandomCase(randomSeed, numberOfClusters);
        assertIntersection(testCase);
    }

    private static void assertIntersection(TestCase testCase) {
        int[] operand1 = testCase.getOperand1();
        int[] operand2 = testCase.getOperand2();
        int[] expectedIntersection = inferIntersection(operand1, operand2);
        assertIntersection(operand1, operand2, expectedIntersection);
    }

    private static void assertIntersection(int[] op1, int[] op2, int[] expectedIntersection) {
        Statement intersection = Assertion.assertThat(op1).intersection(op2).equals(expectedIntersection);
        Statement inverted = Assertion.assertThat(op2).intersection(op1).equals(expectedIntersection);
        MultiSetTester.forAllSets(intersection, inverted);
    }

    private static int[] inferIntersection(int[] set1, int[] set2) {
        TreeSet<Integer> treeSet1 = Mappers.toTreeSet(set1);
        TreeSet<Integer> treeSet2 = Mappers.toTreeSet(set2);
        treeSet1.retainAll(treeSet2);
        return Mappers.toArray(treeSet1);
    }
}
