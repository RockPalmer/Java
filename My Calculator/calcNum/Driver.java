package calcNum;

import java.util.Scanner;

public class Driver 
{
	public static void main (String[] args)
	{
		MultiDigNum number = new MultiDigNum();
		Scanner scan = new Scanner(System.in);
		int x = 1;
		do 
		{
			
			System.out.println("Enter what you want to do:\n"
					+ "a) Scan number\n"
					+ "b) Print out number attributes\n"
					+ "c) Set the span\n"
					+ "d) Add number\n"
					+ "e) Subtract number\n"
					+ "f) Multiply number\n"
					+ "g) Divide number\n"
					+ "h) Compare numbers\n"
					+ "i) Put number to an exponent");
			String choice = scan.next();
			String num;
			MultiDigNum num2;
			System.out.println();
			switch (choice) 
			{
			case "a":
				System.out.println("Enter your number.");
				String newNumber = scan.next();
				number = new MultiDigNum(newNumber);
				System.out.println(number + " was your number.");
				break;
			case "b":
				System.out.println("Your number attributes are...");
				System.out.println("Span: " + number.getSpan()[0] + ":" + number.getSpan()[1]);
				System.out.print("Characters: ");
				for (int count = 0; count < number.getCharacters().length; count++) 
				{
					System.out.print(number.getCharacters()[count] + "|");
				}
				System.out.print("\nDigits: ");
				for (int count = 0; count < number.getDigits().length; count++) 
				{
					System.out.print(number.getDigits()[count] + "|");
				}
				System.out.println("\nIs it negative? " + number.getIsNegative());
				System.out.println("Could it be written as an integer? " + number.getIsInteger());
				System.out.println("Decimal index: " + number.getDecimalIndex());
				break;
			case "c":
				int[] tempSpan = new int[2];
				System.out.println("Enter the span before the decimal place you want.");
				tempSpan[0] = scan.nextInt();
				System.out.println("Enter the span after the decimal place you want.");
				tempSpan[1] = scan.nextInt();
				number.setSpan(tempSpan);
				System.out.println("Your new number is: " + number.getNumString());
			case "d":
				System.out.println("Enter the number you would like to add to " + number + ".");
				num = scan.next();
				num2 = new MultiDigNum(num);
				System.out.println("The number you are adding is " + num2);
				number.setNumString(number.add(num2).getNumString());
				System.out.println("Your new number is:");
				System.out.println(number);
				break;
			case "e":
				System.out.println("Enter the number you would like to subtract from " + number + ".");
				num = scan.next();
				num2 = new MultiDigNum(num);
				System.out.println(number + " is greater than " + num2 + " is " + number.greaterThan(num2));
				System.out.println("The number you are subtracting is " + num2 + ".");
				number.setNumString(number.subtract(num2).getNumString());
				System.out.println("Your new number is:");
				System.out.println(number);
				break;
			case "f":
				System.out.println("Enter the number you would like to multiply " + number + " by.");
				num = scan.next();
				num2 = new MultiDigNum(num);
				System.out.println("The number you are multiplying is " + num2 + ".");
				number.setNumString(number.multiply(num2).getNumString());
				System.out.println("Your new number is:");
				System.out.println(number);
				break;
			case "g":
				System.out.println("Enter the number you would like to divide " + number + " by.");
				num = scan.next();
				num2 = new MultiDigNum(num);
				System.out.println("The number you are dividing by is " + num2 + ".");
				number.setAsEqualTo(number.divideBy(num2));
				System.out.println("Your new number is:");
				System.out.println(number);
				break;
			case "h":
				System.out.println("Enter the number you would like to compare to " + number + ".");
				num = scan.next();
				num2 = new MultiDigNum(num);
				System.out.println("You would like to compare the number " + num2 + " to " + number + ".");
				if(number.greaterThan(num2)) 
				{
					System.out.println(number + " is greater than " + num2 + ".");
				}
				else if (number.lessThan(num2)) 
				{
					System.out.println(number + " is less than " + num2 + ".");
				}
				else if (number.equals(num2)) 
				{
					System.out.println(number + " and " + num2 + " are equal.");
				}
				else 
				{
					System.out.println("The numbers could not be compared.");
				}
				break;
			case "i":
				System.out.println("Enter the number you would like to put " + number + " to the power of.");
				num = scan.next();
				num2 = new MultiDigNum(num);
				System.out.println("The number you are putting it to the power of is " + num2 + ".");
				number.setAsEqualTo(number.toPowerOf(num2));
				System.out.println("Your new number is:");
				System.out.println(number);
				break;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while(x == 1);

		scan.close();
	}
}
