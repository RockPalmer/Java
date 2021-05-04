//   Assignment: ASU CSE205 Spring 2021 #8
//         Name: Rock Palmer
//    StudentID: 1214784662
//      Lecture: T and Th 1:30-2:45PM
//  Description: The Assignment 8 class displays a menu of choices to a user
//               and performs the chosen task. It will keep asking a user to
//               enter the next choice until the choice of 'Q' (Quit) is
//               entered. 

import java.io.*;
public class Assignment8
{
	public static void main(String[] args)
	{
		char input1;
		String deptName, numOfFacultyStr, nameOfUniversity;
		String firstName, lastName, academicLevel;
		String university;
		int numOfFaculty;
		boolean operation = false;
		int operation2 = 0;
		String line;
		String filename;
		
		// create a DeptManagement object. This is used throughout this class.
		DeptManagement deptManage1 = new DeptManagement();
		File file;
		try 
		{
			// print out the menu
			printMenu();
			
			// create a BufferedReader object to read input from a keyboard
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader stdin = new BufferedReader(isr);
			do 
			{
				System.out.print("\nWhat action would you like to perform?\n");
				line = stdin.readLine().trim(); 
				
				//read a line
				input1 = line.charAt(0);
				input1 = Character.toUpperCase(input1);
				if (line.length() == 1) //check if a user entered only one character
				{
					switch (input1) 
					{
					case 'A':
						
						/*Add Department*/
						
						// Store user input in variables
						System.out.print("Please enter the department information:\n");
						System.out.print("Enter department name:\n");
						deptName = stdin.readLine().trim();
						System.out.print("Enter number of faculty:\n");
						numOfFacultyStr = stdin.readLine().trim();
						numOfFaculty = Integer.parseInt(numOfFacultyStr);
						System.out.print("Enter university name:\n");
						nameOfUniversity = stdin.readLine().trim();
						System.out.print("Enter faculty first name:\n");
						firstName = stdin.readLine().trim();
						System.out.print("Enter faculty last name:\n");
						lastName = stdin.readLine().trim();
						System.out.print("Enter faculty academic level:\n");
						academicLevel = stdin.readLine().trim();
						
						// Use user input to specify the department to add
						operation = deptManage1.addDepartment(deptName, nameOfUniversity, numOfFaculty, firstName, lastName, academicLevel);
						if (operation) 
						{
							System.out.println("Department added");
						}
						else
						{
							System.out.println("Department NOT added");
						}
						break;
					case 'C':
						//Create a new department management
						deptManage1 = new DeptManagement();
						break;
						//*****
					case 'D':
						/*Search by department's name and the university*/
						
						// Store user input in variables
						System.out.print("Please enter the department name to search:\n");
						deptName = stdin.readLine().trim();
						System.out.print("Please enter the university name to search:\n");
						university = stdin.readLine().trim();
						
						// Use user input to check if the specified department exists
						operation2 = deptManage1.deptExists(deptName, university);
						if (operation2 != -1) 
						{
							System.out.println(deptName + " at " + university + " is found");
						}
						else 
						{
							System.out.println(deptName + " at " + university + " is NOT found");
						}
						break;
					case 'E':
						/*Search faculty*/
						
						// Store user input in variables
						System.out.print("Please enter the faculty first name to search:\n");
						firstName = stdin.readLine().trim();
						System.out.print("Please enter the faculty last name to search:\n");
						lastName = stdin.readLine().trim();
						System.out.print("Please enter the faculty academic level to search:\n");
						academicLevel = stdin.readLine().trim();
						
						// Use user input to check if the specified faculty exists
						operation2 = deptManage1.facultyExists(firstName, lastName, academicLevel);
						if (operation2 != -1) 
						{
							System.out.println("Faculty: " + firstName + " " + lastName + ", " + academicLevel + " is found");
						}
						else 
						{
							System.out.println("Faculty: " + firstName + " " + lastName + ", " + academicLevel + " is NOT found");
						}
						break;
					case 'L':
						//List departments
						System.out.print(deptManage1.listDepartments());
						break;
					case 'N':
						deptManage1.sortByDepartmentName();
						System.out.print("sorted by department names\n");
						break;
					case 'O':
						deptManage1.sortByFacultyNumbers();
						System.out.print("sorted by faculty numbers\n");
						break;
					case 'P':
						deptManage1.sortByDeptFaculty();
						System.out.print("sorted by current faculty name\n");
						break;
					case 'Q':
						//Quit
						break;
					case 'R':
						/*Remove a department*/
						
						// Store user input in variables
						System.out.print("Please enter the department name to remove:\n");
						deptName = stdin.readLine().trim();
						System.out.print("Please enter the university name to remove:\n");
						university = stdin.readLine().trim();
						
						// Use user input to remove the specified department
						operation = deptManage1.removeDepartment(deptName, university);
						if (operation == true)
							System.out.print(deptName + " at " + university + " is removed\n");
						else
							System.out.print(deptName + " at " + university + " is NOT removed\n");
						break;
					case 'T':
						//Close DeptManagement
						deptManage1.closeDeptManagement();
						System.out.print("Department management system closed\n");
						break;
					case 'U':
						//Write Text to a File
						System.out.print("Please enter a file name that we will write to:\n");
						filename = stdin.readLine().trim();
						try 
						{
							// Initialize objects to write in file
							file = new File(filename);
							FileWriter fwrite = new FileWriter(file);
							BufferedWriter bwrite = new BufferedWriter(fwrite);
							PrintWriter printer = new PrintWriter(bwrite);
							
							//Print text
							System.out.println("Please enter a string to write inside the file:");
							String word = stdin.readLine();
							System.out.println(filename + " is written");
							printer.println(word);
							
							printer.close();
						}
						catch(IOException e) 
						{
							System.out.print("Error encountered when writing string into file\n");
						}
						break;
					case 'V':
						//Read Text from a File
						System.out.print("Please enter a file name which we will read from:\n");
						filename = stdin.readLine().trim();
						try 
						{
							// Initialize objects to read file
							file = new File(filename);
							FileReader fread = new FileReader(file);
							BufferedReader scan = new BufferedReader(fread);
							
							// Read file
							System.out.println(filename + " was read");
							System.out.println("The first line of the file is:\n" + scan.readLine());
							scan.close();
						}
						catch(FileNotFoundException e) 
						{
							System.out.println(filename + " not found error");
						}
						catch(IOException e) 
						{
							System.out.println("Read string from the file error");
						}
						break;
					case 'W':
						
						//Serialize DeptManagement to a File
						System.out.print("Please enter a file name which we will write to:\n");
						filename = stdin.readLine().trim();
						try
						{
							// Initialize objects to serialize an object
							file = new File(filename);
							FileOutputStream storer = new FileOutputStream(file);
							ObjectOutputStream writer = new ObjectOutputStream(storer);
							
							// Serialize object
							writer.writeObject(deptManage1);
							writer.close();
						}
						catch(NotSerializableException e) 
						{
							System.out.print("Not serializable exception\n");
						}
						catch(IOException e) 
						{
							System.out.print("Data file written exception\n");
						}
						break;
					case 'X':
						
						//Deserialize DeptManagement from a File
						System.out.print("Please enter a file name which we will read from:\n");
						filename = stdin.readLine().trim();
						try 
						{
							// Initialize objects to deserialize an object
							file = new File(filename);
							FileInputStream input = new FileInputStream(file);
							ObjectInputStream reader = new ObjectInputStream(input);
							
							// Deserialize the object
							deptManage1 = (DeptManagement) reader.readObject();
							reader.close();
							System.out.println(filename + " was read");
						}
						catch(ClassNotFoundException e) 
						{
							System.out.print("Class not found exception\n");
						}
						catch(NotSerializableException e) 
						{
							System.out.print("Not serializable exception\n");
						}
						catch(IOException e) 
						{
							System.out.print("Data file read exception\n");
						}
						break;
					case '?':
						//Display Menu
						printMenu();
						break;
					default:
						System.out.print("Unknown action\n");
						break;
					}
				}
				else 
				{
					System.out.print("Unknown action\n");
				}
			} while ( input1 != 'Q' || line . length () != 1);
		}
		catch(IOException exception) 
		{
			System.out.print("IO Exception\n");
		}
	}/** The method printMenu displays the menu to a user **/
	public static void printMenu() 
	{
		System.out.print("Choice\t\tAction\n" 
	+ "------\t\t------\n" 
				+ "A\t\tAdd a department\n" 
	+ "C\t\tCreate a DeptManagement\n" 
				+ "D\t\tSearch a department\n"
	+ "E\t\tSearch a faculty\n" 
				+ "L\t\tList departments\n" 
	+ "N\t\tSort by department names\n" 
				+ "O\t\tSort by department faculty numbers\n" 
	+ "P\t\tSort by current faculty name\n" 
				+ "Q\t\tQuit\n" 
	+ "R\t\tRemove a department\n" 
				+ "T\t\tClose DeptManagement\n" 
	+ "U\t\tWrite strings to a text file\n" 
				+ "V\t\tRead strings from a text file\n" 
	+ "W\t\tSerialize DeptManagement to a data file\n"
				+ "X\t\tDeserialize DeptManagement from a data file\n" 
	+ "?\t\tDisplay Help\n");
	}
}