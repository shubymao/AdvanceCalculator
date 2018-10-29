package AdvanceCalculator;
import java.util.Scanner;

public class Addition_And_Subtraction
{
	private static int  NumberArrayLength;
	
	public static int[] toWholeNumber(String n)
	{
		int num[] = new int[NumberArrayLength];
		int i;
		int j=0;
		for(i=0; i<NumberArrayLength-n.length(); i++)
		{
			num[i] = 0;
		}
		
		while(i<NumberArrayLength)
		{
			num[i] = n.charAt(j) - 48;
			i++;
			j++;
		}
		return num;
	}
	
	public static String WholeNumberAddition(String number1, String number2)
	{
		if(number1.length() >= number2.length())
			NumberArrayLength = number1.length();
		else
			NumberArrayLength = number2.length();
		
		int num1[] = toWholeNumber(number1);
		int num2[] = toWholeNumber(number2);
		int result[] = new int[NumberArrayLength+1];
		int temp[] = new int[NumberArrayLength];
		String finalresult="";
		
		for(int i=0; i<NumberArrayLength; i++)
		{
			temp[i] = num1[i];
		}

		int carry = 0;
		int digit =0;
		for(int i=NumberArrayLength-1; i>0; i--)
		{
			digit = temp[i] + num2[i];
			result[i+1] = digit % 10;
			carry = digit / 10;
			temp[i-1] = temp[i-1] + carry;
		}
		
		digit = temp[0] + num2[0];
		result[1] = digit % 10;
		carry = digit / 10;
		if(carry == 1)
			result[0] = 1;
		else 
			result[0] = 0;
		
		for(int i=0; i<result.length; i++)
		{
			finalresult += result[i];
		}
		
		int i;
		for(i=0; i<finalresult.length(); i++)
		{
			if(finalresult.charAt(i) != '0')
			{
				finalresult = finalresult.substring(i);
				break;
			}	
		}
		if(i == finalresult.length())
			finalresult = "0";
		
		return finalresult;
	}
	
	public static String WholeDecimalAddition(String number1, String number2)
	{
		String array1[], array2[];
		String finalresult;
		array1 = number1.split("[.]");
		array2 = number2.split("[.]");
		
		if(array1.length == 1 && array2.length == 2)
		{
			String WholeNumberSum = WholeNumberAddition(array1[0], array2[0]);
			finalresult = WholeNumberSum + "." + array2[1];
		}
		else
		{
			String WholeNumberSum = WholeNumberAddition(array1[0], array2[0]);
			finalresult = WholeNumberSum + "." + array1[1];
		}

		for(int i=finalresult.length()-1; i>=0; i--)
		{
			if(finalresult.charAt(i) != '0')
			{
					finalresult = finalresult.substring(0,i+1);
					if(finalresult.charAt(finalresult.length()-1) == '.')
						finalresult = finalresult.substring(0, finalresult.length()-1);
					break;
			}	
		}
		
		return finalresult;
	}
	
	public static String DecimalNumberAddition(String number1, String number2)
	{
		String array1[], array2[];
		String finalresult;
		array1 = number1.split("[.]");
		array2 = number2.split("[.]");
		String Decimal_Whole1, Decimal_Whole2;
		
		if(array1[1].length() > array2[1].length())
		{
			Decimal_Whole1 = array1[0] + array1[1];
			Decimal_Whole2 = array2[0] + array2[1];
			for(int i=0; i<array1[1].length()-array2[1].length(); i++)
				Decimal_Whole2 = Decimal_Whole2 + "0";
			
			finalresult = WholeNumberAddition(Decimal_Whole1, Decimal_Whole2);
			finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
						  + finalresult.substring(finalresult.length()-array1[1].length());
		}
		else if(array1[1].length() < array2[1].length())
		{
			Decimal_Whole1 = array1[0] + array1[1];
			Decimal_Whole2 = array2[0] + array2[1];
			for(int i=0; i<array2[1].length()-array1[1].length(); i++)
				Decimal_Whole1 = Decimal_Whole1 + "0";
			
			finalresult = WholeNumberAddition(Decimal_Whole1, Decimal_Whole2);
			finalresult = finalresult.substring(0, finalresult.length()-array2[1].length()) + "." 
						  + finalresult.substring(finalresult.length()-array2[1].length());
		}
		else
		{
			Decimal_Whole1 = array1[0] + array1[1];
			Decimal_Whole2 = array2[0] + array2[1];
			
			finalresult = WholeNumberAddition(Decimal_Whole1, Decimal_Whole2);
			finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
						  + finalresult.substring(finalresult.length()-array1[1].length());
		}
		
		for(int i=finalresult.length()-1; i>=0; i--)
		{
			if(finalresult.charAt(i) != '0')
			{
					finalresult = finalresult.substring(0,i+1);
					if(finalresult.charAt(finalresult.length()-1) == '.')
						finalresult = finalresult.substring(0, finalresult.length()-1);
					break;
			}	
		}
		
		return finalresult;
	}
	
