//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: Implements the Comparator interface to compare 2 Department
//				 objects based off of the alphabetical order of the names of their 
//				 respective faculty members.

import java.util.Comparator;
public class DeptFacultyComparator implements Comparator<Department>
{
	// Compares the names of the faculty members within the 2 department objects
	public int compare(Department first, Department second)
	{
		int comparison = 0;
		
		// Compare the last names of the faculty members first
		int compLName = first.getFaculty().getLastName().compareTo(second.getFaculty().getLastName());
		
		// If they have the same last name, their first names are compared.
		if (compLName == 0) 
		{
			comparison = first.getFaculty().getFirstName().compareTo(second.getFaculty().getFirstName());
		}
		else 
		{
			comparison = compLName;
		}
		return comparison;
	}
}