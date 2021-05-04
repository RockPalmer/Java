package application;

// Assignment 6: ASU - CSE 205
// Name: Rock Palmer
// StudentID: 1214784662
// Lecture Date and Time: T Th	1:30 PM - 2:45 PM
// Description: GeneratePane creates a pane where a user can enter
//  department information and create a list of available departments.

/* --------------- */
/* Import Packages */
/* --------------- */

import java.util.ArrayList;
import javafx.event.ActionEvent; 

//**Need to import
import javafx.event.EventHandler;

//**Need to import
// JavaFX classes
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
/** 
 * GeneratePane builds a pane where a user can enter a department 
 * information and create a list of available departments.
 */

public class GeneratePane extends HBox 
{    
	
	/* ------------------ */    
	/* Instance variables */    
	/* ------------------ */    
	ArrayList<Department> departList;  
	private SelectPane selectPane;
	private TextField titleEntry;
	private TextField numEntry;
	private TextField nameEntry;
	private Label errLabel;
	private TextArea departments;
	// The relationship between GeneratePane and SelectPane is Aggregation    
	//declare and init    
	/**     
	 * CreatePane constructor     
	 *     
	 * @param list   the list of departments     
	 * @param sePane the SelectPane instance     
	 */   
	public GeneratePane(ArrayList<Department> list, SelectPane sePane) 
	{        
		
		/* ------------------------------ */
		/* Instantiate instance variables */
		/* ------------------------------ */
		selectPane = sePane;
		departList = list;
		
		// Initialize each instance variable (textfields, labels, textarea, button, etc.)
		GridPane gridPane = new GridPane();
		ColumnConstraints halfWidth = new ColumnConstraints();
		RowConstraints row = new RowConstraints();
		GridPane leftPane = new GridPane();
		GridPane entryPane = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		Label deptTitle = new Label();
		Label numFaculty = new Label();
		Label univName = new Label();
		GridPane buttonPane = new GridPane();
		Button addDept = new Button("Add a Department");
		
		// Set up the layout/create a GridPane to hold labels & text fields.
		// you can choose to use .setPadding() or setHgap(), setVgap()
		// to control the spacing and gap, etc.
		// Set both left and right columns to 50% width (half of window)
		gridPane.getColumnConstraints().addAll(halfWidth, halfWidth);
		halfWidth.setPercentWidth(50);
		gridPane.setPadding(new Insets(10,10,10,10));
		this.getChildren().add(gridPane);

		/* Set layout of nodes on gridPane */
		departments = new TextArea();
		departments.setPrefHeight(350);
		gridPane.add(departments, 1, 0);
		
		if (departList.size() == 0) 
		{
			departments.setText("No department");
		}
		else 
		{
			for (int count = 0; count < departList.size(); count++) 
			{
				departments.setText(departments.getText() + departList.get(count));
			}
		}
		
		/* Set layout of nodes on the left side of gridPane which all rest on the GridPane object 'leftPane' */
		gridPane.add(leftPane, 0, 0);
		leftPane.getRowConstraints().addAll(row,row,row);
		
		/* Set attributes of error message */
		errLabel = new Label();
		errLabel.setTextFill(Color.RED);
		leftPane.add(errLabel, 0, 0);
		
		/* Here, we are setting layout of the user input area, all of whose nodes sit on the GridPane object 'entryPane' */
		leftPane.add(entryPane, 0, 1);
		col1.setPrefWidth(110);
		col2.setPercentWidth(50);
		entryPane.getColumnConstraints().addAll(col1,col2);
		entryPane.getRowConstraints().addAll(row,row,row,row);
		entryPane.setVgap(5);
		entryPane.setPadding(new Insets(0,20,20,80));
		
		/* Add the 3 labels to the 'entryPane' object */
		deptTitle.setText("Department Title");
		entryPane.add(deptTitle, 0, 0);
		numFaculty.setText("Number of Faculty");
		entryPane.add(numFaculty, 0, 1);
		univName.setText("Name of University");
		entryPane.add(univName, 0, 2);
		
		/* Add the 3 user-entry boxes to the 'entryPane' object */
		titleEntry = new TextField();
		entryPane.add(titleEntry, 1, 0);
		numEntry = new TextField();
		entryPane.add(numEntry, 1, 1);
		nameEntry = new TextField();
		entryPane.add(nameEntry, 1, 2);
		
		/* Add the GridPane called 'buttonPane' that will hold the button  */
		leftPane.add(buttonPane, 0, 2);
		buttonPane.setPadding(new Insets(-20,0,0,160));
		
		/* Add the 'addDept' button to the 'buttonPane' object and bind 'addDept' to the 'ButtonHandler' class */
		addDept.setOnAction(new ButtonHandler());
		buttonPane.add(addDept, 0, 0);
		
		// Bind button click action to event handler    
	} // end of constructor    
	/**     
	 * ButtonHandler ButtonHandler listens to see if the button "Add a department" is pushed     
	 * or not, When the event occurs, it get a department's Title, number of faculties,     
	 * and its university information from the relevant text fields, then create a     
	 * new department and adds it to the departList. Meanwhile it will display the department's     
	 * information inside the text area. using the toString method of the Department     
	 * class. It also does error checking in case any of the text fields are empty,     
	 * or a non-numeric value was entered for number of faculty     
	 */    
	private class ButtonHandler implements EventHandler<ActionEvent> 
	{        
		/**         
		 * handle Override the abstract method handle()         
		 */        
		public void handle(ActionEvent event)
		{            
			// Declare local variables            
			Department newDepart;
			String deptTitle;          
			int faculties = 0;
			String nameUniv;
			boolean isEmptyFields;
			boolean alreadyExists = false;
			
			// If any field is empty, set isEmptyFields flag to true           
			// Display error message if there are empty fields            
			// If all fields are filled, try to add the department 
			if (titleEntry.getText().equals("") || numEntry.getText().equals("") || nameEntry.getText().equals("")) 
			{
				isEmptyFields = true;
			}
			else 
			{
				isEmptyFields = false;
			}
			
			if(isEmptyFields)
			{
				errLabel.setText("Please fill all fields");
			}
			else 
			{
				try 
				{                    
					/*                     
					 * Cast faculties Field to an integer, throws NumberFormatException if unsuccessful
					 */
					newDepart = new Department();
					deptTitle = titleEntry.getText();
					faculties = Integer.parseInt(numEntry.getText());
					nameUniv = nameEntry.getText();
					
					// Data is valid, so create new Department object and populate data                    
					// Loop through existing departments to check for duplicates                    
					// do not add it to the list if it exists and dispay a message                    
					// department is not a duplicate, so display it and add it to list
					
					/* Check for duplicate Departments */
					for (int count = 0; count < departList.size() && !alreadyExists; count++) 
					{
						if (departList.get(count).getDeptName().equals(deptTitle) && departList.get(count).getNumberOfMembers() == faculties && departList.get(count).getUniversity().equals(nameUniv))
						{
							alreadyExists = true;
							throw new Exception();
						}
					}
					
					/* If there are no duplicate Departments, add the user's Department to the list */
					if (!alreadyExists) 
					{
						newDepart.setDeptName(deptTitle);
						newDepart.setNumberOfMembers(faculties);
						newDepart.setUniversity(nameUniv);
						
						departList.add(newDepart);
						
						/* Clear the user-entry boxes */
						titleEntry.setText("");
						numEntry.setText("");
						nameEntry.setText("");
						
						/* Reset the text in the text in the TextArea named 'departments' */
						if (departments.getText().equals("No department")) 
						{
							departments.setText("");
						}
						departments.setText(departments.getText() + newDepart);
						
						/* Use the 'errLabel' Label to tell the user that their Department was added */
						errLabel.setText("Department added");
						
						/* Update the info on the other tab of the GUI */
						selectPane.updateDepartList(newDepart);
					}
				} //end of try                
				catch (NumberFormatException e) 
				{                    
					// If the number of faculties entered was not an integer, display an error
					errLabel.setText("Please enter an integer for the number of faculty");
				}                 
				catch (Exception e)
				{                    
					// Catches generic exception and displays message                    
					// Used to display 'Department is not added - already exist' message if department already exist  
					if (alreadyExists)
					{
						errLabel.setText("Department not added - already exist");
					}
				} 
			}
		} // end of handle() method    
	} // end of ButtonHandler class
} // end of GeneratePane class