	public static String WholeNumberSubtraction(String number1, String number2)
	{
		int j;
		for(j=0; j<number1.length(); j++)
		{
			if(number1.charAt(j) != '0')
			{
				number1 = number1.substring(j);
				break;
			}	
		}		
		for(j=0; j<number2.length(); j++)
		{
			if(number2.charAt(j) != '0')
			{
				number2 = number2.substring(j);
				break;
			}	
		}
		
		String Big="", Small="";
		boolean isNegative = false;
		if(number1.length() > number2.length())
		{
			NumberArrayLength = number1.length();
			Big = number1;
			Small = number2;
		}
		else if(number1.length() < number2.length())
		{
			NumberArrayLength = number2.length();
			Big = number2;
			Small = number1;
			isNegative = true;
		}
		else
		{
			int i;
			for(i=0; i<number1.length(); i++)
			{
				if(number1.charAt(i) > number2.charAt(i))
				{
					NumberArrayLength = number1.length();
					Big = number1;
					Small = number2;
					break;
				}
				else if(number1.charAt(i) < number2.charAt(i))
				{
					NumberArrayLength = number2.length();
					Big = number2;
					Small = number1;
					isNegative = true;
					break;
				}
			}
			if(i == number1.length())
				return "0";
		}

		int num1[] = toWholeNumber(Big);
		int num2[] = toWholeNumber(Small);
		int result[] = new int[NumberArrayLength];
		int temp[] = new int[NumberArrayLength];
		String finalresult = "";
		
		for(int i=0; i<NumberArrayLength; i++)
		{
			temp[i] = num1[i];
		}
		
		int digit =0;
		for(int i=NumberArrayLength-1; i>0; i--)
		{
			if(temp[i] - num2[i] >= 0)
			{
				digit = temp[i] - num2[i];
				result[i] = digit;
			}
			else
			{
				digit = temp[i] + 10 - num2[i];
				result[i] = digit;
				temp[i-1] -= 1;
			}
		}
		
		digit = temp[0] - num2[0];
		result[0] = digit;

		for(int i=0; i<result.length; i++)
		{
			finalresult += result[i];
		}
	
		int i;
		for(i=0; i<finalresult.length(); i++)
		{
			if(finalresult.charAt(i) != '0')
			{
				finalresult = finalresult.substring(i);
				if(isNegative)
					finalresult = "-" + finalresult;
				break;
			}	
		}
		
		return finalresult;
	}
	
	public static String WholeDecimalSubtraction(String number1, String number2)
	{
		String array1[], array2[];
		String finalresult;
		array1 = number1.split("[.]");
		array2 = number2.split("[.]");
		
		if(array1.length ==2 && array2.length == 1)
		{
			String Decimal_Whole = array1[0] + array1[1];
			for(int i=0; i<array1[1].length(); i++)
			{
				array2[0] = array2[0] + "0";
			}
			
			String WholeNumberSubtract = WholeNumberSubtraction(Decimal_Whole, array2[0]);

			if(WholeNumberSubtract.length()-array1[1].length() == 0)
				finalresult =  "0." + WholeNumberSubtract;
			else if(WholeNumberSubtract.length()-array1[1].length() == 1 && WholeNumberSubtract.charAt(0) == '-')
				finalresult =  "-0." + WholeNumberSubtract.substring(1);
			else		
				finalresult = WholeNumberSubtract.substring(0, WholeNumberSubtract.length()-array1[1].length()) + "." 
							  + WholeNumberSubtract.substring(WholeNumberSubtract.length()-array1[1].length());
		}
		else
		{
			String Decimal_Whole = array2[0] + array2[1];
			for(int i=0; i<array2[1].length(); i++)
			{
				array1[0] = array1[0] + "0";
			}
			
			String WholeNumberSubtract = WholeNumberSubtraction(array1[0], Decimal_Whole);
			
			if(WholeNumberSubtract.length()-array2[1].length() == 0)
				finalresult =  "0." + WholeNumberSubtract;
			else if(WholeNumberSubtract.length()-array2[1].length() == 1 && WholeNumberSubtract.charAt(0) == '-')
				finalresult =  "-0." + WholeNumberSubtract.substring(1);
			else
				finalresult = WholeNumberSubtract.substring(0, WholeNumberSubtract.length()-array2[1].length()) + "." 
							  + WholeNumberSubtract.substring(WholeNumberSubtract.length()-array2[1].length());
		}
		
		for(int i=finalresult.length()-1; i>=0; i--)
		{
			if(finalresult.charAt(i) != '0')
			{
					finalresult = finalresult.substring(0,i+1);
					if(finalresult.charAt(finalresult.length()-1) == '.')
						finalresult = finalresult.substring(0, finalresult.length()-1);
					break;
			}	
		}
			
		return finalresult;
	}
	
