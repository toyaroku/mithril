package collections.ints;

import collections.ints.assertions.Assertion;
import collections.ints.assertions.Statement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.Mappers;
import util.SimpleBinaryTestCaseGenerator;
import util.SimpleBinaryTestCaseGenerator.TestCase;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class UnionTest {

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:
     *   overlap?
     */
    @Test
    void testUnionPatternOp1() {
        int[] op1 = {167, 169, 171, 180, 181, 184, 186, 190};
        int[] op2 = {};
        int[] union = {167, 169, 171, 180, 181, 184, 186, 190};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2() {
        int[] op1 = {};
        int[] op2 = {64, 65, 68, 71, 75, 81, 82, 83};
        int[] union = {64, 65, 68, 71, 75, 81, 82, 83};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternint() {
        int[] op1 = {35, 38, 41, 42, 47, 51, 54, 56, 58};
        int[] op2 = {32, 35, 40, 41, 45, 48, 52, 54, 56};
        int[] union = {32, 35, 38, 40, 41, 42, 45, 47, 48, 51, 52, 54, 56, 58};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndis() {
        int[] op1 = {0, 3, 9, 16, 27, 29};
        int[] op2 = {1, 2, 13, 15, 17, 19, 21};
        int[] union = {0, 1, 2, 3, 9, 13, 15, 16, 17, 19, 21, 27, 29};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testUnionPatternOp1Op1() {
        int[] op1 = {195, 201, 203, 205, 208, 210, 211, 217, 257, 262, 263, 271, 275, 277, 287};
        int[] op2 = {};
        int[] union = {195, 201, 203, 205, 208, 210, 211, 217, 257, 262, 263, 271, 275, 277, 287};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2Op1() {
        int[] op1 = {611, 612, 622, 624, 625, 630, 638};
        int[] op2 = {291, 296, 299, 301, 306, 309, 318, 319};
        int[] union = {291, 296, 299, 301, 306, 309, 318, 319, 611, 612, 622, 624, 625, 630, 638};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternintOp1() {
        int[] op1 = {225, 231, 232, 237, 238, 246, 247, 249, 255, 289, 290, 294, 295, 305, 310, 316, 317};
        int[] op2 = {224, 226, 227, 230, 231, 238, 244, 247, 249, 255};
        int[] union = {
            224, 225, 226, 227, 230, 231, 232, 237, 238, 244, 246, 247, 249, 255, 289, 290, 294, 295, 305, 310, 316, 317
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndisOp1() {
        int[] op1 = {259, 268, 270, 272, 274, 282, 283, 286, 387, 389, 392, 394, 404, 409, 411};
        int[] op2 = {256, 257, 263, 264, 269, 277, 278, 281};
        int[] union = {
            256, 257, 259, 263, 264, 268, 269, 270, 272, 274, 277, 278, 281, 282, 283, 286, 387, 389, 392, 394, 404,
            409, 411
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp1Op2() {
        int[] op1 = {225, 230, 239, 244, 248, 254, 255};
        int[] op2 = {323, 328, 333, 338, 341, 345, 348, 351};
        int[] union = {225, 230, 239, 244, 248, 254, 255, 323, 328, 333, 338, 341, 345, 348, 351};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2Op2() {
        int[] op1 = {};
        int[] op2 = {261, 263, 264, 269, 270, 280, 320, 323, 330, 339, 341, 345, 349};
        int[] union = {261, 263, 264, 269, 270, 280, 320, 323, 330, 339, 341, 345, 349};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternintOp2() {
        int[] op1 = {292, 297, 298, 301, 304, 309, 310, 312, 315, 317, 318};
        int[] op2 = {288, 292, 296, 298, 304, 306, 307, 309, 310, 315, 318, 484, 490, 494, 497, 498, 503, 504, 507};
        int[] union = {
            288, 292, 296, 297, 298, 301, 304, 306, 307, 309, 310, 312, 315, 317, 318, 484, 490, 494, 497, 498, 503,
            504, 507
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndisOp2() {
        int[] op1 = {102, 111, 115, 116, 122};
        int[] op2 = {98, 112, 113, 114, 123, 126, 384, 385, 390, 394, 396, 399, 400};
        int[] union = {98, 102, 111, 112, 113, 114, 115, 116, 122, 123, 126, 384, 385, 390, 394, 396, 399, 400};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testUnionPatternOp1int() {
        int[] op1 = {290, 294, 300, 303, 305, 306, 313, 580, 581, 582, 584, 594, 597, 598, 601, 604, 606, 607};
        int[] op2 = {576, 580, 581, 582, 591, 593, 596, 600, 603, 606, 607};
        int[] union = {
            290, 294, 300, 303, 305, 306, 313, 576, 580, 581, 582, 584, 591, 593, 594, 596, 597, 598, 600, 601, 603,
            604, 606, 607
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testUnionPatternOp2int() {
        int[] op1 = {259, 263, 264, 268, 272, 277, 280, 282, 285, 286};
        int[] op2 = {1, 9, 11, 12, 19, 24, 264, 267, 268, 271, 275, 277, 279, 281, 282, 284, 287};
        int[] union = {
            1, 9, 11, 12, 19, 24, 259, 263, 264, 267, 268, 271, 272, 275, 277, 279, 280, 281, 282, 284, 285, 286, 287
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testUnionPatternintint() {
        int[] op1 = {224, 225, 235, 239, 248, 250, 251, 252, 385, 388, 392, 395, 397, 402, 404, 415};
        int[] op2 = {
            227, 232, 238, 239, 242, 243, 247, 248, 249, 250, 251, 384, 385, 388, 390, 392, 395, 397, 404, 408, 409, 410
        };
        int[] union = {
            224, 225, 227, 232, 235, 238, 239, 242, 243, 247, 248, 249, 250, 251, 252, 384, 385, 388, 390, 392, 395,
            397, 402, 404, 408, 409, 410, 415
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testUnionPatterndisint() {
        int[] op1 = {0, 5, 13, 17, 23, 27, 30, 292, 293, 299, 302, 303, 306, 307, 308, 310, 311, 315, 317};
        int[] op2 = {3, 6, 12, 19, 288, 290, 291, 295, 298, 299, 306, 307, 308, 311, 312};
        int[] union = {
            0, 3, 5, 6, 12, 13, 17, 19, 23, 27, 30, 288, 290, 291, 292, 293, 295, 298, 299, 302, 303, 306, 307, 308,
            310, 311, 312, 315, 317
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testUnionPatternOp1dis() {
        int[] op1 = {193, 196, 197, 198, 201, 216, 219, 452, 456, 458, 465, 466, 473, 474};
        int[] op2 = {453, 454, 457, 463};
        int[] union = {193, 196, 197, 198, 201, 216, 219, 452, 453, 454, 456, 457, 458, 463, 465, 466, 473, 474};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testUnionPatternOp2dis() {
        int[] op1 = {294, 296, 307, 312, 316, 318};
        int[] op2 = {166, 171, 174, 182, 189, 191, 297, 302, 304, 306};
        int[] union = {166, 171, 174, 182, 189, 191, 294, 296, 297, 302, 304, 306, 307, 312, 316, 318};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testUnionPatternintdis() {
        int[] op1 = {0, 1, 7, 8, 11, 12, 14, 15, 22, 28, 72, 74, 79, 81, 90};
        int[] op2 = {1, 6, 8, 9, 11, 15, 18, 19, 22, 26, 70, 75, 80, 89, 91};
        int[] union = {0, 1, 6, 7, 8, 9, 11, 12, 14, 15, 18, 19, 22, 26, 28, 70, 72, 74, 75, 79, 80, 81, 89, 90, 91};

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testUnionPatterndisdis() {
        int[] op1 = {256, 259, 264, 271, 276, 279, 283, 284, 581, 585, 586, 588, 595, 600};
        int[] op2 = {257, 268, 272, 273, 280, 282, 287, 576, 589, 601, 603, 606, 607};
        int[] union = {
            256, 257, 259, 264, 268, 271, 272, 273, 276, 279, 280, 282, 283, 284, 287, 576, 581, 585, 586, 588, 589,
            595, 600, 601, 603, 606, 607
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:
     *   overlap?
     */
    @Test
    void testUnionPatternOp1Op1Op1() {
        int[] op1 = {
            256, 258, 261, 263, 264, 278, 282, 284, 293, 294, 296, 302, 310, 311, 315, 513, 517, 528, 530, 536, 537, 543
        };
        int[] op2 = {};
        int[] union = {
            256, 258, 261, 263, 264, 278, 282, 284, 293, 294, 296, 302, 310, 311, 315, 513, 517, 528, 530, 536, 537, 543
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2Op1Op1() {
        int[] op1 = {133, 138, 145, 149, 152, 156, 158, 289, 291, 296, 298, 300, 303, 309, 312};
        int[] op2 = {97, 106, 109, 113, 118, 126, 127};
        int[] union = {
            97, 106, 109, 113, 118, 126, 127, 133, 138, 145, 149, 152, 156, 158, 289, 291, 296, 298, 300, 303, 309, 312
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternintOp1Op1() {
        int[] op1 = {
            0, 2, 8, 10, 11, 12, 20, 21, 24, 25, 31, 129, 133, 134, 141, 146, 149, 152, 156, 357, 359, 367, 369, 371,
            376, 383
        };
        int[] op2 = {2, 3, 4, 5, 10, 11, 17, 20, 21, 24, 31};
        int[] union = {
            0, 2, 3, 4, 5, 8, 10, 11, 12, 17, 20, 21, 24, 25, 31, 129, 133, 134, 141, 146, 149, 152, 156, 357, 359, 367,
            369, 371, 376, 383
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndisOp1Op1() {
        int[] op1 = {138, 146, 152, 156, 257, 260, 261, 264, 265, 270, 286, 320, 330, 340, 341, 343, 349, 351};
        int[] op2 = {148, 149};
        int[] union = {
            138, 146, 148, 149, 152, 156, 257, 260, 261, 264, 265, 270, 286, 320, 330, 340, 341, 343, 349, 351
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp1Op2Op1() {
        int[] op1 = {225, 227, 241, 242, 243, 244, 246, 248, 645, 646, 647, 649, 650, 653, 656, 658};
        int[] op2 = {544, 547, 559, 564, 565, 568, 572};
        int[] union = {
            225, 227, 241, 242, 243, 244, 246, 248, 544, 547, 559, 564, 565, 568, 572, 645, 646, 647, 649, 650, 653,
            656, 658
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2Op2Op1() {
        int[] op1 = {648, 655, 657, 659, 660, 662, 664, 665};
        int[] op2 = {193, 201, 210, 211, 213, 219, 220, 485, 490, 492, 498, 500, 506, 507, 510};
        int[] union = {
            193, 201, 210, 211, 213, 219, 220, 485, 490, 492, 498, 500, 506, 507, 510, 648, 655, 657, 659, 660, 662,
            664, 665
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternintOp2Op1() {
        int[] op1 = {0, 3, 6, 11, 12, 13, 15, 19, 26, 27, 28, 192, 196, 201, 202, 205, 220, 221};
        int[] op2 = {0, 2, 3, 5, 9, 13, 15, 18, 19, 26, 27, 64, 65, 66, 70, 72, 76, 83, 87};
        int[] union = {
            0, 2, 3, 5, 6, 9, 11, 12, 13, 15, 18, 19, 26, 27, 28, 64, 65, 66, 70, 72, 76, 83, 87, 192, 196, 201, 202,
            205, 220, 221
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndisOp2Op1() {
        int[] op1 = {227, 231, 239, 241, 252, 254, 449, 450, 454, 458, 462, 465, 476};
        int[] op2 = {230, 234, 237, 243, 244, 251, 328, 329, 333, 348, 350};
        int[] union = {
            227, 230, 231, 234, 237, 239, 241, 243, 244, 251, 252, 254, 328, 329, 333, 348, 350, 449, 450, 454, 458,
            462, 465, 476
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	 yes
     */
    @Test
    void testUnionPatternOp1intOp1() {
        int[] op1 = {
            231, 242, 243, 244, 247, 249, 250, 252, 289, 291, 297, 298, 301, 302, 303, 314, 316, 319, 612, 615, 618,
            619, 623, 633, 634, 635
        };
        int[] op2 = {290, 295, 296, 297, 298, 303, 306, 310, 314};
        int[] union = {
            231, 242, 243, 244, 247, 249, 250, 252, 289, 290, 291, 295, 296, 297, 298, 301, 302, 303, 306, 310, 314,
            316, 319, 612, 615, 618, 619, 623, 633, 634, 635
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	 yes
     */
    @Test
    void testUnionPatternOp2intOp1() {
        int[] op1 = {128, 130, 131, 134, 139, 140, 145, 148, 150, 156, 453, 454, 457, 465, 466, 473, 476};
        int[] op2 = {32, 37, 40, 43, 52, 55, 62, 128, 130, 133, 134, 138, 139, 140, 144, 146, 156};
        int[] union = {
            32, 37, 40, 43, 52, 55, 62, 128, 130, 131, 133, 134, 138, 139, 140, 144, 145, 146, 148, 150, 156, 453, 454,
            457, 465, 466, 473, 476
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testUnionPatternintintOp1() {
        int[] op1 = {
            256, 268, 269, 271, 275, 278, 279, 281, 283, 285, 448, 449, 451, 453, 458, 464, 465, 468, 471, 474, 475,
            479, 709, 712, 721, 722, 723, 728, 731
        };
        int[] op2 = {
            256, 257, 263, 267, 269, 274, 275, 276, 277, 279, 284, 463, 464, 465, 467, 468, 469, 471, 473, 475, 476
        };
        int[] union = {
            256, 257, 263, 267, 268, 269, 271, 274, 275, 276, 277, 278, 279, 281, 283, 284, 285, 448, 449, 451, 453,
            458, 463, 464, 465, 467, 468, 469, 471, 473, 474, 475, 476, 479, 709, 712, 721, 722, 723, 728, 731
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testUnionPatterndisintOp1() {
        int[] op1 = {
            32, 34, 36, 48, 55, 59, 109, 110, 113, 115, 116, 117, 121, 122, 126, 160, 165, 167, 168, 169, 174, 187, 188
        };
        int[] op2 = {33, 41, 42, 44, 49, 63, 99, 101, 102, 109, 110, 116, 117, 118, 120, 122};
        int[] union = {
            32, 33, 34, 36, 41, 42, 44, 48, 49, 55, 59, 63, 99, 101, 102, 109, 110, 113, 115, 116, 117, 118, 120, 121,
            122, 126, 160, 165, 167, 168, 169, 174, 187, 188
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----
     *   overlap?        	empty
     */
    @Test
    void testUnionPatternOp1disOp1() {
        int[] op1 = {
            136, 140, 141, 142, 150, 151, 152, 159, 419, 431, 433, 434, 583, 587, 590, 597, 602, 604, 605, 607
        };
        int[] op2 = {416, 420, 425, 426, 427};
        int[] union = {
            136, 140, 141, 142, 150, 151, 152, 159, 416, 419, 420, 425, 426, 427, 431, 433, 434, 583, 587, 590, 597,
            602, 604, 605, 607
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----
     *   overlap?        	empty
     */
    @Test
    void testUnionPatternOp2disOp1() {
        int[] op1 = {512, 513, 520, 530, 531, 541, 543, 835, 843, 845, 847, 850, 853, 854, 858};
        int[] op2 = {225, 230, 233, 234, 240, 243, 246, 254, 516, 529, 535, 536, 539, 540, 542};
        int[] union = {
            225, 230, 233, 234, 240, 243, 246, 254, 512, 513, 516, 520, 529, 530, 531, 535, 536, 539, 540, 541, 542,
            543, 835, 843, 845, 847, 850, 853, 854, 858
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testUnionPatternintdisOp1() {
        int[] op1 = {33, 34, 37, 41, 42, 45, 48, 51, 53, 55, 268, 270, 271, 272, 279, 384, 387, 392, 393, 394, 410};
        int[] op2 = {33, 37, 39, 40, 45, 47, 53, 56, 59, 260, 261, 278, 281, 287};
        int[] union = {
            33, 34, 37, 39, 40, 41, 42, 45, 47, 48, 51, 53, 55, 56, 59, 260, 261, 268, 270, 271, 272, 278, 279, 281,
            287, 384, 387, 392, 393, 394, 410
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testUnionPatterndisdisOp1() {
        int[] op1 = {290, 291, 296, 299, 303, 305, 308, 388, 389, 395, 402, 708, 710, 712, 714, 720, 722, 727, 730};
        int[] op2 = {288, 298, 306, 307, 315, 317, 385, 387, 393, 403};
        int[] union = {
            288, 290, 291, 296, 298, 299, 303, 305, 306, 307, 308, 315, 317, 385, 387, 388, 389, 393, 395, 402, 403,
            708, 710, 712, 714, 720, 722, 727, 730
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	     	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp1Op1Op2() {
        int[] op1 = {98, 100, 104, 106, 112, 116, 117, 122, 326, 327, 332, 340, 345, 348, 351};
        int[] op2 = {518, 524, 526, 530, 536, 537, 539};
        int[] union = {
            98, 100, 104, 106, 112, 116, 117, 122, 326, 327, 332, 340, 345, 348, 351, 518, 524, 526, 530, 536, 537, 539
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	     	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2Op1Op2() {
        int[] op1 = {611, 613, 619, 622, 629, 632, 634, 637};
        int[] op2 = {288, 294, 299, 306, 307, 310, 318, 802, 804, 806, 808, 809, 813, 819};
        int[] union = {
            288, 294, 299, 306, 307, 310, 318, 611, 613, 619, 622, 629, 632, 634, 637, 802, 804, 806, 808, 809, 813, 819
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternintOp1Op2() {
        int[] op1 = {5, 6, 14, 16, 17, 21, 22, 26, 28, 29, 30, 31, 161, 163, 170, 173, 178, 179, 180};
        int[] op2 = {11, 12, 13, 14, 16, 22, 25, 28, 29, 321, 326, 330, 332, 344, 345, 351};
        int[] union = {
            5, 6, 11, 12, 13, 14, 16, 17, 21, 22, 25, 26, 28, 29, 30, 31, 161, 163, 170, 173, 178, 179, 180, 321, 326,
            330, 332, 344, 345, 351
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndisOp1Op2() {
        int[] op1 = {161, 163, 172, 176, 182, 185, 188, 192, 193, 205, 209, 210, 216, 218, 222};
        int[] op2 = {167, 169, 173, 177, 181, 189, 190, 418, 421, 426, 433, 434, 441, 443, 447};
        int[] union = {
            161, 163, 167, 169, 172, 173, 176, 177, 181, 182, 185, 188, 189, 190, 192, 193, 205, 209, 210, 216, 218,
            222, 418, 421, 426, 433, 434, 441, 443, 447
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:        	-----	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp1Op2Op2() {
        int[] op1 = {105, 110, 112, 113, 116, 125, 127};
        int[] op2 = {354, 357, 358, 359, 364, 365, 373, 384, 386, 395, 397, 398, 408, 413};
        int[] union = {
            105, 110, 112, 113, 116, 125, 127, 354, 357, 358, 359, 364, 365, 373, 384, 386, 395, 397, 398, 408, 413
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:
     *      set2:   -----	-----	-----
     *   overlap?
     */
    @Test
    void testUnionPatternOp2Op2Op2() {
        int[] op1 = {};
        int[] op2 = {
            164, 165, 174, 176, 179, 180, 189, 190, 288, 291, 293, 294, 298, 306, 313, 316, 545, 557, 559, 566, 573, 574
        };
        int[] union = {
            164, 165, 174, 176, 179, 180, 189, 190, 288, 291, 293, 294, 298, 306, 313, 316, 545, 557, 559, 566, 573, 574
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?    yes
     */
    @Test
    void testUnionPatternintOp2Op2() {
        int[] op1 = {97, 99, 105, 109, 111, 115, 117, 121, 123, 124, 126};
        int[] op2 = {
            97, 99, 105, 106, 109, 110, 111, 117, 118, 125, 416, 419, 422, 427, 439, 445, 514, 517, 518, 527, 532, 535
        };
        int[] union = {
            97, 99, 105, 106, 109, 110, 111, 115, 117, 118, 121, 123, 124, 125, 126, 416, 419, 422, 427, 439, 445, 514,
            517, 518, 527, 532, 535
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----
     *      set2:   -----	-----	-----
     *   overlap?   empty
     */
    @Test
    void testUnionPatterndisOp2Op2() {
        int[] op1 = {162, 167, 172, 179, 188};
        int[] op2 = {
            163, 170, 174, 175, 183, 191, 269, 276, 277, 278, 279, 280, 285, 384, 390, 392, 397, 407, 408, 414
        };
        int[] union = {
            162, 163, 167, 170, 172, 174, 175, 179, 183, 188, 191, 269, 276, 277, 278, 279, 280, 285, 384, 390, 392,
            397, 407, 408, 414
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testUnionPatternOp1intOp2() {
        int[] op1 = {289, 294, 310, 312, 315, 316, 318, 319, 356, 366, 367, 369, 370, 372, 376, 378, 382};
        int[] op2 = {355, 356, 359, 361, 362, 366, 367, 369, 370, 380, 448, 449, 451, 452, 453, 455, 457, 458};
        int[] union = {
            289, 294, 310, 312, 315, 316, 318, 319, 355, 356, 359, 361, 362, 366, 367, 369, 370, 372, 376, 378, 380,
            382, 448, 449, 451, 452, 453, 455, 457, 458
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes
     */
    @Test
    void testUnionPatternOp2intOp2() {
        int[] op1 = {195, 198, 200, 206, 207, 208, 210, 211, 212, 213, 214, 221};
        int[] op2 = {
            98, 101, 103, 107, 111, 117, 119, 120, 194, 199, 200, 201, 206, 210, 211, 213, 216, 221, 352, 354, 355, 358,
            364, 369, 370, 371
        };
        int[] union = {
            98, 101, 103, 107, 111, 117, 119, 120, 194, 195, 198, 199, 200, 201, 206, 207, 208, 210, 211, 212, 213, 214,
            216, 221, 352, 354, 355, 358, 364, 369, 370, 371
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes
     */
    @Test
    void testUnionPatternintintOp2() {
        int[] op1 = {288, 291, 299, 305, 307, 309, 310, 311, 319, 481, 482, 483, 484, 487, 490, 498, 504, 506, 508};
        int[] op2 = {
            291, 295, 298, 299, 300, 307, 310, 315, 318, 482, 485, 487, 490, 494, 499, 504, 506, 507, 508, 510, 785,
            787, 789, 791, 794, 795, 798
        };
        int[] union = {
            288, 291, 295, 298, 299, 300, 305, 307, 309, 310, 311, 315, 318, 319, 481, 482, 483, 484, 485, 487, 490,
            494, 498, 499, 504, 506, 507, 508, 510, 785, 787, 789, 791, 794, 795, 798
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes
     */
    @Test
    void testUnionPatterndisintOp2() {
        int[] op1 = {161, 176, 389, 390, 391, 393, 395, 399, 400, 404, 410, 415};
        int[] op2 = {
            162, 166, 184, 384, 385, 389, 390, 392, 393, 395, 398, 400, 404, 407, 452, 460, 461, 465, 466, 468, 469, 470
        };
        int[] union = {
            161, 162, 166, 176, 184, 384, 385, 389, 390, 391, 392, 393, 395, 398, 399, 400, 404, 407, 410, 415, 452,
            460, 461, 465, 466, 468, 469, 470
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testUnionPatternOp1disOp2() {
        int[] op1 = {224, 229, 231, 233, 241, 243, 246, 253, 453, 455, 460, 463, 468};
        int[] op2 = {448, 458, 461, 464, 471, 708, 711, 720, 721, 722, 729};
        int[] union = {
            224, 229, 231, 233, 241, 243, 246, 253, 448, 453, 455, 458, 460, 461, 463, 464, 468, 471, 708, 711, 720,
            721, 722, 729
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty
     */
    @Test
    void testUnionPatternOp2disOp2() {
        int[] op1 = {192, 193, 201, 206, 213, 219, 221};
        int[] op2 = {38, 46, 47, 50, 54, 57, 195, 197, 205, 207, 212, 223, 456, 463, 465, 467, 469, 470, 475};
        int[] union = {
            38, 46, 47, 50, 54, 57, 192, 193, 195, 197, 201, 205, 206, 207, 212, 213, 219, 221, 223, 456, 463, 465, 467,
            469, 470, 475
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty
     */
    @Test
    void testUnionPatternintdisOp2() {
        int[] op1 = {163, 167, 169, 171, 179, 181, 182, 184, 190, 352, 371, 372, 374, 376, 382};
        int[] op2 = {
            160, 167, 171, 177, 181, 184, 189, 190, 355, 358, 365, 375, 379, 380, 383, 456, 461, 467, 468, 470, 475, 479
        };
        int[] union = {
            160, 163, 167, 169, 171, 177, 179, 181, 182, 184, 189, 190, 352, 355, 358, 365, 371, 372, 374, 375, 376,
            379, 380, 382, 383, 456, 461, 467, 468, 470, 475, 479
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty
     */
    @Test
    void testUnionPatterndisdisOp2() {
        int[] op1 = {228, 229, 235, 239, 245, 391, 394, 399, 401};
        int[] op2 = {234, 238, 240, 244, 247, 251, 385, 396, 400, 406, 412, 704, 705, 706, 725, 726, 731, 734};
        int[] union = {
            228, 229, 234, 235, 238, 239, 240, 244, 245, 247, 251, 385, 391, 394, 396, 399, 400, 401, 406, 412, 704,
            705, 706, 725, 726, 731, 734
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testUnionPatternOp1Op1int() {
        int[] op1 = {
            205, 208, 209, 213, 214, 217, 292, 296, 298, 305, 314, 315, 318, 319, 417, 419, 427, 430, 433, 434, 435,
            438, 442, 445
        };
        int[] op2 = {420, 423, 427, 433, 434, 435, 439, 440, 444, 445, 447};
        int[] union = {
            205, 208, 209, 213, 214, 217, 292, 296, 298, 305, 314, 315, 318, 319, 417, 419, 420, 423, 427, 430, 433,
            434, 435, 438, 439, 440, 442, 444, 445, 447
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testUnionPatternOp2Op1int() {
        int[] op1 = {480, 486, 487, 489, 490, 491, 496, 508, 805, 806, 809, 812, 818, 822, 824, 825, 826, 828, 829};
        int[] op2 = {165, 170, 173, 178, 182, 183, 187, 806, 809, 811, 812, 814, 818, 819, 825, 830, 831};
        int[] union = {
            165, 170, 173, 178, 182, 183, 187, 480, 486, 487, 489, 490, 491, 496, 508, 805, 806, 809, 811, 812, 814,
            818, 819, 822, 824, 825, 826, 828, 829, 830, 831
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testUnionPatternintOp1int() {
        int[] op1 = {
            66, 68, 69, 70, 74, 82, 83, 85, 87, 88, 90, 92, 385, 389, 390, 392, 398, 401, 405, 484, 487, 488, 493, 495,
            503, 505, 509, 510
        };
        int[] op2 = {64, 66, 67, 70, 74, 83, 85, 88, 90, 92, 481, 485, 493, 495, 501, 503, 508, 510, 511};
        int[] union = {
            64, 66, 67, 68, 69, 70, 74, 82, 83, 85, 87, 88, 90, 92, 385, 389, 390, 392, 398, 401, 405, 481, 484, 485,
            487, 488, 493, 495, 501, 503, 505, 508, 509, 510, 511
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testUnionPatterndisOp1int() {
        int[] op1 = {
            97, 101, 107, 116, 117, 417, 423, 428, 434, 436, 443, 447, 512, 514, 524, 527, 534, 535, 536, 537, 541
        };
        int[] op2 = {104, 105, 108, 111, 118, 124, 513, 515, 519, 522, 524, 525, 530, 535, 536, 537, 541};
        int[] union = {
            97, 101, 104, 105, 107, 108, 111, 116, 117, 118, 124, 417, 423, 428, 434, 436, 443, 447, 512, 513, 514, 515,
            519, 522, 524, 525, 527, 530, 534, 535, 536, 537, 541
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testUnionPatternOp1Op2int() {
        int[] op1 = {5, 6, 18, 19, 20, 21, 25, 27, 131, 132, 136, 138, 143, 145, 146, 147, 149, 158};
        int[] op2 = {67, 68, 70, 71, 84, 86, 87, 131, 132, 136, 138, 141, 143, 145, 147, 149, 157, 158};
        int[] union = {
            5, 6, 18, 19, 20, 21, 25, 27, 67, 68, 70, 71, 84, 86, 87, 131, 132, 136, 138, 141, 143, 145, 146, 147, 149,
            157, 158
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	 yes
     */
    @Test
    void testUnionPatternOp2Op2int() {
        int[] op1 = {608, 609, 610, 611, 614, 617, 623, 633, 636, 638};
        int[] op2 = {
            230, 231, 232, 233, 239, 250, 255, 291, 295, 301, 303, 305, 308, 315, 319, 608, 609, 614, 617, 619, 626,
            627, 630, 633, 635
        };
        int[] union = {
            230, 231, 232, 233, 239, 250, 255, 291, 295, 301, 303, 305, 308, 315, 319, 608, 609, 610, 611, 614, 617,
            619, 623, 626, 627, 630, 633, 635, 636, 638
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	 yes
     */
    @Test
    void testUnionPatternintOp2int() {
        int[] op1 = {0, 4, 9, 19, 20, 21, 23, 28, 29, 30, 419, 420, 421, 423, 424, 431, 438, 441, 443, 445};
        int[] op2 = {
            0, 3, 4, 11, 13, 16, 18, 19, 22, 28, 30, 31, 167, 169, 171, 174, 176, 186, 188, 190, 421, 423, 428, 433,
            438, 439, 443, 445
        };
        int[] union = {
            0, 3, 4, 9, 11, 13, 16, 18, 19, 20, 21, 22, 23, 28, 29, 30, 31, 167, 169, 171, 174, 176, 186, 188, 190, 419,
            420, 421, 423, 424, 428, 431, 433, 438, 439, 441, 443, 445
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	 yes
     */
    @Test
    void testUnionPatterndisOp2int() {
        int[] op1 = {96, 99, 100, 102, 112, 118, 119, 524, 525, 531, 532, 533, 534, 536, 538, 539, 543};
        int[] op2 = {
            103, 105, 111, 114, 122, 124, 126, 386, 395, 405, 407, 410, 411, 412, 413, 512, 526, 528, 529, 530, 531,
            532, 533, 538, 543
        };
        int[] union = {
            96, 99, 100, 102, 103, 105, 111, 112, 114, 118, 119, 122, 124, 126, 386, 395, 405, 407, 410, 411, 412, 413,
            512, 524, 525, 526, 528, 529, 530, 531, 532, 533, 534, 536, 538, 539, 543
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testUnionPatternOp1intint() {
        int[] op1 = {
            102, 107, 111, 112, 120, 125, 256, 258, 260, 261, 262, 263, 264, 284, 286, 576, 582, 587, 590, 592, 594,
            595, 596, 598, 599, 605
        };
        int[] op2 = {
            258, 260, 261, 262, 267, 273, 278, 281, 285, 286, 579, 580, 581, 582, 584, 586, 594, 596, 598, 599
        };
        int[] union = {
            102, 107, 111, 112, 120, 125, 256, 258, 260, 261, 262, 263, 264, 267, 273, 278, 281, 284, 285, 286, 576,
            579, 580, 581, 582, 584, 586, 587, 590, 592, 594, 595, 596, 598, 599, 605
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	 yes
     */
    @Test
    void testUnionPatternOp2intint() {
        int[] op1 = {
            288, 290, 294, 295, 297, 301, 302, 305, 306, 309, 319, 612, 614, 616, 623, 625, 630, 632, 636, 637, 639
        };
        int[] op2 = {
            132, 134, 140, 142, 146, 154, 155, 288, 294, 301, 303, 305, 306, 312, 315, 316, 317, 610, 616, 617, 621,
            622, 623, 625, 628, 630, 634, 636
        };
        int[] union = {
            132, 134, 140, 142, 146, 154, 155, 288, 290, 294, 295, 297, 301, 302, 303, 305, 306, 309, 312, 315, 316,
            317, 319, 610, 612, 614, 616, 617, 621, 622, 623, 625, 628, 630, 632, 634, 636, 637, 639
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	 yes
     */
    @Test
    void testUnionPatternintintint() {
        int[] op1 = {
            96, 97, 99, 100, 105, 108, 109, 112, 116, 124, 199, 200, 206, 209, 212, 217, 219, 221, 222, 223, 390, 391,
            395, 397, 398, 400, 405, 408, 413
        };
        int[] op2 = {
            96, 97, 98, 99, 101, 109, 110, 112, 116, 117, 124, 126, 200, 203, 206, 207, 208, 209, 210, 213, 219, 221,
            223, 387, 391, 395, 397, 405, 406, 408, 411, 412, 413, 415
        };
        int[] union = {
            96, 97, 98, 99, 100, 101, 105, 108, 109, 110, 112, 116, 117, 124, 126, 199, 200, 203, 206, 207, 208, 209,
            210, 212, 213, 217, 219, 221, 222, 223, 387, 390, 391, 395, 397, 398, 400, 405, 406, 408, 411, 412, 413, 415
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	 yes
     */
    @Test
    void testUnionPatterndisintint() {
        int[] op1 = {
            132, 135, 138, 140, 142, 145, 151, 291, 296, 297, 298, 310, 311, 312, 314, 316, 417, 419, 422, 425, 432,
            434, 437, 446
        };
        int[] op2 = {
            131, 146, 148, 152, 154, 293, 297, 298, 302, 305, 306, 312, 314, 316, 317, 319, 416, 419, 420, 422, 425,
            432, 434, 443, 444
        };
        int[] union = {
            131, 132, 135, 138, 140, 142, 145, 146, 148, 151, 152, 154, 291, 293, 296, 297, 298, 302, 305, 306, 310,
            311, 312, 314, 316, 317, 319, 416, 417, 419, 420, 422, 425, 432, 434, 437, 443, 444, 446
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testUnionPatternOp1disint() {
        int[] op1 = {
            128, 129, 130, 141, 142, 151, 156, 258, 259, 261, 267, 269, 272, 281, 515, 518, 519, 520, 522, 531, 535,
            536, 537, 539, 540, 542
        };
        int[] op2 = {260, 271, 273, 274, 278, 285, 286, 512, 515, 521, 526, 532, 535, 538, 539, 540, 542};
        int[] union = {
            128, 129, 130, 141, 142, 151, 156, 258, 259, 260, 261, 267, 269, 271, 272, 273, 274, 278, 281, 285, 286,
            512, 515, 518, 519, 520, 521, 522, 526, 531, 532, 535, 536, 537, 538, 539, 540, 542
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	 yes
     */
    @Test
    void testUnionPatternOp2disint() {
        int[] op1 = {458, 460, 461, 463, 469, 477, 738, 741, 742, 744, 752, 759, 760, 761, 767};
        int[] op2 = {
            164, 168, 169, 172, 174, 179, 180, 189, 454, 462, 464, 470, 474, 475, 739, 741, 742, 744, 749, 752, 758,
            759, 761, 765, 766, 767
        };
        int[] union = {
            164, 168, 169, 172, 174, 179, 180, 189, 454, 458, 460, 461, 462, 463, 464, 469, 470, 474, 475, 477, 738,
            739, 741, 742, 744, 749, 752, 758, 759, 760, 761, 765, 766, 767
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	 yes
     */
    @Test
    void testUnionPatternintdisint() {
        int[] op1 = {
            2, 8, 13, 16, 18, 22, 24, 28, 29, 75, 76, 88, 89, 224, 225, 227, 231, 232, 234, 237, 238, 242, 248
        };
        int[] op2 = {
            3, 7, 12, 13, 17, 19, 20, 24, 25, 29, 31, 81, 87, 91, 92, 225, 227, 230, 232, 234, 240, 242, 245, 246, 247,
            248, 249
        };
        int[] union = {
            2, 3, 7, 8, 12, 13, 16, 17, 18, 19, 20, 22, 24, 25, 28, 29, 31, 75, 76, 81, 87, 88, 89, 91, 92, 224, 225,
            227, 230, 231, 232, 234, 237, 238, 240, 242, 245, 246, 247, 248, 249
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	 yes
     */
    @Test
    void testUnionPatterndisdisint() {
        int[] op1 = {98, 99, 100, 101, 114, 256, 262, 267, 272, 273, 280, 544, 546, 552, 554, 561, 564, 569, 570, 573};
        int[] op2 = {
            96, 103, 104, 106, 115, 259, 263, 264, 266, 269, 270, 279, 546, 548, 549, 551, 559, 561, 565, 569, 570, 573
        };
        int[] union = {
            96, 98, 99, 100, 101, 103, 104, 106, 114, 115, 256, 259, 262, 263, 264, 266, 267, 269, 270, 272, 273, 279,
            280, 544, 546, 548, 549, 551, 552, 554, 559, 561, 564, 565, 569, 570, 573
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testUnionPatternOp1Op1dis() {
        int[] op1 = {
            261, 264, 265, 270, 271, 275, 279, 286, 386, 394, 395, 398, 400, 405, 406, 672, 675, 677, 683, 687, 693,
            695, 702
        };
        int[] op2 = {680, 684, 689, 691, 694, 698, 700};
        int[] union = {
            261, 264, 265, 270, 271, 275, 279, 286, 386, 394, 395, 398, 400, 405, 406, 672, 675, 677, 680, 683, 684,
            687, 689, 691, 693, 694, 695, 698, 700, 702
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	     	-----
     *   overlap?        	     	empty
     */
    @Test
    void testUnionPatternOp2Op1dis() {
        int[] op1 = {196, 208, 213, 214, 217, 218, 295, 304, 310, 312, 317};
        int[] op2 = {65, 68, 73, 78, 79, 80, 289, 299, 302, 311, 314, 318};
        int[] union = {
            65, 68, 73, 78, 79, 80, 196, 208, 213, 214, 217, 218, 289, 295, 299, 302, 304, 310, 311, 312, 314, 317, 318
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testUnionPatternintOp1dis() {
        int[] op1 = {
            96, 98, 103, 109, 111, 114, 117, 118, 119, 120, 127, 418, 422, 423, 434, 437, 440, 444, 745, 749, 754
        };
        int[] op2 = {96, 98, 99, 101, 109, 112, 113, 114, 119, 124, 125, 127, 741, 747, 757, 763};
        int[] union = {
            96, 98, 99, 101, 103, 109, 111, 112, 113, 114, 117, 118, 119, 120, 124, 125, 127, 418, 422, 423, 434, 437,
            440, 444, 741, 745, 747, 749, 754, 757, 763
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	     	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testUnionPatterndisOp1dis() {
        int[] op1 = {0, 3, 14, 16, 25, 322, 324, 325, 330, 339, 341, 342, 348, 423, 424, 432, 442, 446};
        int[] op2 = {2, 12, 20, 24, 26, 29, 429, 431, 434, 436, 443};
        int[] union = {
            0, 2, 3, 12, 14, 16, 20, 24, 25, 26, 29, 322, 324, 325, 330, 339, 341, 342, 348, 423, 424, 429, 431, 432,
            434, 436, 442, 443, 446
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:        	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testUnionPatternOp1Op2dis() {
        int[] op1 = {100, 104, 106, 107, 115, 118, 124, 126, 587, 594, 598, 602};
        int[] op2 = {256, 260, 266, 268, 270, 280, 284, 287, 583, 586, 590, 599, 606};
        int[] union = {
            100, 104, 106, 107, 115, 118, 124, 126, 256, 260, 266, 268, 270, 280, 284, 287, 583, 586, 587, 590, 594,
            598, 599, 602, 606
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----
     *      set2:   -----	-----	-----
     *   overlap?        	     	empty
     */
    @Test
    void testUnionPatternOp2Op2dis() {
        int[] op1 = {480, 497, 500, 504, 505, 509};
        int[] op2 = {
            160, 173, 174, 176, 183, 187, 188, 192, 195, 206, 215, 216, 218, 219, 220, 481, 482, 486, 487, 495, 502, 510
        };
        int[] union = {
            160, 173, 174, 176, 183, 187, 188, 192, 195, 206, 215, 216, 218, 219, 220, 480, 481, 482, 486, 487, 495,
            497, 500, 502, 504, 505, 509, 510
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	     	empty
     */
    @Test
    void testUnionPatternintOp2dis() {
        int[] op1 = {64, 74, 75, 76, 77, 81, 83, 84, 88, 90, 325, 336, 339, 342, 344, 346};
        int[] op2 = {
            64, 68, 71, 73, 81, 84, 85, 88, 93, 292, 301, 303, 306, 313, 314, 315, 320, 321, 347, 348, 350, 351
        };
        int[] union = {
            64, 68, 71, 73, 74, 75, 76, 77, 81, 83, 84, 85, 88, 90, 93, 292, 301, 303, 306, 313, 314, 315, 320, 321,
            325, 336, 339, 342, 344, 346, 347, 348, 350, 351
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	     	empty
     */
    @Test
    void testUnionPatterndisOp2dis() {
        int[] op1 = {33, 47, 50, 52, 418, 421, 424, 428, 429, 431, 435, 445};
        int[] op2 = {35, 43, 46, 48, 63, 104, 107, 110, 111, 113, 114, 116, 416, 417, 436, 438, 446};
        int[] union = {
            33, 35, 43, 46, 47, 48, 50, 52, 63, 104, 107, 110, 111, 113, 114, 116, 416, 417, 418, 421, 424, 428, 429,
            431, 435, 436, 438, 445, 446
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testUnionPatternOp1intdis() {
        int[] op1 = {
            133, 136, 140, 144, 146, 147, 152, 156, 224, 229, 230, 233, 236, 243, 244, 246, 250, 251, 252, 450, 455,
            457, 459, 463, 475
        };
        int[] op2 = {229, 232, 233, 236, 240, 241, 242, 243, 245, 247, 255, 449, 461, 464, 467, 473, 479};
        int[] union = {
            133, 136, 140, 144, 146, 147, 152, 156, 224, 229, 230, 232, 233, 236, 240, 241, 242, 243, 244, 245, 246,
            247, 250, 251, 252, 255, 449, 450, 455, 457, 459, 461, 463, 464, 467, 473, 475, 479
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	 yes 	empty
     */
    @Test
    void testUnionPatternOp2intdis() {
        int[] op1 = {192, 193, 195, 202, 210, 212, 213, 216, 219, 221, 222, 223, 451, 458, 462, 473, 478};
        int[] op2 = {
            129, 134, 137, 138, 140, 150, 154, 159, 193, 194, 195, 200, 201, 202, 209, 210, 211, 212, 213, 218, 450,
            452, 463, 466, 471, 472, 477
        };
        int[] union = {
            129, 134, 137, 138, 140, 150, 154, 159, 192, 193, 194, 195, 200, 201, 202, 209, 210, 211, 212, 213, 216,
            218, 219, 221, 222, 223, 450, 451, 452, 458, 462, 463, 466, 471, 472, 473, 477, 478
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	 yes 	empty
     */
    @Test
    void testUnionPatternintintdis() {
        int[] op1 = {
            32, 33, 35, 41, 47, 49, 52, 53, 60, 61, 62, 63, 291, 295, 296, 300, 304, 306, 307, 309, 317, 462, 463, 465,
            466, 470
        };
        int[] op2 = {
            32, 34, 37, 38, 43, 44, 48, 52, 53, 59, 60, 291, 299, 300, 302, 303, 304, 306, 313, 454, 455, 461, 473, 477
        };
        int[] union = {
            32, 33, 34, 35, 37, 38, 41, 43, 44, 47, 48, 49, 52, 53, 59, 60, 61, 62, 63, 291, 295, 296, 299, 300, 302,
            303, 304, 306, 307, 309, 313, 317, 454, 455, 461, 462, 463, 465, 466, 470, 473, 477
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	 yes 	empty
     */
    @Test
    void testUnionPatterndisintdis() {
        int[] op1 = {99, 108, 110, 121, 122, 322, 324, 326, 331, 332, 336, 344, 347, 641, 645, 648, 659, 665};
        int[] op2 = {
            107, 111, 113, 115, 125, 324, 326, 331, 332, 337, 339, 343, 344, 346, 347, 349, 350, 644, 650, 653, 658,
            668, 670
        };
        int[] union = {
            99, 107, 108, 110, 111, 113, 115, 121, 122, 125, 322, 324, 326, 331, 332, 336, 337, 339, 343, 344, 346, 347,
            349, 350, 641, 644, 645, 648, 650, 653, 658, 659, 665, 668, 670
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:        	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testUnionPatternOp1disdis() {
        int[] op1 = {96, 97, 114, 119, 120, 122, 125, 127, 389, 396, 408, 410, 415, 609, 617, 619, 623, 630, 637};
        int[] op2 = {385, 391, 392, 398, 412, 614, 618, 626, 628, 633};
        int[] union = {
            96, 97, 114, 119, 120, 122, 125, 127, 385, 389, 391, 392, 396, 398, 408, 410, 412, 415, 609, 614, 617, 618,
            619, 623, 626, 628, 630, 633, 637
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?        	empty	empty
     */
    @Test
    void testUnionPatternOp2disdis() {
        int[] op1 = {231, 236, 239, 242, 243, 255, 352, 367, 374, 379, 381, 382};
        int[] op2 = {194, 202, 207, 210, 215, 216, 223, 230, 237, 238, 245, 254, 359, 362, 364, 370, 376, 377, 378};
        int[] union = {
            194, 202, 207, 210, 215, 216, 223, 230, 231, 236, 237, 238, 239, 242, 243, 245, 254, 255, 352, 359, 362,
            364, 367, 370, 374, 376, 377, 378, 379, 381, 382
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?    yes 	empty	empty
     */
    @Test
    void testUnionPatternintdisdis() {
        int[] op1 = {33, 40, 42, 44, 45, 46, 52, 53, 54, 57, 58, 61, 358, 370, 373, 385, 388, 392, 393, 395, 401, 414};
        int[] op2 = {
            33, 36, 40, 44, 45, 46, 48, 51, 52, 57, 59, 60, 361, 369, 371, 372, 381, 384, 390, 394, 399, 402, 408
        };
        int[] union = {
            33, 36, 40, 42, 44, 45, 46, 48, 51, 52, 53, 54, 57, 58, 59, 60, 61, 358, 361, 369, 370, 371, 372, 373, 381,
            384, 385, 388, 390, 392, 393, 394, 395, 399, 401, 402, 408, 414
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----
     *      set2:   -----	-----	-----
     *   overlap?   empty	empty	empty
     */
    @Test
    void testUnionPatterndisdisdis() {
        int[] op1 = {
            263, 270, 271, 280, 284, 512, 520, 523, 534, 538, 541, 542, 641, 644, 661, 662, 664, 668, 669, 671
        };
        int[] op2 = {260, 261, 277, 278, 283, 513, 516, 527, 530, 531, 532, 540, 640, 643, 648, 652, 658, 659, 660};
        int[] union = {
            260, 261, 263, 270, 271, 277, 278, 280, 283, 284, 512, 513, 516, 520, 523, 527, 530, 531, 532, 534, 538,
            540, 541, 542, 640, 641, 643, 644, 648, 652, 658, 659, 660, 661, 662, 664, 668, 669, 671
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	     	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	-----	-----	-----
     *   overlap?    yes 	     	     	empty	 yes 	empty	empty
     */
    @Test
    void testUnionPatternintOp2Op2disintdisdis() {
        int[] op1 = {
            32, 33, 36, 39, 43, 47, 48, 49, 50, 51, 53, 737, 738, 739, 750, 752, 757, 759, 928, 930, 934, 940, 942, 950,
            951, 952, 953, 955, 958, 966, 967, 974, 981, 985, 1189, 1192, 1206, 1207, 1214, 1215
        };
        int[] op2 = {
            35, 36, 40, 46, 47, 48, 51, 53, 55, 59, 61, 321, 328, 329, 332, 341, 343, 347, 350, 641, 647, 648, 652, 656,
            660, 661, 671, 744, 747, 748, 751, 753, 764, 765, 767, 928, 930, 934, 935, 946, 948, 951, 952, 953, 954,
            965, 973, 980, 988, 989, 990, 1195, 1200, 1201, 1202, 1204, 1209, 1211
        };
        int[] union = {
            32, 33, 35, 36, 39, 40, 43, 46, 47, 48, 49, 50, 51, 53, 55, 59, 61, 321, 328, 329, 332, 341, 343, 347, 350,
            641, 647, 648, 652, 656, 660, 661, 671, 737, 738, 739, 744, 747, 748, 750, 751, 752, 753, 757, 759, 764,
            765, 767, 928, 930, 934, 935, 940, 942, 946, 948, 950, 951, 952, 953, 954, 955, 958, 965, 966, 967, 973,
            974, 980, 981, 985, 988, 989, 990, 1189, 1192, 1195, 1200, 1201, 1202, 1204, 1206, 1207, 1209, 1211, 1214,
            1215
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	-----	-----	     	-----
     *      set2:   -----	-----	     	-----	-----	-----	-----
     *   overlap?        	 yes 	     	 yes 	 yes 	     	empty
     */
    @Test
    void testUnionPatternOp2intOp1intintOp2dis() {
        int[] op1 = {
            323, 324, 327, 334, 336, 337, 342, 344, 346, 347, 350, 581, 591, 595, 598, 599, 602, 607, 832, 833, 834,
            835, 837, 840, 843, 844, 850, 854, 859, 961, 966, 973, 979, 980, 982, 985, 988, 989, 1312, 1319, 1327, 1330,
            1332, 1338
        };
        int[] op2 = {
            291, 296, 299, 301, 306, 309, 318, 319, 322, 327, 330, 339, 341, 344, 346, 347, 350, 351, 834, 836, 837,
            843, 851, 854, 855, 856, 857, 858, 859, 960, 962, 964, 973, 977, 980, 985, 986, 987, 1152, 1155, 1160, 1170,
            1172, 1183, 1324, 1328, 1329, 1334, 1342
        };
        int[] union = {
            291, 296, 299, 301, 306, 309, 318, 319, 322, 323, 324, 327, 330, 334, 336, 337, 339, 341, 342, 344, 346,
            347, 350, 351, 581, 591, 595, 598, 599, 602, 607, 832, 833, 834, 835, 836, 837, 840, 843, 844, 850, 851,
            854, 855, 856, 857, 858, 859, 960, 961, 962, 964, 966, 973, 977, 979, 980, 982, 985, 986, 987, 988, 989,
            1152, 1155, 1160, 1170, 1172, 1183, 1312, 1319, 1324, 1327, 1328, 1329, 1330, 1332, 1334, 1338, 1342
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	-----	-----	-----	-----
     *      set2:   -----	     	-----	     	     	-----
     *   overlap?        	     	 yes 	     	     	empty
     */
    @Test
    void testUnionPatternOp2Op1intOp1Op1disOp1() {
        int[] op1 = {
            133, 138, 145, 149, 152, 156, 158, 353, 355, 360, 362, 364, 367, 371, 373, 376, 381, 382, 609, 622, 624,
            626, 627, 628, 632, 774, 783, 785, 787, 795, 799, 802, 808, 811, 818, 823, 829, 935, 940, 943, 944, 957, 959
        };
        int[] op2 = {
            97, 106, 109, 113, 118, 126, 127, 363, 366, 367, 368, 371, 373, 376, 381, 382, 383, 804, 806, 812, 815, 825
        };
        int[] union = {
            97, 106, 109, 113, 118, 126, 127, 133, 138, 145, 149, 152, 156, 158, 353, 355, 360, 362, 363, 364, 366, 367,
            368, 371, 373, 376, 381, 382, 383, 609, 622, 624, 626, 627, 628, 632, 774, 783, 785, 787, 795, 799, 802,
            804, 806, 808, 811, 812, 815, 818, 823, 825, 829, 935, 940, 943, 944, 957, 959
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	     	-----
     *      set2:        	-----	-----	-----	-----	-----	-----
     *   overlap?        	empty	 yes 	 yes 	     	empty
     */
    @Test
    void testUnionPatternOp1disintintOp2disOp2() {
        int[] op1 = {
            225, 237, 239, 240, 245, 246, 252, 254, 288, 292, 296, 301, 306, 318, 576, 579, 580, 587, 591, 593, 595,
            601, 603, 606, 840, 842, 846, 847, 852, 853, 854, 855, 857, 860, 861, 1120, 1121, 1128, 1135, 1147, 1149,
            1150
        };
        int[] op2 = {
            291, 295, 300, 308, 313, 314, 317, 579, 582, 585, 586, 587, 591, 592, 593, 599, 601, 834, 841, 843, 846,
            847, 848, 850, 851, 854, 855, 860, 930, 935, 936, 942, 947, 1124, 1130, 1131, 1136, 1137, 1141, 1148, 1151,
            1191, 1198, 1203, 1205, 1210, 1214, 1215
        };
        int[] union = {
            225, 237, 239, 240, 245, 246, 252, 254, 288, 291, 292, 295, 296, 300, 301, 306, 308, 313, 314, 317, 318,
            576, 579, 580, 582, 585, 586, 587, 591, 592, 593, 595, 599, 601, 603, 606, 834, 840, 841, 842, 843, 846,
            847, 848, 850, 851, 852, 853, 854, 855, 857, 860, 861, 930, 935, 936, 942, 947, 1120, 1121, 1124, 1128,
            1130, 1131, 1135, 1136, 1137, 1141, 1147, 1148, 1149, 1150, 1151, 1191, 1198, 1203, 1205, 1210, 1214, 1215
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	     	-----	     	-----	-----
     *      set2:   -----	-----	-----	-----	-----	-----	-----
     *   overlap?        	     	empty	     	empty	 yes
     */
    @Test
    void testUnionPatternOp2Op2disOp2disintOp2() {
        int[] op1 = {
            609, 614, 621, 629, 1025, 1036, 1037, 1038, 1046, 1051, 1089, 1094, 1095, 1096, 1101, 1102, 1103, 1106,
            1108, 1110, 1111
        };
        int[] op2 = {
            289, 297, 308, 311, 316, 318, 481, 486, 489, 492, 497, 500, 506, 508, 616, 618, 630, 635, 736, 737, 738,
            741, 744, 745, 1024, 1027, 1034, 1041, 1055, 1089, 1092, 1095, 1096, 1099, 1106, 1107, 1110, 1111, 1113,
            1119, 1248, 1254, 1257, 1260, 1265, 1268, 1276, 1279
        };
        int[] union = {
            289, 297, 308, 311, 316, 318, 481, 486, 489, 492, 497, 500, 506, 508, 609, 614, 616, 618, 621, 629, 630,
            635, 736, 737, 738, 741, 744, 745, 1024, 1025, 1027, 1034, 1036, 1037, 1038, 1041, 1046, 1051, 1055, 1089,
            1092, 1094, 1095, 1096, 1099, 1101, 1102, 1103, 1106, 1107, 1108, 1110, 1111, 1113, 1119, 1248, 1254, 1257,
            1260, 1265, 1268, 1276, 1279
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	-----	-----	-----	-----	-----	-----
     *      set2:   -----	     	-----	-----	-----	     	     	-----	-----
     *   overlap?   empty	     	empty	 yes 	 yes 	     	     	empty	empty
     */
    @Test
    void testUnionPatterndisOp1disintintOp1Op1disdis() {
        int[] op1 = {
            100, 104, 107, 115, 117, 357, 363, 367, 369, 373, 380, 383, 417, 422, 428, 433, 442, 443, 610, 616, 618,
            620, 621, 622, 624, 633, 635, 639, 737, 738, 741, 742, 743, 744, 745, 749, 753, 759, 764, 1024, 1027, 1029,
            1040, 1045, 1047, 1049, 1313, 1320, 1329, 1330, 1332, 1339, 1341, 1343, 1507, 1516, 1518, 1519, 1531, 1792,
            1793, 1806, 1809, 1819
        };
        int[] op2 = {
            99, 101, 102, 108, 118, 120, 418, 423, 432, 439, 444, 447, 610, 614, 617, 618, 620, 622, 623, 624, 626, 637,
            638, 737, 738, 749, 752, 754, 755, 757, 759, 762, 764, 766, 1511, 1517, 1521, 1525, 1527, 1798, 1804, 1811,
            1818
        };
        int[] union = {
            99, 100, 101, 102, 104, 107, 108, 115, 117, 118, 120, 357, 363, 367, 369, 373, 380, 383, 417, 418, 422, 423,
            428, 432, 433, 439, 442, 443, 444, 447, 610, 614, 616, 617, 618, 620, 621, 622, 623, 624, 626, 633, 635,
            637, 638, 639, 737, 738, 741, 742, 743, 744, 745, 749, 752, 753, 754, 755, 757, 759, 762, 764, 766, 1024,
            1027, 1029, 1040, 1045, 1047, 1049, 1313, 1320, 1329, 1330, 1332, 1339, 1341, 1343, 1507, 1511, 1516, 1517,
            1518, 1519, 1521, 1525, 1527, 1531, 1792, 1793, 1798, 1804, 1806, 1809, 1811, 1818, 1819
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	     	-----	-----	     	-----	-----	-----
     *      set2:        	-----	-----	-----	     	-----	-----
     *   overlap?        	 yes 	     	empty	     	     	empty
     */
    @Test
    void testUnionPatternOp1intOp2disOp1Op2disOp1Op1() {
        int[] op1 = {
            262, 269, 270, 271, 272, 276, 280, 416, 420, 421, 422, 427, 432, 434, 440, 441, 444, 446, 447, 578, 581,
            582, 594, 595, 601, 604, 741, 743, 745, 748, 750, 757, 762, 763, 1152, 1159, 1167, 1174, 1183, 1348, 1350,
            1356, 1359, 1371, 1374, 1666, 1668, 1669, 1674, 1684, 1686, 1688, 1691
        };
        int[] op2 = {
            427, 430, 431, 432, 433, 434, 435, 441, 442, 447, 512, 523, 527, 531, 534, 537, 542, 543, 577, 583, 592,
            593, 598, 602, 964, 974, 978, 981, 983, 988, 990, 1154, 1155, 1162, 1166, 1168
        };
        int[] union = {
            262, 269, 270, 271, 272, 276, 280, 416, 420, 421, 422, 427, 430, 431, 432, 433, 434, 435, 440, 441, 442,
            444, 446, 447, 512, 523, 527, 531, 534, 537, 542, 543, 577, 578, 581, 582, 583, 592, 593, 594, 595, 598,
            601, 602, 604, 741, 743, 745, 748, 750, 757, 762, 763, 964, 974, 978, 981, 983, 988, 990, 1152, 1154, 1155,
            1159, 1162, 1166, 1167, 1168, 1174, 1183, 1348, 1350, 1356, 1359, 1371, 1374, 1666, 1668, 1669, 1674, 1684,
            1686, 1688, 1691
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:        	-----	-----	     	-----	-----	-----	-----	-----
     *      set2:   -----	-----	-----	-----	     	-----	     	-----	-----
     *   overlap?        	empty	empty	     	     	 yes 	     	 yes 	empty
     */
    @Test
    void testUnionPatternOp2disdisOp2Op1intOp1intdis() {
        int[] op1 = {
            513, 515, 520, 523, 531, 533, 539, 543, 672, 677, 681, 685, 691, 697, 699, 961, 966, 972, 974, 978, 987,
            988, 991, 1186, 1189, 1195, 1199, 1201, 1204, 1206, 1207, 1209, 1214, 1215, 1314, 1317, 1323, 1332, 1335,
            1338, 1341, 1600, 1603, 1606, 1612, 1613, 1614, 1615, 1616, 1619, 1623, 1624, 1762, 1769, 1781, 1788, 1790,
            1791
        };
        int[] op2 = {
            300, 303, 304, 306, 307, 310, 313, 512, 516, 529, 536, 540, 676, 679, 680, 683, 693, 694, 696, 882, 883,
            886, 888, 891, 893, 1186, 1188, 1189, 1199, 1202, 1206, 1208, 1209, 1212, 1215, 1600, 1601, 1606, 1607,
            1608, 1610, 1611, 1615, 1619, 1626, 1629, 1768, 1773, 1776, 1779, 1784
        };
        int[] union = {
            300, 303, 304, 306, 307, 310, 313, 512, 513, 515, 516, 520, 523, 529, 531, 533, 536, 539, 540, 543, 672,
            676, 677, 679, 680, 681, 683, 685, 691, 693, 694, 696, 697, 699, 882, 883, 886, 888, 891, 893, 961, 966,
            972, 974, 978, 987, 988, 991, 1186, 1188, 1189, 1195, 1199, 1201, 1202, 1204, 1206, 1207, 1208, 1209, 1212,
            1214, 1215, 1314, 1317, 1323, 1332, 1335, 1338, 1341, 1600, 1601, 1603, 1606, 1607, 1608, 1610, 1611, 1612,
            1613, 1614, 1615, 1616, 1619, 1623, 1624, 1626, 1629, 1762, 1768, 1769, 1773, 1776, 1779, 1781, 1784, 1788,
            1790, 1791
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	     	-----	-----	-----	-----	     	-----
     *      set2:        	-----	-----	     	     	-----	-----	-----	-----
     *   overlap?        	     	 yes 	     	     	 yes 	     	empty
     */
    @Test
    void testUnionPatternOp1Op2intOp1Op1intOp2disOp2() {
        int[] op1 = {
            128, 129, 132, 134, 138, 150, 154, 158, 321, 322, 323, 326, 328, 330, 331, 335, 341, 344, 350, 610, 613,
            620, 623, 631, 634, 638, 639, 842, 843, 848, 849, 856, 859, 961, 964, 969, 979, 985, 987, 989, 990, 991,
            1122, 1132, 1138, 1141
        };
        int[] op2 = {
            256, 269, 271, 276, 277, 278, 285, 320, 324, 326, 330, 331, 335, 341, 344, 347, 349, 351, 960, 961, 963,
            964, 969, 972, 979, 984, 987, 989, 990, 992, 993, 999, 1006, 1008, 1010, 1011, 1017, 1134, 1142, 1143, 1376,
            1381, 1390, 1391, 1398, 1400, 1402
        };
        int[] union = {
            128, 129, 132, 134, 138, 150, 154, 158, 256, 269, 271, 276, 277, 278, 285, 320, 321, 322, 323, 324, 326,
            328, 330, 331, 335, 341, 344, 347, 349, 350, 351, 610, 613, 620, 623, 631, 634, 638, 639, 842, 843, 848,
            849, 856, 859, 960, 961, 963, 964, 969, 972, 979, 984, 985, 987, 989, 990, 991, 992, 993, 999, 1006, 1008,
            1010, 1011, 1017, 1122, 1132, 1134, 1138, 1141, 1142, 1143, 1376, 1381, 1390, 1391, 1398, 1400, 1402
        };

        assertUnion(op1, op2, union);
    }

    /*
     * Clusters in this test are of the following form:
     *      set1:   -----	-----	-----	     	-----	     	     	-----	-----
     *      set2:        	-----	     	-----	-----	-----	-----
     *   overlap?        	 yes 	     	     	empty
     */
    @Test
    void testUnionPatternOp1intOp1Op2disOp2Op2Op1Op1() {
        int[] op1 = {
            32, 34, 51, 53, 54, 55, 61, 62, 96, 110, 112, 113, 116, 121, 123, 124, 125, 358, 361, 362, 363, 370, 800,
            813, 830, 1217, 1223, 1226, 1229, 1232, 1244, 1247, 1445, 1448, 1450, 1453, 1459, 1462, 1463
        };
        int[] op2 = {
            96, 105, 113, 116, 118, 120, 123, 124, 125, 673, 680, 684, 685, 689, 699, 701, 702, 804, 805, 822, 829, 902,
            907, 911, 917, 919, 920, 922, 924, 962, 964, 967, 971, 972, 974, 977, 991
        };
        int[] union = {
            32, 34, 51, 53, 54, 55, 61, 62, 96, 105, 110, 112, 113, 116, 118, 120, 121, 123, 124, 125, 358, 361, 362,
            363, 370, 673, 680, 684, 685, 689, 699, 701, 702, 800, 804, 805, 813, 822, 829, 830, 902, 907, 911, 917,
            919, 920, 922, 924, 962, 964, 967, 971, 972, 974, 977, 991, 1217, 1223, 1226, 1229, 1232, 1244, 1247, 1445,
            1448, 1450, 1453, 1459, 1462, 1463
        };

        assertUnion(op1, op2, union);
    }

    @Test
    void testAllPresetCasesWithRandomValues() {
        long randomSeed = new SecureRandom().nextLong();
        TestCase[] testCases = new SimpleBinaryTestCaseGenerator().generateAllPresets(randomSeed);
        for (TestCase testCase : testCases) {
            assertUnion(testCase);
        }
    }

    @Test
    @Disabled("Test can be enabled and run until failure to find bugs with random data.")
    void unionBugCatcher() {
        Random random = new SecureRandom();
        long randomSeed = random.nextLong();
        int numberOfClusters = random.nextInt(20);
        TestCase testCase = new SimpleBinaryTestCaseGenerator().generateRandomCase(randomSeed, numberOfClusters);
        assertUnion(testCase);
    }

    private static void assertUnion(TestCase testCase) {
        int[] operand1 = testCase.getOperand1();
        int[] operand2 = testCase.getOperand2();
        int[] expectedUnion = inferUnion(operand1, operand2);
        assertUnion(operand1, operand2, expectedUnion);
    }

    private static void assertUnion(int[] op1, int[] op2, int[] expectedUnion) {
        Statement union = Assertion.assertThat(op1).union(op2).equals(expectedUnion);
        Statement inverted = Assertion.assertThat(op2).union(op1).equals(expectedUnion);
        MultiSetTester.forAllSets(union, inverted);
    }

    private static int[] inferUnion(int[] set1, int[] set2) {
        TreeSet<Integer> treeSet1 = Mappers.toTreeSet(set1);
        TreeSet<Integer> treeSet2 = Mappers.toTreeSet(set2);
        treeSet1.addAll(treeSet2);
        return Mappers.toArray(treeSet1);
    }
}
