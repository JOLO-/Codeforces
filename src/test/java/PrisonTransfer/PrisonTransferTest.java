package PrisonTransfer;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;
import java.io.IOException;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;

public class PrisonTransferTest {

    private static class FastScannerMockUp extends MockUp<PrisonTransfer.FastScanner> {

        /**
         * NUMBER OF PRISONERS, MAX AGGRESSION, RANK LENGTH, [Prisoners Aggression array]
         */
        private int[] input0 = new int[] {17, 2, 3,   1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1};
        private int[] input1 = new int[] {5, 2, 2,    3, 3, 3, 3, 3};
        private int[] input2 = new int[] {8, 2, 5,    1, 3, 1, 1, 1, 1, 3, 1};
        private int[] input3 = new int[] {4, 3, 3,    2, 3, 1, 1};
        private int[] input4 = new int[] {1, 1, 1,    2};
        private int[] input5 = new int[] {11, 4, 2,   2, 2, 0, 7, 3, 2, 2, 4, 9, 1, 4};
        private int[] input6 = new int[] {10, 2, 3,   1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        private int[] input7 = new int[] {11, 2, 4,   1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1};
        private int[][] inputs = new int[][] {input0, input1, input2, input3, input4, input5, input6, input7};

        private int currInput = 0;
        private int pointer = 0;

        @SuppressWarnings("unused")
        @Mock
        int nextInt() throws IOException {
            return inputs[currInput][pointer++];
        }

        void switchInput() {
            currInput++;
            pointer = 0;
        }

        int getCurrInput() {
            return currInput;
        }

        int getInputsCount() {
            return inputs.length;
        }
    }

    private static class PrintWriterMockUp extends MockUp<PrintWriter> {

        private int actualResult;
        private int[] expectedResults = new int[] {9, 0, 0, 2, 0, 6, 8, 4};
        private final FastScannerMockUp fastScannerMockUp;

        public PrintWriterMockUp(FastScannerMockUp fastScannerMockUp) {
            this.fastScannerMockUp = fastScannerMockUp;
        }

        @SuppressWarnings("unused")
        @Mock(invocations = 8)
        public void print(int i) {
            System.out.println("Result for input#" + fastScannerMockUp.getCurrInput() + " = " + i);
            actualResult = i;
        }

        @SuppressWarnings("unused")
        @Mock(invocations = 8)
        public void flush() {
            System.out.println("Check input#" + fastScannerMockUp.getCurrInput());
            int expectedResult = expectedResults[fastScannerMockUp.getCurrInput()];
            assertEquals(expectedResult, actualResult);
            fastScannerMockUp.switchInput();
        }
    }

    @Test
    public void calcTransferCombinations_test() throws IOException {
        final FastScannerMockUp scannerMockUp = new FastScannerMockUp();
        final PrintWriterMockUp writerMockUp = new PrintWriterMockUp(scannerMockUp);

        for (int i = 0; i < scannerMockUp.getInputsCount(); i++) {
            PrisonTransfer.calcTransferCombinations();
        }
    }
}
