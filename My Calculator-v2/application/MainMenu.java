package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * This class acts as a parent class to all user interface classes of the program
 * and builds its own user interface to be used in the program.
 * 
 * The MainMenu user interface builds a BorderPane containing a title for the pane and 5 buttons
 * each with a designated function.
 * 
 * Describing Data - This brings the user to another pane where he/she can enter a data
 * set of real numbers and, using the program, can calculate key values in that data set 
 * (i.e. mean, median, mode).
 * 
 * Find the Line of best fit - This brings the user to another pane where he/she can enter a
 * set of (x,y) coordinates and the program will find the linear regression equation for that
 * set of coordinate points.
 * 
 * Quadratic Formula - This brings the user to another pane where he/she can enter 3 values which
 * represent the coefficients in a polynomial. The program will then calculate the values of x for
 * that polynomial and display them the the user.
 * 
 * General Calculator - This brings the user to another pane that contains a standard calculator
 * which contains all the basic functions of an everyday calculator (i.e. +,-,/,*).
 * 
 * It also implements the <CODE>EventHandler<CODE> interface to listen to action events triggered
 * by buttons throughout the program.
 * 
 * @see the <CODE>handle()<CODE> method for functionality of this implementation
 * 
 */
public class MainMenu implements EventHandler<ActionEvent>
{
	// GUI objects that are utilized by all panes in the program.
	protected static MainMenu main;
	protected static Label errLabel;
	protected static BorderPane root;
	
	// GUI objects for each pane of the program.
	private static GenCalcGUI calc;
	private static DescDataGUI descData;
	private static QuadFormGUI quadForm;
	private static LnOfBestFitGUI bestFit;
	
	// GUI elements only utilized to build the MainMenu user interface
	private static BorderPane mainPane;
	private static VBox buttonPane;
	private static VBox topPane;
	private static Button[] buttons;
	private static Label title;
	private static Scene scene;
	/**
	 * Builds the stage and window that all panes will be placed on.
	 * 
	 * @param stage
	 */
	public static void buildGUI(Stage stage)
	{	
		main = new MainMenu();
		calc = new GenCalcGUI();
		descData = new DescDataGUI();
		quadForm = new QuadFormGUI();
		bestFit = new LnOfBestFitGUI();
		
		main.start();
		root.setBottom(errLabel);
		root.setPadding(new Insets(10,10,10,10));
		scene = new Scene(root,500,500);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Default constructor of MainMenu
	 * 
	 * Builds the Main Menu GUI but does not add it to the stage
	 */
	public MainMenu() 
	{
		/* Initialize instance variables */
		root = new BorderPane();
		mainPane = new BorderPane();
		buttonPane = new VBox();
		topPane = new VBox();
		title = new Label("My Calculator");
		buttons = new Button[5];
		for (int count = 0; count < buttons.length; count++)
		{
			buttons[count] = new Button();
		}
		
		/* Add elements to mainPane */
		mainPane.setCenter(buttonPane);
		mainPane.setTop(topPane);
		buttonPane.setAlignment(Pos.CENTER);
		topPane.setAlignment(Pos.CENTER);
		
		/* Set the text for each button */
		buttons[0].setText("Describing Data");
		buttons[1].setText("Find the line of best fit");
		buttons[2].setText("Quadratic Formula");
		buttons[3].setText("General Calculator");
		buttons[4].setText("Quit");
		for (int count = 0; count < buttons.length; count++) 
		{
			buttons[count].setTextFill(Color.BLUE);
			buttons[count].setFont(new Font("Comic Sans MS", buttons[count].getFont().getSize()));
		}
		
		/* Set the location of each button on the buttonPane */
		for (int count = 0; count < buttons.length; count++) 
		{
			buttonPane.getChildren().add(buttons[count]);
		}
		
		/* Set event listeners to all buttons */
		buttons[0].setOnAction(new RebuildGUI());
		buttons[1].setOnAction(new RebuildGUI());
		buttons[2].setOnAction(new RebuildGUI());
		buttons[3].setOnAction(new RebuildGUI());
		buttons[4].setOnAction(new RebuildGUI());
		
		/* Add title to the topPane */
		topPane.getChildren().add(title);
		
		/* Set the attributes of the title */
		title.setFont(new Font("Impact", 30));
		title.setTextFill(Color.CRIMSON);
	}
	/**
	 * Adds the built GUI to the stage
	 */
	protected void start() 
	{
		/* Clear the pane */
		root.getChildren().clear();

		/* Add pane to GUI */
		errLabel = new Label();
		errLabel.setTextFill(Color.RED);
		root.setCenter(mainPane);
		root.setBottom(errLabel);
	}
	/**
	 * This method clears the stage adds the MainMenu user interface to it.
	 * 
	 * @param ActionEvent triggered by a button
	 */
	public void handle(ActionEvent event) 
	{
		start();	
	}
	// Private classes to handle the actions associated with each button
	private class RebuildGUI implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event) 
		{
			if (event.getSource().equals(buttons[0])) 
				descData.start();
			else if (event.getSource().equals(buttons[1])) 
				bestFit.start();
			else if (event.getSource().equals(buttons[2])) 
				quadForm.start();
			else if (event.getSource().equals(buttons[3])) 
				calc.start();
			else if (event.getSource().equals(buttons[4]))
				System.exit(0);
			else 
				throw new IllegalArgumentException();
		}
	}
}
