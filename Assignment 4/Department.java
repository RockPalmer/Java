/* Assignment #: 4 
 * Name: Rock Palmer
 * StudentID: 1214784662
 * Lecture: T Th	1:30 PM - 2:45 PM
 * Description: Assignment 4 class displays a menu of choices to a user  
 * and performs the chosen task. It will keep asking a user to enter the 
 * next choice until the choice of 'Q' (Quit) is entered. */

public class Department 
{
	private String Name;
	private int numberOfMembers;
	private String university;
	private Faculty currentFaculty;
	public Department() 
	{
		Name = "?";
		numberOfMembers = 0;
		university = "?";
		currentFaculty = new Faculty();
	}
	public String getDeptName() 
	{
		return Name;
	}
	public int getNumberOfMembers() 
	{
		return numberOfMembers;
	}
	public String getUniversity() 
	{
		return university;
	}
	public Faculty getCurrentFaculty() 
	{
		return currentFaculty;
	}
	public void setDeptName(String Name) 
	{
		this.Name = Name;
	}
	public void setNumberOfMembers(int numberOfMembers) 
	{
		this.numberOfMembers = numberOfMembers;
	}
	public void setUniversity(String university) 
	{
		this.university = university;
	}
	public void setCurrentFaculty(String firstName, String lastName, String academicLevel) 
	{
		currentFaculty.setFirstName(firstName);
		currentFaculty.setLastName(lastName);
		currentFaculty.setAcademicLevel(academicLevel);
	}
	public String toString() 
	{
		return "\nDepartment Name:\t\t" + Name + "\nNumber Of Members:\t" + 
				numberOfMembers + "\nUniversity:\t\t" + university + "\nFaculty:\t\t" + currentFaculty + "\n\n"; 
	}
}
