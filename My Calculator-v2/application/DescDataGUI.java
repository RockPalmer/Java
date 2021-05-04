package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import calcFunction.DescDataFn;

/**
 * This class inherits from the MainMenu class and implements its own user interface
 * to interact with the user.
 * 
 * It builds and places on the stage a BorderPane designated as mainPane that contains 3 separate areas
 * on which nodes are placed: optionsPane, dataArea, and answerArea. In this pane, the user can enter 
 * a data set of real numbers and find the key values of that data set (i.e. five number series, mean,
 * median, mode, etc.).
 * 
 * dataArea contains a series of TextFields that the user can enter the data into. The amount of TextFields
 * available for data entry can be adjusted by the user using the "Add Point" and "Remove Point" buttons at
 * the bottom of the pane. After which, the user can click the "Calculate" button to see the key values of
 * the data in the answerArea.
 * 
 * answerArea contains seven labeled TextFields into which are printed the key values of the data after the
 * user clicks the "Calculate" button.
 * 
 * optionsPane contains all the buttons previously mentioned as well as a button labeled "Return to Menu"
 * that returns the user to the Main Menu.
 *
 */
public class DescDataGUI extends MainMenu
{
	/* Each instance variable represents a major piece of the interface to be built */
	
	// All Pane objects on which the nodes of the interface are placed
	private static BorderPane mainPane;
	private static BorderPane dataArea;
	private static GridPane answerPane;
	private static VBox answerArea;
	private static FlowPane dataPane;
	private static GridPane optionsPane;
	private static ScrollPane dataScroller;
	
	// All TextField and TextField-containing objects
	private static ArrayList<Label> dataLabels;
	private static ArrayList<TextField> dataFields;
	private static ArrayList<VBox> columns;
	private static TextField[] answerFields;
	
	// All Label and Label-containing objects
	private static Label dataSet;
	private static Label keyValues;
	private static Label[] answerLabels;
	
	// All Button objects
	private static Button[] buttons;
	
