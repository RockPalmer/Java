// Assignment #: 5
// Arizona State University - CSE205
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T Th	1:30 PM - 2:45 PM
//  Description: Describes a Graduate object with instance variables that
//				 inherits all the instance variables from the Student class
//				 as well as one other variable that stores the fee required
//				 for the grad student to pay called 'gradFee'. It also inherits
//				 the computeTuition method from Student but implements it with
//				 the 'gradFee' variable.

import java.text.NumberFormat;
import java.util.Locale;

public class Graduate extends Student
{
	/* Stores the fee required for the given grad program */
	private double gradFee;
	public Graduate(String fName, String lName, String id, int credits, double rate, double gradFee) 
	{
		/* Sets the inherited attributes from the Student class as well as the extra gradFee var */
		super(fName, lName, id, credits, rate);
		this.gradFee = gradFee;
	}
	/* Computes the tuition owed by the graduate student */
	public void computeTuition() 
	{
		tuition = rate * numCredit + gradFee;
	}
	/* Returns all the attributes of the Graduate student in the form of  a String */
	public String toString() 
	{
		Locale usDollars = new Locale("en", "US");
		NumberFormat format = NumberFormat.getCurrencyInstance(usDollars);
		return "\nGraduate Student:" + super.toString() + "Grad Fee:\t\t" + format.format(gradFee) + "\n";
	}
}
