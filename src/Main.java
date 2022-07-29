public class Main {
    public static void main(String[] args) {
        calc("1+34");
    }

    public static char calc(String input) {
        input.replaceAll("\\s",""); // Удаляем все пробелы в строке на всякий случай

        boolean arab;

        if (Character.toString(input.charAt(0)).matches("[IVXM]")) {
            arab = false; // Первоначальное определение какие цифры используем
        }

        String firstNum = "";
        String operand = "";
        String lastNum = "";

        for (var i = 0; i < input.length(); i++) {
            if (Character.toString(input.charAt(i)).matches("[\\+\\-\\*\\/]") && operand.equals("")) {
                operand = Character.toString(input.charAt(i));
                continue;
            }
            if (operand.equals("")) {
                firstNum += Character.toString(input.charAt(i));
            }

            if (operand.length() == 1) {
                lastNum += Character.toString(input.charAt(i));
            }
        }

        System.out.println(firstNum+operand+lastNum);
        return input.charAt(0);
    }

}