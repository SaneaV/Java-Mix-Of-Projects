import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.pow;
import static java.util.Collections.reverse;

public class DigPow {

    public static void main(String[] args) {
        System.out.println(DigPow.digPow(46288, 3));
    }

    public static long digPow(int n, int p) {
        List<Integer> numbers = getInitialNumbers(n);
        numbers = exponentiation(numbers, p);
        int sum = findSumOfNumbers(numbers);
        return findK(n, sum);
    }

    private static List<Integer> getInitialNumbers(int n) {
        final List<Integer> initialNumberList = new ArrayList<>();

        while (n > 0) {
            initialNumberList.add(n % 10);
            n /= 10;
        }

        reverse(initialNumberList);
        return initialNumberList;
    }

    private static List<Integer> exponentiation(List<Integer> numbers, int p) {
        final List<Integer> tempArray = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            tempArray.add((int) pow(numbers.get(i), p++));
        }
        return tempArray;
    }

    private static int findSumOfNumbers(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    private static int findK(int initialNumber, int sum) {
        int k = 0;
        int tempSum = 0;
        while (tempSum < sum) {
            tempSum = k * initialNumber;

            if (tempSum == sum) {
                return k;
            } else {
                k++;
            }
        }

        return -1;
    }
}
