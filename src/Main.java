import ArabToRoman.ArabToArabToRoman;
import RomanToArab.RomanToArabGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        var result = calc(input);
        System.out.println(result);
    }

    public static String[] separate(String input) {
        String[] arrayOfInput = input.split(" ");
        // тут выбрасываем исключение если 1 элемент массива не выполняет условию matches("/+|/-|//|/*")
        // или число аргументов не равно трём
        if (arrayOfInput.length > 3) throw new IllegalArgumentException("Слишком много аргументов.");
        if (arrayOfInput.length < 3) throw new IllegalArgumentException("Слишком мало аргументов.");
        if (!arrayOfInput[1].matches("\\+|\\-|\\/|\\*")) throw new IllegalArgumentException("Неизвестный оператор.");

        return arrayOfInput;
    }

    public static boolean checkArab(String operandOne, String operandTwo) { // Какие цифры используем
        String arab = "\\d+";
        String nonArab = "(I|V|X|L|C|D|M)+";
        int variant = 0;
        boolean answer = false;
        if (operandOne.matches(nonArab) && operandTwo.matches(nonArab)) {
            variant = 1;
            return false;
        }
        if (operandOne.matches(arab) && operandTwo.matches(arab)) {
            variant = 2;
            return true;
        }
        if (variant == 0) throw new IllegalArgumentException("Первый операнд не из той же системы исчисления, что и второй, значение операнда не может иметь вид I1V и т.д.");

        return answer;
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


