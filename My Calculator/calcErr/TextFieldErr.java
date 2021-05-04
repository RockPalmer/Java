package calcErr;


import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldErr
{
	private static final String[] EMPTY_BOX = {"You must fill in all input boxes", ""};
	private static final String[] INVALID_ENTRY = {"You must use numerical values", "(i.e. no letters, operational symbols, or special characters)"};
	public static boolean isValidInput(JTextField textBox, JLabel[] warning) 
	{
		boolean isValid = true;
		try 
		{
			Double.parseDouble(textBox.getText());
		}
		catch(NumberFormatException e)
		{
			isValid = false;
			if (textBox.getText().equals("")) 
			{
				warning[0].setText(EMPTY_BOX[0]);
				warning[1].setText(EMPTY_BOX[1]);
			}
			else 
			{
				warning[0].setText(INVALID_ENTRY[0]);
				warning[1].setText(INVALID_ENTRY[1]);
			}
			for (int count = 0; count < warning.length; count++) 
			{
				warning[count].setVisible(true);
				warning[count].setBounds(500 - (warning[count].getPreferredSize().width/2), 500 + (count * warning[count].getPreferredSize().height + 2), warning[count].getPreferredSize().width, warning[count].getPreferredSize().height);			
			}
		}
		
		/* If the text boxes have valid inputs, set the error warning to not visible else, make it visible */
		if (isValid) 
			for (int count = 0; count < warning.length; count++) 
				warning[count].setVisible(false);
		else 
			for (int count = 0; count < warning.length; count++) 
				warning[count].setVisible(true);
		return isValid;
	}
	public static boolean isValidInput(JTextField[] textBoxes, JLabel[] warning) 
	{
		boolean isValid = true;
		int count = 0;
		try 
		{
			for (count = 0; count < textBoxes.length; count++) 
				Double.parseDouble(textBoxes[count].getText());
		}
		catch(NumberFormatException e) 
		{
			isValid = false;
			if (textBoxes[count].getText().equals("")) 
			{
				warning[0].setText(EMPTY_BOX[0]);
				warning[1].setText(EMPTY_BOX[1]);
			}
			else 
			{
				warning[0].setText(INVALID_ENTRY[0]);
				warning[1].setText(INVALID_ENTRY[1]);
			}
			for (int count2 = 0; count2 < warning.length; count2++) 
			{
				warning[count2].setVisible(true);
				warning[count2].setBounds(500 - (warning[count2].getPreferredSize().width/2), 500 + (count2 * warning[count2].getPreferredSize().height + 2), warning[count2].getPreferredSize().width, warning[count2].getPreferredSize().height);
			}
		}
		
		/* If the text boxes have valid inputs, set the error warning to not visible else, make it visible */
		if (isValid) 
			for (int count1 = 0; count1 < warning.length; count1++) 
				warning[count1].setVisible(false);
		else 
			for (int count1 = 0; count1 < warning.length; count1++) 
				warning[count1].setVisible(true);
		return isValid;
	}
	public static boolean isValidInput(ArrayList<JTextField> textBoxes, JLabel[] warning) 
	{
		boolean isValid = true;
		int count = 0;
		try 
		{
			for (count = 0; count < textBoxes.size(); count++) 
				Double.parseDouble(textBoxes.get(count).getText());
		}
		catch(NumberFormatException e) 
		{
			isValid = false;
			if (textBoxes.get(count).getText().equals("")) 
			{
				warning[0].setText(EMPTY_BOX[0]);
				warning[1].setText(EMPTY_BOX[1]);
			}
			else 
			{
				warning[0].setText(INVALID_ENTRY[0]);
				warning[1].setText(INVALID_ENTRY[1]);
			}
			for (int count2 = 0; count2 < warning.length; count2++) 
			{
				warning[count2].setVisible(true);
				warning[count2].setBounds(500 - (warning[count2].getPreferredSize().width/2), 500 + (count2 * warning[count2].getPreferredSize().height + 2), warning[count2].getPreferredSize().width, warning[count2].getPreferredSize().height);
			}
		}
		
		/* If the text boxes have valid inputs, set the error warning to not visible else, make it visible */
		if (isValid) 
			for (int count1 = 0; count1 < warning.length; count1++) 
				warning[count1].setVisible(false);
		else 
			for (int count1 = 0; count1 < warning.length; count1++) 
				warning[count1].setVisible(true);
		return isValid;
	}
	public static boolean isFull(JTextField textBox, JLabel[] warning) 
	{
		boolean isValid = true;
		if (!textBox.getText().equals("")) 
		{
			isValid = true;
			for (int count = 0; count < warning.length; count++) 
				warning[count].setVisible(false);
		}
		else 
		{
			isValid = false;
			for (int count = 0; count < warning.length; count++) 
			{
				warning[count].setVisible(true);
				warning[count].setText(EMPTY_BOX[count]);
				warning[count].setBounds(500 - (warning[count].getPreferredSize().width/2), 500 + (count * warning[count].getPreferredSize().height + 2), warning[count].getPreferredSize().width, warning[count].getPreferredSize().height);
			}
		}
		
		return isValid;
	}
}