	/**
	 * This is the default constructor for the class.
	 * 
	 * This builds the user interface but does not place it onto the stage.
	 */
	public DescDataGUI()
	{
		/* Initialize instance variables */
		mainPane = new BorderPane();
		answerPane = new GridPane();
		answerArea = new VBox();
		dataArea = new BorderPane();
		dataPane = new FlowPane();
		optionsPane = new GridPane();
		dataScroller = new ScrollPane();
		dataLabels = new ArrayList<Label>();
		dataFields = new ArrayList<TextField>();
		columns = new ArrayList<VBox>();
		dataSet = new Label("Data Set");
		keyValues = new Label("Key Values");
		answerLabels = new Label[7];
		answerFields = new TextField[7];
		buttons = new Button[4];
		
		/* Set button attributes */
		buttons[0] = new Button("Return to Menu");
		buttons[1] = new Button("Add Point");
		buttons[2] = new Button("Remove Point");
		buttons[3] = new Button("Calculate");
		for (int count = 0; count < buttons.length; count++) 
		{
			buttons[count].setTextFill(Color.BLUE);
			buttons[count].setFont(new Font("Comic Sans MS", buttons[count].getFont().getSize()));
		}
		
		/* Fill dataFields and dataLabels arrays with their corresponding objects */
		for (int count = 0; count < answerFields.length; count++)
		{
			answerFields[count] = new TextField();
		}
		for (int count = 0; count < answerLabels.length; count++) 
		{
			answerLabels[count] = new Label();
		}
		
		/* Add elements to mainPane */
		mainPane.setCenter(dataArea);
		mainPane.setRight(answerArea);
		mainPane.setBottom(optionsPane);
		
		/* Add elements to optionsPane */
		ColumnConstraints columnSide = new ColumnConstraints();
		ColumnConstraints columnMid = new ColumnConstraints();
		columnSide.setPercentWidth(33);
		columnMid.setPercentWidth(34);
		optionsPane.getColumnConstraints().addAll(columnSide,columnMid,columnSide);
		for (int count = 0; count < 3; count++) 
		{
			columns.add(new VBox());
			columns.get(count).setAlignment(Pos.CENTER);
			optionsPane.add(columns.get(count), count, 0);
		}
		columns.get(0).getChildren().add(buttons[0]);
		columns.get(1).getChildren().add(buttons[1]);
		columns.get(2).getChildren().add(buttons[3]);
		
		/* Add elements to answerArea */
		answerArea.setAlignment(Pos.TOP_CENTER);
		answerArea.getChildren().add(keyValues);
		answerArea.getChildren().add(answerPane);
		keyValues.setFont(new Font("Impact", 20));
		keyValues.setTextFill(Color.CRIMSON);
		answerPane.setVgap(5);
		answerPane.setHgap(5);
		answerLabels[0].setText("Mean");
		answerLabels[1].setText("Median");
		answerLabels[2].setText("Mode");
		answerLabels[3].setText("Range");
		answerLabels[4].setText("Q1");
		answerLabels[5].setText("Q3");
		answerLabels[6].setText("\u03C3");
		
		for (int count = 0; count < answerFields.length; count++) 
		{
			answerPane.add(answerFields[count], 1, count);
		}
		for (int count = 0; count < answerLabels.length; count++) 
		{
			answerPane.add(answerLabels[count], 0, count);
		}
		
		/* Add elements to dataPane */
		
		//Add all elements first to their respective arrays for storage
		for (int count = 0; count < 2; count++) 
		{
			TextField newField = new TextField();
			newField.setPrefWidth(120);
			dataFields.add(newField);
		}
		for (int count = 0; count < 1; count++)
		{
			Label newLabel = new Label(" , ");
			dataLabels.add(newLabel);
		}
		
		//Then, add all elements to the dataArea
		dataArea.setPadding(new Insets(10,10,10,10));
		dataArea.setTop(dataSet);
		BorderPane.setAlignment(dataSet, Pos.TOP_CENTER);
		dataArea.setCenter(dataScroller);
		dataSet.setFont(new Font("Impact", 20));
		dataSet.setTextFill(Color.CRIMSON);
		
		// Add elements to dataScroller
		dataScroller.setContent(dataPane);
		for (int count = 0; count < dataLabels.size(); count++) 
		{
			dataPane.getChildren().add(dataFields.get(count));
			dataPane.getChildren().add(dataLabels.get(count));
		}
		dataPane.getChildren().add(dataFields.get(dataFields.size() - 1));
		
		/* Add event handlers to buttons */
		buttons[0].setOnAction(main);
		buttons[1].setOnAction(new AddPoint());
		buttons[3].setOnAction(new Calculate());
		
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
		
		/* Add new pane */
		errLabel = new Label();
		errLabel.setTextFill(Color.RED);
		root.setCenter(mainPane);
		root.setBottom(errLabel);
	}
	private class AddPoint implements EventHandler<ActionEvent>
	{
		/**
		 * This method adds a new TextField for the user to enter a new data point
		 * onto the dataArea pane as well as a comma to separate it from the other
		 * TextFields.
		 * 
		 * If adding a new data point increased the data size to three, the method
		 * will also add a new button to optionsPane labeled "Remove Point" that will
		 * remove a TextField from dataArea
		 * 
		 * @see ButtonMovement.addButton() for the implementation of this button-adding 
		 * 		feature.
		 * 
		 * @param ActionEvent event that is received from the "Add Point" button
		 * 		being pushed.
		 */
		public void handle(ActionEvent event)
		{
			if (dataFields.size() <= 2) 
			{
				ButtonMovement.addButton(columns, buttons, optionsPane, 2, new RemovePoint());
			}
			Label newLabel = new Label(" , ");
			TextField newField = new TextField();
			newField.setPrefWidth(120);
			dataLabels.add(newLabel);
			dataFields.add(newField);
			dataPane.getChildren().add(dataLabels.get(dataLabels.size() - 1));
			dataPane.getChildren().add(dataFields.get(dataFields.size() - 1));
		}
	}
	private class RemovePoint implements EventHandler<ActionEvent>
	{	
		/**
		 * This method removes a TextField from the dataArea pane as well as the
		 * comma separating it from the other TextFields.
		 * 
		 * If removing that data point reduced the data size to 2, the method will also
		 * remove the "Remove Point" button from the optionsPane pane to prevent the user
		 * from removing more points.
		 * 
		 * @see ButtonMovement.removeButton() for the implementation of this button-removal
		 * 		feature.
		 * 
		 * @param ActionEvent event that is received from the "Add Point" button
		 * 		being pushed.
		 */
		public void handle(ActionEvent event) 
		{
			if (dataFields.size() <= 3) 
			{
				ButtonMovement.removeButton(columns, buttons, optionsPane, 2);
			}
			dataPane.getChildren().remove(dataFields.get(dataFields.size() - 1));
			dataPane.getChildren().remove(dataLabels.get(dataLabels.size() - 1));
			dataFields.remove(dataFields.size() - 1);
			dataLabels.remove(dataLabels.size() - 1);
		}
	}
	private class Calculate implements EventHandler<ActionEvent>
	{
		/**
		 * This method takes all data from the TextFields in the dataArea pane and calculates
		 * the key values of the data set. These are the mean, median, mode, range, first 
		 * quartile, third quartile, and standard deviation (denoted by the symbol "sigma").
		 * 
		 * Before doing this calculation, the method will first check to see if any TextFields
		 * in the dataArea pane are empty. If so, the method throws a generic Exception() which, 
		 * when caught, the program prints out the corresponding error message to the user in 
		 * the errLabel Label.
		 * 
		 * If none of the fields are empty, it will proceed with the calculation. If any of the
		 * values entered in the TextFields are not a real number, then the program will throw 
		 * a NumberFormatException which, when caught, will print out the corresponding error
		 * message to the user in the errLabel Label.
		 * 
		 * @see calcFunction.DescDataFn.describeData() for the implementation of this calculation.
		 * 
		 * @param ActionEvent event triggered when the user clicks the "Calculate" button.
		 */
		public void handle(ActionEvent event) 
		{
			try {
				/* Check if any data fields are empty */
				for (int count = 0; count < dataFields.size(); count++) 
				{
					if (dataFields.get(count).getText().equals("")) 
					{
						throw new Exception();
					}
				}
				
				/* If all data fields are filled, continue with regular button function */
				double[] data = new double[dataFields.size()];
				for (int count = 0; count < data.length; count++) 
				{
					data[count] = Double.parseDouble(dataFields.get(count).getText());
				}
				double[] answers = DescDataFn.describeData(data);
				for (int count = 0; count < answerFields.length; count++) 
				{
					answerFields[count].setText("" + answers[count]);
				}
			}
			catch(NumberFormatException e) 
			{
				errLabel.setText("Please fill all boxes with valid entries");
			} 
			catch (Exception e) 
			{
				errLabel.setText("Please fill all data fields");
			}
		}	
	}
}
