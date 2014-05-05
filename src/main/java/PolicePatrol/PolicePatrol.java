package PolicePatrol;

import java.io.PrintWriter;
import java.util.Scanner;

public class PolicePatrol {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int criminalAmount = in.nextInt();
        int patrolCarCapacity = in.nextInt();
        int[] criminalLocations = new int[criminalAmount];
        for (int i = 0; i < criminalLocations.length; i++)
            criminalLocations[i] = in.nextInt();

        long distance = calcDistance2(criminalLocations, patrolCarCapacity);
        out.print(distance);
        out.flush();
    }

    protected static long calcDistance2(int[] criminals, int patrolCapacity) {
        int patrolPosition = criminals[criminals.length / 2];

        double itr = (criminals.length - 1) / 2.0;
        int itrLeft = (int) Math.ceil(Math.ceil(itr) / ((double) patrolCapacity));
        int itrRight = (int) Math.ceil(Math.floor(itr) / ((double) patrolCapacity));

        long distance = 0;

        int lo = 0;
        while (itrLeft-- > 0) {
            distance += patrolPosition - criminals[lo];
            lo = lo + patrolCapacity;
        }

        int hi = criminals.length - 1;
        while (itrRight-- > 0) {
            distance += criminals[hi] - patrolPosition;
            hi -= patrolCapacity;
        }
        return 2 * distance;
    }

    protected static long calcDistance(int[] criminals, int patrolCapacity) {
        int median = criminals.length / 2;
        return calcLeftDistance(criminals, median, patrolCapacity) + calcRightDistance(criminals, median, patrolCapacity);
    }


    private static long calcLeftDistance(int[] criminals, int currIndex, int patrolCapacity) {
        long distance = 0;
        int currPos = criminals[currIndex];
        for (int i = 0; i < currIndex; i += patrolCapacity) {
            distance += (currPos - criminals[i]);
        }
        return 2 * distance;
    }

    private static long calcRightDistance(int[] criminals, int currIndex, int patrolCapacity) {
        long distance = 0;
        int currPos = criminals[currIndex];
        for (int i = criminals.length - 1; i > currIndex; i -= patrolCapacity) {
            distance += (criminals[i] - currPos);
        }
        return 2 * distance;
    }
}
