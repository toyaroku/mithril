package util;

import collections.Collections;

import java.util.Random;
import java.util.TreeSet;

import static collections.Collections.noSuchInteger;
import static util.BitUtil.firstBit;
import static util.BitUtil.nextSetBitInclusive;

public class SparseIntGenerator {

    private final Random random;
    private final int maxElement;

    public SparseIntGenerator(long seed, int maxElement) {
        this.random = new Random(seed);
        this.maxElement = maxElement;
    }

    private void sparsityCheck(int size) {
        if (size > maxElement / 8) {
            throw new UnsupportedOperationException();
        }
    }

    public int[] equalProbabilityGuaranteedSize(int size) {
        sparsityCheck(size);
        TreeSet<Integer> elements = new TreeSet<>();
        while (elements.size() < size) {
            elements.add(generateElement());
        }
        int[] array = new int[size];
        int index = 0;
        for (int element : elements) {
            array[index++] = element;
        }
        return array;
    }

    public ClusteredOperands clusteredPair(Containment[] clusters, int gap, int clusterSize, int generations) {
        long[] first = new long[clusters.length];
        long[] second = new long[clusters.length];
        int[] clustersSinceStart = new int[clusters.length];
        for (int i = 0; i < clusters.length; i++) {
            if (clusters[i].op1Present()) {
                long word = generate(clusterSize, generations);
                first[i] = word;
            }
            if (clusters[i].op2Present()) {
                long word = generate(clusterSize, generations);
                second[i] = word;
            }
            if (clusters[i].containsBoth()) {
                if (clusters[i].intersects()) {
                    long overlap = generate(clusterSize, generations / 2);
                    first[i] |= overlap;
                    second[i] |= overlap;
                } else {
                    long overlap = first[i] & second[i];
                    first[i] &= ~overlap;
                    second[i] &= ~overlap;
                }
            }
            int previousClustersFromStart = i == 0 ? -1 : clustersSinceStart[i - 1];
            int clustersFromPrev = random.nextInt(gap);
            clustersSinceStart[i] = previousClustersFromStart + clustersFromPrev + 1;
        }
        Cluster[] set1 = build(clustersSinceStart, clusterSize, first);
        Cluster[] set2 = build(clustersSinceStart, clusterSize, second);
        return new ClusteredOperands(set1, set2);
    }

    private Cluster[] build(int[] clusterIndices, int clusterSize, long[] clusters) {
        Cluster[] builtClusters = new Cluster[clusters.length];
        for (int i = 0; i < clusterIndices.length; i++) {
            int clusterIndexFromStart = clusterIndices[i];
            int offsetFromStart = clusterIndexFromStart * clusterSize;
            long word = clusters[i];
            int[] clusterSet = new int[Long.bitCount(word)];
            int index = 0;
            for (int j = firstBit(word); !Collections.noSuchInteger(j); j = nextSetBitInclusive(word, j + 1)) {
                int element = offsetFromStart + j;
                clusterSet[index++] = element;
            }
            Cluster cluster = new Cluster(clusterSet, offsetFromStart, offsetFromStart + clusterSize);
            builtClusters[i] = cluster;
        }
        return builtClusters;
    }

    private long generate(int maxElement, int maxSize) {
        long word = 0L;
        for (int i = 0; i < maxSize; i++) {
            word = BitUtil.withBitSetAtIndex(word, random.nextInt(maxElement));
        }
        return word;
    }

    public int[] randomMaxAttempts(int maxSize) {
        int size = random.nextInt(maxSize);
        return equalProbabilityMaxAttempts(size);
    }

    public int[] equalProbabilityMaxAttempts(int maxAttempts) {
        TreeSet<Integer> elements = new TreeSet<>();
        for (int i = 0; i < maxAttempts; i++) {
            elements.add(generateElement());
        }
        int[] array = new int[elements.size()];
        int index = 0;
        for (int element : elements) {
            array[index++] = element;
        }
        return array;
    }

    private int generateElement() {
        return random.nextInt(maxElement);
    }

    public enum Containment {

        OP1_ONLY("Op1", true, false),
        OP2_ONLY("Op2", false, true),
        INTERSECTING("Int", true, true),
        DISJOINT("Dis", true, true);

        private final String humanReadable;
        private final boolean op1;
        private final boolean op2;

        Containment(String humanReadable, boolean op1, boolean op2) {
            this.humanReadable = humanReadable;
            this.op1 = op1;
            this.op2 = op2;
        }

        boolean op1Present() {
            return op1;
        }

        boolean op2Present() {
            return op2;
        }

        public boolean isEmpty() {
            return !op1 && !op2;
        }

        public boolean containsBoth() {
            return op1 && op2;
        }

        public boolean intersects() {
            return this == INTERSECTING;
        }

        public static Containment generate(Random random) {
            return values()[random.nextInt(values().length)];
        }

        public static String buildCamelCaseString(Containment[] clusters) {
            StringBuilder stringBuilder = new StringBuilder("Pattern");
            for (Containment cluster : clusters) {
                stringBuilder.append(cluster.humanReadable);
            }
            return stringBuilder.toString();
        }
    }

    public static class Cluster {

        private final int[] elements;
        private final int lowerBoundIncl;
        private  final int upperBoundExcl;

        public Cluster(int[] elements, int lowerBoundIncl, int upperBoundExcl) {
            this.elements = elements;
            this.lowerBoundIncl = lowerBoundIncl;
            this.upperBoundExcl = upperBoundExcl;
        }

        public static int[] toArray(Cluster... clusters) {
            int length = 0;
            for (Cluster cluster : clusters) {
                length += cluster.elements.length;
            }
            int index = 0;
            int[] elements = new int[length];
            for (Cluster cluster : clusters) {
                int clusterLength = cluster.elements.length;
                System.arraycopy(cluster.elements, 0, elements, index, clusterLength);
                index += clusterLength;
            }
            return elements;
        }

        public int[] getElements() {
            return elements;
        }

        public int getLowerBoundIncl() {
            return lowerBoundIncl;
        }

        public int getUpperBoundExcl() {
            return upperBoundExcl;
        }
    }

    public static class ClusteredOperands {

        private final Cluster[] operand1;
        private final Cluster[] operand2;

        public ClusteredOperands(Cluster[] operand1, Cluster[] operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
        }

        public Cluster[] getOperand1Clusters() {
            return operand1;
        }

        public Cluster[] getOperand2Clusters() {
            return operand2;
        }

        public int[] createOperand1Array() {
            return Cluster.toArray(operand1);
        }

        public int[] createOperand2Array() {
            return Cluster.toArray(operand2);
        }
    }
}