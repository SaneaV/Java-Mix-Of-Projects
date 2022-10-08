public class PigLatin {
    public static String pigIt(String str) {
        if (!str.isBlank()) {
            StringBuilder newString = new StringBuilder();
            String[] strings = str.split(" ");

            for (String string :
                    strings) {
                if (containsSpecialCharacter(string)) {
                    newString.append(string).append(" ");
                } else {
                    String part1 = string.charAt(0) + "ay";
                    String part2 = string.substring(1);
                    newString.append(part2).append(part1).append(" ");
                }
            }
            return newString.toString().strip();
        }
        return "";
    }

    public static boolean containsSpecialCharacter(String s) {
        return s != null && s.matches("[!?.,]");
    }

    public static void main(String[] args) {
        System.out.println(pigIt("O tempora o mores !"));
    }
}


