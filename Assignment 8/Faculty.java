import java.io.Serializable;

//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: Describes a faculty object that is used in 
//				 other classes in the project. Specifically,
//				 it is used as an instance variable in the 
//				 Department class. It also contains getters and 
//				 setters for all of its instance variables.

public class Faculty implements Serializable
{
	//private instance variables
	private String firstName, lastName, academicLevel;
	//constructor
	public Faculty(String firstName, String lastName, String academicLevel)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.academicLevel = academicLevel;
	}
	
	//getters
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
	
	//sets firstName to the specified string
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	//sets lastName to the specified string
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	//sets academicLevel to the specified string
	public void setAcademicLevel(String level) 
	{
		academicLevel = level;
	}
	
	//return a string in the specified format
	public String toString() 
	{
		return firstName + " " + lastName + ", " + academicLevel;
	}
}