package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import calcFunction.GenCalcFn;

/**
 * This class inherits from the MainMenu class and implements its own user interface
 * to interact with the user.
 * 
 * It builds and places on the stage a BorderPane designated as mainPane that contains all
 * the buttons one might find on a simple calculator with a number pad and several operations.
 * All of these buttons are placed in the center of the pane on their own separate pane, 
 * buttonPane along with a TextField where the answer will be printed out to the user called
 * "answerBox".
 * 
 * Then, when the user wishes to return back the the main page of the program, he/she can 
 * click the "Return to Menu" button and will be returned to the main menu.
 *
 */
public class GenCalcGUI extends MainMenu
{
	/* These are all the labels designated for each button and are laid out in the way they
	 * appear on the pane */
	private static final String[][] BUTTON_LABELS = 
		{
			{"1","2","3","+","\u00AD"},
			{"4","5","6","^","\u221A"},
			{"7","8","9","\u02E3","\u00F7"},
			{"CLEAR","0","\u2190","(",")"},
			{"E","( - )",".","\u03C0","="}
		};
	
	// All major pieces of the user interface.
	private static Button[][] calcButtons;
	private static Button returnToMenu;
	private static GridPane buttonPane;
	private static GridPane optionsPane;
	private static VBox userPane;
	private static TextField answerBox;
	private static BorderPane mainPane;
	
	/**
	 * Default constructor for the QuadFormGUI class
	 * 
	 * Builds the user interface but does not add it to the stage
	 */
	public GenCalcGUI()
	{	
		/* Initialize instance Variables */
		mainPane = new BorderPane();
		userPane = new VBox();
		buttonPane = new GridPane();
		optionsPane = new GridPane();
		answerBox = new TextField();
		calcButtons = new Button[5][5];
		returnToMenu = new Button("Return To Menu");
		returnToMenu.setFont(new Font("Comic Sans MS", returnToMenu.getFont().getSize()));
		returnToMenu.setTextFill(Color.BLUE);
		for (int count1 = 0; count1 < calcButtons.length; count1++)
		{
			for (int count2 = 0; count2 < calcButtons[count1].length; count2++)
			{
				calcButtons[count1][count2] = new Button();
			}
		}
		
		/* Add elements to mainPane */
		mainPane.setCenter(userPane);
		mainPane.setBottom(optionsPane);
		
		/* Add elements to userPane */
		userPane.setAlignment(Pos.CENTER);
		userPane.getChildren().add(answerBox);
		userPane.getChildren().add(buttonPane);
		ColumnConstraints column = new ColumnConstraints();
		RowConstraints row = new RowConstraints();
		column.setHgrow(Priority.ALWAYS);
		row.setVgrow(Priority.ALWAYS);
		buttonPane.getColumnConstraints().addAll(column,column,column,column,column);
		buttonPane.getRowConstraints().addAll(row,row,row,row,row,row);
		userPane.setPadding(new Insets(30,60,30,60));
		VBox.setVgrow(buttonPane, Priority.ALWAYS);
		VBox.setVgrow(answerBox, Priority.ALWAYS);
		
		for (int count1 = 0; count1 < calcButtons.length; count1++)
		{
			for (int count2 = 0; count2 < calcButtons[count1].length; count2++)
			{
				calcButtons[count1][count2].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				buttonPane.add(calcButtons[count1][count2],count1,count2 + 1);
			}
		}
		
		/* Add elements to optionsPane */
		ColumnConstraints col = new ColumnConstraints();
		optionsPane.getColumnConstraints().addAll(col, col);
		col.setPercentWidth(50);
		VBox[] columns = new VBox[2];
		for (int count = 0; count < columns.length; count++) 
		{
			columns[count] = new VBox();
			optionsPane.add(columns[count], count, 0);
		}
		columns[0].getChildren().add(returnToMenu);
		
		/* Set the text for each button */
		for (int count1 = 0; count1 < calcButtons.length; count1++)
		{
			for (int count2 = 0; count2 < calcButtons[count1].length; count2++)
			{
				calcButtons[count1][count2].setText(BUTTON_LABELS[count2][count1]);
			}
		}
		
		/* Add EventHandlers to buttons */
		for (int count1 = 0; count1 < calcButtons.length; count1++) 
		{
			for (int count2 = 0; count2 < calcButtons[count1].length; count2++) 
			{
				calcButtons[count2][count1].setOnAction(new ButtonAction());
			}
		}
		
		returnToMenu.setOnAction(main);
	}
	/**
	 * This method is overridden from the MainMenu class. It clears the "root" pane 
	 * of all nodes and places the already-built user interface for this class 
	 * onto it as well as the Label that would contain the error message on the bottom.
	 */
	public void start()
	{
		/* Clear the panel */
		root.getChildren().clear();
		
		/* Add pane to GUI */
		errLabel = new Label();
		errLabel.setTextFill(Color.RED);
		root.setCenter(mainPane);
		root.setBottom(errLabel);
	}
	/**
	 * This is a private class that assigns all buttons their functions and it implements
	 * the EventHandler<ActionEvent> interface to accomplish this.
	 *
	 */
	private class ButtonAction implements EventHandler<ActionEvent>
	{
		/**
		 * This method handles all button-click events that happen in the keypad area
		 * of the user interface.
		 * 
		 * @param ActionEvent event triggered by any of the buttons in the calculator keypad.
		 */
		public void handle(ActionEvent event) 
		{
			// If it is the button labeled "CLEAR", clear answerBox
			if (event.getSource().equals(calcButtons[0][3])) 
			{
				answerBox.setText("");
			}
			// If it is the button labeled with the '\u2190' character, it acts as a backspace
			// by removing the last character in the expression from the answerBox
			else if (event.getSource().equals(calcButtons[2][3]))
				answerBox.setText(answerBox.getText().substring(0,answerBox.getText().length() - 1));
			// If it is the button labeled with the "( - )" symbol, it places a dash at the end of
			// whatever String is already in the answerBox
			else if (event.getSource().equals(calcButtons[1][4])) 
			{
				String boxText = answerBox.getText();
				answerBox.setText(boxText + "-");
			}
			// If it is the button labeled with the "=" character, clears the answerBox and then
			// displays the answer to the expression entered by the user into the answerBox
			else if (event.getSource().equals(calcButtons[4][4])) 
			{
				if (!answerBox.getText().equals("")) 
				{
					try 
					{
						String expression = answerBox.getText();
						answerBox.setText("");
						answerBox.setText(GenCalcFn.interact(expression));
					}
					catch(NumberFormatException e) 
					{
						errLabel.setText("Invalid number found. Please use the provided operations and numbers.");
					}
					catch(IndexOutOfBoundsException e) 
					{
						errLabel.setText("The provided operations cannot be used in this way.");
					}
				}
			}
			// If it is not any of the buttons mentioned above, the program prints whatever text is
			// the Button is labeled with.
			else 
			{
				String buttonText = ((Button)event.getSource()).getText();
				String boxText = answerBox.getText();
				answerBox.setText(boxText + buttonText);
			}
				
		}
	}
}
