package ArabToRoman;

public class ArabToArabToRoman implements ArabToRoman {

    private ArabToRomanNumeralDictionary dictionary = new ArabToRomanNumeralDictionary();

    @Override
    public String generate(int number) {

        if(number < 1) throw new IllegalArgumentException("Число должно быть не меньше 1");
        if(number > 3999) throw new IllegalArgumentException("Число должно быть не больше 3999.");

        int arabNumberFound = (int)(dictionary.map.floorKey(number));

        if(arabNumberFound == number) return dictionary.map.get(number).toString();

        return dictionary.map.get(arabNumberFound).toString() + generate(number - arabNumberFound);
    }

}