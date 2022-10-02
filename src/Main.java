import ArabToRoman.ArabToArabToRoman;
import RomanToArab.RomanToArabGenerator;

public class Main {
    public static void main(String[] args) {
        var result = calc("V + MMM");
        System.out.println(result);
    }

    public static String[] separate(String input) {
        String[] arrayOfInput = input.split(" ");
        // тут выбрасываем исключение если 1 элемент массива не выполняет условию matches("/+|/-|//|/*")
        // а так же если массив больше или меньше трёх элементов
        return arrayOfInput;
    }

    public static boolean checkArab(String operandOne, String operandTwo) { // Какие цифры используем
        String arab = "^\\d+$";
        String nonArab = "I|V|X|L|C|D|M";
        boolean answer = false;
        if (operandOne.matches(nonArab) && operandTwo.matches(nonArab)) {
            answer = false;
        }
        if (operandOne.matches(arab) && operandTwo.matches(arab)) {
            answer = true;
        }
        if ((operandOne.matches(arab) && operandOne.matches(nonArab)) || (operandTwo.matches(nonArab) && operandTwo.matches(arab))) {
            answer = false; // тут исключение, что значение операнда не может иметь вид I1V и т.д.
        }
        return answer; // тут кидаем исключение если первый операнд не из тех же чисел, что и второй
    }

    public static int[] decode(String[] operandsString, boolean isArab) {
        int[] operandsInt = {0, 0};
        if (isArab){
            operandsInt[0] = Integer.parseInt(operandsString[0]);
            operandsInt[1] = Integer.parseInt(operandsString[2]);
        } else {
            RomanToArabGenerator RomanToArab = new RomanToArabGenerator();
            operandsInt[0] = RomanToArab.generate(operandsString[0]);
            operandsInt[1] = RomanToArab.generate(operandsString[2]);
        }
        return operandsInt;
    }

    public static String encode(int finalNumber, boolean isArab) {
        if (isArab) {
            return Integer.toString(finalNumber);
        }
        ArabToArabToRoman romanGenerator = new ArabToArabToRoman();
        return romanGenerator.generate(finalNumber);
    }

    public static String calc(String input) {

        String[] separated = separate(input);
        String operator = separated[1];
        // тут можно выкинуть исключение если оператор не поддерживается
        boolean isArab = checkArab(separated[0], separated[2]);
        int[] numbers = decode(separated, isArab);
        int result = 0;

        switch (operator) {
            case ("+") -> result = numbers[0] + numbers[1];
            case ("-") -> result = numbers[0] - numbers[1];
            case ("*") -> result = numbers[0] * numbers[1];
            case ("/") -> result = numbers[0] / numbers[1];
            default -> {
            }
        }

        return encode(result, isArab);
    }

}


