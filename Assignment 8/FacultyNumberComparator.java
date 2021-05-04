//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: Implements the Comparator interface to compare the number of
//				 faculty members in 2 departments.

import java.util.Comparator;

public class FacultyNumberComparator implements Comparator<Department>
{
	//Compares 2 department objects based off of the number of faculties contained in each one
	public int compare(Department first, Department second) 
	{
		return first.getNumOfMembers() - second.getNumOfMembers();
	}
}
