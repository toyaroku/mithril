package iterators.assertions;

import iterators.ints.Iterator;
import org.junit.jupiter.api.Assertions;
import util.BitUtil;


public class IteratorAsserter {

    public static Subject assertThat(Iterator subject) {
        return new Subject(subject);
    }

    public static class Subject {

        private final Iterator subject;

        public Subject(Iterator subject) {
            this.subject = subject;
        }

        public void containsExactly(int... expected) {
            assertContainsExactly(subject, expected);
        }

        public void containsExactlyInReverseOrder(int... expected) {
            int[] reversed = BitUtil.copy(expected);
            BitUtil.reverse(reversed);
            assertContainsExactly(subject, reversed);
        }

        public void isEqualTo(Iterator operand) {
            assertEquals(subject, operand);
        }
    }

    public static void assertContainsExactly(Iterator iterator, int... elements) {
        Assertions.assertTrue(containsExactly(iterator, elements));
    }

    public static boolean containsExactly(Iterator iterator, int... elements) {
        for (int i = 0; i < elements.length; i++) {
            int expectedElement = elements[i];
            if (!iterator.hasNext()) {
                return false;
            }
            int actualElement = iterator.next();
            if (actualElement != expectedElement) {
                return false;
            }
        }
        return !iterator.hasNext();
    }

    public static void assertEquals(Iterator one, Iterator other) {
        Assertions.assertTrue(areEqual(one, other));
    }

    public static boolean areEqual(Iterator one, Iterator other) {
        while (one.hasNext() || other.hasNext()) {
            if (one.hasNext() != other.hasNext()) {
                return false;
            }
            if (one.next() != other.next()) {
                return false;
            }
        }
        return true;
    }
}
