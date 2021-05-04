package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// Assignment #7: Arizona State University CSE205
//          Name: Rock Palmer
//     StudentID: 1214784662
//       Lecture: T Th	1:30 PM - 2:45 PM
//   Description: CirclePane class creates a pane where we can use
//                mouse key to drag on canvas and there will be some color following
//                the mouse. We can also use combo boxes to change its colors and stroke widths
//                

//import any necessary classes here
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
//----
public class CirclePane extends BorderPane
{    
	//instance variables   
	//----    
	private ComboBox<String> primaryColorCombo;    
	private ComboBox<String> bgColorCombo;    
	private ComboBox<String> widthCombo;  
	private Color primaryColor;
	private Color secondaryColor;
	private Color bgColor;
	private int selectWidth;
	//These identify the circle that is being selected when the cursor is dragged
	private int circleX;
	private int circleY;
	//----
	private Circle[][] circleArray;    
	
	private final int NUM_COL =6, NUM_ROW = 6, RADIUS = 40;    
	private GridPane canvas;      //this is where circles are drawn 
	
	//constructor    
	public CirclePane()    
	{       
		//Step #1: Initialize instance variables and set up the layout
		primaryColorCombo = new ComboBox<String>();
		bgColorCombo = new ComboBox<String>();
		widthCombo = new ComboBox<String>();
		primaryColor = Color.BLACK;
		secondaryColor = Color.GRAY;
		bgColor = Color.WHITE;
		selectWidth = 1;
		circleX = 0;
		circleY = 0;
		//----  
		
		//Instantiate the two dimensional circleArray that contains        
		//6 columns and 6 rows of circles (36 in total)  
		circleArray = new Circle[NUM_ROW][NUM_COL];      
		//----  
		
		//instantiate canvas and set its width and height        
		canvas = new GridPane();        
		canvas.setPrefWidth(2*RADIUS * NUM_COL);        
		canvas.setPrefHeight(2*RADIUS * NUM_ROW); 
		
		//Use nested loop to instantiate the 6 columns by 6 rows of        
		//Circle objects, add them inside the circleArrary  
		for (int count1 = 0; count1 < NUM_ROW; count1++) 
		{
			for (int count2 = 0; count2 < NUM_COL; count2++) 
			{
				circleArray[count1][count2] = new Circle();
				circleArray[count1][count2].setFill(bgColor);
				circleArray[count1][count2].setStroke(Color.BLACK);
				circleArray[count1][count2].setRadius(RADIUS);
				circleArray[count1][count2].setCenterX(RADIUS*(count2*2 + 1) + selectWidth*(count2 + 1));
				circleArray[count1][count2].setCenterY(RADIUS*(count1*2 + 1) + selectWidth*(count1 + 1));
			}
		}
		//---- 
		
		//Set attributes of each combo box
		primaryColorCombo.getItems().addAll("Black","Red","Green","Blue");
		primaryColorCombo.setValue("Black");
		bgColorCombo.getItems().addAll("White","Beige","Aliceblue","Gold");
		bgColorCombo.setValue("White");
		widthCombo.getItems().addAll("1","3","5","7");
		widthCombo.setValue("1");
		//----
		
		//Add nodes and set up layout of left side of CirclePane       
		VBox leftPane = new VBox();        
		leftPane.setSpacing(20);        
		leftPane.setPadding(new Insets(10, 10, 10, 10));        
		leftPane.setStyle("-fx-border-color: black"); 
		leftPane.getChildren().add(new Label("PrimaryColor"));
		leftPane.getChildren().add(primaryColorCombo);
		leftPane.getChildren().add(new Label("BackgroundColor"));
		leftPane.getChildren().add(bgColorCombo);
		leftPane.getChildren().add(new Label("StrokeWidth"));
		leftPane.getChildren().add(widthCombo);
		//----  
		
		//Add leftPane and canvas to CirclePane
		this.setCenter(canvas);
		this.setLeft(leftPane);
		//---- 
		
		//Add circles to canvas
		for (int count1 = 0; count1 < circleArray.length; count1++)
		{
			for (int count2 = 0; count2 < circleArray[count1].length; count2++) 
			{
				canvas.add(circleArray[count1][count2], count1, count2);
				
			}
		}
		//----
		
		//Register the source nodes with its handler objects 
		primaryColorCombo.setOnAction(new PrimaryColorHandler());
		bgColorCombo.setOnAction(new BackgroundColorHandler());
		widthCombo.setOnAction(new WidthHandler());
		canvas.setOnMouseDragged(new MouseHandler());
		//----
		
	}    
	
