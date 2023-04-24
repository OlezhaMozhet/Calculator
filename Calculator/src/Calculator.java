import java.text.Format;
import java.util.Scanner;

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
            "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII","XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII","XCIX", "C"};

    public static boolean isRoman(String val) { //ищем строковое значение,
        for (int x = 0; x < romanArray.length; x++) { //ищем румынские числа
            if (val.equals(romanArray[x])) { // если V=V
                return true; // идем дальше
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) { //конвертируем в арабские числа
        for (int x = 0; x < romanArray.length; x++) { //ищем x в арабских
            if (roman.equals(romanArray[x])) { //если x=5
                return x;
            }
        }
        return -1; //число не нашли
    }

    public static String convertToRoman(int arabian) {return romanArray[arabian];}
}

class Calculator {

    public static void main(String[] args) throws Exception { // основа
//        Roman.isRoman("2");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        String expression = scanner.nextLine(); // строковое выражение

        try {
            System.out.println(parse(expression)); // разбор строкового выражение
        } catch (NumberFormatException e) {
            System.err.println("Калькулятор работает только с целыми числами");
        }

    }

    public static String parse(String expression) throws Exception {   // разбор строк

        int num1;
        int num2;
        boolean isRoman;
        String operation;
        String result;

        operation = operationDetect(expression); // Операция Обнаружение(выражения)
        String[] numders = expression.split("[+\\-*/]"); // разделение выражения

        if (operation == null) throw new Exception("строка не является математической операцией");

        if (numders.length != 2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        // если числа римские
        if (Roman.isRoman(numders[0]) && Roman.isRoman(numders[1])) {
            // конвертируем в арабские для вычисления
            num1 = Roman.convertToArabian(numders[0]);
            num2 = Roman.convertToArabian(numders[1]);
            isRoman = true;
        }

        // если арабские
        else if (!Roman.isRoman(numders[0]) && !Roman.isRoman(numders[1])) {
        // значит не римские)

            num1 = Integer.parseInt(numders[0]); // целое число. разбор интервала, интегрируем в тип Int


            num2 = Integer.parseInt(numders[1]);
            isRoman = false;
        }
        // если одно число римское, а другое арабское
        else {
            throw new Exception("используются одновременно разные системы счисления");  // создать новое исключение
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("числа должны быть от 1 до 10");
        }
        int arabian = calculator(num1, num2, operation);
        if (isRoman) {
            // если римское число получилось меньше или равно нулю, выдает ошибку
            if (arabian <= 0) {
                throw new Exception("в римской системе нет отрицательных чисел");
            }
            // конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //оставляем арбское значение
            result = String.valueOf(arabian);
        }
        // возвращаем результат
        return result;

    }

    static String operationDetect(String expression) {  // строковое выражение
        if (expression.contains("+")) return "+";  // выражение содержит
        else if (expression.contains("-")) return "-"; // ещё если выражение содержит
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calculator(int a, int b, String operation) {
        if (operation.equals("+")) return a + b; // если опер равняется
        else if (operation.equals("-")) return a - b; // ещё если опер равняется
        else if (operation.equals("*")) return a * b;
        else return a / b;
    }

}




