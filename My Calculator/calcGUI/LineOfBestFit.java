package calcGUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LineOfBestFit extends BaseGUI implements ActionListener
{
	private static JButton[] buttons;
	protected static ArrayList<JTextField[]> textFields = new ArrayList<JTextField[]>();
	protected static ArrayList<JLabel[]> labels = new ArrayList<JLabel[]>();
	private static JLabel[] header;
	protected static JLabel result;
	protected static final int TEXT_ARRAY_SIZE = 2;
	private static final int LABEL_ARRAY_SIZE = 3;
	private static void buildGUI()
	{
		/* Clear the panel */
		panel.removeAll();
		panel.repaint();
		
		setErrWarning();
		buildButtons();
		buildTextFields();
		buildLabels();
	}
	private static void buildLabels() 
	{	
		buildHeader();
		buildResult();
		buildSurroundingLabels();
	}
	private static void buildHeader() 
	{
		/* Initialize header array */
		header = new JLabel[5];
		
		/* Fill header array with JLabels with text */
		header[0] = new JLabel("(");
		header[1] = new JLabel("x");
		header[2] = new JLabel(",");
		header[3] = new JLabel("y");
		header[4] = new JLabel(")");
		
		/* Add the header objects to the panel */
		addComponentsToPanel(header);
		
		/* Set the size of the header objects */
		setComponentsSize(header);
		
		/* Set the location each piece of the header to line up with the 
		 * text fields below it */
		for (int count = 0; count < COLUMN_COORDS.length; count++) 
			header[count].setLocation(COLUMN_COORDS[count] - (header[count].getWidth()/2), 90);
	}
	private static void buildResult() 
	{
		/* Initialize result with text */
		result = new JLabel("Equation: y = _x + _");
		
		/* Set the size and location of the result */
		Dimension dim = result.getPreferredSize();
		result.setBounds(COLUMN_COORDS[2] - (dim.width/2), 30, dim.width, dim.height);
		
		/* Set the result into the panel */
		panel.add(result);
	}
	private static void buildSurroundingLabels() 
	{
		/* Clear current array list content */
		labels.removeAll(labels);
		
		/* Create 2 sets of 3 labels to go with the first 
		 * 4 text fields that we just created */
		for (int count = 0; count < 2; count++) 
		{	
			/* Create new JLabel array with size pf 3 */
			JLabel[] labelArr = new JLabel[LABEL_ARRAY_SIZE];
			
			/* Set the text of the 3 labels with '(' , ',' and ')' respectively 
			 * to show the user which box to put their x and y coordinates in */
			labelArr[0] = new JLabel("(");
			labelArr[1] = new JLabel(",");
			labelArr[2] = new JLabel(")");
			
			/* Set the size of each label to fit the text contained in it */
			setComponentsSize(labelArr);
			
			/* Add each label to the panel */
			addComponentsToPanel(labelArr);
			
			/* Set the location of each label between the 2 text fields that have 
			 * already been created */
			labelArr[0].setLocation(COLUMN_COORDS[0] - (labelArr[0].getWidth()/2), 120 + (count * (TEXT_FIELD_HEIGHT + 2)));
			labelArr[1].setLocation(COLUMN_COORDS[2] - (labelArr[1].getWidth()/2), 120 + (count * (TEXT_FIELD_HEIGHT + 2)));
			labelArr[2].setLocation(COLUMN_COORDS[4] - (labelArr[2].getWidth()/2), 120 + (count * (TEXT_FIELD_HEIGHT + 2)));
			
			/* Add the JLabel array to the JLabel[] arrayList for storage and ease 
			 * of access */
			labels.add(labelArr);
		}
	}
	private static void buildButtons() 
	{
		setMenuButton();
		
		/* Initialize button array */
		buttons = new JButton[3];
		
		/* Fill buttons array and add each one to the panel */
		for (int count = 0; count < buttons.length; count++) 
			buttons[count] = new JButton();
		panel.add(buttons[0]);
		panel.add(buttons[1]);
		
		/* Set the text of each button */
		buttons[0].setText("Add Point");
		buttons[1].setText("Calculate");
		buttons[2].setText("Remove Point");
		
		/* Add action listener to 'Add Point' to add space for another set of coordinates */
		buttons[0].addActionListener(new AddPoint());
		
		/* Add action listener to 'Remove Point' to remove one set of coordinates */
		buttons[1].addActionListener(new Calculate());
		
		/* Add action listener to 'Calculate' button to calculate
		 * the line of best fit */
		buttons[2].addActionListener(new RemovePoint());
		
		/* Set the size of each button based on the text in it */
		setComponentsSize(buttons);
		
		/* Set the location of each button */
		buttons[0].setLocation(COLUMN_COORDS[2] - (buttons[0].getWidth()/2), 172);
		buttons[1].setLocation(COLUMN_COORDS[4] - (buttons[1].getWidth()/2), 500);
	}
	private static void buildTextFields() 
	{
		/* Clear current array list content */
		textFields.removeAll(textFields);
		
		/* Add 2 sets of 2 text fields to start with where the user can put 
		 * their first x and y coordinates */
		
		for (int count1 = 0; count1 < 2; count1++) 
		{
			/* Create new text field array with length of 2 */
			JTextField[] texts = new JTextField[TEXT_ARRAY_SIZE];
			for (int count2 = 0; count2 < texts.length; count2++) 
			{
				/* Fill array with text field objects */
				texts[count2] = new JTextField();
				
				/* Set the size of each text field to be the same */
				texts[count2].setSize(100, TEXT_FIELD_HEIGHT);
				
				/* Set the text in each text field to an empty String */
				texts[count2].setText("");
			}
			/* Add each object to the panel */
			addComponentsToPanel(texts);
			
			/* Set the locations of the text fields into 2 columns 
			 * right above the 'Add Point' button */
			texts[0].setLocation(COLUMN_COORDS[1] - (texts[0].getWidth()/2), 120 + (count1 * (TEXT_FIELD_HEIGHT + 2)));
			texts[1].setLocation(COLUMN_COORDS[3] - (texts[1].getWidth()/2), 120 + (count1 * (TEXT_FIELD_HEIGHT + 2)));
			
			/* Add the text fields to the JTextField array list for 
			 * easy access and storage */
			textFields.add(texts);
		}
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		buildGUI();
	}
	public static JButton[] getButtons() 
	{
		JButton[] tempButtons = new JButton[2];
		tempButtons[0] = buttons[0];
		tempButtons[1] = buttons[2];
		return tempButtons;
	}
	private static class AddPoint implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			resetButtons();
			resetTextFields();
			resetSurroundingLabels();
		}	
		private static void resetTextFields() 
		{
			/* Create another set of 2 text fields where the user can enter values */
			JTextField[] textBoxes = new JTextField[TEXT_ARRAY_SIZE];
			
			/* Fill the array with text field objects, add them to the panel, and set 
			 * their bounds */
			for (int count = 0; count < textBoxes.length; count++) 
			{
				/* Initialize the new text field */
				JTextField textBox = new JTextField();
				
				/* Set the text of the text field to an empty String */
				textBox.setText("");
				
				/* Put the new text field into the text field array */
				textBoxes[count] = textBox;
				
				/* Add the text field to the panel */
				panel.add(textBox);
				
				/* Set the size of the text field to the standard text field size */
				textBox.setSize(TEXT_FIELD_WIDTH,TEXT_FIELD_HEIGHT);
			}
			textBoxes[0].setLocation(COLUMN_COORDS[1] - TEXT_FIELD_WIDTH/2, 120 + textFields.size() * (TEXT_FIELD_HEIGHT + 2));
			textBoxes[1].setLocation(COLUMN_COORDS[3] - TEXT_FIELD_WIDTH/2, 120 + textFields.size() * (TEXT_FIELD_HEIGHT + 2));
			textFields.add(textBoxes);
		}
		private static void resetButtons() 
		{
			JButton[] buttons = getButtons();
			
			/* If there are currently only 2 sets of text fields, it will 
			 * add the 'Remove Point' button to the screen and move the 'Add Point 
			 * button to it's new location */
			if (textFields.size() == 2) 
			{
				panel.add(buttons[1]);
				buttons[0].setLocation(buttons[0].getX() - 60, buttons[0].getY());
				buttons[1].setLocation(COLUMN_COORDS[2] - (buttons[1].getWidth()/2) + 60, 172);
			}
			
			/* If the set of data values reaches the max size of the page, the 'Add Point'
			 * button will be removed */
			if (textFields.size() == 16) 
			{
				panel.remove(buttons[0]);
				buttons[1].setLocation(COLUMN_COORDS[2] - (buttons[1].getWidth()/2), buttons[1].getY());
			}
			
			/* Move both buttons down */
			buttons[0].setLocation(buttons[0].getX(), buttons[0].getY() + TEXT_FIELD_HEIGHT + 2);
			buttons[1].setLocation(buttons[1].getX(), buttons[1].getY() + TEXT_FIELD_HEIGHT + 2);
			
			/* Update the panel */
			panel.repaint();
		}
		private static void resetSurroundingLabels() 
		{
			/* Create another set of 3 labels that will surround the new text fields */
			JLabel[] surrLabels = new JLabel[3];
			
			/* Fill the array with label objects and them to the panel */
			for (int count = 0; count < surrLabels.length; count++) 
			{
				JLabel surrLabel = new JLabel();
				surrLabels[count] = surrLabel;
				panel.add(surrLabel);
			}
			
			/* Set the text contained in each label object */
			surrLabels[0].setText("(");
			surrLabels[1].setText(",");
			surrLabels[2].setText(")");
			
			/* Set the size of each label to fit the text contained in it */
			setComponentsSize(surrLabels);
			
			/* Set the coordinates of the new labels to surround the new text fields */
			surrLabels[0].setLocation(COLUMN_COORDS[0] - (surrLabels[0].getWidth()/2), 120 + labels.size() * (TEXT_FIELD_HEIGHT + 2));
			surrLabels[1].setLocation(COLUMN_COORDS[2] - (surrLabels[1].getWidth()/2), 120 + labels.size() * (TEXT_FIELD_HEIGHT + 2));
			surrLabels[2].setLocation(COLUMN_COORDS[4] - (surrLabels[2].getWidth()/2), 120 + labels.size() * (TEXT_FIELD_HEIGHT + 2));
			
			/* Add the new JLabel array to the labels arrayList */
			labels.add(surrLabels);
		}
	}
	private static class RemovePoint implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			resetButtons();
			resetTextFields();
			resetSurroundingLabels();
		}
		private static void resetTextFields() 
		{
			/* Remove both text fields from the panel */
			panel.remove(textFields.get(textFields.size() - 1)[0]);
			panel.remove(textFields.get(textFields.size() - 1)[1]);
			
			/* Set array in array list to null */
			textFields.set(textFields.size() - 1, null);
			
			/* Remove both text fields from the array list */
			textFields.remove(textFields.size() - 1);	
			
			panel.repaint();
		}
		private static void resetButtons() 
		{
			JButton[] buttons = getButtons();
			
			/* If there are currently only 3 data values, the 'Remove Point' button
			 * will be removed from the panel and the 'Add Point' buttons will be
			 * move to the center of the panel */
			if (textFields.size() == 3) 
			{
				panel.remove(buttons[1]);
				buttons[0].setLocation(COLUMN_COORDS[2] - (buttons[0].getWidth()/2), buttons[0].getY());
			}
			
			if (textFields.size() == 17) 
			{
				panel.add(buttons[0]);
				buttons[0].setLocation(COLUMN_COORDS[2] - (buttons[1].getWidth()/2) - 60, buttons[0].getY());
				buttons[1].setLocation(COLUMN_COORDS[2] - (buttons[1].getWidth()/2) + 60, buttons[1].getY());
			}
			
			/* Move both buttons up */
			for (int count = 0; count < buttons.length; count++) 
				buttons[count].setLocation(buttons[count].getX(), buttons[count].getY() - TEXT_FIELD_HEIGHT - 2);
			
			/* Update the panel */
			panel.repaint();
		}
		private static void resetSurroundingLabels() 
		{
			/* Remove the surrounding labels from the panel */
			for (int count = 0; count < 3; count++) 
				panel.remove(labels.get(labels.size() - 1)[count]);
			
			/* Set array in array list to null */
			labels.set(labels.size() - 1, null);
			
			/* Remove the surrounding labels from the array list */
			labels.remove(labels.size() - 1);	
			
			panel.repaint();
		}
	}
	private static class Calculate implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			/* Check for errors in the text fields */
			boolean isValid = true;
			for (int count = 0; count < textFields.size() && isValid; count++) 
				if (!calcErr.TextFieldErr.isValidInput(textFields.get(count), errWarning)) 
					isValid = false;
			
			/* Proceed with button function if the data fields are valid */
			if (isValid) 
			{	
				double[] xVals = new double[textFields.size()];
				double[] yVals = new double[textFields.size()];
				for (int count = 0; count < xVals.length; count++) 
				{
					xVals[count] = Double.parseDouble(textFields.get(count)[0].getText());
					yVals[count] = Double.parseDouble(textFields.get(count)[1].getText());
				}
				String equation = calcFn.LnOfBestFitFn.findBestFitLine(xVals,yVals);
				result.setText("Equation = " + equation);
				setComponentsSize(result);
			}
		}
	}
}
