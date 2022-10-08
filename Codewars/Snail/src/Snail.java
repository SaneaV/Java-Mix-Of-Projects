import java.util.Arrays;

public class Snail {

    public static int[] snail(int[][] array) {
        int arrayLength = array[0].length;

        if (arrayLength != 0) {
            int pace = 1;
            int[] arrayResult = new int[arrayLength * arrayLength];
            int elementNumber = 0;
            int lToR = arrayLength;
            int uToD = arrayLength - pace;
            int rToL = arrayLength - pace - 1;
            int dToU = arrayLength - pace - 1;

            while (elementNumber<arrayLength*arrayLength) {

                for (int j = pace - 1; j < lToR; j++) {
                    arrayResult[elementNumber++] = array[pace-1][j];
                }
                lToR--;

                for (int i = pace; i <= uToD; i++) {
                    arrayResult[elementNumber++] = array[i][arrayLength - pace];
                }
                uToD--;

                for (int j = rToL; j >= arrayLength - lToR - 1; j--) {
                    arrayResult[elementNumber++] = array[arrayLength - pace][j];
                }
                rToL--;

                for (int i = dToU; i >= pace; i--) {
                    arrayResult[elementNumber++] = array[i][pace-1];
                }
                dToU--;

                pace++;
            }
            return arrayResult;
        }
        else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[][] array = {{}};


        assert snail(array) != null;
        System.out.println(Arrays.toString(Arrays.stream(snail(array)).toArray()));
    }
}