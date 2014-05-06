package PrisonTransfer;

import java.io.*;
import java.util.StringTokenizer;

public class PrisonTransfer {

    protected static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        private static byte inputToggle = 1;

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

        public static void toggleInput() {
            inputToggle++;
        }

        public byte getInputToggle() {
            return inputToggle;
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

        int combinations = PRISONERS_NUMBER;
        int distanceToLastDangerous = RANK_LENGTH;
        int itr = 1;
        while (itr <= PRISONERS_NUMBER) {
            int aggression = in.nextInt();
            if (aggression > MAX_AGGRESSION) {
                int left = min(itr, RANK_LENGTH, distanceToLastDangerous);
                combinations -= left;
                distanceToLastDangerous = 0;

                if (PRISONERS_NUMBER - itr < RANK_LENGTH) {
                    combinations -= (PRISONERS_NUMBER - itr);
                    break;
                }
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