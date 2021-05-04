//  Assignment 6: ASU - CSE 205
//  Name: Rock Palmer
//  StudentID: 1214784662
//  Lecture Date and Time: T Th	1:30 PM - 2:45 PM
//  Description: Describes a Department object that is used by the Assignment6,
//			GeneratePane, and SelectPane classes.

package application;

public class Department
{     
	private String name;     
	private int numberOfFaculty;     
	private String university;     
	public Department()     
	{           
		name = "?";           
		numberOfFaculty = 0;           
		university = "?";     
	}     
	
	//accesssor method     
	public String getDeptName()     
	{           
		return name;     
	}     
	public int getNumberOfMembers()     
	{           
		return numberOfFaculty;     
	}     
	public String getUniversity()     
	{           
		return university;     
	}     
	
	//mutator methods     
	public void setDeptName(String name)     
	{           
		this.name = name;     
	}     
	public void setNumberOfMembers(int numFaculty)     
	{           
		this.numberOfFaculty = numFaculty;     
	}     
	public void setUniversity(String name)     
	{           
		this.university = name;     
	} 
	
	//toString over-rided method
	public String toString()     
	{           
		return "\nDepartment Name:\t\t" + name + "\nNumber Of Faculty:\t" + numberOfFaculty +                     
				"\nUniversity:\t\t" + university + "\n\n";     
	}
}