	public static String DecimalNumberSubtraction(String number1, String number2)
	{
		String array1[], array2[];
		String finalresult="";
		array1 = number1.split("[.]");
		array2 = number2.split("[.]");
		String Decimal_Whole1, Decimal_Whole2;
		
		if(array1[1].length() > array2[1].length())
		{
			Decimal_Whole1 = array1[0] + array1[1];
			Decimal_Whole2 = array2[0] + array2[1];
			for(int i=0; i<array1[1].length()-array2[1].length(); i++)
				Decimal_Whole2 = Decimal_Whole2 + "0";
			
			finalresult = WholeNumberSubtraction(Decimal_Whole1, Decimal_Whole2);
			
			if(finalresult.length()-array1[1].length()<0)
			{
				if(finalresult.charAt(0) == '-')
				{
					finalresult = finalresult.substring(1);
					for(int i=0; i<array1[1].length(); i++)
					{
						finalresult = "0" + finalresult;
					}
					finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
						          + finalresult.substring(finalresult.length()-array1[1].length());
					finalresult = "-" + finalresult;
				}
				else
				{
					for(int i=0; i<array1[1].length(); i++)
					{
						finalresult = "0" + finalresult;
					}
					finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
							      + finalresult.substring(finalresult.length()-array1[1].length());
				}
			}
			else if(finalresult.length()-array1[1].length() == 0)
			{
				if(finalresult.charAt(0) == '-')
				{
					finalresult = finalresult.substring(1);
					finalresult = "-0.0" + finalresult;
				}
				else
				{
					finalresult = "0." + finalresult;
				}
			}
			else if(finalresult.length()-array1[1].length() == 1 && finalresult.charAt(0) == '-')
			{
				finalresult = finalresult.substring(1);
				finalresult = "-0." + finalresult;
			}
			else
				finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
						  	  + finalresult.substring(finalresult.length()-array1[1].length());
		}
		else if(array1[1].length() < array2[1].length())
		{
			Decimal_Whole1 = array1[0] + array1[1];
			Decimal_Whole2 = array2[0] + array2[1];
			for(int i=0; i<array2[1].length()-array1[1].length(); i++)
				Decimal_Whole1 = Decimal_Whole1 + "0";
			
			finalresult = WholeNumberSubtraction(Decimal_Whole1, Decimal_Whole2);
			
			if(finalresult.length()-array2[1].length()<0)
			{
				if(finalresult.charAt(0) == '-')
				{
					finalresult = finalresult.substring(1);
					for(int i=0; i<array2[1].length(); i++)
					{
						finalresult = "0" + finalresult;
					}
					finalresult = finalresult.substring(0, finalresult.length()-array2[1].length()) + "." 
						          + finalresult.substring(finalresult.length()-array2[1].length());
					finalresult = "-" + finalresult;
				}
				else
				{
					for(int i=0; i<array2[1].length(); i++)
					{
						finalresult = "0" + finalresult;
					}
					finalresult = finalresult.substring(0, finalresult.length()-array2[1].length()) + "." 
							      + finalresult.substring(finalresult.length()-array2[1].length());
				}
			}
			else if(finalresult.length()-array2[1].length() == 0)
			{
				if(finalresult.charAt(0) == '-')
				{
					finalresult = finalresult.substring(1);
					finalresult = "-0.0" + finalresult;
				}
				else
				{
					finalresult = "0." + finalresult;
				}
			}
			else if(finalresult.length()-array2[1].length() == 1 && finalresult.charAt(0) == '-')
			{
				finalresult = finalresult.substring(1);
				finalresult = "-0." + finalresult;
			}
			else
				finalresult = finalresult.substring(0, finalresult.length()-array2[1].length()) + "." 
						  	  + finalresult.substring(finalresult.length()-array2[1].length());
		}
		else
		{
			Decimal_Whole1 = array1[0] + array1[1];
			Decimal_Whole2 = array2[0] + array2[1];
			
			finalresult = WholeNumberSubtraction(Decimal_Whole1, Decimal_Whole2);
			
			if(finalresult.length()-array1[1].length()<0)
			{
				if(finalresult.charAt(0) == '-')
				{
					finalresult = finalresult.substring(1);
					for(int i=0; i<array1[1].length(); i++)
					{
						finalresult = "0" + finalresult;
					}
					finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
						          + finalresult.substring(finalresult.length()-array1[1].length());
					finalresult = "-" + finalresult;
				}
				else
				{
					for(int i=0; i<array1[1].length(); i++)
					{
						finalresult = "0" + finalresult;
					}
					finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
							      + finalresult.substring(finalresult.length()-array1[1].length());
				}
			}
			else if(finalresult.length()-array1[1].length() == 0)
			{
				if(finalresult.charAt(0) == '-')
				{
					finalresult = finalresult.substring(1);
					finalresult = "-0.0" + finalresult;
				}
				else
				{
					finalresult = "0." + finalresult;
				}
			}
			else if(finalresult.length()-array1[1].length() == 1 && finalresult.charAt(0) == '-')
			{
				finalresult = finalresult.substring(1);
				finalresult = "-0." + finalresult;
			}
			else
				finalresult = finalresult.substring(0, finalresult.length()-array1[1].length()) + "." 
						  	  + finalresult.substring(finalresult.length()-array1[1].length());
		}
		
		if(finalresult.charAt(0) == '-')
		{
			finalresult = finalresult.substring(1);
			for(int i=0; i<finalresult.length(); i++)
			{
				if(finalresult.charAt(i) != '0')
				{
						finalresult = finalresult.substring(i);
						if(finalresult.charAt(0) == '.')
							finalresult = "0" + finalresult;
						break;
				}	
			}
			finalresult = "-" + finalresult;
		}
		else
		{
			for(int i=0; i<finalresult.length(); i++)
			{
				if(finalresult.charAt(i) != '0')
				{
						finalresult = finalresult.substring(i);
						if(finalresult.charAt(0) == '.')
							finalresult = "0" + finalresult;
						break;
				}	
			}
		}
		
		
		for(int i=finalresult.length()-1; i>=0; i--)
		{
			if(finalresult.charAt(i) != '0')
			{
					finalresult = finalresult.substring(0,i+1);
					if(finalresult.charAt(finalresult.length()-1) == '.')
						finalresult = finalresult.substring(0, finalresult.length()-1);
					break;
			}	
		}
		
		return finalresult;
	}
	
