import java.util.Arrays;

public class Fracts {

    public static String convertFrac(long[][] lst) {
        if (lst.length > 0) {
            long[] denominators = new long[lst.length];

            for (int i = 0; i < lst.length; i++) {
                lst[i] = simpleNumber(lst[i][0], lst[i][1]);
                denominators[i] = lst[i][1];
            }
            long denominator = lcm(denominators);

            for (int i = 0; i < lst.length; i++) {
                long quotient = denominator / lst[i][1];
                lst[i][0] = quotient * lst[i][0];
                lst[i][1] = denominator;
            }

            return Arrays.deepToString(lst)
                    .replaceAll("\\[\\[", "(")
                    .replaceAll("\\]\\]", ")")
                    .replaceAll("\\]",")")
                    .replaceAll("\\[","(")
                    .replaceAll(" ","")
                    .replaceAll("\\),\\(",")(");
        }
        return "";
    }

    public static long[] simpleNumber(long numerator, long denominator) {
        for (long i = denominator / 2; i > 1; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                numerator = numerator / i;
                denominator = denominator / i;
            }
        }
        return new long[]{numerator, denominator};
    }

    public static long lcm(long[] array) {
        long lcmOfArrayElements = 1;
        long divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < array.length; i++) {
                if (array[i] == 0) {
                    return 0;
                }

                if (array[i] == 1) {
                    counter++;
                }

                if (array[i] % divisor == 0) {
                    divisible = true;
                    array[i] = array[i] / divisor;
                }
            }

            if (divisible) {
                lcmOfArrayElements = lcmOfArrayElements * divisor;
            } else {
                divisor++;
            }

            if (counter == array.length) {
                return lcmOfArrayElements;
            }
        }
    }

    public static void main(String[] args) {
        long[][] lst;
        lst = new long[][]{{1, 2}, {1, 3}, {10, 40}};
        System.out.println(convertFrac(lst));
    }
}