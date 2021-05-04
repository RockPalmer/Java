// Assignment #: 1
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T Th	1:30 PM - 2:45 PM
//  Description: This class reads an integer from a keyboard and prints it out
//               along with other messages.

import java.util.Scanner;  
// use the Scanner class located in the "java.util" directory
public class Assignment1 
{  
	public static void main (String[] args)
	{	
		int number;
		
		Scanner console = new Scanner(System.in);
		
		// read the whole line entered by the user
		String line = console.nextLine();
		
		// initialize the String version of the number they entered
		String numberString = "";
		
		// remove any and all spaces from the number the user inputed
		for (int count = 0; count < line.length(); count++) 
		{
			if (line.charAt(count) != ' ') 
			{
				numberString += line.charAt(count);
			}
		}
		
		// parse the number string the program just created into an integer and store it into the number variable
		number = Integer.parseInt(numberString);
		
		// display the number with other messages
		System.out.print("This program reads an integer from a keyboard,\n"
				+ "and prints it out on the display screen.\n"
				+ "The number is:" + number + "\n"
				+ "Make sure that you get the exact same output as the expected one!\n");
		
		// close the scanner
		console.close();
	}
}
