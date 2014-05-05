package PoliceRecruits;

import java.io.*;
import java.util.StringTokenizer;

public class PoliceRecruits {

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

        byte nextByte() throws IOException {
            return Byte.parseByte(next());
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        getCrimesAmount();
    }

    protected static void getCrimesAmount() throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int eventsNumber = in.nextInt();
        int copes = 0, crimes = 0;
        while (eventsNumber-- > 0) {
            int event = in.nextByte();
            if (event > 0) copes += event;
            else {
                if (copes > 0) copes--;
                else crimes++;
            }
        }

        out.print(crimes);
        out.flush();
    }
}
