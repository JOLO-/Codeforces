package PoliceRecruits;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;

public class PoliceRecruitsTest  {

    @Test
    public void policeRecruits_test() throws IOException {
        new MockUp<PoliceRecruits.FastScanner>() {

            private byte[] bytes = new byte [] {-1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1};
            private byte pointer = 0;

            @SuppressWarnings("unused")
            @Mock(invocations = 1)
            int nextInt() throws IOException {
                return 11;
            }

            @SuppressWarnings("unused")
            @Mock(invocations = 11)
            byte nextByte() throws IOException {
                return bytes[pointer++];
            }
        };

        new MockUp<PrintWriter>() {

            private int result;

            @SuppressWarnings("unused")
            @Mock(invocations = 1)
            public void print(int i) {
                result = i;
            }

            @SuppressWarnings("unused")
            @Mock(invocations = 1)
            public void flush() {
                assertEquals(8, result);
            }
        };

        PoliceRecruits.getCrimesAmount();
    }
}
