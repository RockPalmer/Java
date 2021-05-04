// Assignment #: 5
// Arizona State University - CSE205
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T Th	1:30 PM - 2:45 PM
//  Description: Describes a Student object with instance variables that
//				 store a student's first and last name, their ASU ID, 
//				 the number of credits they are taking, their tuition
//				 rate, and their total tuition amount due. The class 
//				 contains getters and setters for all these variables
//				 as well as a method to compute the total tuition due.

import java.text.NumberFormat;
import java.util.Locale;

public abstract class Student 
{
	protected String firstName;
	protected String lastName;
	protected String studentID;
	protected int numCredit;
	protected double rate;
	protected double tuition;
	/* Initializes all instance variables to the parameters implemented by the method
	 * except for tuition that is automatically set to 0 */
	public Student(String fName, String lName, String id, int credits, double rate) 
	{
		firstName = fName;
		lastName = lName;
		studentID = id;
		numCredit = credits;
		this.rate = rate;
		tuition = 0.0;
	}
	/* Returns the number of credits the Student will be charged for */
	public int getNumCredit() 
	{
		return numCredit;
	}
	/* Abstract method that will compute the tuition of a student. This is
	 * implemented in the Graduate and UnderGrad classes */
	public abstract void computeTuition();
	/* Returns the information stored in the Student object in the form of a String */
	public String toString() 
	{
		Locale usDollars = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(usDollars);
		return "\nFirst name:\t\t" + firstName + "\nLast name:\t\t" + lastName + "\nStudent ID:\t\t" + studentID + "\nCredits:\t\t" + numCredit + "\nRate:\t\t\t" + format.format(rate) + "\nTuition:\t\t" + format.format(tuition) + "\n";
	}
}
