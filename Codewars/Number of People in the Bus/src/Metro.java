import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Integer.MAX_VALUE;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

class Metro {

    public static void main(String[] args) {
        long time;
        final Random random = new Random();
        int limit = MAX_VALUE / 25;

        final ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{10, 0});
        for (int i = 0; i < limit; i++) {
            int in = random.nextInt(5) + 1;
            int out = random.nextInt(in) + 1;
            list.add(new int[]{in, out});
        }

        time = timer(() -> countPassengersMyMethod(list));

        System.out.println("My method: " + time + " ms");

        time = timer(() -> countPassengersCodeWarsMethod(list));

        System.out.println("CodeWars method: " + time + " ms");

        time = timer(() -> countPassengersChatGPTMethod(list));

        System.out.println("ChatGPT method: " + time + " ms");

        time = timer(() -> countPassengersChatGPTMethodParallel(list));

        System.out.println("ChatGPT parallel method: " + time + " ms");
    }

    public static int countPassengersMyMethod(ArrayList<int[]> stops) {
        return stops.stream()
                .flatMapToInt(s -> IntStream.of(s[0] - s[1]))
                .reduce(0, Integer::sum);
    }

    public static int countPassengersCodeWarsMethod(ArrayList<int[]> stops) {
        return stops.stream()
                .mapToInt(x -> x[0] - x[1])
                .sum();
    }

    public static int countPassengersChatGPTMethod(ArrayList<int[]> stops) {
        int people = 0;
        for (int i = 0; i < stops.size(); i++) {
            people += stops.get(i)[0] - stops.get(i)[1];
        }
        return people;
    }

    public static int countPassengersChatGPTMethodParallel(ArrayList<int[]> stops) {
        int distance;
        if (stops.size() > 100000) {
            distance = stops.stream()
                    .parallel()
                    .mapToInt(s -> s[0] - s[1])
                    .sum();
        } else {
            distance = stops.stream()
                    .mapToInt(s -> s[0] - s[1])
                    .sum();
        }
        return distance;
    }

    private static long timer(Runnable method) {
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        return MILLISECONDS.convert(time, NANOSECONDS);
    }
}