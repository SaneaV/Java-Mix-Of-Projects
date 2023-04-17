import java.util.Arrays;

import static java.util.Objects.isNull;

public class Counter {
    public static void main(String[] args) {
        Boolean[] array1 = {true, true, true, false,
                true, true, true, true,
                true, false, true, false,
                true, false, false, true,
                true, true, true, true,
                false, false, true, true};

        System.out.println(countSheeps(array1));
    }

    public static int countSheeps(Boolean[] arrayOfSheeps) {
        return (int) Arrays.stream(arrayOfSheeps)
                .filter(sheep -> !isNull(sheep) && sheep)
                .count();
    }
}