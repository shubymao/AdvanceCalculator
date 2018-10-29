package AdvanceCalculator;
import java.util.Scanner;

public class Division {
    private static int NumberArrayLength;
    private static final int Digit = 10;

    public static int[] toWholeNumber(String n) {
        int num[] = new int[NumberArrayLength];
        int i;
        int j = 0;
        for (i = 0; i < NumberArrayLength - n.length(); i++) {
            num[i] = 0;
        }

        while (i < NumberArrayLength) {
            num[i] = n.charAt(j) - 48;
            i++;
            j++;
        }
        return num;
    }

    public static boolean Compare(String number1, String number2) {
        if (number1.length() > number2.length())
            return true;
        else if (number1.length() < number2.length())
            return false;
        else {
            int i;
            for (i = 0; i < number1.length(); i++) {
                if (number1.charAt(i) > number2.charAt(i))
                    return true;
                else if (number1.charAt(i) < number2.charAt(i))
                    return false;
            }
            return true;
        }
    }

    public static String WholeNumberDivision(String number1, String number2) {
        int j;
        for (j = 0; j < number1.length(); j++) {
            if (number1.charAt(j) != '0') {
                number1 = number1.substring(j);
                break;
            }
        }
        if (j == number1.length())
            return "0";

        for (j = 0; j < number2.length(); j++) {
            if (number2.charAt(j) != '0') {
                number2 = number2.substring(j);
                break;
            }
        }
        int DecimalDigit = Digit + number2.length();
        for (int i = 0; i < DecimalDigit; i++) {
            number1 = number1 + "0";
        }
        String finalresult = "";
        String TempNumber1 = number1;
        String TempNumber2 = number2;
        int DigitDifference = number1.length() - number2.length();


        while (Compare(TempNumber1, number2)) {
            NumberArrayLength = TempNumber1.length();
            TempNumber2 = number2;
            for (int i = 0; i < DigitDifference; i++)
                TempNumber2 = TempNumber2 + "0";

            while (!Compare(TempNumber1, TempNumber2)) {
                finalresult = finalresult + "0";
                DigitDifference--;
                TempNumber2 = number2;
                for (int i = 0; i < DigitDifference; i++)
                    TempNumber2 = TempNumber2 + "0";
            }

            String TempSum = TempNumber2;
            int i = 1;
            while (i < 10) {
                TempSum = WholeNumberAddition(TempSum, TempNumber2);
                if (Compare(TempNumber1, TempSum))
                    i++;
                else
                    break;
            }

            finalresult = finalresult + i;

            TempSum = WholeNumberMultiplication(TempNumber2, Integer.toString(i));
            TempNumber1 = WholeNumberSubtraction(TempNumber1, TempSum);
            if (TempNumber1.equals("0")) {
                for (int k = 0; k < DigitDifference; k++)
                    finalresult = finalresult + "0";
            }

            DigitDifference--;
        }

        if ((finalresult.length() - DecimalDigit) > 0) {
            finalresult = finalresult.substring(0, finalresult.length() - DecimalDigit)
                    + "." + finalresult.substring(finalresult.length() - DecimalDigit);
        } else {
            for (int i = 0; i < DecimalDigit; i++) {
                finalresult = "0" + finalresult;
            }
            finalresult = finalresult.substring(0, finalresult.length() - DecimalDigit + DigitDifference + 1)
                    + "." + finalresult.substring(finalresult.length() - DecimalDigit + DigitDifference + 1);
        }

        for (int i = 0; i < finalresult.length(); i++) {
            if (finalresult.charAt(i) != '0') {
                finalresult = finalresult.substring(i);
                if (finalresult.charAt(0) == '.')
                    finalresult = "0" + finalresult;
                break;
            }
        }

        for (int i = finalresult.length() - 1; i >= 0; i--) {
            if (finalresult.charAt(i) != '0') {
                finalresult = finalresult.substring(0, i + 1);
                if (finalresult.charAt(finalresult.length() - 1) == '.')
                    finalresult = finalresult.substring(0, finalresult.length() - 1);
                break;
            }
        }

        int a = 0;
        for (int i = 0; i < finalresult.length(); i++) {
            if (finalresult.charAt(i) == '.') {
                for (a = i + 1; a < finalresult.length(); a++) {
                    if (finalresult.charAt(a) != '0')
                        break;
                }
            }
            if (a != 0)
                break;
        }
        if (finalresult.length() <= (a + 10))
            return finalresult;
        else
            finalresult = finalresult.substring(0, a + 10);

        return finalresult;
    }

    public static String WholeDecimalDivision(String number1, String number2) {
        String array1[], array2[];
        String finalresult;
        array1 = number1.split("[.]");
        array2 = number2.split("[.]");

        if (array1.length == 1 && array2.length == 2) {
            String DecimaltoWhole = array2[0] + array2[1];

            for (int i = 0; i < array2[1].length(); i++) {
                number1 = number1 + "0";
            }

            finalresult = WholeNumberDivision(number1, DecimaltoWhole);
        } else {
            String DecimaltoWhole = array1[0] + array1[1];

            for (int i = 0; i < array1[1].length(); i++) {
                number2 = number2 + "0";
            }

            finalresult = WholeNumberDivision(DecimaltoWhole, number2);
        }

        return finalresult;
    }

