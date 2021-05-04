package application;

// Assignment #: 6
// Name: Rock Palmer
// Student ID: 1214784662
// Arizona State University - CSE205
// Description: SelectPane displays a list of available department
//  from which a user can select and compute total number of faculties in multiple departments.

import java.util.ArrayList;
import javafx.event.ActionEvent; 

//**Need to import
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
//**Need to import

// import all other necessary JavaFX classes here

/** SelectPane displays a list of available departments from which a user
 * can select and compute total number of faculties for selected departments.
 */

public class SelectPane extends BorderPane 
{    
	//declare instance variables    
	private ArrayList<Department> departList;
	private ArrayList<CheckBox> checkBoxes;
	private GridPane checkboxContainer;
	private ColumnConstraints column;
	private RowConstraints row;
	private Label selectedFaculty;
	private Label deptSelect;
	private ScrollPane scrollPane;
	private int numSelectedFaculties;
	public SelectPane(ArrayList<Department> list) 
	{        
		/* ------------------------------ */
		/* Instantiate instance variables */
		/* ------------------------------ */
		
		this.departList = list;  
		selectedFaculty = new Label("The total number of faculty for the selected department(s): 0");  
		checkboxContainer = new GridPane();
		column = new ColumnConstraints();
		row = new RowConstraints();
		deptSelect = new Label("Select Department(s)");
		scrollPane = new ScrollPane();  
		checkBoxes = new ArrayList<CheckBox>();     
		   
		// Setup layout 
		// create an empty pane where you can add check boxes later        
		// SelectPane is a BorderPane - add the components here  
		this.setTop(deptSelect);
		this.setCenter(scrollPane);
		this.setBottom(selectedFaculty);

		// Wrap checkboxContainer in ScrollPane so formatting is        
		// correct when many departments are added  
		scrollPane.setContent(checkboxContainer);
		checkboxContainer.getColumnConstraints().addAll(column,column);
		
	} // end of SelectPane constructor    
	
	// This method uses the newly added parameter Department object    
	// to create a CheckBox and add it to a pane created in the constructor    
	// Such check box needs to be linked to its handler class    
	
	public void updateDepartList(Department newdep) 
	{     
		// Create checkbox for new department with appropriate text
		checkBoxes.add(new CheckBox());
		Label label = new Label("" + newdep); 
		checkboxContainer.add(label, 1, departList.size()); 
		
		// Bind checkbox toggle action to event handler        
		// Passes the number of faculties in each department to the handler. When the checkbox is
		// toggled,this number will be added/subtracted from the total number of selected faculties        
		checkBoxes.get(checkBoxes.size() - 1).setOnAction(new SelectionHandler(newdep.getNumberOfMembers()));
		
		// Add new checkbox to checkbox container
		checkboxContainer.getRowConstraints().add(row);
		checkboxContainer.add(checkBoxes.get(checkBoxes.size() - 1), 0, departList.size());
		this.departList.add(newdep);
	} // end of updateDepartList method    
	
	/**     
	 * SelectionHandler This class handles a checkbox toggle event. When the     
	 * checkbox is toggled, this number will be added/subtracted from the total     
	 * number of selected faculties.     
	 */    
	
	private class SelectionHandler implements EventHandler<ActionEvent> 
	{        
		// Instance variable for number of faculties associated with a given department/checkbox        
		private int faculties;
		public SelectionHandler(int members)
		{            
			// Set instance variable  
			this.faculties = members; 
		} // end of SelectionHandler constructor        
		
		//over-ride the abstract method handle        
		public void handle(ActionEvent event)
		{            
			// Get the object that triggered the event, and cast it to a checkbox, since            
			// only a department checkbox            
			// can trigger the SelectionHandler event. The cast is necessary to have access           
			// to the isSelected() method            
			if (((CheckBox)event.getSource()).isSelected())
			{
				numSelectedFaculties += faculties;
			}
			else 
			{
				numSelectedFaculties -= faculties;
			}

			// Update the selected Faculties label with the new number of selected faculties
			selectedFaculty.setText("The total number of faculty for the selected department(s): " + numSelectedFaculties);
		} // end handle method    
	} // end of SelectHandler class} // end of SelectPane class
}
