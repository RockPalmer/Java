package calcGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DescDataGUI extends DescDataAbstGUI implements ActionListener
{
	private static JButton[] buttons;
	protected static ArrayList<JTextField> textFields = new ArrayList<JTextField>();;
	protected static ArrayList<JLabel> labels = new ArrayList<JLabel>();;
	protected static JLabel[] answers;
	protected static JTextField[] answerBoxes;
	private static JLabel title;
	protected static void buildGUI()
	{
		/* Clear the panel */
		frame.remove(DescDataDiagramGUI.panel2);
		frame.repaint();
		frame.add(panel);
		panel.removeAll();
		panel.repaint();
		
		/* Clear the array Lists */
		textFields.removeAll(textFields);
		labels.removeAll(labels);
		
		/* Build GUI */
		setErrWarning();
		buildButtons();
		buildAnswers();
		buildDataEntries();
	}
	private static void buildButtons() 
	{
		setMenuButton();
		
		buttons = new JButton[4];
		
		/* Fill array with button objects */
		for (int count = 0; count < buttons.length; count++) 
			buttons[count] = new JButton();
		
		/* Set the text on each button */
		buttons[0].setText("Calculate");
		buttons[1].setText("Add Data Point");
		buttons[2].setText("Remove Data Point");
		buttons[3].setText("Show Box Plot");
		
		/* Set each button on the panel except for the 'Remove Point' 
		 * button */
		setOnPanel(buttons[0], 4, 500);
		setOnPanel(buttons[1], 2, 450);
		
		/* Set size and location of 'Remove Point' and 'Show Box PLot' button */
		setComponentsSize(buttons[2]);
		setComponentsSize(buttons[3]);
		buttons[2].setLocation(COLUMN_COORDS[1] - (buttons[2].getWidth()/2), 450);
		buttons[3].setLocation(750, 200 + buttons[3].getHeight());
		
		/* Add required ActionListeners to each one */
		buttons[0].removeActionListener(new Calculate());
		buttons[0].addActionListener(new Calculate());
		buttons[1].removeActionListener(new addPoint());
		buttons[1].addActionListener(new addPoint());
		buttons[2].removeActionListener(new removePoint());
		buttons[2].addActionListener(new removePoint());
		buttons[3].removeActionListener(new ShowBoxPlot());
		buttons[3].addActionListener(new ShowBoxPlot());
		
	}
	private static void buildDataFields() 
	{	
		/* Build 1 text field for the user to start with */
		JTextField textBox = new JTextField();
		
		/* Set Size and location */
		textBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
		textBox.setLocation(frame.getWidth()/8 - TEXT_FIELD_WIDTH/2, 60);
		textBox.setText("");
		
		/* Add it to the panel */
		panel.add(textBox);
		
		/* Add the text field to the text field array list for storage */
		textFields.add(textBox);
		
	}
	private static void buildAnswerFields() 
	{
		/* Set the size of the answerBoxes array */
		answerBoxes = new JTextField[7];
		
		/* Fill array with JTextField objects, set their location and size, and 
		 * add them to the panel */
		for (int count = 0; count < answerBoxes.length; count++) 
		{
			answerBoxes[count] = new JTextField();
			int xCoord = answers[count].getX() + answers[count].getWidth() + 2;
			int width = TEXT_FIELD_WIDTH - answers[count].getWidth();
			answerBoxes[count].setBounds(xCoord, 60 + count * (TEXT_FIELD_HEIGHT + 2), width, TEXT_FIELD_HEIGHT);
			panel.add(answerBoxes[count]);
		}
		
	}
	private static void buildSurroundingLabels() 
	{
		/* Build 1 new label to sit between the text fields */
		JLabel label = new JLabel(",");
		
		/* Set each label size based on the amount of text in each one */
		setComponentsSize(label);
		
		/* Set the labels between the text fields */
		int xCoord = textFields.get(0).getX() + textFields.get(0).getWidth() + 13 - (label.getWidth()/2);
		label.setLocation(xCoord, 60);
		
		/* Add each label to the JLabel array list */
		labels.add(label);
	}
	private static void buildAnswerLabels()
	{
		/* Initialize JLabel array */
		answers = new JLabel[7];
		
		/* fill answers with JLabel objects */
		for (int count = 0; count < answers.length; count++) 
			answers[count] = new JLabel();
		
		/* Set the text of each answer */
		answers[0].setText("Mean = ");
		answers[1].setText("Median = ");
		answers[2].setText("Mode = ");
		answers[3].setText("Range = ");
		answers[4].setText("Q1 = ");
		answers[5].setText("Q3 = ");
		answers[6].setText("\u03C3 = ");
		
		/* Set the size of each answer based on the amount of text contained 
		 * in it */
		setComponentsSize(answers);
		
		/* Set each answer on the panel */
		for (int count = 0; count < answers.length; count++) 
		{
			answers[count].setLocation(COLUMN_COORDS[4], 60 + count * (TEXT_FIELD_HEIGHT + 2));
			panel.add(answers[count]);
		}
	}
	private static void buildDataEntries() 
	{
		buildDataFields();
		buildSurroundingLabels();
		
		/* Build the title for the data and set it on the panel */
		title = new JLabel("Data set:");
		panel.add(title);
		
		/* Set size of title */
		setComponentsSize(title);
		
		/* Set the location of the title */
		title.setLocation(textFields.get(0).getX(), 30);
	}
	private static void buildAnswers() 
	{
		buildAnswerLabels();
		buildAnswerFields();
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		buildGUI();
	}
	public static JButton[] getButtons() 
	{
		JButton[] tempButtons = new JButton[2];
		tempButtons[0] = buttons[1];
		tempButtons[1] = buttons[2];
		return tempButtons;
	}
	protected static class addPoint implements ActionListener 
	{
		private static int xIndex;
		private static int yIndex;
		public void actionPerformed(ActionEvent arg0) 
		{
			addNewPoint();
		}
		protected static void addNewPoint() 
		{
			/* If the data set goes from 1 value to 2 values, the buttons will be adjusted */
			if (textFields.size() == 1) 
				addRemoveButton();
			else if (textFields.size() == 67) 
				removeAddButton();
			
			xIndex = textFields.size() % 4;
			yIndex = (textFields.size() - xIndex)/4;
			
			resetTextFields();
			resetSurroundingLabels();
		}
		private static void addRemoveButton() 
		{
			/* Move the 'Add Data Point' Button to make room for the 'Remove Data Point' button */
			getButtons()[0].setLocation(COLUMN_COORDS[1] - (getButtons()[0].getWidth()/2), getButtons()[0].getY());
			
			/* Add the 'Remove Data Point' button and set its location to the bottom right of the panel */
			panel.add(getButtons()[1]);
			getButtons()[1].setLocation(COLUMN_COORDS[3] - (getButtons()[1].getWidth()/2), getButtons()[0].getY());
			
			/* Update the panel */
			panel.repaint();
		}
		private static void removeAddButton() 
		{
			/* Remove 'Add Data Point' button */
			panel.remove(getButtons()[0]);
			
			/* Move 'Remove Data Point' button */
			getButtons()[1].setLocation(COLUMN_COORDS[2] - (getButtons()[1].getWidth()/2), getButtons()[0].getY());
			
			/* Update panel */
			panel.repaint();
		}
		private static void resetTextFields() 
		{
			/* Initialize new JTextField object */
			JTextField dataField = new JTextField();
			
			/* Add text field to panel */
			panel.add(dataField);
			
			/* Set dataField size to standard text field size */
			dataField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
			
			/* Set the text in the text field to an empty String */
			dataField.setText("");
			
			/* Set location of dataField */
			dataField.setLocation((xIndex + 1) * frame.getWidth()/8 - TEXT_FIELD_WIDTH/2, 60 + ((TEXT_FIELD_HEIGHT + 2) * yIndex));
			
			/* Add text field to text field array list */
			textFields.add(dataField);
		}
		private static void resetSurroundingLabels() 
		{
			if (textFields.size() == 2) 
				panel.add(labels.get(0));
			else
			{
				/* Initialize new JLabel object */
				JLabel label = new JLabel(",");
				
				/* Set the size of label */
				setComponentsSize(label);
				
				/* Set the location of the new label */
				label.setLocation(textFields.get(textFields.size() - 2).getX() + TEXT_FIELD_WIDTH + 13 - (label.getWidth()/2), textFields.get(textFields.size() - 2).getY());
				
				/* Add label to label array list */
				labels.add(label);
				
				/* Add label to panel */
				panel.add(label);
			}
		}
	}
	private static class removePoint implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			/* If the data set is only 1, the button will be adjusted */
			if (textFields.size() == 2) 
				removeRemoveButtons();
			else if (textFields.size() == 68) 
				addAddButton();
			resetTextFields();
			resetSurroundingLabels();
		}
		private static void removeRemoveButtons() 
		{
			/* Remove the 'Remove Data Point' button */
			panel.remove(getButtons()[1]);
			
			/* Move the 'Add Data Point' button to the center of the panel */
			getButtons()[0].setLocation(COLUMN_COORDS[2] - (getButtons()[0].getWidth()/2), getButtons()[0].getY());
			
			/* Update the panel */
			panel.repaint();
		}
		private static void addAddButton() 
		{
			/* Move 'Remove Data Point' to the right side of the panel */
			getButtons()[1].setLocation(COLUMN_COORDS[3] - (getButtons()[1].getWidth()/2), getButtons()[1].getY());
			
			/* Add 'Add Data Point' button to the left side of the panel */
			panel.add(getButtons()[0]);
			getButtons()[0].setLocation(COLUMN_COORDS[1] - (getButtons()[0].getWidth()/2), getButtons()[0].getY());
			
			/* Update panel */
			panel.repaint();
		}
		private static void resetTextFields() 
		{
			/* Remove the textField from the panel */
			panel.remove(textFields.get(textFields.size() - 1));
			
			/* Set the value of the removed text field as null */
			textFields.set(textFields.size() - 1, null);
			
			/* Remove the text field from the array list */
			textFields.remove(textFields.size() - 1);
			
			/* Update the panel */
			panel.repaint();
		}
		private static void resetSurroundingLabels() 
		{
			
			/* Remove the label from the panel */
			panel.remove(labels.get(labels.size() - 1));
			
			if (textFields.size() != 1) 
			{
				/* Set the value of the removed text field as null */
				labels.set(labels.size() - 1, null);
				
				/* Remove the label from the array list */
				labels.remove(labels.size() - 1);
			}
			
			/* Update the panel */
			panel.repaint();
		}
	}
	private static class Calculate implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			/* If none of the text fields designated for input are empty, the program
			 * proceeds with the standard button operation */
			if (calcErr.TextFieldErr.isValidInput(textFields, errWarning)) 
			{
				/* update the dataSet variable */
				dataSet = new double[textFields.size()];
				for (int count = 0; count < dataSet.length; count++) 
					dataSet[count] = Double.parseDouble(textFields.get(count).getText());
				
				/* calculate the output numbers to describe the data and store them
				 * resultVals variable */
				resultVals = calcFn.DescDataFn.describeData(dataSet);
				
				/* Fill the answer boxes with their corresponding values */
				for (int count = 0; count < resultVals.length; count++) 
					answerBoxes[count].setText("" + resultVals[count]);
				
				/* Add the 'Show BoxPlot' button to the panel */
				panel.add(buttons[3]);
				panel.repaint();
				
				/* build panel2 based on the above data to be added if the user clicks 'Show Box Plot' */
				setPanel2();
			}
		}
	}
	private static class ShowBoxPlot implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			GoToDiagram();
		}
	}
}
