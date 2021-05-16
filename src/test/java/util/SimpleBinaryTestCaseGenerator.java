package util;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import util.SparseIntGenerator.ClusteredOperands;
import util.SparseIntGenerator.Containment;

import java.util.Random;

import static util.BitUtil.BITS_PER_INT;
import static util.SparseIntGenerator.Containment.*;

public class SimpleBinaryTestCaseGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBinaryTestCaseGenerator.class);

    private static final int MAX_WORDS_BETWEEN_CLUSTERS = 10;
    private static final int GENERATIONS_PER_WORD = 8;

    private final int bitsPerWord;
    private final int upperBound;

    public SimpleBinaryTestCaseGenerator(int bitsPerWord, int upperBound) {
        this.bitsPerWord = bitsPerWord;
        this.upperBound = upperBound;
    }

    public SimpleBinaryTestCaseGenerator() {
        this(BITS_PER_INT, 10_000);
    }

    public TestCase[] generateAllPresets(long seed) {
        LOGGER.info(() -> "Generating cases from all presets, using random seed " + seed);
        Random random = new Random(seed);
        TestCase[] length1 = generateAllCases(random.nextLong(), bitsPerWord, upperBound, 1);
        TestCase[] length2 = generateAllCases(random.nextLong(), bitsPerWord, upperBound, 2);
        TestCase[] length3 = generateAllCases(random.nextLong(), bitsPerWord, upperBound, 3);
        int totalLength = length1.length + length2.length + length3.length;
        TestCase[] allCases = new TestCase[totalLength];
        System.arraycopy(length1, 0, allCases, 0, length1.length);
        System.arraycopy(length2, 0, allCases, length1.length, length2.length);
        System.arraycopy(length3, 0, allCases, length1.length + length2.length, length3.length);
        return allCases;
    }

    public TestCase generateRandomCase(long seed, int numberOfClusters) {
        LOGGER.info(() -> "Generating random case from seed " + seed + " and nr of clusters: " + numberOfClusters);
        Random random = new Random(seed);
        long seedForContainmentGeneration = random.nextLong();
        Random randomForContainmentGeneration = new Random(seedForContainmentGeneration);
        Containment[] clusters = new Containment[numberOfClusters];
        for (int i = 0; i < numberOfClusters; i++) {
            clusters[i] = Containment.generate(randomForContainmentGeneration);
        }
        long seedForClusterGeneration = random.nextLong();

        return generate(seedForClusterGeneration, bitsPerWord, upperBound, clusters);
    }

    public static TestCase[] generateAllCases(long seed, int bitsPerWord, int upperBound, int numberOfClusters) {
        assert numberOfClusters < 5 : "Override this cap by running generating without assert mode enabled.";
        Random random = new Random(seed);
        Containment[] values = values();
        int[] powers = new int[numberOfClusters + 1];
        for (int i = 0; i < numberOfClusters + 1; i++) {
            powers[i] = (int) Math.pow(values.length, i);
        }
        TestCase[] cases = new TestCase[powers[powers.length - 1]];
        int testCaseIndex = 0;
        for (int i = 0; i < cases.length; i++) {
            Containment[] containment = new Containment[numberOfClusters];
            for (int index = 0; index < numberOfClusters; index++) {
                int containmentIndex = (testCaseIndex / powers[index]) % values.length;
                Containment containmentForCluster = values[containmentIndex];
                containment[index] = containmentForCluster;
            }
            TestCase testCase = generate(random.nextLong(), bitsPerWord, upperBound, containment);
            cases[testCaseIndex++] = testCase;
        }
        return cases;
    }

    private static TestCase generate(long seed, int bitsPerWord, int upperBound, Containment... clusters) {
        SparseIntGenerator sparseIntGenerator = new SparseIntGenerator(seed, upperBound);
        ClusteredOperands clusteredOperands = sparseIntGenerator.clusteredPair(
            clusters, MAX_WORDS_BETWEEN_CLUSTERS, bitsPerWord, GENERATIONS_PER_WORD
        );
        String camelCaseString = Containment.buildCamelCaseString(clusters);
        return new TestCase(camelCaseString, clusters, clusteredOperands);
    }

    public static class TestCase {

        private final String caseName;
        private final Containment[] clusters;
        private final int[] operand1;
        private final int[] operand2;

        public TestCase(String caseName, Containment[] clusters, ClusteredOperands operands) {
            this.caseName = caseName;
            this.clusters = clusters;
            this.operand1 = operands.createOperand1Array();
            this.operand2 = operands.createOperand2Array();
        }

        public String getCaseName() {
            return caseName;
        }

        public int[] getOperand1() {
            return operand1;
        }

        public int[] getOperand2() {
            return operand2;
        }

        public int[] getOperand2Clusters() {
            return operand2;
        }

        public String toDocString() {
            String commentOpenLine = "/*";
            String titleLine = "* Clusters in this test are of the following form:";
            StringBuilder set1Line = new StringBuilder("*      set1:   ");
            StringBuilder set2Line = new StringBuilder("*      set2:   ");
            StringBuilder overlapLine = new StringBuilder("*   overlap?   ");
            String commentClosedLine = "*/";

            String containmentSting = "-----\t";
            String emptySting = "     \t";
            String overlapString = " yes \t";
            String nonOverlapString = "empty\t";
            for (int i = 0; i < clusters.length; i++) {
                Containment cluster = clusters[i];
                set1Line.append(cluster.op1Present() ? containmentSting : emptySting);
                set2Line.append(cluster.op2Present() ? containmentSting : emptySting);
                if (cluster.containsBoth()) {
                    overlapLine.append(cluster.intersects() ? overlapString : nonOverlapString);
                } else {
                    overlapLine.append(emptySting);
                }
            }

            return commentOpenLine + "\n"
                + titleLine + "\n"
                + set1Line + "\n"
                + set2Line + "\n"
                + overlapLine + "\n"
                + commentClosedLine;
        }

        @Override
        public String toString() {
            return caseName;
        }
    }
}
