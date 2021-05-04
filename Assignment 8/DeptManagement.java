//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: DeptManagement carries out several different methods relating to
//				 the variable 'deptList' which is an instance variable of this class
//				 and is an array list of Departments. Within these methods, the class
//				 will search, sort, and list the departments it is managing based off
//				 of various different criteria.

import java.io.Serializable;
import java.util.ArrayList;

public class DeptManagement implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Department> deptList;
	public DeptManagement() 
	{
		deptList = new ArrayList<Department>();
	}
	/* This code searches the deptLisrt array list and takes each department object 
	 * compares its name and the university name to the parameter values given in the 
	 * method header. If it finds a match, the method returns the index of the department
	 * it found */
	public int deptExists(String deptName, String universityName) 
	{
		int deptIndex = -1;
		for (int count = 0; count < deptList.size() && deptIndex == -1; count++) 
		{
			if (deptList.get(count).getDeptName().equals(deptName) && deptList.get(count).getUniversity().equals(universityName)) 
			{
				deptIndex = count;
			}
		}
		return deptIndex;
	}
	/* This code searches the deptList array list and takes each department object 
	 * gets the faculty member contained in it and compares their first name, last
	 * name, and academic level to the corresponding parameter values given in the
	 * method header, if it finds a match, it will return the index of the matching
	 * department object in deptList */
	public int facultyExists(String firstName, String lastName, String academicLevel) 
	{
		int facIndex = -1;
		for (int count = 0; count < deptList.size() && facIndex == -1; count++) 
		{
			if (deptList.get(count).getFaculty().getFirstName().equals(firstName) && deptList.get(count).getFaculty().getLastName().equals(lastName) && deptList.get(count).getFaculty().getAcademicLevel().equals(academicLevel)) 
			{
				facIndex = count;
			}
		}
		return facIndex;
	}
	/* This code first goes through the array list, deptList, and checks if any of
	 * the departments contained in it match the department object trying to be added
	 * 
	 * 2 department objects are considered as matching if they have the same if their
	 * department names and their university names are the same.
	 * 
	 *  If they are, the method returns false. If the method finds no matches, then it 
	 *  adds a new Department object to deptList based on the parameters provided by
	 *  the method header and returns true */
	public boolean addDepartment(String deptName, String university, int numOfMembers, String firstName, String lastName, String academicLevel) 
	{
		boolean deptAdded = true;
		for (int count = 0; count < deptList.size() && deptAdded; count++)
		{
			if (deptList.get(count).getDeptName().equals(deptName) && deptList.get(count).getUniversity().equals(university)) 
			{
				deptAdded = false;
			}
		}
		if (deptAdded) 
		{
			Department newDep = new Department(deptName,university,numOfMembers,firstName,lastName,academicLevel);
			deptList.add(newDep);
		}
		return deptAdded;
	}
	
	//***will remove all departments with the same name and university
	public boolean removeDepartment(String deptName, String universityName) 
	{
		boolean deptRemoved = false;
		if (deptExists(deptName,universityName) != -1) 
		{
			deptList.remove(deptExists(deptName,universityName));
			deptRemoved = true;
		}
		return deptRemoved;
	}
	
	/* The following 3 methods sort the department list by department name,
	 * number of faculty, and faculty name */
	public void sortByDepartmentName() 
	{
		DeptNameComparator deptNameComp = new DeptNameComparator();
		Sorts.sort(deptList, deptNameComp);
	}
	public void sortByFacultyNumbers() 
	{
		FacultyNumberComparator facNumComp = new FacultyNumberComparator();
		Sorts.sort(deptList, facNumComp);
	}
	public void sortByDeptFaculty() 
	{
		DeptFacultyComparator deptFacComp = new DeptFacultyComparator();
		Sorts.sort(deptList, deptFacComp);
	}
	
	/* This code will list the departments in the order they appear in
	 * deptList, regardless if they have been sorted yet or not.
	 * 
	 *  If their is currently no departments in deptList, the method will
	 *  return the string "No Department" */
	public String listDepartments() 
	{
		String list = "\nNo Department\n";
		String outString = "\n";
		if (deptList.size() != 0) 
		{
			for (int count = 0; count < deptList.size(); count++) 
			{
				outString += deptList.get(count);
			}
		}
		else 
		{
			outString = list;
		}
		return outString + "\n";
	}
	//Clears the list of departments
	public void closeDeptManagement() 
	{
		deptList.removeAll(deptList);
	}
}