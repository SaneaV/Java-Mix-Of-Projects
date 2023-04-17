import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.util.concurrent.TimeUnit.*;

public class EvenOrOdd {

    private static final String EVEN = "Even";
    private static final String ODD = "Odd";

    public static void main(String[] args) {
        long time = timer(() -> {
            for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
                if (i % 2 == 0 && !even_or_odd(i).equalsIgnoreCase(EVEN)) {
                    throw new RuntimeException();
                }
                if (i % 2 != 0 && !even_or_odd(i).equalsIgnoreCase(ODD)) {
                    throw new RuntimeException();
                }
            }
        });

        System.out.println("Algebraic: " + time + " ms");

        time = timer(() -> {
            for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
                if ((i & 1) == 0 && !even_or_odd_bitwise(i).equalsIgnoreCase(EVEN)) {
                    throw new RuntimeException();
                }
                if ((i & 1) != 0 && !even_or_odd_bitwise(i).equalsIgnoreCase(ODD)) {
                    throw new RuntimeException();
                }
            }
        });

        System.out.println("Bitwise: " + time + " ms");
    }

    public static String even_or_odd(int number) {
        return number % 2 == 0 ? EVEN : ODD;
    }

    public static String even_or_odd_bitwise(int number) {
        return (number & 1) == 0 ? EVEN : ODD;
    }

    private static long timer(Runnable method) {
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        return MILLISECONDS.convert(time, NANOSECONDS);
    }
}
