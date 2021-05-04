//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: This class has only one static method and is used to sort an
//				 array list of Department objects based on a specified method of 
//				 comparison given as a parameter of the sort() method.

import java.util.Comparator;
import java.util.ArrayList;
public class Sorts
{
	/* Given a sorting method represented by the Comparator<Department> parameter,
	 * this program will sort the designated arrayList according to that sorting method 
	 * 
	 * This program utilizes a bubble sorting algorithm to accomplish this */
	public static void sort(ArrayList<Department> deptList, Comparator<Department> xComparator) 
	{
		for (int count1 = 0; count1 < deptList.size(); count1++)
		{
			for (int count2 = count1 + 1; count2 < deptList.size(); count2++)
			{
				if (xComparator.compare(deptList.get(count1), deptList.get(count2)) > 0) 
				{
					Department tempDept = deptList.get(count1);
					deptList.set(count1, deptList.get(count2));
					deptList.set(count2, tempDept);
				}
			}
		}
	} //end sort
} //end class Sorts