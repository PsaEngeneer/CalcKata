import java.util.Scanner;

class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String expression = scanner.nextLine();
        expression = expression.replaceAll("\\s", "");
        System.out.println(calc(expression));
    }

    public static String calc(String input) throws Exception
    {
        String[] operand = input.split("[+\\-*/]");
        String operation = DetectedOperation(input);
        if (operand.length == 1) throw new Exception("т.к. строка не является математической операцией");
        if (operand.length != 2) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        boolean thisRim;
        String result;
        int num1;
        int num2;
        if (Converter.thisRim(operand[0]) && Converter.thisRim(operand[1]))
        {
            num1 = Converter.ArabConverter(operand[0]);
            num2 = Converter.ArabConverter(operand[1]);
            thisRim = true;
        }
        else if (!Converter.thisRim(operand[0]) && !Converter.thisRim(operand[1]))
        {
            num1 = Integer.parseInt(operand[0]);
            num2 = Integer.parseInt(operand[1]);
            thisRim = false;
        }
        else throw new Exception("т.к. используются одновременно разные системы счисления");
        if (num1>10 || num2>10) throw new Exception("Числа должны быть от 1 до 10");
        if (num2 == 0 & input.contains("/")) throw new Exception("На ноль делить нельзя");

        assert operation != null;
        int arab = calculator(num1, num2, operation);
        if (thisRim && arab <= 0) throw new Exception("т.к. в римской системе нет отрицательных чисел");
        if (thisRim) result=Converter.RimConverter(arab);
        else  result = String.valueOf(arab);
        return result;
    }



    static String DetectedOperation(String expression)
    {
        //Исключаю ошибку ввода типа 2-1+ или II-i+ и т.д. при этом вычисляя первый введённый знак операции
        expression=expression.replaceAll("[a-zA-Z0-9]","");
        int znak = expression.indexOf("+");
        if (znak == 0) return "+";
        znak = expression.indexOf("-");
        if (znak == 0) return "-";
        znak = expression.indexOf("*");
        if (znak == 0) return "*";
        znak = expression.indexOf("/");
        if (znak == 0) return "/";
        else return null;
    }

    static int calculator(int a, int b, String operation)
    {
        if (operation.contains("+")) return a + b;
        if (operation.contains("-")) return a - b;
        if (operation.contains("*")) return a * b;
        else return a / b;
    }


    static class Converter
    {
        static String[] rimArray = new String[]{"/0/", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};
        static boolean thisRim(String arab)
        {
            for (int i = 0; i < rimArray.length; i++)
                if (arab.equals(rimArray[i])) return true;
            return false;
        }

        static int ArabConverter(String arabconvert)
        {
            for (int i = 0; i < rimArray.length; i++)
                if (arabconvert.equals(rimArray[i])) return i;
            return 0;
        }

        static String RimConverter (int rimrconverter)
        {return rimArray[rimrconverter];}

    }



}