	public static boolean isRight(String number1, String number2)
	{
		String array1[] = number1.split("[.]");
		String array2[] = number2.split("[.]");
		
		if(array1.length > 2 || array2.length > 2)
			return false;
		
		if(!(number1.charAt(0)=='-' || (number1.charAt(0)>='0' && number1.charAt(0)<='9')))
			return false;
		for(int i=1; i<number1.length(); i++)
		{
			if(!(number1.charAt(i)=='.' || (number1.charAt(i)>='0' && number1.charAt(i)<='9')))
				return false;
		}
		
		if(!(number2.charAt(0)=='-' || (number2.charAt(0)>='0' && number2.charAt(0)<='9')))
			return false;
		for(int i=1; i<number2.length(); i++)
		{
			if(!(number2.charAt(i)=='.' || (number2.charAt(i)>='0' && number2.charAt(i)<='9')))
				return false;
		}
		
		return true;
	}
	
	public static void Display(String number1, char operator, String number2, String result)
	{
		if(operator == '+')
			System.out.println(number1 + " + " + number2 + " = " + result);
		else
			System.out.println(number1 + " - " + number2 + " = " + result);
	}
	
	public static int CalculateStatus(String number1, String number2)
	{
		String array1[] = number1.split("[.]");
		String array2[] = number2.split("[.]");
		
		if(array1.length == 1 && array2.length == 1)
			return 1;
		else if(array1.length == 1 && array2.length == 2)
			return 2;
		else if(array1.length == 2 && array2.length == 1)
			return 2;
		else if(array1.length == 2 && array2.length == 2)
			return 3;
		else
			return 0;
	}
	
