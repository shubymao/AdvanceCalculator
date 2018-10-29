package AdvanceCalculator;

import java.util.Scanner;

public class Multiplication {
    private static int NumberArrayLength;

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

    public static String WholeNumberMultiplication(String number1, String number2) {
        int NumberofColumn = 0;
        String Number1, Number2;

        if (number1.length() >= number2.length()) {
            NumberArrayLength = number1.length();
            NumberofColumn = number2.length();
            Number1 = number1;
            Number2 = number2;
        } else {
            NumberArrayLength = number2.length();
            NumberofColumn = number1.length();
            Number2 = number1;
            Number1 = number2;
        }

        int num1[] = toWholeNumber(Number1);
        int num2[] = toWholeNumber(Number2);

        int MaxLength = Number1.length() + Number2.length();
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

        if (i == finalresult.length())
            finalresult = "0";

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

    public static String WholeDecimalMultiplication(String number1, String number2) {
        String array1[], array2[];
        String finalresult;
        array1 = number1.split("[.]");
        array2 = number2.split("[.]");

        if (array1.length == 1 && array2.length == 2) {
            String DecimaltoWhole = array2[0] + array2[1];

            if (array1[0].length() >= DecimaltoWhole.length())
                NumberArrayLength = array1[0].length();
            else
                NumberArrayLength = DecimaltoWhole.length();

            String Product = WholeNumberMultiplication(array1[0], DecimaltoWhole);

            if ((Product.length() - array2[1].length()) > 0) {
                finalresult = Product.substring(0, Product.length() - array2[1].length())
                        + "." + Product.substring(Product.length() - array2[1].length());
            } else {
                for (int i = 0; i < array2[1].length(); i++) {
                    Product = "0" + Product;
                }
                finalresult = Product.substring(0, Product.length() - array2[1].length())
                        + "." + Product.substring(Product.length() - array2[1].length());
            }
        } else {
            String DecimaltoWhole = array1[0] + array1[1];

            if (array2[0].length() >= DecimaltoWhole.length())
                NumberArrayLength = array2[0].length();
            else
                NumberArrayLength = DecimaltoWhole.length();

            String Product = WholeNumberMultiplication(array2[0], DecimaltoWhole);

            if ((Product.length() - array1[1].length()) > 0) {
                finalresult = Product.substring(0, Product.length() - array1[1].length())
                        + "." + Product.substring(Product.length() - array1[1].length());
            } else {
                for (int i = 0; i < array1[1].length(); i++) {
                    Product = "0" + Product;
                }
                finalresult = Product.substring(0, Product.length() - array1[1].length())
                        + "." + Product.substring(Product.length() - array1[1].length());
            }
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

        return finalresult;
    }

    public static String DecimalNumberMultiplication(String number1, String number2) {
        String array1[], array2[];
        String finalresult;
        int DecimalDigit;


        array1 = number1.split("[.]");
        array2 = number2.split("[.]");
        String DecimaltoWhole1 = array1[0] + array1[1];
        String DecimaltoWhole2 = array2[0] + array2[1];
        DecimalDigit = array1[1].length() + array2[1].length();


        if (DecimaltoWhole1.length() >= DecimaltoWhole2.length())
            NumberArrayLength = DecimaltoWhole1.length();
        else
            NumberArrayLength = DecimaltoWhole2.length();

        String Product = WholeNumberMultiplication(DecimaltoWhole1, DecimaltoWhole2);


        if ((Product.length() - DecimalDigit) > 0) {
            finalresult = Product.substring(0, Product.length() - DecimalDigit)
                    + "." + Product.substring(Product.length() - DecimalDigit);
        } else {
            for (int i = 0; i < DecimalDigit; i++) {
                Product = "0" + Product;
            }
            finalresult = Product.substring(0, Product.length() - DecimalDigit)
                    + "." + Product.substring(Product.length() - DecimalDigit);
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
        System.out.println(number1 + " * " + number2 + " = " + result);
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
                            result = WholeNumberMultiplication(number1, number2);
                            break;
                        case 2:
                            result = "-" + WholeNumberMultiplication(number1.substring(1, number1.length()), number2);
                            break;
                        case 3:
                            result = "-" + WholeNumberMultiplication(number1, number2.substring(1, number2.length()));
                            break;
                        case 4:
                            result = WholeNumberMultiplication(number1.substring(1, number1.length()), number2.substring(1, number2.length()));
                            break;
                    }
                    Display(number1, number2, result);
                    break;
                case 2:
                    switch (Sign) {
                        case 1:
                            result = WholeDecimalMultiplication(number1, number2);
                            break;
                        case 2:
                            result = "-" + WholeDecimalMultiplication(number1.substring(1, number1.length()), number2);
                            break;
                        case 3:
                            result = "-" + WholeDecimalMultiplication(number1, number2.substring(1, number2.length()));
                            break;
                        case 4:
                            result = WholeDecimalMultiplication(number1.substring(1, number1.length()), number2.substring(1, number2.length()));
                            break;
                    }
                    Display(number1, number2, result);
                    break;
                case 3:
                    switch (Sign) {
                        case 1:
                            result = DecimalNumberMultiplication(number1, number2);
                            break;
                        case 2:
                            result = "-" + DecimalNumberMultiplication(number1.substring(1, number1.length()), number2);
                            break;
                        case 3:
                            result = "-" + DecimalNumberMultiplication(number1, number2.substring(1, number2.length()));
                            break;
                        case 4:
                            result = DecimalNumberMultiplication(number1.substring(1, number1.length()), number2.substring(1, number2.length()));
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

