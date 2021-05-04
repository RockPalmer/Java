// Assignment #: 5
// Arizona State University - CSE205
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T Th	1:30 PM - 2:45 PM
//  Description: Creates a Student object given an input
//				 String containing all the parameters for
//				 the Student object

import java.util.Scanner;

public class StuParser 
{
	public static Student parseStringToStudent(String lineToParse) 
	{
		/* Create a String array to store all the attributes that may be given by the use
		 * 
		 * Because the String can either have 7 or 8 attributes based on if the Student is an Undergrad
		 * or a Graduate, the array is given a length of 8 catch all possible inputs */
		String[] attributes = new String[8];
		
		/* Create a scanner that will scan the String inputed by the user and distinguishes the attributes
		 * from one another with the '/' character */
		Scanner scan = new Scanner(lineToParse).useDelimiter("/");
		for (int count = 0; scan.hasNext(); count++)
		{
			attributes[count] = scan.next();
		}
		scan.close();
		
		/* If the first String is "Graduate" the program will set the next 6 Strings as the parameters for the Graduate object */
		if (attributes[0].equals("Graduate")) 
		{
			Graduate grad = new Graduate(attributes[1], attributes[2], attributes[3], Integer.parseInt(attributes[4]), Double.parseDouble(attributes[5]), Double.parseDouble(attributes[6]));
			return grad;
		}
		/* If the String describes an UnderGrad, the program will set the next 7 Strings as the UnderGrad Attributes */
		else 
		{
			boolean isInState;
			if (attributes[6].equals("InState") || attributes[6].equals("inState"))
			{
				isInState = true;
			}
			else
			{
				isInState = false;
			}
			UnderGrad unGrad = new UnderGrad(attributes[1], attributes[2], attributes[3], Integer.parseInt(attributes[4]), Double.parseDouble(attributes[5]), isInState, Double.parseDouble(attributes[7]));
			return unGrad;
		}
	}
}
