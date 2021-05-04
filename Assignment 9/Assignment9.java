//  Assignment: ASU CSE205 Spring 2021 #9
//  Name: Rock Palmer
//  StudentID: 1214784662
//  Lecture: T and Th 1:30-2:45PM
//  Description: The Assignment 9 class reads a sequence of integers from input,
//  and compute the maximum, counts numbers divisible by 3, sum of numbers larger than the last, and
//  compute the largest even integer in the sequence using recursion.

import java.io.*;
public class Assignment9 
{
	public static void main(String[] args) 
	{
		// Declare integers and int array to store information
		int num, even, max, count, countDiv, sum, i;
		num = 0;
		even = 0;
		count = 0;
		countDiv = 0;
		sum = 0;
		i = 0;
		int[] numbers = new int[100];
		
		// Try-Catch block for input stream and buffered reader io exceptions
		try 
		{
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(input);
			
			// Read the first line and parse it into an integer.
			String line = reader.readLine().trim();
			String number = "";
			for (i = 0; i < line.length(); i++) 
			{
				if (line.charAt(i) != ' ') 
				{
					number += line.charAt(i);
				}
			}
			num = Integer.parseInt(number);
			
			// Only Store the line in the array and continue reading if the number read is not a zero
			while (num != 0) 
			{
				numbers[count] = num;
				count++;
				
				line = reader.readLine().trim();
				number = "";
				for (i = 0; i < line.length(); i++) 
				{
					if (line.charAt(i) != ' ') 
					{
						number += line.charAt(i);
					}
				}
				num = Integer.parseInt(number);
			}
			reader.close();
			
			// Increment count down one to match the index of the last number entered by the user.
			count--;
		} //end of try block
		// Catch an IO Exception and print out it occurred
		catch(IOException ex) 
		{
			System.out.println("IO Exception");
		}
		
		// Call recursive functions to calculate min, countOdd, largeEven, and sum
		max = findMax(numbers, 0, count);
		even = largestEven(numbers, 0, count);
		countDiv = countDivisibleBy3(numbers, 0, count);
		sum = sumLargerThanLast(numbers, 0, count, numbers[count]);
		
		// Print out results in required format
		System.out.println("The maximum number is " + max);
		System.out.println("The largest even integer in the sequence is " + even);
		System.out.println("The count of numbers divisible by 3 is " + countDiv);
		System.out.println("The sum of numbers larger than the last is " + sum);
	}// End main method
	
	// Recursive static method to find maximum array value
	public static int findMax(int[] nums, int startIndex, int endIndex) 
	{
		int max = nums[startIndex];
		startIndex++;
		if (startIndex <= endIndex)
		{
			if (findMax(nums, startIndex, endIndex) > max) 
			{
				max = findMax(nums,startIndex,endIndex);
			}
		}
		return max;
	}// End findMax method
	
	// Recursive static method to find the largest even number in the array        
	public static int largestEven(int[] nums, int startIndex, int endIndex) 
	{
		int largestEven = nums[startIndex];

		startIndex++;
		if (startIndex <= endIndex)
		{
			if (largestEven % 2 != 0) 
			{
				if (largestEven(nums, startIndex, endIndex) % 2 == 0) 
				{
					largestEven = largestEven(nums, startIndex, endIndex);
				}
			}
			else
			{
				if (largestEven(nums, startIndex, endIndex) % 2 == 0)
				{
					if (largestEven < largestEven(nums, startIndex, endIndex))
					{
						largestEven = largestEven(nums, startIndex, endIndex);
					}
				}
			}
		}
		
		return largestEven;
	}// End computeLargestEven method
	
	public static int countDivisibleBy3(int[] nums, int startIndex, int endIndex)
	{
		int count = 0;
		int num = nums[startIndex];
		if (startIndex <= endIndex) 
		{
			startIndex++;
			if (num % 3 == 0)
			{
				count++;
			}
			count += countDivisibleBy3(nums, startIndex, endIndex);
		}
		
		return count;
	} //end countDivisibleBy3
	
	// Recursive static method to find the sum of all numbers larger than the last number in the array
	public static int sumLargerThanLast(int[] nums, int startIndex, int endIndex, int lastNumber) 
	{
		int sum = 0;
		endIndex--;
		if (endIndex >= startIndex)
		{
			int num = nums[endIndex];
			if (num > lastNumber)
			{
				sum += num;
			}
			sum += sumLargerThanLast(nums, startIndex, endIndex, lastNumber);
		}
		return sum;
	}// End sumOfNumbersLargerThanFirst method
}//end Assignment9 class
