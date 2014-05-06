package PrisonTransfer;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;

public class PrisonTransferTest {

    @Test
    public void calcTransferCombinations_correctnessTest2() throws IOException {
        final int INPUT_NUMBER = 6;

        new MockUp<PrisonTransfer.FastScanner>() {

            private int[] input1 = new int[] {17, 2, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1};
            private int[] input2 = new int[] {5, 2, 2, 3, 3, 3, 3, 3};
            private int[] input3 = new int[] {8, 2, 5, 1, 3, 1, 1, 1, 1, 3, 1};
            private int[] input4 = new int[] {4, 3, 3, 2, 3, 1, 1};
            private int[] input5 = new int[] {1, 1, 1, 2};
            private int[] input6 = new int[] {11, 4, 2, 2, 2, 0, 7, 3, 2, 2, 4, 9, 1, 4};

            private byte pointer = 0;

            @SuppressWarnings("unused")
            int nextInt() throws IOException {
                int[] input = null;
                switch (getMockInstance().getInputToggle()) {
                    case 1: input = input1; break;
                    case 2: input = input2; break;
                    case 3: input = input3; break;
                    case 4: input = input4; break;
                    case 5: input = input5; break;
                    case 6: input = input6; break;
                }
                return input[pointer++];
            }
        };

        new MockUp<PrintWriter>() {

            private int actualResult;
            private int[] expectedResults = new int[] {9, 2, 0, 2, 0, 6};
            private byte pointer = 0;

            @SuppressWarnings("unused")
            @Mock(invocations = INPUT_NUMBER)
            public void print(int i) {
                actualResult = i;
            }

            @SuppressWarnings("unused")
            @Mock(invocations = INPUT_NUMBER)
            public void flush() {
                assertEquals(expectedResults[pointer++], actualResult);
            }
        };

        for (int i = 0; i < INPUT_NUMBER; i++) {
            PrisonTransfer.calcTransferCombinations();
            PrisonTransfer.FastScanner.toggleInput();
        }
    }
}
