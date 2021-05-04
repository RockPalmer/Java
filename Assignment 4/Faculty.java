/* Assignment #: 4 
 * Name: Rock Palmer
 * StudentID: 1214784662
 * Lecture: T Th	1:30 PM - 2:45 PM
 * Description: Assignment 4 class displays a menu of choices to a user  
 * and performs the chosen task. It will keep asking a user to enter the 
 * next choice until the choice of 'Q' (Quit) is entered. */

public class Faculty 
{
	private String firstName;
	private String lastName;
	private String academicLevel;
	public Faculty() 
	{
		firstName = "?";
		lastName = "?";
		academicLevel = "?";
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public String getAcademicLevel() 
	{
		return academicLevel;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public void setAcademicLevel(String academicLevel) 
	{
		this.academicLevel = academicLevel;
	}
	public String toString() 
	{
		return lastName + "," + firstName + "(" + academicLevel + ")";
	}
}
