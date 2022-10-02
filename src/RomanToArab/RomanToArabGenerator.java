package RomanToArab;

public class RomanToArabGenerator implements RomanToArab {

    private static int[] intervals={0, 1, 4, 5, 9, 10, 15, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static String[] numerals={"", "I", "IV", "V", "IX", "X", "XV", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public int generate(String roman) {
        int result = 0;
        for (int i = intervals.length-1; i >= 0; i-- ) {
            while (roman.indexOf(numerals[i]) == 0 && numerals[i].length() > 0) {
                result += intervals[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        return result;
    }

}