	//Step #2(A) - MouseHandler   
	private class MouseHandler implements EventHandler<MouseEvent>    
	{        
		public void handle(MouseEvent event)       
		{            
			//Get the location of the cursor when dragged
			double indexX = event.getX();
			double indexY = event.getY();
			
			//Find the circle that is at that location (its location is donated by the coordinates circleX and circleY)
			for (int count1 = 0; count1 < circleArray.length; count1++) 	
			{
				for (int count2 = 0; count2 < circleArray.length; count2++) 
				{
					if (indexX <= circleArray[count1][count2].getCenterX() + RADIUS + selectWidth && indexX >= circleArray[count1][count2].getCenterX() - RADIUS - selectWidth && indexY <= circleArray[count1][count2].getCenterY() + RADIUS + selectWidth && indexY >= circleArray[count1][count2].getCenterY() - RADIUS - selectWidth) 
					{
						circleX = count1;
						circleY = count2;
					}
				}
			}
			
			// Set the color of all circles to the background color to reset the circle array colors
			for (int count1 = 0; count1 < circleArray.length; count1++) 	
			{
				for (int count2 = 0; count2 < circleArray.length; count2++) 
				{
					circleArray[count1][count2].setFill(bgColor);
				}
			}
			
			// Set the color of the selected circle to the primaryColor variable
			circleArray[circleY][circleX].setFill(primaryColor);
			
			// Set the color of the surrounding circles (if there are any) to the secondaryColor variable 
			if (circleX != 0) 
			{
				circleArray[circleY][circleX - 1].setFill(secondaryColor);
			}
			if (circleX != circleArray.length - 1) 
			{
				circleArray[circleY][circleX + 1].setFill(secondaryColor);
			}
			if (circleY != 0) 
			{
				circleArray[circleY - 1][circleX].setFill(secondaryColor);
			}
			if(circleY != circleArray.length - 1) 
			{
				circleArray[circleY + 1][circleX].setFill(secondaryColor);
			}       
		}//end of handle method    
	}//end MouseHandler
	
	//Step #2(B) - Primary and secondary color handler    
	private class PrimaryColorHandler implements EventHandler<ActionEvent>   
	{        
		public void handle(ActionEvent event)
		{
			// Set the primaryColor and secondaryColor variables to the values corresponding to the choice set in the combo box
			@SuppressWarnings("unchecked")
			String choice = ((ComboBox<String>)event.getSource()).getValue().toString();
			switch (choice) 
			{
			case "Black":
				primaryColor = Color.BLACK;
				secondaryColor = Color.GRAY;
				break;
			case "Red":
				primaryColor = Color.RED;
				secondaryColor = Color.PINK;
				break;
			case "Green":
				primaryColor = Color.GREEN;
				secondaryColor = Color.LIGHTGREEN;
				break;
			case "Blue":
				primaryColor = Color.BLUE;
				secondaryColor = Color.POWDERBLUE;
				break;
			}
			//----
		}    
	}//end PrimaryColorHandler    
	//Step #2(C): background color handler 
	private class BackgroundColorHandler implements EventHandler<ActionEvent>  
	{        
		public void handle(ActionEvent event)
		{
			// Set the bgColor variable to the value corresponding to the choice set in the combo box
			@SuppressWarnings("unchecked")
			String choice = ((ComboBox<String>)event.getSource()).getValue().toString();
			switch (choice) 
			{
			case "White":
				bgColor = Color.WHITE;
				break;
			case "Beige":
				bgColor = Color.BEIGE;
				break;
			case "Aliceblue":
				bgColor = Color.ALICEBLUE;
				break;
			case "Gold":
				bgColor = Color.GOLD;
				break;
			}
			//----
			
			// Reset all circles to bgColor
			for (int count1 = 0; count1 < circleArray.length; count1++) 
			{
				for (int count2 = 0; count2 < circleArray[count1].length; count2++) 
				{
					circleArray[count1][count2].setFill(bgColor);
				}
			}
		}    
	}
	//----    
	//----   
	
	//Step #2(D): handle the stroke width    
	private class WidthHandler implements EventHandler<ActionEvent>    
	{ 
		public void handle(ActionEvent event) 
		{
			// Set the selectWidth variable to the value corresponding to the choice set in the combo box
			@SuppressWarnings("unchecked")
			String choice = ((ComboBox<String>)event.getSource()).getValue().toString();
			switch (choice) 
			{
			case "1":
				selectWidth = 1;
				break;
			case "3":
				selectWidth = 3;
				break;
			case "5":
				selectWidth = 5;
				break;
			case "7":
				selectWidth = 7;
				break;
			}
			
			for (int count1 = 0; count1 < circleArray.length; count1++) 
			{
				for (int count2 = 0; count2 < circleArray[count1].length; count2++) 
				{
					// Set the new stroke width
					circleArray[count1][count2].setStrokeWidth(selectWidth);
					
					// When the stroke width is adjusted, the circles' locations
					// are changed slightly so the set centers of each circle must be 
					// adjusted accordingly
					circleArray[count1][count2].setCenterX(RADIUS*(count2*2 + 1) + selectWidth*(count2 + 1));
					circleArray[count1][count2].setCenterY(RADIUS*(count1*2 + 1) + selectWidth*(count1 + 1));
				}
			}
		}       
	}//end of WidthHandler
} //end of CirclePane