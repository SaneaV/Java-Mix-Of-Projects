import java.util.Arrays;

import static java.util.Objects.isNull;

public class App {

    private static final String SPACE = " ";

    public static void main(String[] args) {
        System.out.println(toJadenCase("How can mirrors be real if our eyes aren't real"));
    }

    public static String toJadenCase(String phrase) {
        if (isEmptyOrNull(phrase)) {
            return null;
        }
        final StringBuilder jadenCase = new StringBuilder();
        Arrays.stream(phrase.split(SPACE))
                .forEach(w -> jadenCase.append(w.substring(0, 1).toUpperCase())
                        .append(w.substring(1))
                        .append(SPACE));
        return jadenCase.toString().trim();
    }

    private static boolean isEmptyOrNull(String phrase) {
        return isNull(phrase) || phrase.length() == 0;
    }
}
