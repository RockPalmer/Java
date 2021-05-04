package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/**
 * This class has 2 static methods that are utilized in both the DescDataGUI class and the LnOfBestFitGUI class.
 * This program is completely encapsulated and accepts does all calculations and adjustments based on the parameters it is given.
 */
public class ButtonMovement 
{
	/**
	 * This is a public static method which, when implemented, will add a button to the designated pane and redistribute the 
	 * existing buttons on the pane to space them apart evenly. This method assumes the buttons are to be distributed into a single
	 * horizontal line and that there are only 3 buttons and one button being added.
	 * 
	 * This method is utilized in both the DescDataGUI and LnOfBestFitGUI class to add and remove the "Remove Point" Button
	 * when the user should or should not, respectively, be able to do so.
	 * 
	 * @param columns is an ArrayLixt of VBox objects used to evenly space the elements on the pane parameter.
	 * @param buttons is an array of Button objects that are to be placed on the VBoxes contained in the columns parameter.
	 * @param pane is the pane on which all the elements sit.
	 * @param buttonIndex is the index of the Button object that is to be added onto the pane.
	 * @param handler is the EventHandler object that is to be added to the added Button once it is added to the pane.
	 */
	public static void addButton(ArrayList<VBox> columns, Button[] buttons, GridPane pane, int buttonIndex, EventHandler<ActionEvent> handler) 
	{
		// Clear the pane and VBox ArrayList
		pane.getColumnConstraints().clear();
		columns.removeAll(columns);
		
		// Add the amount of necessary columns to the pane
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(25);
		pane.getColumnConstraints().addAll(column,column,column,column);
		
		// Fill the VBox ArrayList and add the respective Button object to the VBox
		for (int count = 0; count < 4; count++)
		{
			columns.add(new VBox());
			columns.get(count).setAlignment(Pos.CENTER);
			pane.add(columns.get(count), count, 0);
			columns.get(count).getChildren().add(buttons[count]);
		}
		
		// Set the EventHandler to the new Button
		buttons[buttonIndex].setOnAction(handler);
	}
	/**
	 * This is a public static method which, when implemented, will remove a button from the designated pane and redistribute the 
	 * existing buttons on the pane to space them apart evenly. This method assumes the buttons are to be distributed into a single
	 * horizontal line and that there are only 4 buttons and one button being removed.
	 * 
	 * This method is utilized in both the DescDataGUI and LnOfBestFitGUI class to add and remove the "Remove Point" Button
	 * when the user should or should not, respectively, be able to do so.
	 * 
	 * @param columns is an ArrayLixt of VBox objects used to evenly space the elements on the pane parameter.
	 * @param buttons is an array of Button objects that are to be placed on the VBoxes contained in the columns parameter.
	 * @param pane is the pane on which all the elements sit.
	 * @param buttonIndex is the index of the Button object that is to be removed from the pane.
	 */
	public static void removeButton(ArrayList<VBox> columns, Button[] buttons, GridPane pane, int buttonIndex) 
	{
		// Remove the Button from the pane
		columns.get(buttonIndex).getChildren().remove(buttons[buttonIndex]);
		
		// Clear the pane and VBox ArrayList
		pane.getColumnConstraints().clear();
		columns.removeAll(columns);
		
		// Add the amount of necessary columns to the pane
		ColumnConstraints columnSide = new ColumnConstraints();
		ColumnConstraints columnMid = new ColumnConstraints();
		columnSide.setPercentWidth(33);
		columnMid.setPercentWidth(34);
		pane.getColumnConstraints().addAll(columnSide,columnMid,columnSide);
		
		// Fill the VBox ArrayList and add the respective Button object to the VBox
		for (int count = 0; count < 3; count++)
		{
			columns.add(new VBox());
			columns.get(count).setAlignment(Pos.CENTER);
			pane.add(columns.get(count), count, 0);
			
			// Skip the button that was removed when replacing the Button objects onto the pane.
			if (count < buttonIndex)
			{
				columns.get(count).getChildren().add(buttons[count]);
			}
			else 
			{
				columns.get(count).getChildren().add(buttons[count + 1]);
			}
		}
	}
}