    public static String DecimalNumberDivision(String number1, String number2) {
        String array1[], array2[];
        String finalresult;
        array1 = number1.split("[.]");
        array2 = number2.split("[.]");

        String DecimaltoWhole1 = array1[0] + array1[1];
        String DecimaltoWhole2 = array2[0] + array2[1];

        if (array1[1].length() >= array2[1].length()) {
            for (int i = 0; i < array1[1].length() - array2[1].length(); i++) {
                DecimaltoWhole2 = DecimaltoWhole2 + "0";
            }
        } else {
            for (int i = 0; i < array2[1].length() - array1[1].length(); i++) {
                DecimaltoWhole1 = DecimaltoWhole1 + "0";
            }
        }

        finalresult = WholeNumberDivision(DecimaltoWhole1, DecimaltoWhole2);

        return finalresult;
    }

    public static String WholeNumberAddition(String number1, String number2) {
        int num1[] = toWholeNumber(number1);
        int num2[] = toWholeNumber(number2);
        int result[] = new int[NumberArrayLength + 1];
        int temp[] = new int[NumberArrayLength];
        String finalresult = "";

        for (int i = 0; i < NumberArrayLength; i++) {
            temp[i] = num1[i];
        }

        int carry = 0;
        int digit = 0;
        for (int i = NumberArrayLength - 1; i > 0; i--) {
            digit = temp[i] + num2[i];
            result[i + 1] = digit % 10;
            carry = digit / 10;
            temp[i - 1] = temp[i - 1] + carry;
        }

        digit = temp[0] + num2[0];
        result[1] = digit % 10;
        carry = digit / 10;
        if (carry == 1)
            result[0] = 1;
        else
            result[0] = 0;

        for (int i = 0; i < result.length; i++) {
            finalresult += result[i];
        }

        for (int i = 0; i < finalresult.length(); i++) {
            if (finalresult.charAt(i) != '0') {
                finalresult = finalresult.substring(i);
                break;
            }
        }

        return finalresult;
    }

    public static String WholeNumberSubtraction(String number1, String number2) {
        int num1[] = toWholeNumber(number1);
        int num2[] = toWholeNumber(number2);
        int result[] = new int[NumberArrayLength];
        int temp[] = new int[NumberArrayLength];
        String finalresult = "";

        for (int i = 0; i < NumberArrayLength; i++) {
            temp[i] = num1[i];
        }

        int digit = 0;
        for (int i = NumberArrayLength - 1; i > 0; i--) {
            if (temp[i] - num2[i] >= 0) {
                digit = temp[i] - num2[i];
                result[i] = digit;
            } else {
                digit = temp[i] + 10 - num2[i];
                result[i] = digit;
                temp[i - 1] -= 1;
            }
        }

        digit = temp[0] - num2[0];
        result[0] = digit;

        for (int i = 0; i < result.length; i++) {
            finalresult += result[i];
        }

        int i;
        int length = finalresult.length();
        for (i = 0; i < length; i++) {
            if (finalresult.charAt(i) != '0') {
                finalresult = finalresult.substring(i);
                break;
            }
        }
        if (i == length)
            return "0";
        return finalresult;
    }

    public static String WholeNumberMultiplication(String number1, String number2) {
        int NumberofColumn = number2.length();

        int num1[] = toWholeNumber(number1);
        int num2[] = toWholeNumber(number2);

        int MaxLength = number1.length() + number2.length();
        int ProductResult[][] = new int[NumberofColumn][MaxLength];
        for (int i = 0; i < ProductResult.length; i++) {
            for (int j = 0; j < ProductResult[i].length; j++) {
                ProductResult[i][j] = 0;
            }
        }


        int digit = 0;
        int carry = 0;
        int temp = 0;
        int power_of_ten = 0;
        int k = 0, l = 1;
        int[] WholeSum = new int[MaxLength];
        for (int i = 0; i < WholeSum.length; i++) {
            WholeSum[i] = 0;
        }

        for (int i = NumberArrayLength - 1; i >= NumberArrayLength - NumberofColumn; i--) {
            for (int j = NumberArrayLength - 1; j >= 0; j--) {
                digit = num2[i] * num1[j];
                if (ProductResult[k][MaxLength - power_of_ten - l] + digit >= 10) {
                    temp = ProductResult[k][MaxLength - power_of_ten - l];
                    ProductResult[k][MaxLength - power_of_ten - l] = (temp + digit) % 10;
                    carry = (temp + digit) / 10;
                    l++;
                    ProductResult[k][MaxLength - power_of_ten - l] = carry;
                } else {
                    ProductResult[k][MaxLength - power_of_ten - l] += digit;
                    l++;
                }

            }
            WholeSum = AddOperation(ProductResult[k], WholeSum, MaxLength);

            l = 1;
            k++;
            power_of_ten++;
        }

        String finalresult = "";
        for (int i = 0; i < WholeSum.length; i++) {
            finalresult += WholeSum[i];
        }

        int i;
        for (i = 0; i < finalresult.length(); i++) {
            if (finalresult.charAt(i) != '0') {
                finalresult = finalresult.substring(i);
                break;
            }
        }

        return finalresult;
    }

