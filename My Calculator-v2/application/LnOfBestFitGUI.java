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
import javafx.scene.layout.GridPane;
import calcFunction.LnOfBestFitFn;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class inherits from the MainMenu class and implements its own user interface
 * to interact with the user.
 * 
 * It builds and places on the "root" pane a BorderPane designated as mainPane that contains 
 * two major panes upon which nodes are placed: dataArea and optionsPane. With this program,
 * the user can enter a set of (x,y) coordinate points and get the equation for the linear
 * regression line, or line of best fit for the data.
 * 
 * dataArea is a ScrollPane that contains the users (x,y) coordinate points. Each point is made
 * up of 2 TextFields for each coordinate. The user can adjust the number of points in dataArea
 * using the optionsPane Pane.
 * 
 * optionsPane is a GridPane that contains 4 buttons labeled "Add Point", "Remove Point", "Calculate",
 * and "Return to Menu". "Add Point" adds 2 new TextFields for a new data point to the data set, 
 * "Remove Point" removes 2 TextFields from the data set, "Calculate" calculates the linear regression
 * equation for the data and displays it to the user, and "Return to Menu" returns the user to the
 * Main Menu.
 * 
 * Then, when the user wishes to return back the the main page of the program, he/she can 
 * click the "Return to Menu" button and will be returned to the main menu.
 *
 */
public class LnOfBestFitGUI extends MainMenu
{
	// All major pieces of the user interface
	private BorderPane mainPane;
	private ScrollPane dataArea;
	private GridPane dataPane;
	private VBox centerPane;
	private VBox titlePane;
	private GridPane answerPane;
	private GridPane optionsPane;
	private Label title;
	private ArrayList<TextField[]> dataPoints;
	private ArrayList<Label[]> dataLabels;
	private ArrayList<VBox> columns;
	private TextField answer;
	private Button[] buttons;
	
	/**
	 * Default constructor for the LnOfBestFitGUI class
	 * 
	 * Builds the user interface but does not add it to the "root" pane
	 */
	public LnOfBestFitGUI() 
	{
		/* Initialize instance variables */
		mainPane = new BorderPane();
		dataArea = new ScrollPane();
		dataPane = new GridPane();
		centerPane = new VBox();
		titlePane = new VBox();
		answerPane = new GridPane();
		optionsPane = new GridPane();
		title = new Label("Line of Best Fit");
		dataPoints = new ArrayList<TextField[]>();
		dataLabels = new ArrayList<Label[]>();
		columns = new ArrayList<VBox>();
		answer = new TextField();
		buttons = new Button[4];
		
		/* Fill buttons array */
		buttons[0] = new Button("Return to Menu");
		buttons[1] = new Button("Add Point");
		buttons[2] = new Button("Remove Point");
		buttons[3] = new Button("Calculate");
		for (int count = 0; count < buttons.length; count++) 
		{
			buttons[count].setFont(new Font("Comic Sans MS", buttons[count].getFont().getSize()));
			buttons[count].setTextFill(Color.BLUE);
		}
		
		/* Add elements to mainPane */
		mainPane.setCenter(centerPane);
		mainPane.setBottom(optionsPane);
		mainPane.setTop(titlePane);
		
		/* Add elements to titlePane */
		title.setFont(new Font("Impact", 30));
		title.setTextFill(Color.CRIMSON);
		titlePane.setAlignment(Pos.CENTER);
		titlePane.getChildren().add(title);
		
		/* Add elements to optionsPane */
		ColumnConstraints colSide = new ColumnConstraints();
		ColumnConstraints colMid = new ColumnConstraints();
		colMid.setPercentWidth(34);
		colSide.setPercentWidth(33);
		optionsPane.getColumnConstraints().addAll(colSide,colMid,colSide);
		for (int count = 0; count < 3; count++) 
		{
			columns.add(new VBox());
			columns.get(count).setAlignment(Pos.CENTER);
			optionsPane.add(columns.get(count), count, 0);
		}
		columns.get(0).getChildren().add(buttons[0]);
		columns.get(1).getChildren().add(buttons[1]);
		columns.get(2).getChildren().add(buttons[3]);
		
		/* Add elements to centerPane */
		centerPane.setAlignment(Pos.TOP_CENTER);
		centerPane.getChildren().add(answerPane);
		centerPane.getChildren().add(dataArea);
		
		/* Add elements to answerPane */
		ColumnConstraints col = new ColumnConstraints();
		answerPane.getColumnConstraints().addAll(col,col);
		answerPane.setPadding(new Insets(0,0,0,90));
		answerPane.add(new Label("linear regression equation: "), 0, 0);
		answerPane.add(answer, 1, 0);
		answer.setText("y = mx + b");
		
		/* Add elements to dataArea */
		dataArea.setContent(dataPane);
		ColumnConstraints column = new ColumnConstraints();
		dataPane.setPadding(new Insets(0,0,0,80));
		dataPane.getColumnConstraints().addAll(column,column,column,column,column);
		addAPoint();
		addAPoint();
		
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
		
		/* Add new elements to pane */
		errLabel = new Label();
		errLabel.setTextFill(Color.RED);
		root.setCenter(mainPane);
		root.setBottom(errLabel);
	}
	
