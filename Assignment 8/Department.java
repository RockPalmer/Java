import java.io.Serializable;

//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: Describes a department object that is used
//				 in other classes with getters and setters
//				 for each instance variable

public class Department implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private instance variables
	private String deptName, university;
	private int numOfMembers;
	private Faculty currentFaculty;
	
	//constructor
	public Department(String deptName, String university, int numOfMembers,String firstName, String lastName, String academicLevel)
	{
		this.deptName = deptName;
		this.university = university;
		this.numOfMembers = numOfMembers;
		currentFaculty = new Faculty(firstName, lastName, academicLevel);
	}
	public String getDeptName() 
	{
		return deptName;
	}
	
	//getters
	public String getUniversity() 
	{
		return university;
	}
	public int getNumOfMembers() 
	{
		return numOfMembers;
	}
	public Faculty getFaculty() 
	{
		return currentFaculty;
	}
	
	//setters
	public void setDeptName(String a) 
	{
		deptName = a;
	}
	public void setNumOfMembers(int a) 
	{
		numOfMembers = a;
	}
	public void setUniversity(String a) 
	{
		university = a;
	}
	public void setFaculty(String firstName, String lastName, String academicLevel) 
	{
		currentFaculty.setFirstName(firstName);
		currentFaculty.setLastName(lastName);
		currentFaculty.setAcademicLevel(academicLevel);
	}
	
	//return a string in a specified format
	public String toString() 
	{
		return "\nDept. Name:\t\t" + deptName + "\n"+ "University:\t\t" + university + "\n"+ "# of Members:\t" + numOfMembers + "\n"+ "Faculty:\t\t" + currentFaculty.toString() + "\n";
	}
}