    public static int[] AddOperation(int num1[], int num2[], int ArrayLength) {
        int finalresult[] = new int[ArrayLength];
        int temp[] = new int[ArrayLength];
        for (int i = 0; i < ArrayLength; i++) {
            temp[i] = num1[i];
        }
        int carry = 0;
        int digit = 0;
        for (int i = ArrayLength - 1; i > 0; i--) {
            digit = temp[i] + num2[i];
            finalresult[i] = digit % 10;
            carry = digit / 10;
            temp[i - 1] = temp[i - 1] + carry;
        }

        digit = temp[0] + num2[0];
        finalresult[0] = digit;

        return finalresult;
    }


    public static boolean isRight(String number1, String number2) {
        String array1[] = number1.split("[.]");
        String array2[] = number2.split("[.]");

        if (array1.length > 2 || array2.length > 2)
            return false;

        if (!(number1.charAt(0) == '-' || (number1.charAt(0) >= '0' && number1.charAt(0) <= '9')))
            return false;
        for (int i = 1; i < number1.length(); i++) {
            if (!(number1.charAt(i) == '.' || (number1.charAt(i) >= '0' && number1.charAt(i) <= '9')))
                return false;
        }

        if (!(number2.charAt(0) == '-' || (number2.charAt(0) >= '0' && number2.charAt(0) <= '9')))
            return false;
        for (int i = 1; i < number2.length(); i++) {
            if (!(number2.charAt(i) == '.' || (number2.charAt(i) >= '0' && number2.charAt(i) <= '9')))
                return false;
        }

        return true;
    }

    public static void Display(String number1, String number2, String result) {
        System.out.println(number1 + " / " + number2 + " = " + result);
    }

    public static int CalculateStatus(String number1, String number2) {
        String array1[] = number1.split("[.]");
        String array2[] = number2.split("[.]");

        if (array1.length == 1 && array2.length == 1)
            return 1;
        else if (array1.length == 1 && array2.length == 2)
            return 2;
        else if (array1.length == 2 && array2.length == 1)
            return 2;
        else if (array1.length == 2 && array2.length == 2)
            return 3;
        else
            return 0;
    }

    public static int CheckSign(String number1, String number2) {
        if (number1.charAt(0) != '-' && number2.charAt(0) != '-')
            return 1;
        else if (number1.charAt(0) == '-' && number2.charAt(0) != '-')
            return 2;
        else if (number1.charAt(0) != '-' && number2.charAt(0) == '-')
            return 3;
        else
            return 4;
    }

    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter a number: ");
        String number1 = reader.next();

        System.out.println("Enter another number: ");
        String number2 = reader.next();

        System.out.println();

        if (isRight(number1, number2)) {
            String result = "";
            int Status = CalculateStatus(number1, number2);
            int Sign = CheckSign(number1, number2);

            switch (Status) {
                case 1:
                    switch (Sign) {
                        case 1:
                            result = WholeNumberDivision(number1, number2);
                            break;
                        case 2:
                            result = "-" + WholeNumberDivision(number1.substring(1), number2);
                            break;
                        case 3:
                            result = "-" + WholeNumberDivision(number1, number2.substring(1));
                            break;
                        case 4:
                            result = WholeNumberDivision(number1.substring(1), number2.substring(1));
                            break;
                    }
                    Display(number1, number2, result);
                    break;
                case 2:
                    switch (Sign) {
                        case 1:
                            result = WholeDecimalDivision(number1, number2);
                            break;
                        case 2:
                            result = "-" + WholeDecimalDivision(number1.substring(1), number2);
                            break;
                        case 3:
                            result = "-" + WholeDecimalDivision(number1, number2.substring(1));
                            break;
                        case 4:
                            result = WholeDecimalDivision(number1.substring(1), number2.substring(1));
                            break;
                    }
                    Display(number1, number2, result);
                    break;
                case 3:
                    switch (Sign) {
                        case 1:
                            result = DecimalNumberDivision(number1, number2);
                            break;
                        case 2:
                            result = "-" + DecimalNumberDivision(number1.substring(1), number2);
                            break;
                        case 3:
                            result = "-" + DecimalNumberDivision(number1, number2.substring(1));
                            break;
                        case 4:
                            result = DecimalNumberDivision(number1.substring(1), number2.substring(1));
                            break;
                    }
                    Display(number1, number2, result);
                    break;
                case 0:
                    System.out.println("Incorrect Input! Program Ends!");
                    System.exit(0);
            }
            System.out.println();
        } else {
            System.out.println("Incorrect Input! Please make sure that your input is");
            System.out.println("valid. Program ends.");
            System.exit(0);
        }

        reader.close();
    }
}

