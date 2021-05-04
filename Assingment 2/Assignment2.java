// Assignment #: 2
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T Th	1:30 PM - 2:45 PM
//  Description: This class reads an series of integers and prints out 
//				various calculations of those integers.

import java.util.Scanner;

public class Assignment2 
{
	public static void main (String[] args) 
	{
		// Variables that will store the 4 calculated values
		int maxInt = 0;
		int evenNumCount = 0;
		int smallestOddInt = 0;
		int sumNegInts = 0;
		
		/* Initialize other variables that will be used to track and/or
		 * calculate the above output variables */
		String term = "";
		Scanner scan = new Scanner(System.in);
		boolean nextNumIsNeg = false;
		boolean firstOddIntFound = false;
		boolean firstIntFound = false;
		
		// scan in all terms
		do
		{
			term = scan.next();
			
			/* If the program scans a free floating '-', it tells the program
			 * that the next number will have a '-' sign on the front */
			if(term.equals("-")) 
			{
				nextNumIsNeg = true;
			}
			else 
			{
				/* If the last term was a free-floating '-' sign, the program adds a '-' to the term string */
				if (nextNumIsNeg) 
				{
					term = '-' + term;
					nextNumIsNeg = false;
				}
				/* If the term is less than 0, the value of the term is added onto the sumNegInts variable */
				if (Double.parseDouble(term) < 0) 
				{
					sumNegInts += Double.parseDouble(term);
				}
				/* If the term is even, the evenNumCount variable is increased by one */
				if (Double.parseDouble(term) % 2 == 0) 
				{
					evenNumCount++;
				}
				else 
				{
					/* If the term is odd and the first odd number has not been found yet
					 * so that the program can check which odd number is smallest, it will
					 * automatically set that odd number as the smallest odd number */
					if (!firstOddIntFound) 
					{
						smallestOddInt = (int)Double.parseDouble(term);
						firstOddIntFound = true;
					}
					/* If the first odd number has already been found, then the smallestOddInt
					 * variable will only be set to be equal to the term if the term is smaller
					 * than the current value of smallestOddInt */
					else 
					{
						if (Double.parseDouble(term) < smallestOddInt) 
						{
							smallestOddInt = (int)Double.parseDouble(term);
						}
					}
				}
				/* The same concept of tracking smallestOddInt is applied here. And only if
				 * the first int is found, does the program check to make sure that the term is larger or 
				 * smaller than the current value of the maxInt variable */
				if (!firstIntFound) 
				{
					maxInt = (int)Double.parseDouble(term);
					firstIntFound = true;
				}
				else 
				{
					if (maxInt < Double.parseDouble(term)) 
					{
						maxInt = (int)Double.parseDouble(term);
					}
				}
			}
			// Continue this loop until the term variable is equal to 0
		} while (!term.equals("0"));
		
		// Print out calculated values
		System.out.println("The maximum integer is " + maxInt);
		System.out.println("The count of even integers in the sequence is " + evenNumCount);
		System.out.println("The smallest odd integer in the sequence is " + smallestOddInt);
		System.out.println("The sum of negative integers is " + sumNegInts);
		scan.close();
	}
}