	public static int CheckSign(String number1, String number2)
	{
		if(number1.charAt(0) != '-' && number2.charAt(0) != '-')
			return 1;
		else if(number1.charAt(0) == '-' && number2.charAt(0) != '-')
			return 2;
		else if(number1.charAt(0) != '-' && number2.charAt(0) == '-')
			return 3;
		else
			return 4;
	}
	
	public static void main(String args[])
	{
		Scanner reader = new Scanner(System.in);  
		
		System.out.println("Enter a number: ");
		String number1 = reader.next();
		
		System.out.println("Enter an operator: ");
		char operator = reader.next().charAt(0);
		
		System.out.println("Enter another number: ");
		String number2 = reader.next();
		
		System.out.println();
		
		if(isRight(number1, number2))
		{
			String result = "";
			int Status = CalculateStatus(number1, number2);
			int Sign = CheckSign(number1, number2);
			
			if(operator == '+')
			{
				switch(Status)
				{
				case 1:
					switch(Sign)
					{
					case 1:
						result = WholeNumberAddition(number1, number2);
						break;
					case 2:
						result = WholeNumberSubtraction(number2, number1.substring(1));
						break;
					case 3:
						result = WholeNumberSubtraction(number1, number2.substring(1));
						break;
					case 4:
						result = "-" + WholeNumberAddition(number1.substring(1), number2.substring(1));
						break;
					}	
					Display(number1, '+', number2, result);
					break;
				case 2:	
					switch(Sign)
					{
					case 1:
						result = WholeDecimalAddition(number1, number2);
						break;
					case 2:
						result = WholeDecimalSubtraction(number2, number1.substring(1));
						break;
					case 3:
						result = WholeDecimalSubtraction(number1, number2.substring(1));
						break;
					case 4:
						result = "-" + WholeDecimalAddition(number1.substring(1), number2.substring(1));
						break;
					}
					Display(number1, '+', number2, result);
					break;
				case 3:
					switch(Sign)
					{
					case 1:
						result = DecimalNumberAddition(number1, number2);
						break;
					case 2:
						result = DecimalNumberSubtraction(number2, number1.substring(1));
						break;
					case 3:
						result = DecimalNumberSubtraction(number1, number2.substring(1));
						break;
					case 4:
						result = "-" + DecimalNumberAddition(number1.substring(1), number2.substring(1));
						break;
					}
					Display(number1, '+', number2, result);
					break;
				case 0:
					System.out.println("Incorrect Input! Program Ends!");
					System.exit(0);	
				}
			}
			else if(operator == '-')
			{
				switch(Status)
				{
				case 1:
					switch(Sign)
					{
					case 1:
						result = WholeNumberSubtraction(number1, number2);
						break;
					case 2:
						result = "-" + WholeNumberAddition(number1.substring(1), number2);
						break;
					case 3:
						result = WholeNumberAddition(number1, number2.substring(1));
						break;
					case 4:
						result = WholeNumberSubtraction(number2.substring(1), number1.substring(1));
						break;
					}	
					Display(number1, '-', number2, result);
					break;
				case 2:	
					switch(Sign)
					{
					case 1:
						result = WholeDecimalSubtraction(number1, number2);
						break;
					case 2:
						result = "-" + WholeDecimalAddition(number1.substring(1), number2);
						break;
					case 3:
						result = WholeDecimalAddition(number1, number2.substring(1));
						break;
					case 4:
						result = WholeDecimalSubtraction(number2.substring(1), number1.substring(1));
						break;
					}
					Display(number1, '-', number2, result);
					break;
				case 3:
					switch(Sign)
					{
					case 1:
						result = DecimalNumberSubtraction(number1, number2);
						break;
					case 2:
						result = "-" + DecimalNumberAddition(number1.substring(1), number2);
						break;
					case 3:
						result = DecimalNumberAddition(number1, number2.substring(1));
						break;
					case 4:
						result = DecimalNumberSubtraction(number2.substring(1), number1.substring(1));
						break;
					}
					Display(number1, '-', number2, result);
					break;
				case 0:
					System.out.println("Incorrect Input! Program Ends!");
					System.exit(0);	
				}
			}
			else
			{
				System.out.println("Incorrect Operator! Program Ends.");
				System.exit(0);	
			}
		}
		else
		{
			System.out.println("Incorrect Input! Please make sure that your input is");
			System.out.println("valid. Program ends.");
			System.exit(0);
		}
		
		reader.close();
	}
}
