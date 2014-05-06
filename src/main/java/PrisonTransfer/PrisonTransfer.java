package PrisonTransfer;

import java.io.*;
import java.util.StringTokenizer;

public class PrisonTransfer {

    protected static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String... args) throws IOException {
        calcTransferCombinations();
    }


    protected static void calcTransferCombinations() throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        final int PRISONERS_NUMBER = in.nextInt();
        final int MAX_AGGRESSION = in.nextInt();
        final int RANK_LENGTH = in.nextInt();

        int combinations = PRISONERS_NUMBER - RANK_LENGTH + 1;
        int distanceToLastDangerous = RANK_LENGTH;
        int itr = 1;
        while (itr <= PRISONERS_NUMBER) {
            int aggression = in.nextInt();
            if (aggression > MAX_AGGRESSION) {
                if (PRISONERS_NUMBER - itr < RANK_LENGTH) {
                    combinations -= Math.min(PRISONERS_NUMBER - itr + 1, distanceToLastDangerous);
                    break;
                }

                int left = min(itr, RANK_LENGTH, distanceToLastDangerous);
                combinations -= left;
                distanceToLastDangerous = 0;
            }
            distanceToLastDangerous++;
            itr++;
        }

        out.print(combinations);
        out.flush();
    }

    protected static int min(int a, int b, int c) {
        int min1 = Math.min(a, b);
        return Math.min(min1, c);
    }
}