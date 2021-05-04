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
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import calcFunction.QuadFormFn;

/**
 * This class inherits from the MainMenu class and implements its own user interface
 * to interact with the user.
 * 
 * It builds and places on the stage a BorderPane designated as mainPane that contains a button
 * labeled "Calculate", a button labeled "Return to Menu", and 4 labeled TextFields. The first 
 * 3 of which are designated for the user to input numerical values. Once the user presses the 
 * "Calculate" button the program uses the 3 entered values as the standard a, b, and c in the 
 * quadratic formula to calculate the value of x for those values. The program then presents the 
 * user with the answer into the 4th TextField.
 * 
 * Then, when the user wishes to return back the the main page of the program, he/she can 
 * click the "Return to Menu" button and will be returned to the main menu.
 *
 */
public class QuadFormGUI extends MainMenu
{
	// Major user interface elements of the QuadFormGUI class
	private static BorderPane mainPane;
	private static VBox topPane;
	private static GridPane dataPane;
	private static GridPane optionsPane;
	private static TextField[] values;
	private static Label[] labels;
	private static Label paneTitle;
	private static Button calculate;
	private static Button returnToMenu;
	
	/**
	 * Default constructor for the QuadFormGUI class
	 * 
	 * Builds the user interface but does not add it to the "root" pane
	 */
	public QuadFormGUI()
	{
		/* Initialize instance variables */
		mainPane = new BorderPane();
		topPane = new VBox();
		dataPane = new GridPane();
		optionsPane = new GridPane();
		values = new TextField[4];
		labels = new Label[4];
		paneTitle = new Label("Quadratic Formula Calculator");
		calculate = new Button("Calculate");
		returnToMenu = new Button("Return to Menu");
		
		/* Set attributes of buttons */
		calculate.setFont(new Font("comic Sans MS", calculate.getFont().getSize()));
		returnToMenu.setFont(new Font("comic Sans MS", returnToMenu.getFont().getSize()));
		calculate.setTextFill(Color.BLUE);
		returnToMenu.setTextFill(Color.BLUE);
		
		/* Add elements to mainPane */
		mainPane.setTop(topPane);
		mainPane.setCenter(dataPane);
		mainPane.setBottom(optionsPane);
		
		/* Add elements to topPane */
		topPane.setAlignment(Pos.CENTER);
		paneTitle.setFont(new Font("Impact", 30));
		paneTitle.setTextFill(Color.CRIMSON);
		topPane.getChildren().add(paneTitle);
		
		/* Add elements to optionsPane */
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(50);
		optionsPane.getColumnConstraints().addAll(column,column);
		VBox[] columns = new VBox[2];
		for (int count = 0; count < columns.length; count++)
		{
			columns[count] = new VBox();
			columns[count].setAlignment(Pos.CENTER);
			optionsPane.add(columns[count], count, 0);
		}
		columns[0].getChildren().add(returnToMenu);
		columns[1].getChildren().add(calculate);
		
		/* Add elements to dataPane */
		dataPane.setPadding(new Insets(20,70,70,70));
		RowConstraints row = new RowConstraints();
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(50);
		row.setPercentHeight(25);
		dataPane.getColumnConstraints().addAll(col,col);
		dataPane.getRowConstraints().addAll(row,row,row,row);
		for (int count = 0; count < labels.length; count++) 
		{
			labels[count] = new Label();
		}
		for (int count = 0; count < values.length; count++) 
		{
			values[count] = new TextField();
		}
		labels[0].setText("a = ");
		labels[1].setText("b = ");
		labels[2].setText("c = ");
		labels[3].setText("x = ");
		dataPane.add(labels[0], 0, 0);
		dataPane.add(labels[1], 0, 1);
		dataPane.add(labels[2], 0, 2);
		dataPane.add(labels[3], 0, 3);
		dataPane.add(values[0], 1, 0);
		dataPane.add(values[1], 1, 1);
		dataPane.add(values[2], 1, 2);
		dataPane.add(values[3], 1, 3);
		
		/* Add event listeners to buttons */
		returnToMenu.setOnAction(main);
		calculate.setOnAction(new Calculate());
	}
	
	/**
	 * This method is overridden from the MainMenu class. It clears the "root" pane 
	 * of all nodes and places the already-built user interface for this class 
	 * onto it as well as the Label that would contain the error message on the bottom.
	 */
	public void start() 
	{
		/* Clear the pane */
		root.getChildren().clear();
		
		/* Add new elements to pane */
		errLabel = new Label();
		errLabel.setTextFill(Color.RED);
		root.setCenter(mainPane);
		root.setBottom(errLabel);
	}
	/**
	 * This class implements the <CODE>EventHanlder<CODE> interface to listened to an action
	 * event triggered by a button.
	 * 
	 * Accordingly, it implements the <CODE>handle()<CODE> method from the EventHandler interface.
	 */
	private class Calculate implements EventHandler<ActionEvent>
	{
		/**
		 * This method gathers data from the TextFields in the values[] array and uses them to calculate
		 * the values of x of a polynomial with those designated coefficients.
		 * 
		 * It then sets the text in the values[3] TextField to those values.
		 * 
		 * If there is no solution to such a calculation, the text in that TextField is set to "No Solution".
		 * 
		 * @param ActionEvent event triggered by a button
		 * 
		 * @see calcFunction.QuadFormFn.claculate for details on how the calculation occurs
		 */
		public void handle(ActionEvent event) 
		{
			try 
			{
				/* Check if any data fields are empty */
				for (int count = 0; count < values.length - 1; count++) 
				{
					if (values[count].getText().equals("")) 
					{
						throw new Exception();
					}
				}
				
				/* If all required data fields are filled, continue with regular button function */
				Double valA = Double.parseDouble(values[0].getText());
				Double valB = Double.parseDouble(values[1].getText());
				Double valC = Double.parseDouble(values[2].getText());
				double[] answers = QuadFormFn.calculate(valA, valB, valC);
				String answer = answers[0] + " , " + answers[1];
				if (answer.equals("NaN , NaN"))
				{
					answer = "No Solution";
				}
				values[3].setText(answer);
			}
			catch(NumberFormatException e) 
			{
				errLabel.setText("Please fill all boxes with valid entries");
			}
			catch(Exception e) 
			{
				errLabel.setText("Please fill all required data fields");
			}
		}
	}
}
