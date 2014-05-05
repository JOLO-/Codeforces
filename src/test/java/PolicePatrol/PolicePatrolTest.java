package PolicePatrol;


import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PolicePatrolTest {

    @Test
    public void calcDistance_correctnessTest() {
        int[] criminals1 = new int[]{0, 1, 2, 3, 4, 5, 6};
        assertEquals(24, PolicePatrol.calcDistance(criminals1, 1));
        assertEquals(16, PolicePatrol.calcDistance(criminals1, 2));
        assertEquals(12, PolicePatrol.calcDistance(criminals1, 3));

        int[] criminals2 = new int[]{-1, 1, 2, 5, 6};
        assertEquals(22, PolicePatrol.calcDistance(criminals2, 1));
        assertEquals(14, PolicePatrol.calcDistance(criminals2, 2));
        assertEquals(14, PolicePatrol.calcDistance(criminals2, 3));
        assertEquals(14, PolicePatrol.calcDistance(criminals2, 4));
        assertEquals(14, PolicePatrol.calcDistance(criminals2, 5));

        int[] criminals3 = new int[]{-5, -4, -3, -2, -1, 3, 6, 7, 8};
        assertEquals(76, PolicePatrol.calcDistance(criminals3, 1));
        assertEquals(44, PolicePatrol.calcDistance(criminals3, 2));
        assertEquals(36, PolicePatrol.calcDistance(criminals3, 3));
        assertEquals(26, PolicePatrol.calcDistance(criminals3, 4));
        assertEquals(26, PolicePatrol.calcDistance(criminals3, 5));

        int[] criminals4 = new int[] {0, 1, 1, 3, 5, 5, 5, 8, 10, 13, 16, 17, 17, 18, 19, 22, 24, 27, 31, 32, 32, 32, 32, 33, 35};
        assertEquals(506, PolicePatrol.calcDistance(criminals4, 1));
        assertEquals(198, PolicePatrol.calcDistance(criminals4, 3));

        int[] criminals5 = new int[] {1, 2, 3, 4, 4, 4, 5, 6, 8, 11, 12, 14, 17, 19, 22, 26, 28, 30, 33, 36};
        assertEquals(148, PolicePatrol.calcDistance(criminals5, 3));
    }


    @Test
    public void calcDistance_timeTest() {
        int[] array = genRandomArray(100000000);
        System.out.println("Start time test");
        long beg = System.nanoTime();
        long distance = PolicePatrol.calcDistance(array, 1);
        long end = System.nanoTime();
        System.out.println(TimeUnit.MILLISECONDS.convert(end - beg, TimeUnit.NANOSECONDS)); // 168 milliseconds on 100 millions

        long beg2 = System.nanoTime();
        long distance2 = PolicePatrol.calcDistanceOptimized(array, 1);
        long end2 = System.nanoTime();
        System.out.println(TimeUnit.MILLISECONDS.convert(end2 - beg2, TimeUnit.NANOSECONDS)); // 68 milliseconds on 100 millions

        assertEquals(distance, distance2);
    }

    private int[] genRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}