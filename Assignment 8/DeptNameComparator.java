//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: Implements the Comparator interface and compares
//				 2 department objects based off of the alphabetical
//				 order of the names of each department

import java.util.Comparator;

public class DeptNameComparator implements Comparator<Department>
{
	// Compares 2 department objects based on the alphabetical order of their names
	public int compare(Department first, Department second) 
	{
		int comparison = first.getDeptName().compareTo(second.getDeptName());
		return comparison;
	}

}
