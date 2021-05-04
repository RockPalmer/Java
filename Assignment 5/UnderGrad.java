// Assignment #: 5
// Arizona State University - CSE205
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T Th	1:30 PM - 2:45 PM
//  Description: Describes a Graduate object with instance variables that
//				 inherits all the instance variables from the Student class
//				 as well as 3 other variables that store the fee required
//				 for the undergrad to pay, the total amount of credits the 
//				 undergrad can be charged for, and whether the student is
//				 in-State or not. It also inherits the computeTuition method 
//				 from Student but implements it with these extra variables.

import java.text.NumberFormat;
import java.util.Locale;

public class UnderGrad extends Student
{
	private boolean inState;
	private int creditUpperbound;
	private double programFee;
	public UnderGrad(String fName, String lName, String id, int credits, double rate, boolean inState, double programFee) 
	{
		/* Sets the inherited attributes */
		super(fName, lName, id, credits, rate);
		
		/* Sets the extra attributes of the UnderGrad class */
		this.inState = inState;
		this.programFee = programFee;
		
		/* If the undergrad is in State, the max amount of credits the undergrad can be charged for
		 * will be set to 7, if they are out of state, it will be set to 12 instead */
		if (inState) 
		{
			creditUpperbound = 7;
		}
		else 
		{
			creditUpperbound = 12;
		}
	}
	
	/* Computes the tuition that will be owed by the undergrad based on all of the instance variables */
	public void computeTuition() 
	{
		if (numCredit >= creditUpperbound) 
		{
			tuition = rate * creditUpperbound + programFee;
		}
		else
		{
			 tuition = rate * numCredit + programFee;
		}
	}
	
	/* Prints out all attributes of the UnderGRad object in the form of a String */
	public String toString() 
	{
		Locale usDollars = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(usDollars);
		String result = "\nUnderGrad:\n";
		if (inState) 
		{
			result += "In";
		}
		else
		{
			result += "Out-Of";
		}
		result += "-State" + super.toString() + "Student Program Fee:\t" + format.format(programFee) + "\n";
		return result;
	}
}
