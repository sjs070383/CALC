import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();

        exp = exp.replaceAll("\\s+", "");

        String resultStr = calc(exp);
        System.out.println("Результат: " + resultStr);


    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int actionIndex = -1;

        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new IllegalArgumentException("Строка не является математической операцией");
        }

        int result = 0;
        String[] data = input.split(regexActions[actionIndex]);
        if (data.length > 2) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);


            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a > 10 || b > 10 || a < 1 || b < 1) {
                throw new RuntimeException("Числа должны быть в диапазоне от 1 до 10");
            }
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman && result <= 0) {
                throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
            }
            if (isRoman) {
                return converter.intToRoman(result);
            }

        } else {
            throw new RuntimeException("Ошибка! Используются одновременно разные системы счисления");
        }
        return String.valueOf(result);
    }
}

