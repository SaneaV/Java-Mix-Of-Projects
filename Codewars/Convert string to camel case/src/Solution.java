public class Solution {

    static String toCamelCase(String s) {
        if (!s.isBlank()) {
            StringBuilder newString = new StringBuilder();
            String[] strings = s.split("-|_");

            int i = 0;
            if (Character.isLowerCase(strings[0].charAt(0))) {
                String part1 = strings[0].substring(0, 1).toLowerCase();
                String part2 = strings[0].substring(1).toLowerCase();
                newString.append(part1).append(part2);
                i = 1;
            }

            for (; i < strings.length; i++) {
                String part1 = strings[i].substring(0, 1).toUpperCase();
                String part2 = strings[i].substring(1).toLowerCase();
                newString.append(part1).append(part2);

            }
            return newString.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(toCamelCase("the_Stealth_Warrior"));
    }
}
