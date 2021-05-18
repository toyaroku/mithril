package collections.longs.datastructures.arrays;

import collections.longs.datastructures.MutableList;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ResizableArrayTest {

    @Test
    void testInsertAndRemove() {
        MutableList list = ResizableArray.empty();
        List<Long> stdList = new java.util.ArrayList<>();

        Random random = new Random(0L);
        for (int i = 0; i < 100; i++) {
            randomOperation(random, list, stdList);
        }
    }

    private void randomOperation(Random random, MutableList list, List<Long> stdList) {
        int randomInt = random.nextInt(4);
        int randomIndex = stdList.isEmpty() ? 0 : random.nextInt(stdList.size());
        long randomValue = random.nextInt(200);
        switch (randomInt) {
            case 0 -> {
                list.add(randomValue);
                stdList.add(randomValue);
            }
            case 1 -> {
                list.remove(randomIndex);
                stdList.remove(randomIndex);
            }
            case 2 -> {
                list.insert(randomValue, randomIndex);
                stdList.add(randomIndex, randomValue);
            }
            case 3 -> {
                if (!stdList.isEmpty()) {
                    int randIndex2 = random.nextInt(stdList.size());
                    long orig = stdList.get(randomIndex);
                    stdList.set(randomIndex, stdList.get(randIndex2));
                    stdList.set(randIndex2, orig);
                    list.swap(randomIndex, randIndex2);
                }
            }
        }
        assertEquals(list.length(), stdList.size());
        assertEquals(list.isEmpty(), stdList.isEmpty());
        for (int i = 0; i < stdList.size(); i++) {
            long actual = list.get(i);
            long expected = stdList.get(i);
            assertEquals(actual, expected);
        }
    }
}