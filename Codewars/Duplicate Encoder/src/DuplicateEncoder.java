public class DuplicateEncoder {
    static String encode(String word) {
        final StringBuilder stringBuilder = new StringBuilder();
        final String lowerCaseWord = word.toLowerCase();
        for (int i = 0; i < lowerCaseWord.length(); i++) {
            if (isUnique(lowerCaseWord, lowerCaseWord.charAt(i))) {
                stringBuilder.append("(");
            } else {
                stringBuilder.append(")");
            }
        }
        return stringBuilder.toString();
    }

    static boolean isUnique(String word, char character) {
        final int firstPosition = word.indexOf(character);
        return word.indexOf(character, firstPosition + 1) == -1;
    }

    public static void main(String[] args) {
        System.out.println(encode("Prespecialized"));
    }
}