	/* This method adds a new set of TextFields to the data set for the user to enter a new data point */
	private void addAPoint() 
	{
		RowConstraints row = new RowConstraints();
		dataPane.getRowConstraints().add(row);
		dataPoints.add(new TextField[2]);
		dataLabels.add(new Label[3]);
		int index = dataPoints.size() - 1;
		for (int count = 0; count < 2; count++) 
		{
			dataPoints.get(index)[count] = new TextField();
			dataPane.add(dataPoints.get(index)[count], count * 2 + 1, index);
		}
		index = dataLabels.size() - 1;
		for (int count = 0; count < 3; count++) 
		{
			dataLabels.get(index)[count] = new Label();
			switch (count) 
			{
			case 0:
				dataLabels.get(index)[count].setText("(");
				dataPane.add(dataLabels.get(index)[count], 0, index);
				break;
			case 1:
				dataLabels.get(index)[count].setText(",");
				dataPane.add(dataLabels.get(index)[count], 2, index);
				break;
			case 2:
				dataLabels.get(index)[count].setText(")");
				dataPane.add(dataLabels.get(index)[count], 4, index);
				break;
			}
		}
	}
	
	/* This method removes the last data point added to the data set from the data set and from the pane */
	private void removeAPoint() 
	{
		int index = dataPoints.size() - 1;
		for (int count = 0; count < dataPoints.get(index).length; count++) 
		{
			dataPane.getChildren().remove(dataPoints.get(index)[count]);
		}
		for (int count = 0; count < dataLabels.get(index).length; count++) 
		{
			dataPane.getChildren().remove(dataLabels.get(index)[count]);
		}
		dataPane.getRowConstraints().remove(dataPane.getRowConstraints().size() - 1);
		dataPoints.remove(index);
		dataLabels.remove(index);
	}
	private class AddPoint implements EventHandler<ActionEvent>
	{
		/**
		 * This method adds a new labeled set of TextFields for the user to enter a new (x,y)
		 * coordinate point to the data set.
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
			if (dataPoints.size() == 2) 
			{
				ButtonMovement.addButton(columns, buttons, optionsPane, 2, new RemovePoint());
			}
			addAPoint();
		}	
	}
	private class RemovePoint implements EventHandler<ActionEvent>
	{
		/**
		 * This method removes a the last data point added to the data set from the data
		 * set and from the pane.
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
			if (dataPoints.size() == 3) 
			{
				ButtonMovement.removeButton(columns, buttons, optionsPane, 2);
			}
			removeAPoint();
		}
	}
	private class Calculate implements EventHandler<ActionEvent>
	{
		/**
		 * This method takes all data from the TextFields in the dataArea pane and calculates
		 * the equation for the linear regression line of the data in the form y = mx + b.
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
		 * @see calcFunction.LnOfBestFitFn.findBestFitLine() for the implementation of this calculation.
		 * 
		 * @param ActionEvent event triggered when the user clicks the "Calculate" button.
		 */
		public void handle(ActionEvent event) 
		{
			try 
			{
				/* Check if any data fields are empty */
				for (int count = 0; count < dataPoints.size(); count++) 
				{
					if (dataPoints.get(count)[0].getText().equals("") || dataPoints.get(count)[1].getText().equals("")) 
					{
						throw new Exception();
					}
				}
				
				/* If all data fields are filled, continue with regular button function */
				double[] xVals = new double[dataPoints.size()];
				double[] yVals = new double[dataPoints.size()];
				for (int count = 0; count < dataPoints.size(); count++) 
				{
					xVals[count] = Double.parseDouble(dataPoints.get(count)[0].getText());
					yVals[count] = Double.parseDouble(dataPoints.get(count)[1].getText());
				}
				answer.setText(LnOfBestFitFn.findBestFitLine(xVals, yVals));
			}
			catch(NumberFormatException e) 
			{
				errLabel.setText("Please fill all boxes with valid entries");
			}
			catch(Exception e) 
			{
				errLabel.setText("Please fill all data fields");
			}
		}
	}
}
