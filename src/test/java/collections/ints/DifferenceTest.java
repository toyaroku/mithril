package collections.ints;

import collections.ints.assertions.Assertion;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.Mappers;
import util.SimpleBinaryTestCaseGenerator;
import util.SimpleBinaryTestCaseGenerator.TestCase;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class DifferenceTest {

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1 () {
        int[] op1 = {195, 197, 201, 205, 208, 212, 215};
        int[] op2 = {};
        int[] difference = {195, 197, 201, 205, 208, 212, 215};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2 () {
        int[] op1 = {};
        int[] op2 = {162, 167, 171, 173, 175, 176, 181, 189};
        int[] difference = {};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternInt () {
        int[] op1 = {3, 5, 6, 9, 11, 21, 22, 26, 28, 30, 31};
        int[] op2 = {3, 4, 6, 9, 15, 18, 21, 28, 30, 31};
        int[] difference = {5, 11, 22, 26};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDis () {
        int[] op1 = {107, 112, 121, 126, 127};
        int[] op2 = {97, 104, 106, 110, 123, 125};
        int[] difference = {107, 112, 121, 126, 127};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1Op1 () {
        int[] op1 = {261, 262, 263, 283, 286, 287, 385, 386, 402, 405, 411, 413};
        int[] op2 = {};
        int[] difference = {261, 262, 263, 283, 286, 287, 385, 386, 402, 405, 411, 413};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2Op1 () {
        int[] op1 = {355, 364, 365, 368, 374, 377};
        int[] op2 = {101, 107, 108, 110, 114, 118, 121, 124};
        int[] difference = {355, 364, 365, 368, 374, 377};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternIntOp1 () {
        int[] op1 = {69, 70, 75, 80, 85, 86, 87, 89, 91, 99, 102, 109, 119, 120, 123, 125};
        int[] op2 = {64, 66, 71, 75, 80, 85, 87, 89};
        int[] difference = {69, 70, 86, 91, 99, 102, 109, 119, 120, 123, 125};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDisOp1 () {
        int[] op1 = {225, 232, 237, 242, 253, 255, 481, 485, 487, 488, 495, 497, 500, 506};
        int[] op2 = {224, 227, 228, 240, 245, 252};
        int[] difference = {225, 232, 237, 242, 253, 255, 481, 485, 487, 488, 495, 497, 500, 506};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1Op2 () {
        int[] op1 = {227, 228, 232, 234, 240, 250};
        int[] op2 = {384, 387, 390, 398, 408, 410, 411, 413};
        int[] difference = {227, 228, 232, 234, 240, 250};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2Op2 () {
        int[] op1 = {};
        int[] op2 = {9, 12, 19, 20, 22, 29, 31, 164, 166, 169, 175, 180, 186, 190};
        int[] difference = {};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternIntOp2 () {
        int[] op1 = {64, 69, 73, 76, 81, 82, 83, 84, 89, 95};
        int[] op2 = {64, 65, 66, 68, 72, 73, 74, 82, 84, 86, 357, 358, 361, 365, 376, 380};
        int[] difference = {69, 76, 81, 83, 89, 95};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDisOp2 () {
        int[] op1 = {258, 260, 271, 273, 283, 287};
        int[] op2 = {272, 275, 280, 282, 285, 323, 326, 327, 331, 337, 340, 351};
        int[] difference = {258, 260, 271, 273, 283, 287};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testDifferencePatternOp1Int () {
        int[] op1 = {4, 9, 15, 17, 20, 22, 29, 195, 201, 202, 203, 204, 211, 212, 213, 218};
        int[] op2 = {195, 198, 201, 202, 207, 209, 212, 213, 221, 223};
        int[] difference = {4, 9, 15, 17, 20, 22, 29, 203, 204, 211, 218};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testDifferencePatternOp2Int () {
        int[] op1 = {385, 387, 388, 391, 392, 396, 407, 413};
        int[] op2 = {129, 134, 137, 147, 151, 156, 158, 384, 385, 388, 389, 391, 392, 396, 401, 402, 407, 415};
        int[] difference = {387, 413};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testDifferencePatternIntInt () {
        int[] op1 = {66, 68, 70, 71, 73, 75, 76, 81, 84, 85, 94, 196, 198, 199, 200, 205, 206, 207, 213, 219, 223};
        int[] op2 = {66, 69, 70, 71, 78, 79, 84, 85, 91, 194, 200, 201, 204, 205, 206, 213, 216, 219, 220};
        int[] difference = {68, 73, 75, 76, 81, 94, 196, 198, 199, 207, 223};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testDifferencePatternDisInt () {
        int[] op1 = {66, 67, 77, 88, 94, 384, 386, 389, 392, 395, 401, 403, 404, 409, 412, 413};
        int[] op2 = {65, 82, 84, 89, 92, 93, 385, 386, 387, 394, 397, 401, 402, 404, 405, 409, 412, 413};
        int[] difference = {66, 67, 77, 88, 94, 384, 389, 392, 395, 403};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testDifferencePatternOp1Dis () {
        int[] op1 = {165, 172, 175, 179, 185, 186, 189, 384, 392, 395, 405, 407, 410, 414};
        int[] op2 = {389, 390, 393, 399, 400, 406};
        int[] difference = {165, 172, 175, 179, 185, 186, 189, 384, 392, 395, 405, 407, 410, 414};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testDifferencePatternOp2Dis () {
        int[] op1 = {615, 622, 627, 631, 637, 638};
        int[] op2 = {288, 294, 296, 298, 299, 300, 315, 612, 621, 624, 626, 628, 632};
        int[] difference = {615, 622, 627, 631, 637, 638};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testDifferencePatternIntDis () {
        int[] op1 = {224, 227, 228, 229, 231, 240, 245, 247, 251, 254, 387, 391, 392, 398, 400, 412};
        int[] op2 = {227, 228, 229, 232, 240, 242, 245, 247, 255, 390, 406, 410, 411, 413};
        int[] difference = {224, 231, 251, 254, 387, 391, 392, 398, 400, 412};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testDifferencePatternDisDis () {
        int[] op1 = {232, 233, 236, 242, 255, 256, 260, 271, 275, 276, 279, 282};
        int[] op2 = {229, 231, 235, 248, 250, 253, 264, 265, 281, 284, 285, 286};
        int[] difference = {232, 233, 236, 242, 255, 256, 260, 271, 275, 276, 279, 282};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1Op1Op1 () {
        int[] op1 = {259, 263, 268, 272, 278, 281, 285, 355, 356, 363, 366, 371, 373, 376, 381, 576, 578, 583, 591, 599, 604};
        int[] op2 = {};
        int[] difference = {259, 263, 268, 272, 278, 281, 285, 355, 356, 363, 366, 371, 373, 376, 381, 576, 578, 583, 591, 599, 604};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2Op1Op1 () {
        int[] op1 = {259, 268, 271, 274, 277, 279, 281, 512, 513, 516, 520, 525, 531, 534, 540};
        int[] op2 = {36, 41, 42, 44, 45, 51};
        int[] difference = {259, 268, 271, 274, 277, 279, 281, 512, 513, 516, 520, 525, 531, 534, 540};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternIntOp1Op1 () {
        int[] op1 = {128, 130, 131, 142, 144, 146, 147, 148, 151, 154, 156, 157, 170, 175, 177, 182, 183, 190, 490, 494, 496, 497, 500, 501, 503, 511};
        int[] op2 = {128, 131, 132, 135, 138, 142, 143, 147, 148, 155};
        int[] difference = {130, 144, 146, 151, 154, 156, 157, 170, 175, 177, 182, 183, 190, 490, 494, 496, 497, 500, 501, 503, 511};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDisOp1Op1 () {
        int[] op1 = {227, 231, 233, 238, 246, 247, 249, 328, 331, 338, 339, 347, 348, 349, 448, 449, 453, 455, 462, 471, 472};
        int[] op2 = {228, 237, 243, 244, 254};
        int[] difference = {227, 231, 233, 238, 246, 247, 249, 328, 331, 338, 339, 347, 348, 349, 448, 449, 453, 455, 462, 471, 472};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1Op2Op1 () {
        int[] op1 = {193, 196, 206, 210, 212, 213, 222, 223, 836, 839, 843, 847, 849, 859, 861};
        int[] op2 = {519, 523, 526, 533, 534, 543};
        int[] difference = {193, 196, 206, 210, 212, 213, 222, 223, 836, 839, 843, 847, 849, 859, 861};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2Op2Op1 () {
        int[] op1 = {800, 801, 811, 819, 825, 827, 831};
        int[] op2 = {162, 167, 171, 178, 184, 186, 191, 492, 493, 498, 499, 501, 508, 510};
        int[] difference = {800, 801, 811, 819, 825, 827, 831};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternIntOp2Op1 () {
        int[] op1 = {129, 130, 136, 139, 145, 147, 151, 152, 154, 616, 619, 621, 625, 629, 634, 635, 636};
        int[] op2 = {128, 131, 132, 134, 136, 139, 141, 142, 145, 147, 148, 152, 303, 304, 305, 309, 313, 318, 319};
        int[] difference = {129, 130, 151, 154, 616, 619, 621, 625, 629, 634, 635, 636};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDisOp2Op1 () {
        int[] op1 = {203, 204, 213, 214, 215, 772, 773, 776, 781, 783, 791, 793, 794};
        int[] op2 = {199, 202, 206, 221, 223, 450, 455, 456, 463, 467, 470, 474, 475};
        int[] difference = {203, 204, 213, 214, 215, 772, 773, 776, 781, 783, 791, 793, 794};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testDifferencePatternOp1IntOp1 () {
        int[] op1 = {160, 166, 171, 175, 180, 188, 189, 191, 416, 420, 424, 427, 428, 429, 432, 435, 438, 440, 441, 445, 577, 584, 585, 590, 592, 595, 604};
        int[] op2 = {422, 423, 424, 426, 427, 431, 436, 437, 441, 444, 445};
        int[] difference = {160, 166, 171, 175, 180, 188, 189, 191, 416, 420, 428, 429, 432, 435, 438, 440, 577, 584, 585, 590, 592, 595, 604};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testDifferencePatternOp2IntOp1 () {
        int[] op1 = {330, 334, 336, 337, 338, 340, 341, 342, 349, 350, 351, 428, 431, 432, 434, 436, 440};
        int[] op2 = {163, 165, 166, 171, 174, 175, 177, 181, 324, 331, 334, 336, 337, 338, 339, 340, 343, 346, 349};
        int[] difference = {330, 341, 342, 350, 351, 428, 431, 432, 434, 436, 440};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testDifferencePatternIntIntOp1 () {
        int[] op1 = {96, 98, 100, 101, 103, 105, 106, 112, 113, 115, 119, 418, 423, 425, 431, 432, 434, 435, 437, 438, 439, 516, 517, 518, 524, 530, 533, 534};
        int[] op2 = {97, 101, 102, 104, 105, 106, 107, 110, 113, 115, 119, 418, 421, 424, 425, 432, 433, 434, 435, 439, 440, 443, 445};
        int[] difference = {96, 98, 100, 103, 112, 423, 431, 437, 438, 516, 517, 518, 524, 530, 533, 534};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testDifferencePatternDisIntOp1 () {
        int[] op1 = {194, 198, 207, 209, 222, 225, 231, 235, 238, 244, 245, 252, 254, 255, 550, 555, 557, 563, 567, 568, 569, 572};
        int[] op2 = {197, 199, 205, 208, 223, 224, 225, 226, 230, 234, 235, 245, 247};
        int[] difference = {194, 198, 207, 209, 222, 231, 238, 244, 252, 254, 255, 550, 555, 557, 563, 567, 568, 569, 572};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testDifferencePatternOp1DisOp1 () {
        int[] op1 = {4, 14, 15, 19, 23, 65, 69, 83, 84, 94, 164, 171, 172, 184, 186, 187, 190};
        int[] op2 = {80, 85, 88, 89, 90, 95};
        int[] difference = {4, 14, 15, 19, 23, 65, 69, 83, 84, 94, 164, 171, 172, 184, 186, 187, 190};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testDifferencePatternOp2DisOp1 () {
        int[] op1 = {359, 372, 374, 378, 380, 576, 577, 580, 587, 602, 604};
        int[] op2 = {66, 68, 79, 83, 90, 94, 95, 361, 362, 364, 365, 369, 381};
        int[] difference = {359, 372, 374, 378, 380, 576, 577, 580, 587, 602, 604};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testDifferencePatternIntDisOp1 () {
        int[] op1 = {96, 97, 103, 105, 110, 112, 113, 114, 115, 121, 125, 196, 197, 203, 204, 205, 214, 218, 221, 385, 390, 391, 395, 398, 405, 407};
        int[] op2 = {96, 99, 101, 105, 106, 109, 110, 111, 112, 115, 117, 210, 211, 212, 215, 216, 217};
        int[] difference = {97, 103, 113, 114, 121, 125, 196, 197, 203, 204, 205, 214, 218, 221, 385, 390, 391, 395, 398, 405, 407};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testDifferencePatternDisDisOp1 () {
        int[] op1 = {1, 10, 11, 21, 27, 67, 72, 75, 76, 77, 90, 93, 386, 387, 390, 400, 412, 414};
        int[] op2 = {8, 14, 19, 29, 30, 70, 84, 85, 92, 94, 95};
        int[] difference = {1, 10, 11, 21, 27, 67, 72, 75, 76, 77, 90, 93, 386, 387, 390, 400, 412, 414};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	     	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1Op1Op2 () {
        int[] op1 = {137, 141, 148, 150, 152, 153, 157, 196, 197, 198, 201, 206, 209, 215};
        int[] op2 = {288, 295, 296, 299, 300};
        int[] difference = {137, 141, 148, 150, 152, 153, 157, 196, 197, 198, 201, 206, 209, 215};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	     	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2Op1Op2 () {
        int[] op1 = {417, 428, 444, 446};
        int[] op2 = {193, 198, 204, 208, 209, 214, 218, 223, 614, 615, 616, 618, 620, 636, 639};
        int[] difference = {417, 428, 444, 446};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternIntOp1Op2 () {
        int[] op1 = {192, 196, 198, 199, 204, 210, 218, 221, 222, 223, 387, 390, 391, 399, 402, 403, 415};
        int[] op2 = {192, 195, 198, 199, 204, 206, 208, 210, 214, 216, 222, 676, 677, 679, 680, 681, 694, 703};
        int[] difference = {196, 218, 221, 223, 387, 390, 391, 399, 402, 403, 415};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDisOp1Op2 () {
        int[] op1 = {196, 201, 205, 212, 213, 219, 220, 515, 516, 518, 523, 525, 539, 540};
        int[] op2 = {199, 207, 215, 218, 223, 806, 811, 813, 814, 815, 823, 826};
        int[] difference = {196, 201, 205, 212, 213, 219, 220, 515, 516, 518, 523, 525, 539, 540};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp1Op2Op2 () {
        int[] op1 = {292, 298, 304, 309, 310, 316, 317};
        int[] op2 = {320, 323, 325, 331, 333, 347, 348, 609, 610, 611, 624, 630, 634, 636, 637};
        int[] difference = {292, 298, 304, 309, 310, 316, 317};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----	-----
     *   overlap?
     */
    @Test
    void testDifferencePatternOp2Op2Op2 () {
        int[] op1 = {};
        int[] op2 = {224, 228, 229, 244, 248, 249, 252, 292, 294, 301, 304, 307, 313, 317, 583, 585, 587, 592, 595, 597, 602};
        int[] difference = {};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?    yes
     */
    @Test
    void testDifferencePatternIntOp2Op2 () {
        int[] op1 = {300, 301, 302, 303, 304, 305, 309, 311, 313, 314, 317, 319};
        int[] op2 = {288, 290, 296, 297, 301, 303, 305, 314, 317, 319, 616, 622, 623, 624, 625, 628, 633, 639, 737, 738, 751, 752, 755, 763};
        int[] difference = {300, 302, 304, 309, 311, 313};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?   empty
     */
    @Test
    void testDifferencePatternDisOp2Op2 () {
        int[] op1 = {131, 141, 151, 152};
        int[] op2 = {133, 135, 139, 140, 145, 226, 229, 230, 246, 249, 517, 522, 524, 525, 529, 530, 537, 539};
        int[] difference = {131, 141, 151, 152};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testDifferencePatternOp1IntOp2 () {
        int[] op1 = {7, 10, 11, 14, 18, 23, 28, 29, 33, 34, 37, 39, 41, 48, 50, 52, 58, 60};
        int[] op2 = {33, 34, 37, 40, 41, 52, 54, 58, 60, 352, 353, 362, 363, 370, 375, 381, 383};
        int[] difference = {7, 10, 11, 14, 18, 23, 28, 29, 39, 48, 50};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testDifferencePatternOp2IntOp2 () {
        int[] op1 = {259, 260, 263, 270, 271, 274, 275, 276, 282, 283};
        int[] op2 = {135, 137, 140, 144, 145, 146, 157, 159, 259, 263, 269, 272, 275, 276, 281, 282, 284, 546, 548, 553, 556, 568, 571, 572, 575};
        int[] difference = {260, 270, 271, 274, 283};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testDifferencePatternIntIntOp2 () {
        int[] op1 = {258, 260, 265, 266, 268, 269, 276, 277, 280, 281, 283, 284, 386, 387, 388, 392, 400, 406, 409, 410, 415};
        int[] op2 = {257, 264, 266, 273, 277, 279, 281, 283, 284, 285, 286, 385, 386, 388, 390, 398, 401, 403, 406, 407, 409, 412, 609, 610, 620, 622, 630, 637, 639};
        int[] difference = {258, 260, 265, 268, 269, 276, 280, 387, 392, 400, 410, 415};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testDifferencePatternDisIntOp2 () {
        int[] op1 = {256, 268, 278, 547, 548, 549, 553, 558, 560, 566, 568, 572, 573};
        int[] op2 = {260, 276, 281, 284, 548, 549, 557, 558, 559, 560, 562, 564, 568, 573, 745, 747, 748, 752, 754, 758, 759, 762};
        int[] difference = {256, 268, 278, 547, 553, 566, 572};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testDifferencePatternOp1DisOp2 () {
        int[] op1 = {65, 69, 72, 73, 77, 78, 84, 87, 391, 394, 398, 400, 401, 408, 414};
        int[] op2 = {384, 386, 403, 405, 406, 409, 415, 578, 579, 581, 590, 591, 592, 594, 607};
        int[] difference = {65, 69, 72, 73, 77, 78, 84, 87, 391, 394, 398, 400, 401, 408, 414};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testDifferencePatternOp2DisOp2 () {
        int[] op1 = {321, 331, 332, 344, 347};
        int[] op2 = {224, 225, 228, 239, 243, 247, 249, 320, 327, 328, 329, 343, 513, 517, 518, 527, 528, 529, 539};
        int[] difference = {321, 331, 332, 344, 347};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testDifferencePatternIntDisOp2 () {
        int[] op1 = {0, 2, 6, 8, 11, 13, 22, 23, 24, 25, 68, 84, 87, 90, 93};
        int[] op2 = {2, 5, 6, 8, 14, 15, 19, 21, 22, 29, 64, 70, 75, 78, 79, 92, 230, 233, 238, 240, 245, 249, 250};
        int[] difference = {0, 11, 13, 23, 24, 25, 68, 84, 87, 90, 93};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testDifferencePatternDisDisOp2 () {
        int[] op1 = {137, 139, 140, 142, 148, 157, 158, 325, 327, 329, 335, 338, 339, 341};
        int[] op2 = {129, 136, 145, 151, 153, 159, 326, 334, 336, 343, 346, 349, 544, 546, 548, 557, 560, 562, 567, 573};
        int[] difference = {137, 139, 140, 142, 148, 157, 158, 325, 327, 329, 335, 338, 339, 341};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testDifferencePatternOp1Op1Int () {
        int[] op1 = {39, 44, 45, 49, 51, 59, 258, 266, 269, 272, 275, 285, 286, 385, 386, 390, 398, 400, 403, 406, 407, 410};
        int[] op2 = {385, 386, 392, 397, 399, 402, 403, 406, 407, 410, 411};
        int[] difference = {39, 44, 45, 49, 51, 59, 258, 266, 269, 272, 275, 285, 286, 390, 398, 400};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testDifferencePatternOp2Op1Int () {
        int[] op1 = {419, 423, 428, 430, 438, 443, 517, 518, 524, 525, 527, 528, 535, 537, 541, 542};
        int[] op2 = {198, 204, 210, 213, 217, 219, 220, 513, 517, 518, 521, 525, 528, 529, 530, 543};
        int[] difference = {419, 423, 428, 430, 438, 443, 524, 527, 535, 537, 541, 542};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testDifferencePatternIntOp1Int () {
        int[] op1 = {33, 35, 40, 41, 42, 44, 47, 54, 58, 62, 295, 298, 300, 304, 311, 315, 318, 320, 325, 327, 330, 331, 334, 335, 338, 339};
        int[] op2 = {34, 41, 42, 45, 47, 51, 53, 58, 59, 60, 62, 322, 323, 325, 333, 334, 336, 338, 339, 344, 349};
        int[] difference = {33, 35, 40, 44, 54, 295, 298, 300, 304, 311, 315, 318, 320, 327, 330, 331, 335};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testDifferencePatternDisOp1Int () {
        int[] op1 = {64, 67, 70, 78, 84, 95, 290, 294, 297, 300, 301, 306, 313, 318, 480, 482, 484, 487, 489, 492, 494, 496, 498, 503, 505};
        int[] op2 = {66, 74, 75, 77, 82, 86, 90, 481, 482, 484, 487, 496, 498, 499, 502, 503, 507, 509};
        int[] difference = {64, 67, 70, 78, 84, 95, 290, 294, 297, 300, 301, 306, 313, 318, 480, 489, 492, 494, 505};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testDifferencePatternOp1Op2Int () {
        int[] op1 = {96, 106, 112, 115, 120, 121, 122, 123, 193, 194, 195, 198, 204, 209, 212, 216};
        int[] op2 = {163, 167, 168, 175, 176, 177, 179, 184, 195, 198, 209, 210, 211, 212, 215, 216, 217, 218, 222};
        int[] difference = {96, 106, 112, 115, 120, 121, 122, 123, 193, 194, 204};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testDifferencePatternOp2Op2Int () {
        int[] op1 = {193, 198, 201, 202, 209, 217, 218, 219, 220, 221, 222};
        int[] op2 = {1, 5, 8, 11, 17, 23, 24, 29, 33, 34, 36, 37, 46, 49, 57, 61, 193, 198, 199, 210, 212, 213, 215, 219, 220};
        int[] difference = {201, 202, 209, 217, 218, 221, 222};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testDifferencePatternIntOp2Int () {
        int[] op1 = {235, 237, 238, 242, 245, 246, 247, 248, 251, 254, 255, 612, 615, 616, 618, 619, 621, 625, 636, 639};
        int[] op2 = {225, 237, 238, 240, 242, 253, 254, 255, 416, 423, 424, 428, 433, 434, 438, 440, 608, 611, 612, 615, 618, 620, 621, 637};
        int[] difference = {235, 245, 246, 247, 248, 251, 616, 619, 625, 636, 639};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testDifferencePatternDisOp2Int () {
        int[] op1 = {33, 34, 38, 47, 52, 62, 265, 267, 270, 274, 276, 282, 285, 286};
        int[] op2 = {41, 42, 45, 51, 60, 98, 114, 116, 120, 124, 125, 127, 259, 261, 267, 270, 273, 274, 276, 277, 284, 285};
        int[] difference = {33, 34, 38, 47, 52, 62, 265, 282, 286};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testDifferencePatternOp1IntInt () {
        int[] op1 = {36, 40, 43, 46, 50, 53, 54, 62, 193, 194, 197, 202, 203, 205, 213, 214, 217, 218, 512, 514, 515, 517, 528, 538, 539, 540};
        int[] op2 = {195, 196, 198, 200, 201, 202, 203, 204, 216, 218, 222, 515, 518, 519, 522, 529, 538, 539, 543};
        int[] difference = {36, 40, 43, 46, 50, 53, 54, 62, 193, 194, 197, 205, 213, 214, 217, 512, 514, 517, 528, 540};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testDifferencePatternOp2IntInt () {
        int[] op1 = {451, 452, 461, 466, 471, 472, 473, 476, 477, 478, 704, 707, 709, 713, 718, 727, 728, 733, 734};
        int[] op2 = {194, 195, 197, 207, 210, 215, 216, 219, 455, 456, 457, 461, 462, 464, 466, 467, 468, 469, 473, 477, 704, 707, 710, 713, 716, 718, 720, 724, 728, 733, 735};
        int[] difference = {451, 452, 471, 472, 476, 478, 709, 727, 734};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	 yes
     */
    @Test
    void testDifferencePatternIntIntInt () {
        int[] op1 = {32, 35, 36, 44, 47, 51, 57, 58, 59, 61, 63, 99, 104, 108, 110, 111, 112, 113, 115, 119, 192, 195, 197, 203, 204, 206, 210, 211, 216, 221, 222};
        int[] op2 = {37, 38, 41, 47, 52, 57, 63, 97, 99, 106, 111, 112, 115, 118, 119, 120, 121, 123, 192, 193, 195, 202, 203, 206, 210, 211, 214, 217, 219};
        int[] difference = {32, 35, 36, 44, 51, 58, 59, 61, 104, 108, 110, 113, 197, 204, 216, 221, 222};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	 yes
     */
    @Test
    void testDifferencePatternDisIntInt () {
        int[] op1 = {193, 195, 200, 210, 213, 217, 219, 352, 356, 358, 360, 362, 368, 369, 374, 375, 380, 382, 513, 515, 516, 517, 518, 521, 532, 534, 537, 538, 541, 542};
        int[] op2 = {198, 203, 206, 211, 218, 222, 353, 356, 358, 360, 361, 362, 371, 372, 374, 382, 383, 513, 515, 517, 519, 523, 531, 532, 533, 534, 537, 538};
        int[] difference = {193, 195, 200, 210, 213, 217, 219, 352, 368, 369, 375, 380, 516, 518, 521, 541, 542};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testDifferencePatternOp1DisInt () {
        int[] op1 = {96, 101, 102, 113, 119, 121, 126, 127, 386, 392, 398, 400, 412, 579, 585, 586, 591, 592, 595, 600, 601, 604, 606};
        int[] op2 = {388, 390, 397, 399, 403, 410, 413, 583, 584, 588, 589, 591, 595, 597, 601, 604, 606};
        int[] difference = {96, 101, 102, 113, 119, 121, 126, 127, 386, 392, 398, 400, 412, 579, 585, 586, 592, 600};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testDifferencePatternOp2DisInt () {
        int[] op1 = {424, 437, 438, 439, 441, 445, 576, 580, 581, 584, 585, 593, 595, 597, 599, 602};
        int[] op2 = {164, 171, 172, 174, 175, 179, 180, 190, 418, 419, 422, 425, 447, 578, 580, 581, 582, 584, 585, 588, 590, 595, 597, 602};
        int[] difference = {424, 437, 438, 439, 441, 445, 576, 593, 599};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	 yes
     */
    @Test
    void testDifferencePatternIntDisInt () {
        int[] op1 = {64, 69, 74, 76, 81, 82, 83, 86, 89, 91, 100, 113, 119, 123, 193, 198, 203, 206, 210, 217, 218, 219, 221, 222, 223};
        int[] op2 = {64, 73, 75, 79, 81, 83, 84, 86, 92, 98, 99, 101, 109, 114, 118, 202, 203, 204, 205, 206, 213, 214, 218, 221, 223};
        int[] difference = {69, 74, 76, 82, 89, 91, 100, 113, 119, 123, 193, 198, 210, 217, 219, 222};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	 yes
     */
    @Test
    void testDifferencePatternDisDisInt () {
        int[] op1 = {36, 40, 43, 47, 58, 60, 128, 147, 152, 159, 416, 418, 421, 423, 427, 435, 436, 437, 443, 446};
        int[] op2 = {38, 48, 49, 53, 56, 63, 133, 138, 145, 148, 416, 418, 421, 423, 424, 426, 427, 428, 429, 438, 439};
        int[] difference = {36, 40, 43, 47, 58, 60, 128, 147, 152, 159, 435, 436, 437, 443, 446};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testDifferencePatternOp1Op1Dis () {
        int[] op1 = {194, 199, 201, 202, 204, 208, 219, 335, 339, 342, 345, 347, 350, 351, 512, 514, 525, 532};
        int[] op2 = {515, 529, 536, 538, 539};
        int[] difference = {194, 199, 201, 202, 204, 208, 219, 335, 339, 342, 345, 347, 350, 351, 512, 514, 525, 532};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testDifferencePatternOp2Op1Dis () {
        int[] op1 = {294, 296, 297, 306, 311, 314, 319, 423, 431, 432, 433, 446};
        int[] op2 = {256, 265, 268, 269, 272, 276, 277, 284, 417, 420, 422, 440, 442};
        int[] difference = {294, 296, 297, 306, 311, 314, 319, 423, 431, 432, 433, 446};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testDifferencePatternIntOp1Dis () {
        int[] op1 = {33, 38, 40, 49, 51, 53, 54, 56, 62, 195, 197, 201, 212, 214, 219, 220, 221, 323, 326, 327, 334, 335, 339, 343};
        int[] op2 = {32, 40, 46, 47, 48, 51, 52, 53, 54, 320, 325, 341, 347, 351};
        int[] difference = {33, 38, 49, 56, 62, 195, 197, 201, 212, 214, 219, 220, 221, 323, 326, 327, 334, 335, 339, 343};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testDifferencePatternDisOp1Dis () {
        int[] op1 = {35, 43, 49, 56, 60, 99, 101, 114, 119, 121, 123, 126, 224, 227, 230, 234, 239, 252};
        int[] op2 = {37, 39, 40, 45, 62, 226, 231, 232, 233, 240, 245, 247, 249};
        int[] difference = {35, 43, 49, 56, 60, 99, 101, 114, 119, 121, 123, 126, 224, 227, 230, 234, 239, 252};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testDifferencePatternOp1Op2Dis () {
        int[] op1 = {4, 13, 15, 16, 18, 24, 29, 385, 387, 395, 402, 407, 409};
        int[] op2 = {192, 197, 198, 201, 207, 209, 214, 221, 386, 389, 390, 398, 403, 405, 411};
        int[] difference = {4, 13, 15, 16, 18, 24, 29, 385, 387, 395, 402, 407, 409};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testDifferencePatternOp2Op2Dis () {
        int[] op1 = {548, 551, 552, 575};
        int[] op2 = {193, 200, 201, 209, 210, 217, 219, 223, 480, 481, 497, 499, 501, 504, 508, 511, 545, 550, 554, 561, 569};
        int[] difference = {548, 551, 552, 575};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testDifferencePatternIntOp2Dis () {
        int[] op1 = {37, 40, 41, 46, 52, 53, 55, 56, 58, 60, 364, 367, 369, 374, 380};
        int[] op2 = {34, 36, 37, 40, 44, 49, 53, 55, 58, 60, 195, 209, 212, 216, 217, 218, 219, 352, 368, 370, 372, 378, 383};
        int[] difference = {41, 46, 52, 56, 364, 367, 369, 374, 380};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testDifferencePatternDisOp2Dis () {
        int[] op1 = {129, 130, 142, 152, 154, 158, 494, 504, 505, 511};
        int[] op2 = {128, 132, 138, 146, 153, 297, 305, 307, 308, 309, 310, 314, 316, 493, 495, 498, 500, 502, 503};
        int[] difference = {129, 130, 142, 152, 154, 158, 494, 504, 505, 511};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testDifferencePatternOp1IntDis () {
        int[] op1 = {33, 38, 39, 40, 41, 42, 44, 56, 66, 68, 71, 72, 75, 80, 85, 86, 87, 90, 353, 357, 361, 364, 365, 366, 375};
        int[] op2 = {68, 73, 78, 80, 84, 85, 86, 88, 94, 358, 367, 368, 373, 374, 377};
        int[] difference = {33, 38, 39, 40, 41, 42, 44, 56, 66, 71, 72, 75, 87, 90, 353, 357, 361, 364, 365, 366, 375};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testDifferencePatternOp2IntDis () {
        int[] op1 = {227, 228, 230, 235, 239, 245, 250, 251, 254, 384, 387, 395, 405, 411};
        int[] op2 = {1, 4, 7, 10, 17, 29, 231, 233, 237, 241, 242, 244, 245, 251, 255, 386, 389, 393, 394, 397, 409};
        int[] difference = {227, 228, 230, 235, 239, 250, 254, 384, 387, 395, 405, 411};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	empty
     */
    @Test
    void testDifferencePatternIntIntDis () {
        int[] op1 = {99, 101, 105, 108, 109, 110, 117, 118, 119, 125, 194, 196, 198, 200, 202, 206, 208, 209, 213, 223, 328, 335, 338, 343, 345, 350};
        int[] op2 = {97, 100, 103, 106, 107, 109, 110, 115, 118, 119, 123, 127, 194, 198, 206, 208, 209, 211, 213, 219, 223, 322, 324, 327, 329, 340, 341};
        int[] difference = {99, 101, 105, 108, 117, 125, 196, 200, 202, 328, 335, 338, 343, 345, 350};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	empty
     */
    @Test
    void testDifferencePatternDisIntDis () {
        int[] op1 = {288, 289, 293, 297, 301, 302, 304, 545, 550, 554, 556, 560, 561, 565, 574, 575, 807, 818, 819, 824, 825, 829};
        int[] op2 = {294, 295, 303, 307, 312, 317, 550, 552, 554, 557, 558, 560, 561, 562, 564, 566, 573, 574, 801, 802, 811, 816, 828, 831};
        int[] difference = {288, 289, 293, 297, 301, 302, 304, 545, 556, 565, 575, 807, 818, 819, 824, 825, 829};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testDifferencePatternOp1DisDis () {
        int[] op1 = {34, 40, 41, 48, 51, 55, 57, 60, 160, 161, 166, 169, 175, 189, 354, 355, 358, 361, 365, 367, 376};
        int[] op2 = {162, 163, 167, 176, 177, 191, 353, 357, 366, 371, 377, 382};
        int[] difference = {34, 40, 41, 48, 51, 55, 57, 60, 160, 161, 166, 169, 175, 189, 354, 355, 358, 361, 365, 367, 376};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testDifferencePatternOp2DisDis () {
        int[] op1 = {548, 549, 551, 570, 571, 575, 578, 581, 582, 585, 586, 588, 593};
        int[] op2 = {291, 292, 296, 299, 302, 305, 315, 547, 552, 553, 565, 566, 568, 591, 592, 595, 598, 603, 607};
        int[] difference = {548, 549, 551, 570, 571, 575, 578, 581, 582, 585, 586, 588, 593};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	empty
     */
    @Test
    void testDifferencePatternIntDisDis () {
        int[] op1 = {195, 198, 199, 207, 210, 216, 217, 220, 221, 223, 257, 259, 278, 280, 283, 290, 296, 301, 303, 313};
        int[] op2 = {195, 198, 205, 209, 210, 219, 221, 223, 256, 258, 260, 271, 274, 279, 294, 302, 307, 311};
        int[] difference = {199, 207, 216, 217, 220, 257, 259, 278, 280, 283, 290, 296, 301, 303, 313};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	empty
     */
    @Test
    void testDifferencePatternDisDisDis () {
        int[] op1 = {70, 72, 84, 85, 87, 227, 229, 237, 238, 248, 252, 384, 399, 404, 412, 413, 414};
        int[] op2 = {67, 69, 73, 76, 86, 88, 224, 226, 233, 234, 239, 241, 254, 391, 393, 400, 401, 409, 415};
        int[] difference = {70, 72, 84, 85, 87, 227, 229, 237, 238, 248, 252, 384, 399, 404, 412, 413, 414};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----	-----	-----	-----	-----
     *      set2:   -----	-----	     	-----	     	     	-----
     *   overlap?    yes 	     	     	 yes 	     	     	 yes
     */
    @Test
    void testDifferencePatternIntOp2Op1IntOp1Op1Int () {
        int[] op1 = {98, 103, 107, 109, 111, 112, 115, 117, 118, 121, 125, 355, 358, 359, 360, 370, 374, 377, 379, 482, 483, 488, 490, 491, 493, 498, 501, 505, 506, 677, 684, 690, 692, 696, 864, 865, 871, 882, 891, 895, 1056, 1057, 1061, 1065, 1066, 1068, 1071, 1073, 1082};
        int[] op2 = {97, 98, 106, 110, 111, 113, 115, 118, 121, 125, 126, 257, 258, 259, 266, 269, 278, 281, 481, 482, 483, 487, 490, 493, 495, 498, 499, 501, 505, 1057, 1060, 1061, 1062, 1063, 1065, 1066, 1068, 1076, 1079, 1081, 1085};
        int[] difference = {103, 107, 109, 112, 117, 355, 358, 359, 360, 370, 374, 377, 379, 488, 491, 506, 677, 684, 690, 692, 696, 864, 865, 871, 882, 891, 895, 1056, 1071, 1073, 1082};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----	-----
     *      set2:   -----	     	     	     	-----	     	-----
     *   overlap?   empty	     	     	     	empty	     	empty
     */
    @Test
    void testDifferencePatternDisOp1Op1Op1DisOp1Dis () {
        int[] op1 = {197, 203, 206, 210, 220, 323, 325, 330, 332, 334, 335, 336, 350, 423, 427, 430, 435, 436, 439, 676, 680, 681, 687, 696, 699, 838, 840, 846, 850, 855, 871, 874, 877, 881, 890, 895, 1097, 1101, 1108, 1110};
        int[] op2 = {195, 205, 208, 209, 845, 848, 859, 863, 1099, 1100, 1102, 1113};
        int[] difference = {197, 203, 206, 210, 220, 323, 325, 330, 332, 334, 335, 336, 350, 423, 427, 430, 435, 436, 439, 676, 680, 681, 687, 696, 699, 838, 840, 846, 850, 855, 871, 874, 877, 881, 890, 895, 1097, 1101, 1108, 1110};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----
     *      set2:   -----	     	-----	-----	-----	     	-----
     *   overlap?    yes 	     	empty	empty	 yes
     */
    @Test
    void testDifferencePatternIntOp1DisDisIntOp1Op2 () {
        int[] op1 = {160, 164, 169, 170, 172, 173, 179, 181, 182, 224, 225, 227, 228, 232, 243, 250, 252, 291, 293, 315, 316, 318, 319, 579, 580, 588, 593, 604, 607, 801, 804, 805, 806, 812, 817, 820, 823, 824, 826, 960, 962, 963, 966, 976, 980, 985, 991};
        int[] op2 = {160, 162, 163, 170, 172, 175, 178, 181, 182, 183, 185, 288, 290, 294, 301, 314, 583, 585, 598, 602, 603, 812, 813, 814, 817, 820, 826, 830, 831, 1297, 1298, 1301, 1304, 1307};
        int[] difference = {164, 169, 173, 179, 224, 225, 227, 228, 232, 243, 250, 252, 291, 293, 315, 316, 318, 319, 579, 580, 588, 593, 604, 607, 801, 804, 805, 806, 823, 824, 960, 962, 963, 966, 976, 980, 985, 991};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	     	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	-----	     	-----
     *   overlap?   empty	 yes 	     	 yes 	 yes 	     	empty
     */
    @Test
    void testDifferencePatternDisIntOp2IntIntOp1Dis () {
        int[] op1 = {67, 71, 78, 82, 86, 93, 95, 353, 355, 358, 363, 365, 366, 370, 371, 375, 379, 381, 769, 778, 781, 783, 785, 788, 790, 793, 794, 796, 797, 1088, 1089, 1092, 1094, 1095, 1098, 1105, 1107, 1114, 1313, 1316, 1322, 1327, 1334, 1338, 1339, 1341, 1348, 1351, 1354, 1362, 1365, 1368, 1369};
        int[] op2 = {65, 66, 69, 75, 87, 92, 94, 355, 356, 358, 363, 368, 370, 371, 378, 379, 613, 614, 617, 619, 623, 627, 628, 637, 769, 781, 783, 789, 790, 792, 794, 796, 1088, 1089, 1090, 1091, 1092, 1095, 1101, 1103, 1107, 1108, 1119, 1344, 1347, 1349, 1352, 1358, 1361};
        int[] difference = {67, 71, 78, 82, 86, 93, 95, 353, 365, 366, 375, 381, 778, 785, 788, 793, 797, 1094, 1098, 1105, 1114, 1313, 1316, 1322, 1327, 1334, 1338, 1339, 1341, 1348, 1351, 1354, 1362, 1365, 1368, 1369};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	-----	     	-----	-----
     *      set2:   -----	-----	-----	     	-----	     	-----
     *   overlap?        	empty	 yes 	     	     	     	 yes
     */
    @Test
    void testDifferencePatternOp2DisIntOp1Op2Op1Int () {
        int[] op1 = {198, 219, 221, 256, 260, 262, 264, 265, 267, 276, 281, 282, 284, 286, 386, 388, 392, 395, 396, 397, 411, 773, 778, 780, 783, 787, 791, 794, 795, 800, 802, 803, 806, 809, 815, 822, 826, 831};
        int[] op2 = {114, 115, 117, 118, 123, 124, 127, 199, 201, 213, 215, 256, 260, 264, 266, 273, 276, 280, 281, 283, 284, 287, 449, 450, 451, 455, 470, 473, 478, 479, 802, 803, 806, 809, 810, 811, 813, 815, 816, 828, 830};
        int[] difference = {198, 219, 221, 262, 265, 267, 282, 286, 386, 388, 392, 395, 396, 397, 411, 773, 778, 780, 783, 787, 791, 794, 795, 800, 822, 826, 831};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	     	     	-----	-----	-----
     *      set2:   -----	     	-----	-----	-----	-----	-----	-----	-----
     *   overlap?   empty	     	 yes 	     	     	empty	 yes 	empty
     */
    @Test
    void testDifferencePatternDisOp1IntOp2Op2DisIntDisOp2 () {
        int[] op1 = {4, 5, 10, 13, 21, 22, 100, 113, 118, 119, 123, 125, 126, 226, 227, 235, 239, 242, 245, 246, 251, 253, 254, 255, 929, 932, 936, 942, 946, 953, 1089, 1090, 1095, 1097, 1101, 1103, 1104, 1106, 1114, 1119, 1124, 1126, 1134, 1141, 1147};
        int[] op2 = {14, 17, 18, 20, 30, 226, 227, 228, 235, 238, 242, 245, 246, 251, 254, 448, 454, 465, 467, 471, 474, 477, 479, 644, 647, 648, 650, 655, 658, 665, 670, 930, 937, 949, 952, 954, 1090, 1095, 1096, 1097, 1099, 1101, 1105, 1107, 1114, 1115, 1119, 1127, 1131, 1139, 1140, 1188, 1204, 1205, 1207, 1213, 1215};
        int[] difference = {4, 5, 10, 13, 21, 22, 100, 113, 118, 119, 123, 125, 126, 239, 253, 255, 929, 932, 936, 942, 946, 953, 1089, 1103, 1104, 1106, 1124, 1126, 1134, 1141, 1147};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	     	-----	-----	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	     	-----	     	-----	-----
     *   overlap?   empty	empty	     	empty	     	empty	     	empty	empty
     */
    @Test
    void testDifferencePatternDisDisOp2DisOp1DisOp1DisDis () {
        int[] op1 = {8, 16, 24, 74, 83, 84, 90, 93, 95, 293, 301, 304, 306, 307, 310, 319, 516, 519, 524, 527, 534, 535, 537, 540, 705, 707, 723, 729, 733, 960, 962, 964, 971, 975, 985, 987, 1251, 1255, 1264, 1269, 1272, 1278, 1376, 1382, 1385, 1393, 1401, 1403};
        int[] op2 = {9, 25, 27, 30, 70, 75, 80, 82, 85, 192, 198, 200, 202, 207, 212, 219, 290, 291, 295, 300, 303, 706, 710, 721, 724, 728, 732, 1248, 1257, 1262, 1265, 1277, 1279, 1380, 1381, 1384, 1388, 1399, 1400};
        int[] difference = {8, 16, 24, 74, 83, 84, 90, 93, 95, 293, 301, 304, 306, 307, 310, 319, 516, 519, 524, 527, 534, 535, 537, 540, 705, 707, 723, 729, 733, 960, 962, 964, 971, 975, 985, 987, 1251, 1255, 1264, 1269, 1272, 1278, 1376, 1382, 1385, 1393, 1401, 1403};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	     	-----	     	-----	-----	     	-----
     *      set2:   -----	-----	-----	     	-----	     	     	-----	-----
     *   overlap?   empty	     	     	     	     	     	     	     	 yes
     */
    @Test
    void testDifferencePatternDisOp2Op2Op1Op2Op1Op1Op2Int () {
        int[] op1 = {294, 298, 306, 318, 319, 902, 903, 904, 920, 922, 923, 926, 1024, 1030, 1032, 1047, 1048, 1049, 1051, 1055, 1190, 1194, 1196, 1199, 1203, 1205, 1209, 1211, 1634, 1636, 1638, 1640, 1641, 1646, 1651, 1660, 1663};
        int[] op2 = {290, 295, 303, 313, 316, 610, 613, 616, 618, 624, 626, 636, 638, 769, 770, 771, 776, 779, 792, 795, 797, 928, 936, 939, 940, 942, 946, 954, 955, 1508, 1519, 1522, 1523, 1525, 1526, 1527, 1636, 1637, 1638, 1641, 1643, 1650, 1655, 1659, 1663};
        int[] difference = {294, 298, 306, 318, 319, 902, 903, 904, 920, 922, 923, 926, 1024, 1030, 1032, 1047, 1048, 1049, 1051, 1055, 1190, 1194, 1196, 1199, 1203, 1205, 1209, 1211, 1634, 1640, 1646, 1651, 1660};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	-----	     	     	     	-----	-----
     *      set2:   -----	-----	-----	-----	-----	-----	-----	     	-----
     *   overlap?        	empty	empty	empty	     	     	     	     	empty
     */
    @Test
    void testDifferencePatternOp2DisDisDisOp2Op2Op2Op1Dis () {
        int[] op1 = {354, 355, 366, 370, 371, 377, 382, 385, 398, 401, 402, 408, 418, 438, 440, 441, 447, 707, 710, 717, 724, 731, 733, 734, 735, 742, 743, 747, 759, 762, 763};
        int[] op2 = {289, 291, 295, 299, 302, 310, 319, 360, 367, 369, 374, 380, 384, 387, 390, 391, 405, 417, 421, 424, 442, 480, 486, 489, 504, 505, 507, 508, 548, 554, 561, 564, 570, 572, 573, 608, 610, 617, 624, 628, 635, 638, 739, 741, 750, 756, 758, 765};
        int[] difference = {354, 355, 366, 370, 371, 377, 382, 385, 398, 401, 402, 408, 418, 438, 440, 441, 447, 707, 710, 717, 724, 731, 733, 734, 735, 742, 743, 747, 759, 762, 763};

        assertDifference(op1, op2, difference);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----	-----	-----	-----	     	-----	-----
     *      set2:   -----	-----	-----	-----	     	-----	-----	-----	-----
     *   overlap?    yes 	     	empty	empty	     	 yes 	     	empty	 yes
     */
    @Test
    void testDifferencePatternIntOp2DisDisOp1IntOp2DisInt () {
        int[] op1 = {64, 70, 72, 75, 77, 79, 82, 88, 89, 92, 93, 486, 498, 503, 510, 738, 748, 752, 753, 759, 764, 804, 805, 811, 825, 826, 834, 837, 840, 841, 850, 856, 857, 861, 862, 1316, 1324, 1332, 1333, 1342, 1505, 1508, 1509, 1510, 1518, 1519, 1526, 1528, 1530, 1533, 1534};
        int[] op2 = {64, 67, 70, 72, 75, 79, 82, 83, 85, 86, 88, 288, 289, 295, 302, 307, 308, 310, 319, 485, 489, 490, 493, 496, 504, 742, 750, 761, 762, 763, 837, 838, 840, 848, 849, 850, 853, 854, 855, 862, 1152, 1154, 1157, 1161, 1165, 1176, 1182, 1312, 1315, 1317, 1320, 1326, 1328, 1337, 1505, 1507, 1509, 1510, 1515, 1517, 1518, 1519, 1530, 1533};
        int[] difference = {77, 89, 92, 93, 486, 498, 503, 510, 738, 748, 752, 753, 759, 764, 804, 805, 811, 825, 826, 834, 841, 856, 857, 861, 1316, 1324, 1332, 1333, 1342, 1508, 1526, 1528, 1534};

        assertDifference(op1, op2, difference);
    }

    @Test
    void testAllPresetCasesWithRandomValues() {
        long randomSeed = new SecureRandom().nextLong();
        TestCase[] testCases = new SimpleBinaryTestCaseGenerator().generateAllPresets(randomSeed);
        for (TestCase testCase : testCases) {
            assertDifference(testCase);
        }
    }

    @Test
    @Disabled("Test can be enabled and run until failure to find bugs with random data.")
    void intersectionBugCatcher() {
        Random random = new SecureRandom();
        long randomSeed = random.nextLong();
        int numberOfClusters = random.nextInt(20);
        TestCase testCase = new SimpleBinaryTestCaseGenerator().generateRandomCase(randomSeed, numberOfClusters);
        assertDifference(testCase);
    }

    private static void assertDifference(TestCase testCase) {
        int[] operand1 = testCase.getOperand1();
        int[] operand2 = testCase.getOperand2();
        int[] expectedDifference = inferDifference(operand1, operand2);
        assertDifference(operand1, operand2, expectedDifference);
    }

    private static void assertDifference(int[] op1, int[] op2, int[] expectedDifference) {
        MultiSetTester.forAllSets(
            Assertion.assertThat(op1).difference(op2).equals(expectedDifference)
        );
    }

    private static int[] inferDifference(int[] set1, int[] set2) {
        TreeSet<Integer> treeSet1 = Mappers.toTreeSet(set1);
        TreeSet<Integer> treeSet2 = Mappers.toTreeSet(set2);
        treeSet1.removeAll(treeSet2);
        return Mappers.toArray(treeSet1);
    }
}
