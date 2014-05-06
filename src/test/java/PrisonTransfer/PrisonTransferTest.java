package PrisonTransfer;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;

public class PrisonTransferTest {

    @Test
    public void calcTransferCombinations_test() throws IOException {
        final int INPUT_NUMBER = 6;

        new MockUp<PrisonTransfer.FastScanner>() {

            private int[] input1 = new int[] {17, 2, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1};
            private int[] input2 = new int[] {5, 2, 2, 3, 3, 3, 3, 3};
            private int[] input3 = new int[] {8, 2, 5, 1, 3, 1, 1, 1, 1, 3, 1};
            private int[] input4 = new int[] {4, 3, 3, 2, 3, 1, 1};
            private int[] input5 = new int[] {1, 1, 1, 2};
            private int[] input6 = new int[] {11, 4, 2, 2, 2, 0, 7, 3, 2, 2, 4, 9, 1, 4};

            @SuppressWarnings("unused")
            @Mock
            int nextInt() throws IOException {
                int[] input = null;
                switch (InputCounter.inputNumber) {
                    case 1: input = input1; break;
                    case 2: input = input2; break;
                    case 3: input = input3; break;
                    case 4: input = input4; break;
                    case 5: input = input5; break;
                    case 6: input = input6; break;
                }
                return input[InputCounter.pointer++];
            }
        };

        new MockUp<PrintWriter>() {

            private int actualResult;
            private int[] expectedResults = new int[] {9, 0, 0, 2, 0, 6};
            private byte pointer = 0;

            @SuppressWarnings("unused")
            @Mock(invocations = INPUT_NUMBER)
            public void print(int i) {
                System.out.println("Result for " + InputCounter.inputNumber + " input = " + i);
                actualResult = i;
            }

            @SuppressWarnings("unused")
            @Mock(invocations = INPUT_NUMBER)
            public void flush() {
                System.out.println("Check input# " + InputCounter.inputNumber + " result");
                assertEquals(expectedResults[InputCounter.inputNumber - 1], actualResult);
                InputCounter.inputNumber++;
                InputCounter.pointer = 0;
            }
        };

        for (int i = 0; i < INPUT_NUMBER; i++) {
            PrisonTransfer.calcTransferCombinations();
        }
    }

    private static class InputCounter {
        public static byte inputNumber = 1;
        public static byte pointer = 0;
    }
}
