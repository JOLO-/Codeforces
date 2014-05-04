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

        long distance = calcDistance(criminalLocations, patrolCarCapacity);
        out.print(distance);
        out.flush();
    }

    protected static long calcDistance(int[] criminals, int patrolCapacity) {
        int median = criminals.length / 2;
        return calcLeftDistance(criminals, median, patrolCapacity) + calcRightDistance(criminals, median, patrolCapacity);
    }


    private static long calcLeftDistance(int[] criminals, int currIndex, int patrolCapacity) {
        long distance = 0;
        int currPos = criminals[currIndex];
        for (int i = 0; i < currIndex; i += patrolCapacity) {
            distance += 2 * (currPos - criminals[i]);
        }
        return distance;
    }

    private static long calcRightDistance(int[] criminals, int currIndex, int patrolCapacity) {
        long distance = 0;
        int currPos = criminals[currIndex];
        for (int i = criminals.length - 1; i > currIndex; i -= patrolCapacity) {
            distance += 2 * (criminals[i] - currPos);
        }
        return distance;
    }
}
